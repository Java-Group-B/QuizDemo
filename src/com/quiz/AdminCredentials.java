package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdminCredentials {
public boolean getAdminVerification(String uname,String pword) throws Exception {

	/*set one flag, if user credentials are matched then it
    i== true and if not  then i==false(also throw exception)
	 */
	boolean flag=false;
	/*establish connection between java application & database*/
	Connection connection=null;
	PreparedStatement preparedStatement =null;
	try {

		ConnectionDetails connectionDetails=new ConnectionDetails();
		connection=connectionDetails.getConnection();
		/*pass sql query through preparedStatement, to fetch admin username & password from database*/
		preparedStatement = connection.prepareStatement("select username,password from admin_credentials;");
		ResultSet resultSet=preparedStatement.executeQuery();
		/*Store username and corresponding password in map in form of key,value*/
		Map<String,String> credentials=new LinkedHashMap<String, String>();
		while(resultSet.next()) {
			credentials.put(resultSet.getString(1),resultSet.getString(2));
		}
		/*now compare given credentials with database,if matched then i==true,else i==false*/
		for(Map.Entry<String,String> m:credentials.entrySet()) {
			if(m.getKey().equals(uname)&&m.getValue().equals(pword)){
				flag=true;
			}}
		if(flag==false) {
			/*throw exception if credentials not match*/
			throw new Exception();
		}
	}
	catch (Exception e) {
		System.out.println("Access denied...");
	}
	finally {
		connection.close();
		preparedStatement.close();
	}	


	return flag;
}
}
