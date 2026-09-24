package classFlow; 
 
import java.time.LocalDate; 
import java.util.Scanner; 
 
// ------------------------------------------------------------------------- 
/** 
*  Entry point. Runs a simple text menu loop so the app is usable end 
*  to end: add a course, add an assignment, view assignments, quit. 
* 
*  @author Group 88 
*  @version Sep 24, 2026 
*/ 
public class Main 
{ 
    public static void main(String[] args) 
    { 
        AssignmentManager manager = new AssignmentManager(); 
        ClassFlowUI ui = new ClassFlowUI(manager); 
        Scanner scanner = new Scanner(System.in); 
 
        // Seed one course so there's somewhere to add assignments to. 
        manager.addCourse("Software Design", "CS2114", "Prof. Shaffer"); 
 
        boolean running = true; 
        while (running) 
        { 
            System.out.println("\n1) Add course  2) Add assignment  " 
                + "3) View all  4) Quit"); 
            System.out.print("Choice: "); 
            String choice = scanner.nextLine(); 
 
            switch (choice) 
            { 
                case "1": 
                    System.out.print("Course name: "); 
                    String courseName = scanner.nextLine(); 
                    System.out.print("Course code: "); 
                    String courseCode = scanner.nextLine(); 
                    System.out.print("Instructor: "); 
                    String instructor = scanner.nextLine(); 
                    try 
                    { 
                        boolean added = manager.addCourse(courseName, 
                            courseCode, instructor); 
                        System.out.println(added ? "Course added." 
                            : "That course code already exists."); 
                    } 
                    catch (IllegalArgumentException e) 
                    { 
                        ui.showError(e.getMessage()); 
                    } 
                    break; 
 
                case "2": 
                    ui.getAssignmentInput();
                    break; 
 
                case "3": 
                    ui.displayAllAssignments(); 
                    break; 
 
                case "4": 
                    running = false; 
                    break; 
 
                default: 
                    ui.showError("Please enter 1, 2, 3, or 4."); 
            } 
        } 
 
        scanner.close(); 
        System.out.println("Goodbye!"); 
    } 
} 