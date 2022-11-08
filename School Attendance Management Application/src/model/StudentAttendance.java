package model;

import java.time.LocalDateTime;

public class StudentAttendance {
	private String student_id;
	private LocalDateTime attendance_date;
	private String student_grade;
	private String staff_incharge_id;
	private String attendance_record;
	private boolean is_holiday;
	private boolean is_absent;
	private boolean has_leave_approval;
	private String todo_instruction;
	
	public static String school_code="ES2022";
	public static int student_counter=6;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public LocalDateTime getAttendance_date() {
		return attendance_date;
	}
	public void setAttendance_date(LocalDateTime attendance_date) {
		this.attendance_date = attendance_date;
	}
	public String getStudent_grade() {
		return student_grade;
	}
	public void setStudent_grade(String student_grade) {
		this.student_grade = student_grade;
	}
	public String getStaff_incharge_id() {
		return staff_incharge_id;
	}
	public void setStaff_incharge_id(String staff_incharge_id) {
		this.staff_incharge_id = staff_incharge_id;
	}
	public String getAttendance_record() {
		return attendance_record;
	}
	public void setAttendance_record(String attendance_record) {
		this.attendance_record = attendance_record;
	}
	public boolean isIs_holiday() {
		return is_holiday;
	}
	public void setIs_holiday(boolean is_holiday) {
		this.is_holiday = is_holiday;
	}
	public boolean isIs_absent() {
		return is_absent;
	}
	public void setIs_absent(boolean is_absent) {
		this.is_absent = is_absent;
	}
	public boolean isHas_leave_approval() {
		return has_leave_approval;
	}
	public void setHas_leave_approval(boolean has_leave_approval) {
		this.has_leave_approval = has_leave_approval;
	}
	public String getTodo_instruction() {
		return todo_instruction;
	}
	public void setTodo_instruction(String todo_instruction) {
		this.todo_instruction = todo_instruction;
	}
	
	
}
