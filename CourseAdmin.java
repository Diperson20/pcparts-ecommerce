package CourseManagementSystem;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseAdmin {
    MysqlCon conn = new MysqlCon();
    Scanner sc = new Scanner(System.in);
    ResultSet rs;
    String Course;
    public CourseAdmin() {
    	System.out.println("-----------------------------------------------------------");
    	System.out.println("Welcome to Admin Window");
        System.out.println("Please choose any one from the below to begin editing !!!!");
        System.out.println("1 Course");
        System.out.println("2 Instructor");
        System.out.println("3 Result");
        System.out.println("4 Subject");
        System.out.println("5 Add New Student");
        System.out.println("6 Main Menu");
        System.out.println("-----------------------------------------------------------");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                courseMain();
                break;
            case 2:
                InstructorMain();
                break;
            case 3:
               resultMain();
                break;
            case 4:
                moduleMain();
                break;
            case 5:
            	addstudent();
            	break;
            case 6:
            	new Main();
            default:
            	System.out.println("Incorrect Option: ");
            	 new CourseAdmin();
        }

    }
    private void addstudent() {
    	System.out.println("--------------------------------------------------------------");
    	String name = "";
    	String email ="";
    	String phno ="";
    	String address ="";
    	try {
    		rs = conn.st.executeQuery("Select * from newstudent;");
    		System.out.println("Please enter a name of new student: ");
    		sc.nextLine();
    		name = sc.nextLine();
    		System.out.println("Please assign an email for new student: ");
    		email = sc.next();
    		System.out.println("Please enter a phone number:");
    		phno =sc.next();
    		System.out.println("Please enter the address of new student: ");
    		address = sc.next();
    		
    		conn.st.executeUpdate("insert into newstudent (fullname, email, phone_number, address) values ('" + name+ "','"+email+"','"+phno+"','"+address+"');");
    		System.out.println("Added new Student successfully.......");
    		new CourseAdmin();
    		
    		
    	}catch (Exception e){
    		System.out.println(e);
    		
    	}
    }

	private void courseMain() {		
		
		System.out.println("Please choose any option below: ");
		System.out.println("1. Add");
		System.out.println("2. Update");
		System.out.println("3. Delete");
		System.out.println("4. Back to Admin window");
		
		int opt = sc.nextInt();
		System.out.println("-----------------------------------------------------------");

		System.out.println("Already existed Course names: ");
		String SQL_course = "Select * from course";
		try {
			rs = conn.st.executeQuery(SQL_course);
			ArrayList course_list = new ArrayList(); 
			while (rs.next()) {
				course_list.add(rs.getString(1));
			}
			for (int i = 0; i < course_list.size(); i++) {
				System.out.println(course_list.get(i).toString());
			}
			switch(opt) {
				case 1:
					System.out.println("-----------------------------------------------------------");
					System.out.println("Please enter different course name: ");
					System.out.println("Please enter the course name to be added");
					String Coursename = sc.next();
					try {
						conn.st.executeUpdate("Insert into course values('"+Coursename+"')");
						System.out.println("Added" + Coursename + "Sucessfully");
						
					}
					catch (SQLException a) {
						a.printStackTrace();
					}
					break;
					
				case 2:
					System.out.println("-----------------------------------------------------------");
					System.out.println("Please enter the name of course: ");
					Course =sc.next().toUpperCase();
					while(!course_list.contains(Course.toUpperCase())) {
						System.out.println("Please enter the already existed course to update: ");
						Course =sc.next();
					}
					System.out.println("-----------------------------------------------------------");
					System.out.println("Please choose a new name for the course");
					String newCourse = sc.next().toUpperCase();
					conn.st.executeUpdate("update course set field ='"+newCourse+"' where field = '"+Course+"';");
					System.out.println("Changed Course Name Sucessfully");
					break;
					
				case 3:
					System.out.println("-----------------------------------------------------------");
					System.out.println("Please select a course to delete: ");
					Course =sc.next().toUpperCase();
					while(!course_list.contains(Course.toUpperCase())) {
						System.out.println("No such course are avaliable: ");
						System.out.println("Please select already existed Course to delete: ");
						Course = sc.next().toUpperCase();
					}
					conn.st.executeUpdate("Delete from course where field = '"+Course+"';");
					System.out.println("Course removed Successfully");
					break;
					
				case 4:
					System.out.println("-----------------------------------------------------------");
					new CourseAdmin();
					break;
				
				default:
					System.out.println("Incorrect option\nTaking you back");
					System.out.println("-----------------------------------------------------------");
					courseMain();
					break;
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void InstructorMain() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("List of Instructor with their respective course:");
		try {
			rs = conn.st.executeQuery("select * from Instructor;");
			
			ArrayList mail = new ArrayList();
			ArrayList fullname = new ArrayList();
			ArrayList field1 = new ArrayList();
			ArrayList sub = new ArrayList();
			ArrayList lvl = new ArrayList();
			ArrayList sem = new ArrayList();
			System.out.println("------------------------------------------------------------\n");
			int a = 1;
			while(rs.next()) {
				String email = rs.getString(1);
				String name = rs.getString(2);
				String field = rs.getString(3);
				String subject = rs.getString(4);
				String level = rs.getString(5);
				String semester = rs.getString(6);
				System.out.println("List number: " + a);
				System.out.println("Details on Instructor " + name);
				System.out.println("Assigned Email: " + email);
                System.out.println("Assigned Course: " + field);
                System.out.println("Assigned Module: " + subject);
                System.out.println("Assigned Level: " + level);
                System.out.println("Assigned Semester: " + semester);
                
                mail.add(email);
                fullname.add(name);
                field1.add(field);
                sub.add(subject);
                lvl.add(level);
                sem.add(semester);
                a++;
                System.out.println("----------------------------------------------------------------\n");
			}
			System.out.println("Please Select your Option:");
			System.out.println("1. Add");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. Admin Window");
			int option = sc.nextInt();
			System.out.println("------------------------------------------------------------------");
			switch(option) {
				case 1:
				
					System.out.println("You have selected add option \nPlease Enter the details of the Instructor:");
					System.out.println("Enter the name of Instructor: ");
					String name = sc.nextLine();
					name = sc.nextLine();
					
					System.out.println("------------------------------------------------------------------");
					System.out.println("Enter the email of Instructor: ");
					String email = sc.next();
					System.out.println("------------------------------------------------------------------");
					
					System.out.println("Select a course :");
                    String qur = "Select * from course";
                    rs = conn.st.executeQuery(qur);


                    ArrayList course_list = new ArrayList();
                    while (rs.next()) {
                        course_list.add(rs.getString(1));
                    }
                    for (int i = 0; i < course_list.size(); i++) {
                        System.out.println(course_list.get(i).toString().toUpperCase());
                    }
                    String corse3 = sc.next().toUpperCase();
                    while (!course_list.contains(corse3.toUpperCase())) {
                        System.out.println("The course doesnot exits! ");
                        System.out.println("Please select a course which matches above: ");
                        corse3 = sc.next();
                    }
                    System.out.println("------------------------------------------------------------------");
					
                    System.out.println("Module Avaliable");
					rs = conn.st.executeQuery("Select Name from module");
					ArrayList module = new ArrayList();
					while(rs.next()) {
						module.add(rs.getString(1));
					}
					for (int i = 0; i < module.size(); i++) {
                        System.out.println(module.get(i).toString().toUpperCase());
                    }
					
					System.out.println("------------------------------------------------------------------");
					System.out.println("Please Select a module to assign: ");
					String subject = sc.nextLine().toUpperCase();
					subject = sc.nextLine().toUpperCase();
		
                    while (!module.contains(subject)) {
                        System.out.println("Module not avaliable");
                        System.out.println("Select subject :");
                        subject = sc.nextLine().toUpperCase();
                    }
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Enter the level: ");
                    String stair = sc.next();
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Enter Semester: ");
                    String sim = sc.next();
                    System.out.println("------------------------------------------------------------------");
                    conn.st.executeUpdate("insert into instructor values ('" + email + "','" + name + "','" + corse3 + "','" + subject + "','" + stair + "','" + sim + "');");
                    System.out.println("Added teacher "+ name+" Successfully");
                    System.out.println("------------------------------------------------------------------");
                    InstructorMain();
                    break;
                    
                    
				case 2:
					System.out.println("Please enter a list number of any instructor to update: ");
                    int ch = sc.nextInt();
                    if (ch <= mail.size()) {
                        int coi = ch - 1;
                        String e_mail = (String) mail.get(coi);
                        String full_name = (String) fullname.get(coi);
                        System.out.println("Email " + e_mail);
                        System.out.println("Fullname " + full_name);
                        System.out.println("Enter teacher name to update ");
                        String nam = sc.nextLine();
                        nam = sc.nextLine();
                        conn.st.executeUpdate("update instructor set name = '" + nam + "' where email = '" + e_mail + "';");
                        System.out.println("Teacher name updated Successfully");
                        System.out.println("------------------------------------------------------------------");
                        InstructorMain();
                        
                    } else {
                        System.out.println("Wrong list number");
                        System.out.println("Please try again from the begining");
                        System.out.println("------------------------------------------------------------------");
                        InstructorMain();
                    }
                    break;
                    
				case 3:
					System.out.println("Please select a list number from above to delete ");
                    int cho = sc.nextInt();
                    if (cho <= mail.size()) {
                        int coo = cho - 1;
                        String em = (String) mail.get(coo);
                        String fullnam = (String) fullname.get(coo);
                        System.out.println("Email " + em);
                        System.out.println("Fullname " + fullnam);

                        conn.st.executeUpdate("delete from instructor where email = '" + em + "';");
                        System.out.println("Instructor Deleted Successfully");
                        
                        InstructorMain();

                    } else {
                    	System.out.println("Wrong list number");
                        System.out.println("Please try again from the begining");
                        System.out.println("------------------------------------------------------------------");
                        InstructorMain();
                    }
                    break;
				case 4:
					new CourseAdmin();
					break;
					
                default:
                	System.out.println("Wrong number Please Try again:");
                	InstructorMain();
                	break;
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
			
	}
	
	private void resultMain() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("Welcome to Result panel");
        System.out.println("Enter name of course which you want to view your result: ");
    
        try {
            rs = conn.st.executeQuery("Select * from course");
            ArrayList SQL_course = new ArrayList();
            while (rs.next()) {
                SQL_course.add(rs.getString(1));
            }
            for (int i = 0; i < SQL_course.size(); i++) {
                System.out.println(SQL_course.get(i).toString().toUpperCase());
            }
            Course = sc.next();
            while (!SQL_course.contains(Course.toUpperCase())) {
            	System.out.println("The course doesnot exits! ");
                System.out.println("Please select a course which matches above: ");
                Course = sc.next();
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("Enter level ");
            int lvl = sc.nextInt();
            
            System.out.println("------------------------------------------------------------------");
            System.out.println("enter semester ");
            String sem = sc.next();
            
            System.out.println("------------------------------------------------------------------");
            ArrayList emails = new ArrayList();
            rs = conn.st.executeQuery("Select email from oldstudent where course = '" + Course + "' and Level = '" + lvl + "' and Semester = '" + sem + "';");
            System.out.println("Students currently studying this course are: ");
            while (rs.next()) {
                emails.add(rs.getString(1));
            }
            int a = 1;

            for (int i=0; i<emails.size(); i++){
            	
                System.out.println(a + ". "+emails.get(i));
                a++;
            }
            System.out.println("Enter a number of any student's email from above :");
            int num = sc.nextInt();
            System.out.println("------------------------------------------------------------------");
            if (num<= emails.size()){
                int cols = num-1;
                String emm = (String) emails.get(cols);
                System.out.println("You will get your result soon!! "+ emm);

                rs = conn.st.executeQuery("Select * from result where email = '"+emm+"';");
                String resultText ="Result \nEmail: "+emm +"\n";
                while (rs.next()){

                    resultText += "Full name: "+rs.getString(2)+"\n";
                    resultText += "Marks Obtained: "+rs.getString(8)+"\n";
                }

                try {
                    File myObj = new File(emm+".txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                            System.out.println("Already Available.");
                            System.out.println("Rewriting the data inside the file....");
                            System.out.println("------------------------------------------------------------------");
                    }
                } catch (IOException e) {
                    System.out.println("Error!!!.");
                    e.printStackTrace();
                    System.out.println("------------------------------------------------------------------");
                }


                try {
                    FileWriter myWriter = new FileWriter(emm+".txt");
                    myWriter.write(resultText);
                    myWriter.close();
                    System.out.println("Your result is ready!!.");
                    System.out.println("------------------------------------------------------------------");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    System.out.println("------------------------------------------------------------------");
                }

            }else {
                System.out.println("Provided number does not matches :\nPlease try again");
                new CourseAdmin();
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }
	
	private void moduleMain() {
		
		System.out.println("------------------------------------------------------------------");
		try {
			System.out.println("Modules Currently: ");
			rs = conn.st.executeQuery("select * from module");
			ArrayList name = new ArrayList();
			ArrayList course = new ArrayList();
			ArrayList level = new ArrayList();
			ArrayList semester = new ArrayList();
			int a = 1;
			while (rs.next()) {
                String modul = rs.getString(1);
                String co = rs.getString(2);
                String lvl = rs.getString(3);
                String sem = rs.getString(4);
                System.out.println("Number " + a);
                System.out.println("Module Name: " +modul);
                System.out.println("Course: " + co);
                System.out.println("Level: " + lvl);
                System.out.println("Semester: "+ sem);
                a++;
                name.add(modul);
                semester.add(sem);
                course.add(co);
                level.add(lvl);
                System.out.println("-------------------------------");
            }
			System.out.println("Please select a option below: ");
			System.out.println("1. Add Module");
			System.out.println("2. Update Module");
			System.out.println("3. Delete Module");
			
			int opt = sc.nextInt();
			System.out.println("------------------------------------------------------------------");
			switch(opt) {
			
			case 1:
				System.out.println("You have choose to add a Module: ");
				System.out.println("Please Enter course short form: ");
				String cou = sc.next().toUpperCase();
				
				System.out.println("-------------------------------");
				System.out.println("Please Enter Module Name: ");
				String mod =sc.nextLine();
				mod = sc.nextLine().toUpperCase();
				
				System.out.println("-------------------------------");
				
                System.out.println("Please enter level: ");
                String l = sc.next();
                System.out.println("-------------------------------");
                
                System.out.println("Please enter semester: ");
                String sems = sc.next();
                System.out.println("-------------------------------");
                conn.st.executeUpdate("insert into course values('" + mod + "','" + cou + "','" + l +"','" + sems + "';" );
                System.out.println("Added Module, Course and other information successfully.");
                break;
                
			case 2:
				System.out.println("You have choose to update the Module: ");
				System.out.println("Select any number from above list:");
				int option =sc.nextInt();
				if(option <= name.size()) {
					int ind = option - 1;
					String module = (String) name.get(ind);
					String field = (String) course.get(ind);
					String lvl = (String) level.get(ind);
					String sem = (String) semester.get(ind);
					System.out.println("You have selected "+ module + "to update");
					System.out.println("Course: " + field);
					System.out.println("Level: " + lvl);
					System.out.println("Semester: " + sem);
	                System.out.println("-------------------------------");
	                
					System.out.println("Please Enter new name to update subject ");
					
                    String mode = sc.nextLine();
                    mode = sc.nextLine();

                    conn.st.executeUpdate("update module set Name ='" + mode + "' where Name = '" + module + "' ;");
                    System.out.println("Updated Successfully.");
					
				}
				else {
					System.out.println("Option out of List: ");
					moduleMain();
				}
				
				case 3:
					System.out.println("You have choose to delete the Module: ");
					System.out.println("Select any number from above list:");
					int in = sc.nextInt();
					if(in <= name.size()) {
						int index = in - 1;
						String module = (String) name.get(index);
						String field = (String) course.get(index);
						String lvl = (String) level.get(index);
						String sem = (String) semester.get(index);
						
						System.out.println("below field will be deleted");
						System.out.println("You have selected "+ module + "to update");
						System.out.println("Course: " + field);
						System.out.println("Level: " + lvl);
						System.out.println("Semester: " + sem);
		                System.out.println("-------------------------------");
		                System.out.println("You have selected "+ module + "to update");
						System.out.println("Course: " + field);
						System.out.println("Level: " + lvl);
						System.out.println("Semester: " + sem);
		                System.out.println("-------------------------------");
					}
					else {
						System.out.println("Option out of list: ");
						moduleMain();
					}
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

