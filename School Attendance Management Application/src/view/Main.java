package view;

import java.util.Scanner;

import controller.AdminProcess;
import controller.Validation;
import model.Admin;
import model.Staff;
import model.Student;

public class Main {
	
	public static void main(String[] args)
	{
		Main login=new Main();
		login.begin(login);
	}
	
	public void begin(Main login)
	{
		Scanner user_input=new Scanner(System.in);
		Validation validate=new Validation();
		
		System.out.println("*************School Attendance Application*******************");
		boolean lets_start=true;
		
		do {
			System.out.println("Choose any option to get in to application...\n1.	Admin Login \n2.	Staff Login \n3.	Student Login \n4.	Exit");
			int login_choice=user_input.nextInt();
			boolean isValid_email=false;
			String email_id="";
			String password="";
			if(login_choice<=3)
			{
				user_input.nextLine();
				do {
					System.out.print("Email id:");
					email_id=user_input.nextLine();
					isValid_email=validate.validate_email_id(email_id);
					if(!isValid_email)
							System.out.println("Invalid mail Id....enter valid email");
				}while(!isValid_email);
				
				System.out.print("Password :");
				password=user_input.nextLine();
				UserAccount account=(login_choice==1) ? new AdminAccount() : (login_choice==2 ? new StaffAccount() : new StudentAccount());
				account.Login(email_id, password);
				
			}
			
			else if(login_choice==4)
			{
				lets_start=false;
			}
			else
			{
				System.out.println("We are not providing this service...Kindly try any of our mentioned service");
				System.out.println("****************************************************************************");
			}
			
			
		}while(lets_start);
		
		
	}

	
}
