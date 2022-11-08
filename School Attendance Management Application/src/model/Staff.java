package model;
import java.sql.SQLException;

import model.Notification;

public class Staff{
	private String staff_id;
	private String name;
	private String email_id;
	private String password;
	private String mobile_number;
	private String standard_incharge;
	private String notification_id;
	
	
	
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
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStandard_incharge() {
		return standard_incharge;
	}
	public void setStandard_incharge(String standard_incharge) {
		this.standard_incharge = standard_incharge;
	}
	public String getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	

	
}
