package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Student {

	/*setFirstname()-method is used to check given firstname is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/
	public boolean setFirstname(String firstname) {
		boolean s=false;
		for(int i=0;i<firstname.length();i++) {		 
			if(!((firstname.charAt(i)>=65 && firstname.charAt(i)<=90) || (firstname.charAt(i)>=97 && firstname.charAt(i)<=122))){
				s=true;	
			}}
		if(s==true) {
			System.out.println("Firstname should contain alphabets only...\nPlease enter correct input:");
		}
		return s;
	}

	/*setLastname()-method is used to check given lastname is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/
	public boolean setLastname(String lastname) {
		boolean s=false;
		for(int i=0;i<lastname.length();i++) {		 
			if(!((lastname.charAt(i)>=65 && lastname.charAt(i)<=90) || (lastname.charAt(i)>=97 && lastname.charAt(i)<=122))){
				s=true;	
			}}
		if(s==true) {
			System.out.println("Lastname should contain alphabets only...\nPlease enter correct input:");
		}
		return s;
	}

	/*setUsername()-method is used to check given username is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/
	public boolean setUsername(String username) {
		Connection connection=null;
		PreparedStatement ps=null;
		String admin_username=null;
		boolean i=false;
		try{
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			ps=connection.prepareStatement("select username from admin_credentials");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				admin_username=rs.getString(1);
			}
			if(username.equalsIgnoreCase(admin_username)) {
				System.out.println("Username already exist\nPlease enter another username -");
				i=true;
			}		
		}catch(Exception sqle) {
			System.out.println(sqle.getMessage());
		}
		return i;
	}

	/*setPassword()-method is used to check given password is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/
	public boolean setPassword(String password) {	
		boolean s=false;
		int countSp=0;
		int countCl=0;
		int countSl=0;
		int countNum=0;
		for(int j=0;j<password.length();j++) {
			if(password.length()>=8){
				if((password.charAt(j)>=33 && password.charAt(j)<=47) || (password.charAt(j)>=58 && password.charAt(j)<=64) ||
						(password.charAt(j)>=91 && password.charAt(j)<=96) || (password.charAt(j)>=123 && password.charAt(j)<=126)){
					countSp++;
				}
				if(password.charAt(j)>=65 && password.charAt(j)<=90) {
					countCl++;
				}
				if (password.charAt(j)>=97 && password.charAt(j)<=122){
					countSl++;
				}if(password.charAt(j)>=48 && password.charAt(j)<=57){
					countNum++;
				}
			}
		}
		if(countSp<1 || countCl<1 || countSl<1 || countNum<1) {
			System.out.println("Sorry...Password should contain combination of small letter,capital letter,number,special character");
			System.out.println("Weak password...Password must contain at least 8 characters");
			System.out.println("Please enter the valid password :");
			s=true;
		}

		return s;
	}

	/*setCity()-method is used to check given city is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/	
	public boolean setCity(String city) {
		boolean s=false;
		for(int i=0;i<city.length();i++) {		 
			if(!((city.charAt(i)>=65 && city.charAt(i)<=90) || (city.charAt(i)>=97 && city.charAt(i)<=122))){
				s=true;	
			}}
		if(s==true) {
			System.out.println("City should contain alphabets only...\nPlease enter correct input:");
		}
		return s;
	}

	/*setEmailId()-method is used to check given emailId is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/	

	public boolean setEmailId(String emailId) {
		boolean s=false;
		int count=0;
		for(int i=0;i<emailId.length();i++) {
			if(emailId.charAt(i)=='@') {
				count++;
			}
		}
		if(count!=1) {
			System.out.println("invalid emailId...try again\nPlease enter valid email id:");
			s=true;
		}else {
			int lastOccurance=emailId.lastIndexOf("@");
			String email=emailId.substring(0,lastOccurance);
			if(email.contains("@") || email.contains("%")|| email.contains("&")||
					email.contains("#")|| email.contains("*")|| email.contains("$")) {
				if(emailId.endsWith(".com")){
					System.out.println("invalid emailId...try again\nPlease enter valid email id:");
					s=true;
				}
			}
		}
		return s;
	}

	/*setMobileNumber()-method is used to check given mobileNumber is valid or not*/
	/*Author Name-Priyanka Suyog Gawali*/	

	public boolean setMobileNumber(String mobileNumber) {
		boolean s=false;
		for(int i=0;i<mobileNumber.length();i++) {		 
			if(!(((mobileNumber.charAt(i)>=48) && (mobileNumber.charAt(i)<=57)) && (mobileNumber.length()==10))){
				s=true;	
			}
		}
		if(s==true) {
			System.out.println("Invalid mobile no\nPlease enter valid mobile number :");
		}
		return s;
	}
}
