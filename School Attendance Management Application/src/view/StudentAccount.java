package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.StudentProcess;
import model.Student;

public class StudentAccount implements UserAccount{
	
	Connection connect = null;
	PreparedStatement prepared_statement = null;
	ResultSet result_set = null;

	public StudentAccount() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_db", "root", "@Jjack007");
			System.out.println("AdminPage connected");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	@Override
	public void Login(String email_id, String password) {
		try
		{
			prepared_statement=connect.prepareStatement("SELECT * FROM Student WHERE email_id=? AND Student_password=?");
			prepared_statement.setString(1, email_id);
			prepared_statement.setString(2, password);
			result_set=prepared_statement.executeQuery();
			if(result_set.next())
			{
				Student student_detail=new Student();
				student_detail.setStudent_id(result_set.getNString(1));
				student_detail.setStaff_incharge_id(result_set.getNString(2));
				student_detail.setName(result_set.getNString(3));
				student_detail.setEmail_id(result_set.getNString(4));
				student_detail.setPassword(result_set.getNString(5));
				student_detail.setMobile_number(result_set.getNString(6));
				student_detail.setStandard(result_set.getNString(7));
				student_detail.setIs_live_data(result_set.getBoolean(8));
				student_detail.setGuardian_name(result_set.getNString(9));
				student_detail.setEmergency_contact(result_set.getNString(10));
				welcomePage(student_detail);
			}
			else
			{
				System.out.println("Incorrect Email id or password");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void welcomePage(Student student_detail)
	{
		Scanner student_input=new Scanner(System.in);
		System.out.println("**************************Welcome Student************************");
		boolean student_lets_start=true;
		do {
			System.out.println("Choose any service to explore.....\n1.	My details \n2.	My attendance details \n3.	Apply Leave \n4.	Notification \n5.	Back");
			int student_start_choice=student_input.nextInt();
			
			switch(student_start_choice)
			{
			case 1:
			{
				StudentProcess.getInstance().view_my_details(student_detail.getStudent_id());
			}
			break;
			
			case 2:
			{
				StudentProcess.getInstance().view_my_attendance_details(student_detail.getStudent_id());
			}
			break;
			
			case 3:
			{
				student_input.nextLine();
				System.out.println("Student Id :");
				String student_id=student_input.nextLine();
				System.out.println("From date(\"yyyy-mm-dd\") :");
				String from_date=student_input.nextLine();
				System.out.println("To date (\\\"yyyy-mm-dd\\\"):");
				String to_date=student_input.nextLine();
				System.out.println("Leave reason :");
				String reason=student_input.nextLine();
				StudentProcess.getInstance().apply_leave(student_id, from_date, to_date, reason,student_detail);
			}
			break;
			
			case 4:
			{
				student_input.nextLine();
				System.out.println("Student Id :");
				String student_id=student_input.nextLine();
				StudentProcess.getInstance().view_notification(student_id);
			}
			break;
			
			case 5:
			{
				student_lets_start=false;
			}
			break;
			
			default:
			{
				System.out.println("We are not providing this service...Kindly try any of our mentioned service");
				System.out.println("****************************************************************************");
			}
			break;
			}
		}while(student_lets_start);
	}

}
