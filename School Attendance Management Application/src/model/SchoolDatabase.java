package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class SchoolDatabase {
	public static ArrayList<Student> students_detail;
	public static ArrayList<Staff> staffs_detail;
	public static LinkedHashMap<String,ArrayList<Student>> staff_incharge_details;
	public static LinkedHashMap<String,ArrayList<Notification>> notification_list;
	public static LinkedHashMap<String,ArrayList<StudentAttendance>> student_attendance_record;
	
	
	//Adding Student details
	public void insert_student_detail(Student student_detail)
	{
		students_detail.add(student_detail);
	}
	
	public void insert_student_detail(ArrayList<Student> student_detail)
	{
		students_detail.addAll(student_detail);
	}
	
	public void insert_staff_detail(Staff staff_detail)
	{
		staffs_detail.add(staff_detail);
	}
	
	public void insert_staff_detail(ArrayList<Staff> staff_detail)
	{
		staffs_detail.addAll(staff_detail);
	}
	
	public void insert_staff_incharge_detail(String staff_id,Student student_record)
	{
		if(staff_incharge_details.get(staff_id)==null)
		{
			staff_incharge_details.put(staff_id, new ArrayList<>(Arrays.asList(student_record)));
		}
		else
		{
			ArrayList<Student> stu_record=staff_incharge_details.get(staff_id);
			stu_record.add(student_record);
			staff_incharge_details.put(staff_id, stu_record);
		}
	}
	
	public void insert_staff_incharge_detail(String staff_id,ArrayList<Student> student_record)
	{
		staff_incharge_details.put(staff_id, student_record);
	}

	public void insert_notification_list(String staff_id,Notification notification_record)
	{
		if(notification_list.get(staff_id)==null)
		{
			notification_list.put(staff_id, new ArrayList<>(Arrays.asList(notification_record)));
		}
		else
		{
			ArrayList<Notification> record=notification_list.get(staff_id);
			record.add(notification_record);
			notification_list.put(staff_id, record);
		}
	}
	
	public void insert_notification_list(String staff_id,ArrayList<Notification> notifications)
	{
		notification_list.put(staff_id, notifications);
	}

	public void insert_student_attendance_record(String student_id,StudentAttendance attendance_record)
	{
		if(student_attendance_record.get(attendance_record)==null)
		{
			student_attendance_record.put(student_id, new ArrayList<>(Arrays.asList(attendance_record)));
		}
		else
		{
			ArrayList<StudentAttendance> attendance=student_attendance_record.get(student_id);
			attendance.add(attendance_record);
			student_attendance_record.put(student_id, attendance);
		}
	}
	
	public void insert_student_attendance_record(String student_id,ArrayList<StudentAttendance> student_attendance)
	{
		student_attendance_record.put(student_id, student_attendance);
	}
	
}
