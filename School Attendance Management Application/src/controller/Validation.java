package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Admin;
import model.Staff;
import model.Student;

public class Validation {
	
	
	//validation
	public boolean validate_email_id(String email_id)
	{
		Pattern email_id_pattern =Pattern.compile("([a-z]{1,15})([@][a-z]{1,5}[.][a-z && (com)]{3})");
		Matcher email_id_matcher=email_id_pattern.matcher(email_id);
		if(email_id_matcher.find())
			return true;
		return false;
	}
}
