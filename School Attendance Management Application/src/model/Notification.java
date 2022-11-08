package model;

public class Notification {
	private String notification_id;
	private String notification_type;
	private String requested_student_id;
	private String approver_id;
	private String request_reason;
	private String notication_status;
	private boolean is_live_requst;
	private String from_date;
	private String to_date;
	public static int counter=6;
	
	
	
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	public String getNotification_type() {
		return notification_type;
	}
	public void setNotification_type(String notification_type) {
		this.notification_type = notification_type;
	}
	public String getRequested_student_id() {
		return requested_student_id;
	}
	public void setRequested_student_id(String requested_student_id) {
		this.requested_student_id = requested_student_id;
	}
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	public String getRequest_reason() {
		return request_reason;
	}
	public void setRequest_reason(String request_reason) {
		this.request_reason = request_reason;
	}
	public String getNotication_status() {
		return notication_status;
	}
	public void setNotication_status(String notication_status) {
		this.notication_status = notication_status;
	}
	public boolean isIs_live_requst() {
		return is_live_requst;
	}
	public void setIs_live_requst(boolean is_live_requst) {
		this.is_live_requst = is_live_requst;
	}
	
	
}
