package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.Student;

public class StaffProcess {
	
	Connection connect=null;
	PreparedStatement prepared_statement=null;
	ResultSet result_set=null;
	ResultSet result_set1=null;
	Scanner staff_input=new Scanner(System.in);
	private static StaffProcess staff_process=null;
	
	public static int rows_affected=0;
	
	public StaffProcess()
	{
		try {
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/School_db", "root", "@Jjack007");
			System.out.println("Staff process connected");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static StaffProcess getInstance()
	{
		if(staff_process==null)
			staff_process=new StaffProcess();
		return staff_process;
	}
	//add new student
	public void add_new_student(String name,String staff_incharge,String email,String mobile,String standard,boolean live_Status,String guardian,String emergency_contact)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT Standard_Incharge FROM Staff WHERE Staff_id=?");
			prepared_statement.setString(1, staff_incharge);
			result_set=prepared_statement.executeQuery();
			boolean can_add=false;
			while(result_set.next())
			{
				if(result_set.getString(1).equals(standard))
					can_add=true;
			}
			if(can_add)
			{
				PreparedStatement prepared_statement1=connect.prepareStatement("INSERT INTO Student_Id VALUES()");
				rows_affected=prepared_statement1.executeUpdate();
				prepared_statement.close();
				
				PreparedStatement prepared_statement2=connect.prepareStatement("SELECT CONCAT(\"ST20220\",id) FROM Student_Id ORDER BY id DESC LIMIT 1");
				ResultSet result_set1=prepared_statement2.executeQuery();
				String password=null;
				String student_id=null;
				if(result_set1.next())
				{
					password=result_set1.getString(1).substring(0, 2)+name.substring(0,4)+result_set1.getString(1).substring(6, result_set1.getString(1).length());
					student_id=result_set1.getString(1);
				}
	
				
				
				PreparedStatement prepared_statement3=connect.prepareStatement("INSERT INTO Student VALUES(?,?,?,?,?,?,?,?,?,?)");
				prepared_statement3.setString(1, student_id);
				prepared_statement3.setString(2, staff_incharge);
				prepared_statement3.setString(3, name);
				prepared_statement3.setString(4, email);
				prepared_statement3.setString(5, password);
				prepared_statement3.setString(6, mobile);
				prepared_statement3.setString(7, standard);
				prepared_statement3.setBoolean(8, live_Status);
				prepared_statement3.setString(9, guardian);
				prepared_statement3.setString(10, emergency_contact);
				rows_affected=prepared_statement3.executeUpdate();
				if(rows_affected>0)System.out.println("Student record inserted successfully");
				System.out.println(rows_affected+"rows affected");
			}
			else
			{
				System.out.println("You are only allowed to add student to your grade section...");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return;
	}
	
	
	
	public void update_student(String student_id,String name,String staff_incharge,String email,String mobile,String standard,boolean live_Status,String guardian,String emergency_contact)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT Standard_Incharge FROM Staff WHERE Staff_id=?");
			prepared_statement.setString(1, staff_incharge);
			result_set=prepared_statement.executeQuery();
			boolean can_add=false;
			while(result_set.next())
			{
				if(result_set.getString(1).equals(standard))
					can_add=true;
			}
			if(can_add)
			{
				prepared_statement=connect.prepareStatement("UPDATE Student SET Staff_Incharge_Id=?,Student_name=?,Email_id=?,Mobile_number=?,Standard=?,is_live_data=?,Guardian_name=?,Emergency_Contact=? WHERE Student_Id=?");
				
				prepared_statement.setString(1, staff_incharge);
				prepared_statement.setString(2, name);
				prepared_statement.setString(3, email);
				prepared_statement.setString(4, mobile);
				prepared_statement.setString(5, standard);
				prepared_statement.setBoolean(6, live_Status);
				prepared_statement.setString(7, guardian);
				prepared_statement.setString(8, emergency_contact);
				prepared_statement.setString(9, student_id);
				rows_affected=prepared_statement.executeUpdate();
				if(rows_affected>0)System.out.println("Student record inserted successfully");
				System.out.println(rows_affected+"rows affected");
			}
			else
			{
				System.out.println("You are only allowed to update student to your grade section...");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return;
	}
	
	public void delete_student_detail(String student_id,String staff_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT Staff_Incharge_Id FROM Student WHERE Student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			boolean can_delete=false;
			while(result_set.next())
			{
				if(result_set.getString(1).equals(staff_id))
					can_delete=true;
			}
			if(can_delete)
			{
				PreparedStatement prepared_statement1=connect.prepareStatement("SELECT * FROM Student WHERE student_id=?");
				prepared_statement1.setString(1, student_id);
				ResultSet result_set1=prepared_statement1.executeQuery();
				
				if(result_set1.next())
				{
					PreparedStatement prepared_statement3=connect.prepareStatement("INSERT INTO Deleted_Student VALUES(?,?,?,?,?,?,?,?,?,?)");
					prepared_statement3.setString(1, result_set1.getString(1));
					prepared_statement3.setString(2, result_set1.getString(2));
					prepared_statement3.setString(3, result_set1.getString(3));
					prepared_statement3.setString(4, result_set1.getString(4));
					prepared_statement3.setString(5, result_set1.getString(5));
					prepared_statement3.setString(6, result_set1.getString(6));
					prepared_statement3.setString(7, result_set1.getString(7));
					prepared_statement3.setBoolean(8, result_set1.getBoolean(8));
					prepared_statement3.setString(9, result_set1.getString(9));
					prepared_statement3.setString(10, result_set1.getString(10));
					rows_affected=prepared_statement3.executeUpdate();
					if(rows_affected>0)System.out.println("Student record inserted to backup table successfully");
					System.out.println(rows_affected+"rows affected");
				}
				
				
				PreparedStatement prepared_statement=connect.prepareStatement("DELETE FROM Student WHERE Student_id=?");
				prepared_statement.setString(1, student_id);
				rows_affected=prepared_statement.executeUpdate();
				System.out.println("Student record deleted successfully!...");
				System.out.println(rows_affected+"rows affected");
				
				PreparedStatement prepared_statement4=connect.prepareStatement("DELETE FROM Student_Id WHERE id=?");
				prepared_statement4.setInt(1, Integer.parseInt(student_id.substring(6)));
				rows_affected=prepared_statement4.executeUpdate();
				System.out.println(rows_affected+"row(s) affected id table");
				prepared_statement4.close();
			}
			else
			{
				System.out.println("\nYou are only allowed to delete your grade students..........");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	//view student detail
	public void view_student_detail(String student_id,String staff_id)
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
	
	//view all student list
	public void view_student_detail(String staff_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM Student WHERE Staff_Incharge_Id=?");
			prepared_statement.setString(1, staff_id);
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
				
			}catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	
	}
	
	//Add student attendance record
	public void add_student_attendance(String student_id,String attendance_date,String grade,String staff_id,String attendance_record,boolean is_holiday,boolean is_absent,boolean has_approval,String instruction)
	{
		try {
			boolean can_add=false;
			boolean is_correct_date=false;
			prepared_statement=connect.prepareStatement("SELECT Staff_Incharge_Id FROM Student WHERE Student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			while(result_set.next())
			{
				System.out.println(result_set.getString(1));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
				LocalDate localdate = LocalDate.parse(attendance_date, formatter);
				//System.out.println(date);
				
				System.out.println(localdate);
				if(result_set.getString(1).equals(staff_id))
					can_add=true;
					if(LocalDateTime.now().toLocalDate().equals(localdate) )
							{
							is_correct_date=true;
							}
			}
			if(is_correct_date)
			{
				if(can_add)
				{
					prepared_statement=connect.prepareStatement("INSERT INTO StudentAttendance VALUES(?,?,?,?,?,?,?,?,?)");
					prepared_statement.setString(1, student_id);
					prepared_statement.setString(2, LocalDateTime.now().toLocalDate().toString());
					prepared_statement.setString(3,staff_id);
					prepared_statement.setString(4, grade);
					prepared_statement.setString(5, attendance_record);
					prepared_statement.setBoolean(6, is_holiday);
					prepared_statement.setBoolean(7, is_absent);
					prepared_statement.setBoolean(8, has_approval);
					prepared_statement.setString(9, instruction);
					rows_affected=prepared_statement.executeUpdate();
					System.out.println("Student attendance added successfully!....");
					System.out.println(rows_affected+"rows affected");
				}
				else
				{
					System.out.println("\nYou are only allowed to add attendance for your grade students....");
				}
			}
			else
			{
				System.out.println("\n You are not allowed to add attendance for future dates.....");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		rows_affected=0;
	}
	
	//update student attendance
	public void update_student_attendance(String student_id,String attendance_date,String grade,String staff_id,String attendance_record,boolean is_holiday,boolean is_absent,boolean has_approval,String instruction)
	{
		try {
			boolean can_add=false;
			boolean is_correct_date=false;
			prepared_statement=connect.prepareStatement("SELECT Staff_Incharge_Id FROM Student WHERE Student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			while(result_set.next())
			{
				System.out.println(result_set.getString(1));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
				LocalDate localdate = LocalDate.parse(attendance_date, formatter);
				if(result_set.getString(1).equals(staff_id))
					can_add=true;
					if(LocalDateTime.now().toLocalDate().equals(localdate) )
							{
							is_correct_date=true;
							}
				
			}
			if(is_correct_date)
			{
				if(can_add)
				{
					prepared_statement=connect.prepareStatement("UPDATE StudentAttendance SET Attendance_date=?,Staff_Incharge_Id=?,Student_Standard=?,Attendance_Record=?,is_holiday=?,is_Absent=?,has_approval=?,Instruction=? WHERE Student_id=?");
					prepared_statement.setString(1, LocalDateTime.now().toLocalDate().toString());
					prepared_statement.setString(2,staff_id);
					prepared_statement.setString(3, grade);
					prepared_statement.setString(4, attendance_record);
					prepared_statement.setBoolean(5, is_holiday);
					prepared_statement.setBoolean(6, is_absent);
					prepared_statement.setBoolean(7, has_approval);
					prepared_statement.setString(8, instruction);
					prepared_statement.setString(9, student_id);
					rows_affected=prepared_statement.executeUpdate();
					System.out.println("Student attendance added successfully!....");
					System.out.println(rows_affected+"rows affected");
				}
				else
				{
					System.out.println("\nYou are only allowed to add attendance for your grade students....");
				}
			}
			else
			{
				System.out.println("\n You can't update attendance for future dates");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		rows_affected=0;
	}
	
	//delete student attendance
	public void delete_student_attendance(String student_id,String staff_id,String att_date)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT Staff_Incharge_Id FROM Student WHERE Student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			boolean can_delete=false;
			while(result_set.next())
			{
				if(result_set.getString(1).equals(staff_id))
					can_delete=true;
			}
			if(can_delete)
			{
				PreparedStatement prepared_statement1=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE Student_id=? AND Attendance_date=?");
				prepared_statement1.setString(1, student_id);
				prepared_statement1.setString(2, att_date);
				ResultSet result_set1=prepared_statement1.executeQuery();
				
				if(result_set1.next())
				{
					rows_affected=0;
					PreparedStatement prepared_statement2=connect.prepareStatement("INSERT INTO Deleted_StudentAttendance VALUES(?,?,?,?,?,?,?,?,?)");
					prepared_statement2.setString(1, result_set1.getString(1));
					prepared_statement2.setString(2, result_set1.getString(2));
					prepared_statement2.setString(3,result_set1.getString(3));
					prepared_statement2.setString(4, result_set1.getString(4));
					prepared_statement2.setString(5, result_set1.getString(5));
					prepared_statement2.setBoolean(6, result_set1.getBoolean(6));
					prepared_statement2.setBoolean(7, result_set1.getBoolean(7));
					prepared_statement2.setBoolean(8, result_set1.getBoolean(8));
					prepared_statement2.setString(9,  result_set1.getString(9));
					rows_affected=prepared_statement2.executeUpdate();
					System.out.println("Student attendance added to backup table successfully!....");
					System.out.println(rows_affected+"rows affected");
				}
				rows_affected=0;
				prepared_statement=connect.prepareStatement("DELETE FROM StudentAttendance WHERE Student_id=? AND Attendance_date=?");
				prepared_statement.setString(1, student_id);
				prepared_statement.setString(2, att_date);
				rows_affected=prepared_statement.executeUpdate();
				System.out.println("Student record deleted successfully!...");
				System.out.println(rows_affected+"rows affected");
			}
			else
			{
				System.out.println("\nYou are only allowed to delete your grade students attendance details..........");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//view student attendance
	public void view_student_attendance(String student_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE student_id=?");
			prepared_statement.setString(1, student_id);
			result_set=prepared_statement.executeQuery();
			if(result_set.getFetchSize()>0)
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
				System.out.println("No records found..........");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	
		
	}
	
	//view students attendance details
	public void my_grade_student_attendance(String staff_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE Staff_Incharge_Id=?");
			prepared_statement.setString(1, staff_id);
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
				System.out.println("No records found..........");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	
		
	}

	public void weekly_report(String student_id,String staff_id,String from_Date)
	{
		try {
			LocalDate to_date=LocalDate.parse(from_Date).plusDays(7);
			prepared_statement=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE Student_id=? AND Attendance_date BETWEEN ? AND ?");
			prepared_statement.setString(1, student_id);
			prepared_statement.setString(2, from_Date.toString());
			prepared_statement.setString(3, to_date.toString());
			result_set=prepared_statement.executeQuery();
			if(result_set.next())
			{
				System.out.println("**************************************Student Detail*****************************************");
				System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
				System.out.printf("|%-10s |%-15s |%-10s |%-5s |%-10s |%-8s |%-8s |%-10s |%-20s |","Student Id","Attendance Date","Staff Id","Grade","attendance","Holiday","Absent","Approval","Instruction");
				System.out.println();
				System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
			}
			while(result_set.next())
			{
				System.out.printf("|%-10s |%-15s |%-10s |%-5s |%-10s |%-8s |%-8s |%-10s |%-20s |",result_set.getNString(1),result_set.getDate(2),result_set.getNString(3),result_set.getNString(4),result_set.getNString(5),(result_set.getBoolean(6)? "Yes":"No"),(result_set.getBoolean(7)? "Yes":"No"),(result_set.getBoolean(8)? "Yes":"No"),result_set.getNString(9));
				System.out.println();
				System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void monthly_report(String student_id,String staff_id,String from_Date)
	{
		try {
			LocalDate to_date=LocalDate.parse(from_Date).plusDays(30);
			prepared_statement=connect.prepareStatement("SELECT * FROM StudentAttendance WHERE Student_id=? AND Attendance_date BETWEEN ? AND ?");
			prepared_statement.setString(1, student_id);
			prepared_statement.setString(2, from_Date.toString());
			prepared_statement.setString(3, to_date.toString());
			result_set=prepared_statement.executeQuery();
			if(result_set.next())
			{
				System.out.println("**************************************Student Detail*****************************************");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%-10s %-15s %-10s %-5s %-10s %-8s %-8s %-10s %-20s","Student Id","Attendance Date","Staff Id","Grade","attendance","Holiday","Absent","Approval","Instruction");
				System.out.println();
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			}
			while(result_set.next())
			{
				System.out.printf("%-10s %-15s %-10s %-5s %-10s %-8s %-8s %-10s %-20s",result_set.getNString(1),result_set.getDate(2),result_set.getNString(3),result_set.getNString(4),result_set.getNString(5),(result_set.getBoolean(6)? "Yes":"No"),(result_set.getBoolean(7)? "Yes":"No"),(result_set.getBoolean(8)? "Yes":"No"),result_set.getNString(9));
				System.out.println();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//view notification
	public void view_notification(String staff_id)
	{
		try {
			prepared_statement=connect.prepareStatement("SELECT Notification_id From Staff WHERE staff_id=?");
			prepared_statement.setString(1, staff_id);
			ResultSet result_set1=prepared_statement.executeQuery();
			String notification_id="";
			if(result_set1.next())
			{
				notification_id=result_set1.getString(1);
		
			prepared_statement=connect.prepareStatement("SELECT requested_student_id,request_status,is_live_request From Leave_Notification WHERE Notification_id=?");
			prepared_statement.setString(1,notification_id );
			result_set=prepared_statement.executeQuery();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("%-10s %-10s %-20s","Student Id","Request status","is live status");
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------");
			while(result_set.next())
			{
				System.out.printf("%-10s %-10s %-20s",result_set.getString(1),result_set.getString(2),result_set.getBoolean(3));
				System.out.println();
				System.out.println("------------------------------------------------------------------------------------");
				String approval="";
				System.out.print("Do you want to approve?(yes or no):");
				String input=staff_input.nextLine();
				if(input.equals("yes"))approval="Approved";
				else approval="Pending";
				if(result_set.getBoolean(3))
				{
					prepared_statement=connect.prepareStatement("UPDATE Leave_Notification SET request_status=?,is_live_request=? WHERE Notification_id=?");
					prepared_statement.setString(1, approval);
					prepared_statement.setBoolean(2, false);
					prepared_statement.setString(3, notification_id);
					rows_affected=prepared_statement.executeUpdate();
					if(rows_affected>0)System.out.println("Request approval completed...");
				}
				
				prepared_statement=connect.prepareStatement("UPDATE Staff SET Notification_id=? WHERE staff_id=?");
				prepared_statement.setString(1, null);
				prepared_statement.setString(2, staff_id);
				rows_affected=prepared_statement.executeUpdate();
				if(rows_affected>0)System.out.println("Notification details updated successfully...");
				
			}
			}
			else
			{
				System.out.println("There is no notification for you!.......");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
