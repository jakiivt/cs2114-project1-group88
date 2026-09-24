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
                + "3) View all  4) Edit Assignment 5) Quit"); 
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
                    System.out.println("Input Coruse Code"); 
                    String cCode = scanner.nextLine();
                    Course c = (Course)manager.courseTracker.get(cCode);

                    
                    System.out.println("Input Assignment Name");
                    String aName = scanner.nextLine();
                    Assignment a = c.assignmentTracker.get(aName);
                    boolean changed = false;
                    
                    while(!changed) {
                        
                        System.out.println("What do you want to change?");
                        System.out.println("1) Assignment Name 2) Points 3) Score"
                            + " 4) Date 5) Type 6) Course Code 7) quit");
                    
                        String c4Choice = scanner.nextLine();
                    
                    
                        switch (c4Choice) {
                            case "1":
                                System.out.println("Input new Assignment name");
                                String name = scanner.nextLine();
                                try {
                                    manager.setName(a, name);
                                    changed = true;
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                
                                break;
                            case "2":
                                System.out.println("Input Points");
                                Double pts = scanner.nextDouble();
                                try {
                                    manager.setPoints(a, pts);
                                    changed = true;
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                changed = true;
                                break;
                            case "3":
                                System.out.println("Input Score");
                                Double score = scanner.nextDouble();
                                try {
                                    manager.setScore(a, score);
                                    changed = true;
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                changed = true;
                                break;
                            case "4":
                                System.out.println("Input new due Date");
                                String date = scanner.nextLine();
                                LocalDate dueDate = LocalDate.parse(date);
                                try {
                                    manager.setDueDate(a, dueDate);
                                    changed = true;
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                changed = true;
                                break;
                            case "5":
                                System.out.println("Input new Assignment Type");
                                String newType = scanner.nextLine();
                                try {
                                    manager.setAssignmentType(a, newType);
                                    changed = true;
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                changed = true;
                                break;
                            case "6":
                                System.out.println("Input new Cousre Code");
                                String newCode = scanner.nextLine();
                                try {
                                    manager.setCourseCode(a, newCode);
                                    changed = true;
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                changed = true;
                                break;
                            case "7":
                                changed = true;
                                break;
                            default: 
                                ui.showError("Please enter 1, 2, 3, 4,"
                                        + " 5, 6, or 7.");
                            }
                        }
                    
                    break;
                case "5": 
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