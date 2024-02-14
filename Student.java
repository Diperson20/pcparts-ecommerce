package CourseManagementSystem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    MysqlCon conn = new MysqlCon();
    Scanner scan = new Scanner(System.in);
    ResultSet rs;
    
    String Course;
    int Level;
    int id;
    String Semester;

    public  Student(){
    	System.out.println("------------------------------------------------------");
        System.out.println("Welcome to student windows");
        System.out.println("Are you a new student?");
        String choice = scan.next();
        System.out.println("------------------------------------------------------");
        if (choice.toLowerCase().equals("yes")){
            newStudent();
        }
        else if ( choice.toLowerCase().equals("no")){
            oldStudent();
        }
        else {
        	new Main();
        }


    }
    private void newStudent(){
    	System.out.println("Please enter the email provided by college : ");
    	String email = scan.next();
    	String SQL_email = "Select * from newStudent where email ='"+ email +"';";
    	String name ="";
    	String num ="";
    	String address="";
    	
    	try {
	    	rs = conn.st.executeQuery(SQL_email);
	    	while(rs.next()) {	
	    		name = rs.getString(2);
	    		num = rs.getString(4);
	    		address = rs.getString(5);
	    		System.out.println("Greetings "+ name);
	    		System.out.println("------------------------------------------------------");
	    	}
	    	if (!name.isEmpty() && !num.isEmpty() && !address.isEmpty()) {
	    		System.out.println("Fullname: "+ name);
	    		System.out.println("Phone number: "+ num);
	    		System.out.println("Address: " + address);
	    		System.out.println("Your email: "+ email);
	    		System.out.println("------------------------------------------------------");
	    		System.out.println("Courses we have:");
	    		rs = conn.st.executeQuery("Select * from course");
	    		
	    		ArrayList subject = new ArrayList();
	    		while(rs.next()) {
	    			subject.add(rs.getString(1));
	    		}
	    		int i = 0;
	    		while(i < subject.size()) {
	    			System.out.println(subject.get(i).toString().toUpperCase());
	    			i++;
	    		}
	    		System.out.println("Please select a course:");
	    		Course = scan.next();
	    		System.out.println("-----------------------------------------------------");
	    		while(!subject.contains(Course.toUpperCase())) {
	    			System.out.println("Course doesnot Exist");
	    			System.out.println("Select course: ");
	    			Course = scan.next();
	    		}
	    		System.out.println("------------------------------------");
	    		System.out.println("Please Select your level: ");
	    		Level = scan.nextInt();
	    		System.out.println("------------------------------------");
	    		System.out.println("Please select your Semester: "); 
	    		Semester = scan.next();
	    		System.out.println("------------------------------------");
	    		
	    		conn.st.executeUpdate("Insert into oldstudent (email, fullname, phone_number, address, course, Level, Semester) values "
	    				+ "('" + email + "','" + name+"','"+num+"','"+address+"','"+Course+"','"+String.valueOf(Level)+"','"+Semester+"');");
	    		
	    		conn.st.executeUpdate("Delete from newstudent where email ='"+email+"';");
	    		System.out.println("You have been enrolled Successfully: ");
	    		System.out.println("------------------------------------------------");
	    		System.out.flush();
	    		oldStudent();
	    	}
	    		
	    	
	    	else {
	    		System.out.println("You have entered an incorrect email");
	    		newStudent();
	    	}

	    }
    	catch(Exception e){
	    		System.out.println(e);
    		
    	}
    }
    private  void  oldStudent(){
    	System.out.println("Provide your old email for identification: ");
    	String email = scan.next();
    	String SQL_email = "Select * from oldstudent where email ='"+email+"';";
    
    try {
    	rs = conn.st.executeQuery(SQL_email);
    	String name = "";
    	String num = "";
    	String address = "";
    	
    	while(rs.next()) {
    		name = rs.getString(2);
    		num = rs.getString(4);
    		address = rs.getString(5);
    		Course = rs.getString(6);
    		Semester = rs.getString(7);
    		Level = Integer.valueOf(rs.getString(8));
    		id = rs.getInt(1);
    	}
    	if (!name.isEmpty() && !num.isEmpty() && !address.isEmpty()){
    		System.out.println("--------------------------------------------");
    		System.out.println("Your email is: "+ email);
    		System.out.println("Full Name: " + name);
    		System.out.println("Contact Number: "+ num);
    		System.out.println("Address: "+ address);
    		System.out.println("Your Course: " + Course);
    		System.out.println("Current Level: " + Level);
    		System.out.println("Current Semester: " + Semester);
    		System.out.println("Your Assigned ID: " + id);
    		System.out.println("--------------------------------------------");
    		
    		ArrayList subject = new ArrayList();
    		rs = conn.st.executeQuery("select Name from module where course ='"+Course+"' and level = "+Level+" and Semester = " +Semester+";");
    		while(rs.next()) {
    			subject.add(rs.getString(1));
    		}
    		System.out.println("Your list of modules for this semester: ");
    		for (int i = 0; i < subject.size(); i++) {
    			System.out.println(subject.get(i));
    		}
    		System.out.println("--------------------------------------------");
    		
    		System.out.println("Do you want to view your result?");
    		String choice = scan.next();
    		if (choice.toLowerCase().equals("yes")) {
    			
    			rs = conn.st.executeQuery("Select * from result where email ='"+email+"';");
    			String nam ="";
    			String sub ="";
    			String mark ="";
    			while(rs.next()) {
    				nam = rs.getString(2);
    				sub = rs.getString(5);
    				mark = rs.getString(8);
    			}
    			if (!nam.isEmpty() && !sub.isEmpty() && !mark.isEmpty()){
    				System.out.println("Name: " + nam);
    				System.out.println("Module: " + sub);
    				System.out.println("Obtained Marks: " + mark);
    				new Student();
    			}
    			else {
    				System.out.println("Your result isn't ready yet.");
    				new Main();
    			}
    		}
    		else {		
    		System.out.println("Thank you for the visit \nTaking you back to the Student menu");
    		new Student();
    		}
    	}
    	
    	else {
    		System.out.println("Incorrect email");
    		oldStudent();
    	}
    }
    catch(Exception e) {
    	System.out.println(e);
    }
    }
}