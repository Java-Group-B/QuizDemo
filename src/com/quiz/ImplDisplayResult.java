package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;


public class ImplDisplayResult implements DisplayResult{

	@Override
	public void getDisplayResult() throws Exception {
		/*take username and password from user*/
		System.out.println("Enter the username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		/*call getUserVarification method and verify user*/
		boolean varify=getUserVarification(uname,pword);
		//if credentials are correct, then call getresult to display result 
		if(varify==true) {
			getResult(uname,pword);	
		}


	}
	public static boolean getUserVarification(String uname,String pword) throws Exception {
		/*set one flag, if user credentials are matched then it
	    i== true and if not  then i==false(also throw exception)
		 */
		boolean i=false;
		/*establish connection between java application & database*/
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {

			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			/*pass sql query through preparedStatement, to fetch username & password from database*/
			preparedStatement = connection.prepareStatement("select student.username,student.password from student inner join scoredetails on student.username=scoredetails.username;");
			ResultSet resultSet=preparedStatement.executeQuery();
			/*Store username and corresponding password in map in form of key,value*/
			Map<String,String> credentials=new LinkedHashMap<String, String>();
			while(resultSet.next()) {
				credentials.put(resultSet.getString(1),resultSet.getString(2));
			}
			/*now compare given credentials with database,if matched then i==true,else i==false*/
			for(Map.Entry<String,String> m:credentials.entrySet()) {
				if(m.getKey().equals(uname)&&m.getValue().equals(pword)){
					i=true;
				}}
			if(i==false) {
				/*throw exception if credentials not match*/
				throw new Exception();
			}
		}
		catch (Exception e) {
			System.out.println("Username Or Password is incorrect\nOr You may not attempted exam.");
		}
		finally {
			connection.close();
			preparedStatement.close();
		}	
		return i;
	}
	public static void getResult(String uname,String pword) throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			/*Pass the sql query via preparedstatement and fetch score of student*/
			preparedStatement = connection.prepareStatement("select student.username,student.password,scoredetails.score from student inner join scoredetails on student.username=scoredetails.username where scoredetails.username=?;");
			preparedStatement.setString(1,uname);
			ResultSet resultSet=preparedStatement.executeQuery();

			while(resultSet.next()) {
				System.out.print("Your score is "+resultSet.getInt(3));
			}
		}
		catch (Exception e) {
           /*Even after verification, if score is not fetching properly
             then it may be some unexpected error*/
            System.out.println("Unexpected Error...");
		}
		finally {
			connection.close();
			preparedStatement.close();
		}
	}
}
