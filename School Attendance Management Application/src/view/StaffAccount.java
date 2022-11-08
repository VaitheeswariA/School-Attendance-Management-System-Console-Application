package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.StaffProcess;
import model.Admin;
import model.Staff;

public class StaffAccount implements UserAccount{
	
	Connection connect = null;
	PreparedStatement prepared_statement = null;
	ResultSet result_set = null;

	public StaffAccount() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_db", "root", "@Jjack007");
			System.out.println("AdminPage connected");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	
	@Override
	public void Login(String email_id, String password) {
		
		try {
			prepared_statement =connect.prepareStatement("SELECT staff_id,name,email_id,password,mobile_number,Standard_incharge,notification_id FROM Staff WHERE email_id=? AND password=?");
			prepared_statement.setString(1,email_id);
			prepared_statement.setString(2, password);
			result_set=prepared_statement.executeQuery();
			if(result_set.next())
			{
				Staff staff_detail=new Staff();
				staff_detail.setStaff_id(result_set.getNString(1));
				staff_detail.setName(result_set.getNString(2));
				staff_detail.setEmail_id(result_set.getNString(3));
				staff_detail.setPassword(result_set.getNString(4));
				staff_detail.setMobile_number(result_set.getNString(5));
				staff_detail.setStandard_incharge(result_set.getNString(6));
				staff_detail.setNotification_id(result_set.getNString(7));
				welcomePage(staff_detail);
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

	
	public void welcomePage(Staff staff_detail) {
		Scanner staff_input=new Scanner(System.in);
		boolean staff_lets_start=true;
		do {
			System.out.println("**********************************Welcome Staff****************************************");
			System.out.println("Choose any service......\n1.	Add new Student  \n2.	Update Student detail \n3.	Delete Student \n4.	View Student detail  \n5.	View Student list \n6.	Add students Attendance  \n7.	Update Student Attendance \n8.	Delete Student Attendance \n9.	View Student Attendance \n10.	My students attendance \n11.	Weekly Attendance Report \n12.	Monthly Attendance Report \n13.	Notification \n14.	Back");
			int admin_start_choice=staff_input.nextInt();
			
			switch(admin_start_choice)
			{
			case 1:
			{
				System.out.println("*******************Provide details**************************");
				staff_input.nextLine();
				System.out.print("Student name :");
				String stu_name=staff_input.nextLine();
				System.out.print("Email id :");
				String stu_email=staff_input.next();
				System.out.print("Staff incharge :");
				String staff_id=staff_input.next();
				System.out.print("Mobile number :");
				String stu_mobile=staff_input.next();
				System.out.print("Standard :");
				String stu_class=staff_input.next();
				System.out.print("Record live status:");
				boolean stu_record_status=staff_input.nextBoolean();
				staff_input.nextLine();
				System.out.print("Guardian Name :");
				String stu_guardian=staff_input.nextLine();
				System.out.print("Emergency contact :");
				String stu_emergency_contact=staff_input.nextLine();
				StaffProcess.getInstance().add_new_student(stu_name, staff_id, stu_email, stu_mobile, stu_class, stu_record_status, stu_guardian, stu_emergency_contact);
				System.out.println("**************************************************************");
			}
			break;
			
			case 2:
			{
				System.out.println("*******************Provide details**************************");
				staff_input.nextLine();
				System.out.print("Student Id :");
				String student_id=staff_input.nextLine();
				System.out.print("Student name :");
				String stu_name=staff_input.nextLine();
				System.out.print("Email id :");
				String stu_email=staff_input.next();
				System.out.print("Staff incharge :");
				String staff_id=staff_input.next();
				System.out.print("Mobile number :");
				String stu_mobile=staff_input.next();
				System.out.print("Standard :");
				String stu_class=staff_input.next();
				System.out.print("Record live status:");
				boolean stu_record_status=staff_input.nextBoolean();
				staff_input.nextLine();
				System.out.print("Guardian Name :");
				String stu_guardian=staff_input.nextLine();
				System.out.print("Emergency contact :");
				String stu_emergency_contact=staff_input.nextLine();
				StaffProcess.getInstance().update_student(student_id,stu_name, staff_id, stu_email, stu_mobile, stu_class, stu_record_status, stu_guardian, stu_emergency_contact);
				System.out.println("**************************************************************");
			}
			break;
			
			case 3:
			{
				System.out.print("Student Id:");
				String student_id=staff_input.next();
				System.out.print("Staff Id:");
				String staff_id=staff_input.next();
				StaffProcess.getInstance().delete_student_detail(student_id,staff_id);
			}
			break;
			
			case 4:
			{
				System.out.print("Student Id:");
				String student_id=staff_input.next();
				StaffProcess.getInstance().view_student_detail(student_id,staff_detail.getStaff_id());
			}
			break;
			
			case 5:
			{
				System.out.print("Staff Id:");
				String staff_id=staff_input.next();
				StaffProcess.getInstance().view_student_detail(staff_id);
			}
			break;
			
			case 6:
			{
				System.out.print("Student Id :");
				String stu_id=staff_input.next();
				System.out.print("Attendance date (\"dd-mmm-yyyy\"):");
				String stu_attendance_date=staff_input.next();
				staff_input.nextLine();
				System.out.print("Student standard :");
				String stu_grade=staff_input.nextLine();
				System.out.print("Staff id :");
				String staff_incharge_id=staff_input.next();
				System.out.print("Attendance Entry :");
				String attendance_entry=staff_input.next();
				staff_input.nextLine();
				System.out.println("Is holiday (yes or no):");
				boolean is_holiday=staff_input.nextLine().equalsIgnoreCase("yes") ? true :false;
				System.out.println("is absent(yes or no):");
				boolean is_absent=staff_input.nextLine().equalsIgnoreCase("yes") ? true :false;
				System.out.println("Has leave approval (yes or no):");
				boolean has_approval=staff_input.nextLine().equalsIgnoreCase("yes") ? true :false;
				System.out.println("Instruction :");
				String instruction=staff_input.nextLine();
				StaffProcess.getInstance().add_student_attendance(stu_id, stu_attendance_date, stu_grade, staff_incharge_id, attendance_entry, is_holiday, is_absent, has_approval, instruction);	
			}
			break;
			
			case 7:
			{
				System.out.print("Student Id :");
				String stu_id=staff_input.next();
				System.out.print("Attendance date (\"dd-mmm-yyyy\"):");
				String stu_attendance_date=staff_input.next();
				System.out.print("Student standard :");
				staff_input.nextLine();
				String stu_grade=staff_input.nextLine();
				System.out.print("Staff id :");
				String staff_incharge_id=staff_input.next();
				System.out.print("Attendance Entry :");
				String attendance_entry=staff_input.next();
				staff_input.nextLine();
				System.out.print("Is holiday (yes or no):");
				boolean is_holiday=staff_input.nextLine().equalsIgnoreCase("yes") ? true :false;
				System.out.print("is absent(yes or no):");
				boolean is_absent=staff_input.nextLine().equalsIgnoreCase("yes") ? true :false;
				System.out.print("Has leave approval (yes or no):");
				boolean has_approval=staff_input.nextLine().equalsIgnoreCase("yes") ? true :false;
				System.out.print("Instruction :");
				String instruction=staff_input.nextLine();
				StaffProcess.getInstance().update_student_attendance(stu_id, stu_attendance_date, stu_grade, staff_incharge_id, attendance_entry, is_holiday, is_absent, has_approval, instruction);
			}
			break;
			
			case 8:
			{
				System.out.print("Student Id :");
				String stu_id=staff_input.next();
				staff_input.nextLine();
				System.out.print("Attendance Date(\"yyyy-mm-dd\"):");
				String att_date=staff_input.nextLine();
				StaffProcess.getInstance().delete_student_attendance(stu_id,staff_detail.getStaff_id(),att_date);
			}
			break;
			
			case 9:
			{
				System.out.println("***********************************Student's attendance Report********************************");
				System.out.print("Student Id :");
				String stu_id=staff_input.next();
				StaffProcess.getInstance().view_student_attendance(stu_id);
			}
			break;
			
			case 10:
			{
				System.out.println("***********************************Grade students attendance Report********************************");
				StaffProcess.getInstance().my_grade_student_attendance(staff_detail.getStaff_id());
			}
			break;
			
			case 11:
			{
				System.out.println("***********************************Weekly Report********************************");
				System.out.print("Student Id Id:");
				String student_id=staff_input.next();
				System.out.print("From date(\"yyyy-mm-dd\"):");
				String from_date=staff_input.next();
				StaffProcess.getInstance().weekly_report(student_id,staff_detail.getStaff_id(), from_date);//LocalDate.parse(from_date,DateTimeFormatter.ofPattern("yyyy-mm-dd")));
			}
			break;
			
			case 12:
			{
				System.out.println("***********************************Monthly Report********************************");
				System.out.print("Student Id Id:");
				String student_id=staff_input.next();
				System.out.print("From date(\"yyyy-mm-dd\"):");
				String from_date=staff_input.next();
				StaffProcess.getInstance().monthly_report(student_id,staff_detail.getStaff_id(),from_date) ;
			}
			break;
			
			case 13:
			{
				StaffProcess.getInstance().view_notification(staff_detail.getStaff_id());
			}
			break;
			
			case 14:
			{
				staff_lets_start=false;
			}
			break;
			
			default:
			{
				System.out.println("We are not providing this service...Kindly try any of our mentioned service");
				System.out.println("****************************************************************************");
			}
			}
			
		}while(staff_lets_start);
		
	}

}
