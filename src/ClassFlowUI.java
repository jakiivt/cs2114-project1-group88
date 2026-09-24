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
    
    public Assignment getAssignmentInput()
    {
        Scanner scanner = new Scanner(System.in);

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
        String type = scanner.nextLine();

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        return new Assignment(
            name,
            points,
            score,
            dueDate,
            type,
            courseCode
        );
    }
    
    public void displayAllAssignments()
    {
        for (Course course : manager.courseTracker.values())
        {
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
