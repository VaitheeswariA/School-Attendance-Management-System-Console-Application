package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Notification;
import model.Student;

public class StudentProcess {
	Connection connect=null;
	PreparedStatement prepared_statement=null;
	ResultSet result_set=null;
	public static int rows_affected=0;
	private static StudentProcess student_process=null;
	
	private StudentProcess()
	{
		try {
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/School_db", "root", "@Jjack007");
			System.out.println("Student process connected");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static StudentProcess getInstance()
	{
		if(student_process==null)
			student_process=new StudentProcess();
		return student_process;
	}

	//View my details
	public void view_my_details(String student_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM Student WHERE student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			if(result_set.getRow()>=0)
			{
				System.out.println("**************************************Student Detail*****************************************");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%-10s %-10s %-15s %-20s %-10s %-5s %-13s %-20s %-10s","Student Id","Staff_Id","Student Name","Email id","Mobile number","Grade","Record_status","Guardian","Emergency Contact");
				System.out.println();
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				while(result_set.next())
				{
					System.out.printf("%-8s %-8s %-15s %-20s %-10s %-5s %-13s %-20s %-10s",result_set.getNString(1),result_set.getNString(2),result_set.getNString(3),result_set.getNString(4),result_set.getNString(6),result_set.getNString(7),result_set.getBoolean(8),result_set.getNString(9),result_set.getNString(10));
					System.out.println();
				}
			}
			else
			{
				System.out.println("No records found..........");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	
	}
	
	
	public void view_my_attendance_details(String student_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			if(result_set.getRow()>=0)
			{
			System.out.println("**************************************Student Detail*****************************************");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-15s %-10s %-5s %-10s %-8s %-8s %-10s %-20s","Student Id","Attendance Date","Staff Id","Grade","attendance","Holiday","Absent","Approval","Instruction");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			while(result_set.next())
			{
				System.out.printf("%-10s %-15s %-10s %-5s %-10s %-8s %-8s %-10s %-20s",result_set.getNString(1),result_set.getDate(2),result_set.getNString(3),result_set.getNString(4),result_set.getNString(5),(result_set.getBoolean(6)? "Yes":"No"),(result_set.getBoolean(7)? "Yes":"No"),(result_set.getBoolean(8)? "Yes":"No"),result_set.getNString(9));
				System.out.println();
			}
			}
			else
			{
				System.out.println("No records found .....");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	
	}
	//apply leave
	public void apply_leave(String student_id,String from_date,String to_date,String reason,Student student_detail)
	{
		try {
			PreparedStatement prepared_statement1=connect.prepareStatement("INSERT INTO Notification_Id VALUES()");
			rows_affected=prepared_statement1.executeUpdate();

			
			PreparedStatement prepared_statement2=connect.prepareStatement("SELECT CONCAT(\"L\",id) FROM Notification_Id ORDER BY id DESC LIMIT 1");
			ResultSet result_set1=prepared_statement2.executeQuery();
			String notification_id=null;
			
			if(result_set1.next())
			{
				notification_id=result_set1.getString(1);
			}
			prepared_statement=connect.prepareStatement("INSERT INTO Leave_Notification VALUES(?,?,?,?,?,?,?,?,?)");
			prepared_statement.setString(1,notification_id);
			prepared_statement.setString(2, "Leave request");
			prepared_statement.setString(3, student_id);
			prepared_statement.setString(4, from_date);
			prepared_statement.setString(5, to_date);
			prepared_statement.setString(6, student_detail.getStaff_incharge_id());
			prepared_statement.setString(7,reason);
			prepared_statement.setString(8,"requested");
			prepared_statement.setBoolean(9,true);
			rows_affected=prepared_statement.executeUpdate();
			if(rows_affected>0)System.out.println("Leave request is captured!..........");
			rows_affected=0;
			
			prepared_statement=connect.prepareStatement("UPDATE Staff SET Notification_Id=? WHERE staff_id=?");
			prepared_statement.setString(1, notification_id);
			prepared_statement.setString(2, student_detail.getStaff_incharge_id());
			rows_affected=prepared_statement.executeUpdate();
			if(rows_affected>0)System.out.println("Leave request sent to approver....");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//view notification like approvals or instruction
	
	public void view_notification(String student_id)
	{
		try {			prepared_statement=connect.prepareStatement("SELECT notification_id,requested_student_id,Approver_staff_id,Request_Status FROM Leave_Notification WHERE requested_student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			if(result_set.getRow()>=0)
			{
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("%-5s %-10s %-10s %-20s","Notification Id","Student Id","Approver Id","Request status");
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------");
			while(result_set.next())
			{
				System.out.printf("%-5s %-10s %-10s %-20s",result_set.getString(1),result_set.getString(2),result_set.getString(3),result_set.getString(4));
				System.out.println();
			}
			System.out.println("------------------------------------------------------------------------------------");
			}
			else
			{
				System.out.println("No records found .....");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
