package classFlow;
import java.util.Scanner;
import java.time.LocalDate;

public class ClassFlowUI
{
    private AssignmentManager manager;
 
    
    public ClassFlowUI(AssignmentManager manager)
    {
        this.manager = manager;
    }
    
    public void showError(String message)
    {
        System.out.println(message);
    }
    /*
     * Gets initial input for creating assignments. 
     * loops inputs until an assignment is created
     */
    public void getAssignmentInput()
    {
        boolean added = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!added) {

        System.out.print("Assignment name: ");
        String name = scanner.nextLine();

        System.out.print("Points: ");
        double points = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Score: ");
        double score = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Due date (YYYY-MM-DD): ");
        String dueDateInput = scanner.nextLine();
        LocalDate dueDate = LocalDate.parse(dueDateInput);

        System.out.print("Assignment type: ");
        String assignmentType = scanner.nextLine();

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();
        
            try {
                
                added = manager.addAssignment(name,
                                 points,
                                 score,
                                 dueDate,
                                 assignmentType,
                                 courseCode);
            } 
            catch (IllegalArgumentException e) {
                
                System.out.println(e.getMessage());
                added = false;
            }

        }

    }

    /*
     * Gets input for removeAssignment and passes it AssignmentManager
     */
    public void removeAssignmentInput() {
        
        boolean removed = false;
        String in = "";
        Scanner scanner = new Scanner(System.in);
        
        while (!removed) {
            
            System.out.print("Assignment name: ");
            String name = scanner.nextLine();
            
            System.out.print("Course code: ");
            String courseCode = scanner.nextLine();
            
            try {
                
                manager.removeAssignment(name, courseCode);
                
            }
            catch (IllegalArgumentException e) {
                
                System.out.println(e.getMessage());
                removed = false;
            }
        }
    }
    
    
    /*
     * All of these setter inputs will input course code and assignment name
     * to find correct assignment reference before passing data to Assignment
     * Manager
     */
        
    /*
     * Gets input for creating courses and passes it AssignmentManager
     */
    public void addCourseInput() {
        
    }
    
    /*
     * Gets input for removing courses and passes it AssignmentManager
     */
    public void removeCourseInput() {
        
    }
    
    /*
     * Gets input for setting due date and passes it AssignmentManager
     */
    public void setDueDateInput() {
        
    }
    
    /*
     * Gets input for setting score and passes it AssignmentManager
     */
    public void setScoreInput() {
        
    }
    
    /*
     * Gets input for setting points and passes it AssignmentManager
     */
    public void setPointsInput() {
        
    }
    
    /*
     * Gets input for setting assignment name and passes it AssignmentManager
     */
    public void setAssignmentName() {
        
    }
    
    /*
     * Gets input for setting course code and passes it AssignmentManager
     */
    public void setCourseCode() {
        
    }
    
    /*
     * Gets input for setting assignment type and passes it AssignmentManager
     */
    public void setAssignmentType() {
        
    }
    
    public void displayAllAssignments()
    {
        for (Object obj : AssignmentManager.courseTracker.values())
        {
            Course course = (Course)obj;
            for (Assignment assignment : course.getAssignmentList().values())
            {
                System.out.println("Name: " + assignment.getName());
                System.out.println("Points: " + assignment.getPoints());
                System.out.println("Score: " + assignment.getScore());
                System.out.println("Due Date: " + assignment.getDueDate());
                System.out.println("Type: " + assignment.getType());
                System.out.println("Course Code: " + assignment.getCourseCode());
                System.out.println();
            }
        }
    }
}
