package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;
import model.Notification;
import model.Staff;
import model.Student;
import view.UserAccount;

public class AdminProcess{
	
	Connection connect=null;
	PreparedStatement prepared_statement=null;
	ResultSet result_set=null;
	public static int rows_affected=0;
	private static AdminProcess admin_process=null;
	
	private AdminProcess()
	{
		
		try {
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/School_db", "root", "@Jjack007");
			System.out.println("AdminPage connected");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static AdminProcess getInstance()
	{
		if(admin_process==null)
			admin_process=new AdminProcess();
		return admin_process;
	}
	
	public void add_new_staff(String name,String email,String mobile,String staff_class,String notification_detail)
	{
		try {
			PreparedStatement prepared_statement=connect.prepareStatement("INSERT INTO Staff_Id VALUES()");
			rows_affected=prepared_statement.executeUpdate();
			prepared_statement.close();
			
			String password=null;
			String staff_id=null;
			PreparedStatement prepared_statement1=connect.prepareStatement("SELECT CONCAT(\"EF20220\",id) FROM Staff_Id ORDER BY id DESC LIMIT 1");
			ResultSet result_set1=prepared_statement1.executeQuery();
			if(result_set1.next())
			{
				password=result_set1.getString(1).substring(0, 2)+name.substring(0,4)+result_set1.getString(1).substring(6, result_set1.getString(1).length());
				staff_id=result_set1.getString(1);
			}
			prepared_statement1.close();
			
			
			PreparedStatement prepared_statement2=connect.prepareStatement("INSERT INTO Staff VALUES(?,?,?,?,?,?,?)");
			prepared_statement2.setString(1, staff_id);
			prepared_statement2.setString(2, name);
			prepared_statement2.setString(3, email);
			prepared_statement2.setString(4, password);
			prepared_statement2.setString(5, mobile);
			prepared_statement2.setString(6, staff_class);
			prepared_statement2.setString(7, notification_detail);
			rows_affected=prepared_statement2.executeUpdate();
			System.out.println(rows_affected+"record(s) added successfully");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//update mobile number 
	
	public void update_mobile_number(String staff_id,String mobile_number)
	{
		try {
			PreparedStatement prepared_statement1=connect.prepareStatement("UPDATE Staff SET mobile_number=? WHERE Staff_id=?");
			prepared_statement1.setString(1, mobile_number);
			prepared_statement1.setString(2, staff_id);
			rows_affected=prepared_statement1.executeUpdate();
			
			System.out.println("Mobile number updated successfully!...");
			System.out.println(rows_affected+"row(s) affected");
			
			prepared_statement1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//update email
	public void update_email(String staff_id,String email)
	{
		try {
			PreparedStatement prepared_statement=connect.prepareStatement("UPDATE Staff SET Email_id=? WHERE Staff_id=?");
			prepared_statement.setString(1, email);
			prepared_statement.setString(2, staff_id);
			rows_affected=prepared_statement.executeUpdate();
			
			System.out.println("Email Id updated successfully!...");
			System.out.println(rows_affected+"row(s) affected");
			
			prepared_statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//update grade 
	
	public void update_grade(String staff_id,String grade)
	{
		try {
			PreparedStatement prepared_statement=connect.prepareStatement("UPDATE Staff SET Standard_incharge=? WHERE Staff_id=?");
			prepared_statement.setString(1, grade);
			prepared_statement.setString(2, staff_id);
			rows_affected=prepared_statement.executeUpdate();
			
			
			System.out.println("Staff incharge grade details updated successfully!...");
			System.out.println(rows_affected+"row(s) affected");
			
			prepared_statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//delete staff
	public void delete_staff(String staff_id)
	{
		try {
			PreparedStatement prepared_statement1=connect.prepareStatement("SELECT * FROM Staff WHERE Staff_id=?");
			prepared_statement1.setString(1, staff_id);
			ResultSet result_set1=prepared_statement1.executeQuery();
			
			PreparedStatement prepared_statement2;
			while(result_set1.next())
			{
				prepared_statement2=connect.prepareStatement("INSERT INTO Deleted_Staff VALUES(?,?,?,?,?,?,?)");
				prepared_statement2.setString(1, result_set1.getString(1));
				prepared_statement2.setString(2, result_set1.getString(2));
				prepared_statement2.setString(3, result_set1.getString(3));
				prepared_statement2.setString(4, result_set1.getString(4));
				prepared_statement2.setString(5, result_set1.getString(5));
				prepared_statement2.setString(6, result_set1.getString(6));
				prepared_statement2.setString(7, result_set1.getString(7));
				rows_affected=prepared_statement2.executeUpdate();
				if(rows_affected>0) {
					System.out.println(rows_affected+" no of Staff detail(s) updated in backup table");
				}
			}
			result_set1.close();
			
			PreparedStatement prepared_statement3=connect.prepareStatement("DELETE FROM Staff WHERE Staff_id=?");
			prepared_statement3.setString(1, staff_id);
			rows_affected=prepared_statement3.executeUpdate();
			System.out.println("Staff detail deleted successfully!...");
			System.out.println(rows_affected+"row(s) affected");
			prepared_statement3.close();
			
			PreparedStatement prepared_statement4=connect.prepareStatement("DELETE FROM Staff_Id WHERE id=?");
			prepared_statement4.setInt(1, Integer.parseInt(staff_id.substring(staff_id.length()-2)));
			rows_affected=prepared_statement4.executeUpdate();
			System.out.println(rows_affected+"row(s) affected id table");
			prepared_statement4.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//student detail

	public void view_student_detail()
	{
		
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM Student");// WHERE student_id=?
			//prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			
			System.out.println("**************************************Student Detail*****************************************");
			System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
			System.out.printf("|%-10s | %-10s | %-15s | %-20s | %-10s | %-5s | %-13s | %-10s | %-10s|","Student Id","Staff_Id","Student Name","Email id","Mobile number","Grade","Record_status","Guardian","Emergency Contact");
			System.out.println();
			System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
			while(result_set.next())
			{
				System.out.printf("|%-8s | %-8s | %-15s | %-20s | %-10s | %-5s | %-13s | %-10s | %-10s|",result_set.getNString(1),result_set.getNString(2),result_set.getNString(3),result_set.getNString(4),result_set.getNString(6),result_set.getNString(7),result_set.getBoolean(8),result_set.getNString(9),result_set.getNString(10));
				System.out.println();
				System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	}
	
	//view each student details
	public void view_student_detail(String student_id)
	{
		
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM Student WHERE student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			
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
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	}
	
	//student attendance detail
	public void view_student_attendance_detail(String student_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			
			System.out.println("**************************************Student Detail*****************************************");
			System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
			System.out.printf("|%-10s |%-15s |%-10s |%-5s |%-10s |%-8s |%-8s |%-10s |%-20s|","Student Id","Attendance Date","Staff Id","Grade","attendance","Holiday","Absent","Approval","Instruction");
			System.out.println();
			System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
			while(result_set.next())
			{
				System.out.printf("|%-10s |%-15s |%-10s |%-5s |%-10s |%-8s |%-8s |%-10s |%-20s|",result_set.getNString(1),result_set.getDate(2),result_set.getNString(3),result_set.getNString(4),result_set.getNString(5),(result_set.getBoolean(6)? "Yes":"No"),(result_set.getBoolean(7)? "Yes":"No"),(result_set.getBoolean(8)? "Yes":"No"),result_set.getNString(9));
				System.out.println();
				System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	
		
	}

	
}
