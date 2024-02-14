package CourseManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instructor {
    MysqlCon conn = new MysqlCon();
    ResultSet rs;
    Scanner sc = new Scanner(System.in);

    public Instructor() {
    	System.out.println("------------------------------------------------------------");
        System.out.println("Welcome to Instructor windows");
        System.out.println("Please provide your email for further:");
        Instructormain();
        
    }
    private void Instructormain() {
    	String mail = sc.next();
    	
    	try {
    		System.out.println("------------------------------------------------------------");
			rs = conn.st.executeQuery("select * from instructor where email = '" + mail+"';");
			String name = "";
			ArrayList field = new ArrayList();
			ArrayList module = new ArrayList();
			ArrayList lvl = new ArrayList();
			ArrayList sem = new ArrayList();
			
			while (rs.next()) {
				name = rs.getString(2);
				field.add(rs.getString(3));
				module.add(rs.getString(4));
				lvl.add(rs.getString(5));
				sem.add(rs.getString(6));
				
			}
			if (!name.isEmpty()) {
				System.out.println("Welcome Instructor "+ name);
				System.out.println("Email: "+ mail);
				System.out.println("------------------------------------------------------------");
				System.out.println("Lectures you need to prepare for are:");
				int a = 1;
				for (int i = 0; i < field.size(); i++) {
					
					System.out.println("Number "+ a);
					a++;
					System.out.println("Course: " + field.get(i));
					System.out.println("Level: " + lvl.get(i));
					System.out.println("Semester: " + sem.get(i));
					System.out.println("Module: "+ module.get(i));
					System.out.println("------------------------------------------------------------");
				}
				System.out.println("Please select the number of module which you want to add the marks for: ");
				int opt = sc.nextInt();
				System.out.println("------------------------------------------------------------");
				System.out.println("You have select Number " + opt);
				System.out.println("Which contains");
				
				int index = opt - 1;
				String course = "";
				String level = "";
				String semester = "";
				String modul = "";
				if (opt <= field.size()) {
					course = (String) field.get(index);
					level = (String) lvl.get(index);
					semester = (String) sem.get(index);
					modul = (String) module.get(index);
					System.out.println("Course: " + course);
					System.out.println("Level: " + level);
					System.out.println("Semester: " + semester);
					System.out.println("Module: " + modul);
				}
				ArrayList id = new ArrayList();
				ArrayList student = new ArrayList();
				ArrayList email = new ArrayList();
				
				rs = conn.st.executeQuery("select * from oldstudent where course='" + course + "' and Level='"+level+"' and Semester ='"+semester+"';");
				int i = 1;
				System.out.println("------------------------------------------------------------");
				System.out.println("Students who are currently studying this subjects are:");
				while (rs.next()) {
					String fullname = rs.getString(2);
					String gmail =rs.getString(3);
					int StudentID = rs.getInt(1);
					student.add(fullname);
					email.add(gmail);
					id.add(StudentID);
					System.out.println("Fullname: " + fullname);
					System.out.println("Student ID: "  + StudentID);
					System.out.println("Enter "+ i +" to select " + fullname);
					i++;
					System.out.println("------------------------------------------------------------");
				}
				System.out.println("Please enter the number of a student for adding their marks");
				int num = sc.nextInt();
				if (num <= student.size()) {
					String nam =(String) student.get(num-1);
					String m = (String) email.get(num-1);
					int stId= (int) id.get(num-1);
					
					System.out.println("You have select "+nam);
					System.out.println("------------------------------------------------------------");
					System.out.println("Enter marks:");
					int val = sc.nextInt();
					String query ="insert into result (StudentID, name, email, course, subject, level, semester, marks) values ("+stId+",'"+nam+"','"+m+"','"+course+"','"+modul+"','"+level+"','"+semester+"','"+val+"');";
					conn.st.executeUpdate(query);
					System.out.println("Marks added Successfully");
					System.out.println("------------------------------------------------------------");
					System.out.println("Taking you back to the Main Menu:");
					new Main();
				}
				
				
			}
			else {
				System.out.println("------------------------------------------------------------");
				System.out.println("Email does not match");
				System.out.println("Please provide the assigned email by College for Instructor");
				Instructormain();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
        
    }