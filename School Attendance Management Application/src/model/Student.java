package model;

public class Student{
	private String student_id;
	private String name;
	private String email_id;
	private String password;
	private String mobile_number;
	private String standard;
	private String staff_incharge_id;
	private boolean is_live_data;
	private String Guardian_name;
	private String emergency_contact;
	public static String student_code="ST20220";
	public static int student_counter=11;
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getStaff_incharge_id() {
		return staff_incharge_id;
	}
	public void setStaff_incharge_id(String staff_incharge_id) {
		this.staff_incharge_id = staff_incharge_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public boolean isIs_live_data() {
		return is_live_data;
	}
	public void setIs_live_data(boolean is_live_data) {
		this.is_live_data = is_live_data;
	}
	public String getGuardian_name() {
		return Guardian_name;
	}
	public void setGuardian_name(String guardian_name) {
		Guardian_name = guardian_name;
	}
	public String getEmergency_contact() {
		return emergency_contact;
	}
	public void setEmergency_contact(String emergency_contact) {
		this.emergency_contact = emergency_contact;
	}
	
	
}
