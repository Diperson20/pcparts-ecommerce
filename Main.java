package CourseManagementSystem;

import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    public Main(){
        System.out.println("\t\t\t\t\t\t-------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tWelcome to Course Management System");
        System.out.println("\t\t\t\t\t\t\t   Herald International College");
        System.out.println("\t\t\t\t\t\t-------------------------------------------------");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Choose the menu list");
        System.out.println("1. Student");
        System.out.println("2. Instructor");
        System.out.println("3. Course Admin");
        System.out.println("4. Exit Program");
        int choice =  sc.nextInt();
        switch (choice){
            case 1:
                new Student();
                break;
            case 2:
                new Instructor();
                break;
            case 3:
                new CourseAdmin();
                break; 
            case 4:
            	exit();
            default:
            	System.out.println("Invalid Choice !!!\nPlease Enter Again.");
            	new Main();
            
        }
    }
    public void exit() {
    	System.out.println("You have existed the prgram. \nThank you for visiting.");
    	System.exit(0);
    }
    public static void main(String[] args) {
        new Main();
    }
}