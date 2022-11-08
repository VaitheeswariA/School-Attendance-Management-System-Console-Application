package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.AdminProcess;
import model.Admin;
import model.Student;

public class AdminAccount implements UserAccount {
	Connection connect = null;
	PreparedStatement prepared_statement = null;
	ResultSet result_set = null;

	public AdminAccount() {
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
			prepared_statement = connect.prepareStatement("Select Name,Email_id,Password,Mobile_Number FROM Admin WHERE Email_id=? AND Password=?");
			prepared_statement.setString(1, email_id);
			prepared_statement.setString(2, password);
			result_set = prepared_statement.executeQuery();
			if (result_set.next()) {
				Admin admin_detail = new Admin();
				admin_detail.setName(result_set.getNString(1));
				admin_detail.setEmail_id(result_set.getNString(2));
				admin_detail.setPassword(result_set.getNString(3));
				admin_detail.setMobile_number(result_set.getNString(4));
				welcomePage(admin_detail);
			}
			else
			{
				System.out.println("Incorrect Email id or password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void welcomePage(Admin admin_detail) {
		Scanner admin_input = new Scanner(System.in);
		boolean admin_lets_start = true;
		do {
			System.out.println("**************************Welcome Admin************************");
			System.out.println(
					"Choose any service......\n1.Add new Staff  \n2.Update staff detail \n3.Delete staff detail \n4.View Students detail \n5.View Student \n6.View students Attendance \n7.Back");
			int admin_start_choice = admin_input.nextInt();

			switch (admin_start_choice) {
			case 1: {
				System.out.print("Staff name :");
				String staff_name = admin_input.next();
				System.out.print("Email id :");
				String staff_email = admin_input.next();
				System.out.print("Mobile number :");
				String staff_mobile = admin_input.next();
				System.out.println("standard_incharge :");
				String staff_class = admin_input.next();
				AdminProcess.getInstance().add_new_staff(staff_name, staff_email, staff_mobile, staff_class, null);
			}
				break;

			case 2: {
				boolean admin_update = true;
				do {
					System.out.println("**************************Updating details***********************************");
					System.out.println("Choose field to update...\n1.Email id \n2.Mobile number \n3.Standard \n4.back");
					int admin_update_choice = admin_input.nextInt();
					switch (admin_update_choice) {
					case 1: {
						System.out.print("Staff id :");
						String staff_id = admin_input.next();
						System.out.print("Email id :");
						String staff_email = admin_input.next();
						AdminProcess.getInstance().update_email(staff_id, staff_email);
					}
						break;

					case 2: {
						System.out.print("Staff id :");
						String staff_id = admin_input.next();
						System.out.print("Mobile number :");
						String staff_mobile = admin_input.next();
						AdminProcess.getInstance().update_mobile_number(staff_id, staff_mobile);
					}
						break;

					case 3: {
						System.out.print("Staff id :");
						String staff_id = admin_input.next();
						System.out.print("Standard :");
						String staff_grade = admin_input.next();
						AdminProcess.getInstance().update_grade(staff_id, staff_grade);
					}
						break;

					case 4: {
						admin_update = false;
					}

					default: {
						System.out
								.println("We are not providing this service...Kindly try any of our mentioned service");
						System.out.println(
								"****************************************************************************");
					}
					}

				} while (admin_update);

			}
				break;

			case 3: {
				System.out.print("Staff id :");
				String staff_id = admin_input.next();
				AdminProcess.getInstance().delete_staff(staff_id);
			}
				break;

			case 4: {

				AdminProcess.getInstance().view_student_detail();

			}
				break;

			case 5: {
				System.out.print("Student id :");
				String student_id = admin_input.next();
				AdminProcess.getInstance().view_student_detail(student_id);

			}
				break;

			case 6: {
				System.out.print("Student id :");
				String student_id = admin_input.next();
				AdminProcess.getInstance().view_student_attendance_detail(student_id);
			}
				break;

			case 7: {
				admin_lets_start = false;
			}
				break;

			default: {
				System.out.println("We are not providing this service...Kindly try any of our mentioned service");
				System.out.println("****************************************************************************");
			}
			}

		} while (admin_lets_start);

	}

}
