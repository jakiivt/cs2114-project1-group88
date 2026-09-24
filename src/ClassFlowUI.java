package classFlow;
import java.util.Scanner;
import java.time.LocalDate;

/*
 *  Responsible for taking user input and passing it to the AssignmentManager
 * 
 *  @author Jack Jones
 *  setters created with the assistance of AI
 *  @version Sep 24, 2026
 */
public class ClassFlowUI
{
    private AssignmentManager manager;

    /*
     * Create a new ClassFlowUI object.
     * @param manager
     */
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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Instructor: ");
        String instructor = scanner.nextLine();

        try {
            manager.addCourse(courseName, courseCode, instructor);
            System.out.println("Course added successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for removing courses and passes it AssignmentManager
     */
    public void removeCourseInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        try {
            boolean removed = manager.removeCourse(courseCode);

            if (removed) {
                System.out.println("Course removed successfully.");
            }
            else {
                System.out.println("Course does not exist.");
            }
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for setting due date and passes it AssignmentManager
     */
    public void setDueDateInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Assignment name: ");
        String assignmentName = scanner.nextLine();

        try {
            Course course = (Course)AssignmentManager.courseTracker.get(courseCode);

            if (course == null) {
                throw new IllegalArgumentException(
                    courseCode + " does not exist!"
                );
            }

            Assignment assignment =
                course.assignmentTracker.get(assignmentName);

            if (assignment == null) {
                throw new IllegalArgumentException(
                    assignmentName + " does not exist in " + courseCode
                );
            }

            System.out.print("New due date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();

            LocalDate newDueDate = LocalDate.parse(dateInput);

            manager.setDueDate(assignment, newDueDate);

            System.out.println("Due date updated successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for setting score and passes it AssignmentManager
     */
    public void setScoreInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Assignment name: ");
        String assignmentName = scanner.nextLine();

        try {
            Course course = (Course)AssignmentManager.courseTracker.get(courseCode);

            if (course == null) {
                throw new IllegalArgumentException(
                    courseCode + " does not exist!"
                );
            }

            Assignment assignment =
                course.assignmentTracker.get(assignmentName);

            if (assignment == null) {
                throw new IllegalArgumentException(
                    assignmentName + " does not exist in " + courseCode
                );
            }

            System.out.print("New score: ");
            double newScore = scanner.nextDouble();

            manager.setScore(assignment, newScore);

            System.out.println("Score updated successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for setting points and passes it AssignmentManager
     */
    public void setPointsInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Assignment name: ");
        String assignmentName = scanner.nextLine();

        try {
            Course course = (Course)AssignmentManager.courseTracker.get(courseCode);

            if (course == null) {
                throw new IllegalArgumentException(
                    courseCode + " does not exist!"
                );
            }

            Assignment assignment =
                course.assignmentTracker.get(assignmentName);

            if (assignment == null) {
                throw new IllegalArgumentException(
                    assignmentName + " does not exist in " + courseCode
                );
            }

            System.out.print("New point value: ");
            double newPoints = scanner.nextDouble();

            manager.setPoints(assignment, newPoints);

            System.out.println("Points updated successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for setting assignment name and passes it AssignmentManager
     */
    public void setAssignmentName() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Current assignment name: ");
        String assignmentName = scanner.nextLine();

        try {
            Course course = (Course)AssignmentManager.courseTracker.get(courseCode);

            if (course == null) {
                throw new IllegalArgumentException(
                    courseCode + " does not exist!"
                );
            }

            Assignment assignment =
                course.assignmentTracker.get(assignmentName);

            if (assignment == null) {
                throw new IllegalArgumentException(
                    assignmentName + " does not exist in " + courseCode
                );
            }

            System.out.print("New assignment name: ");
            String newName = scanner.nextLine();

            manager.setName(assignment, newName);

            System.out.println("Assignment name updated successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for setting course code and passes it AssignmentManager
     */
    public void setCourseCode() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Current course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Assignment name: ");
        String assignmentName = scanner.nextLine();

        try {
            Course course = (Course)AssignmentManager.courseTracker.get(courseCode);

            if (course == null) {
                throw new IllegalArgumentException(
                    courseCode + " does not exist!"
                );
            }

            Assignment assignment =
                course.assignmentTracker.get(assignmentName);

            if (assignment == null) {
                throw new IllegalArgumentException(
                    assignmentName + " does not exist in " + courseCode
                );
            }

            System.out.print("New course code: ");
            String newCourseCode = scanner.nextLine();

            manager.setCourseCode(assignment, newCourseCode);

            System.out.println("Assignment moved successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }


    /*
     * Gets input for setting assignment type and passes it AssignmentManager
     */
    public void setAssignmentType() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Assignment name: ");
        String assignmentName = scanner.nextLine();

        try {
            Course course = (Course)AssignmentManager.courseTracker.get(courseCode);

            if (course == null) {
                throw new IllegalArgumentException(
                    courseCode + " does not exist!"
                );
            }

            Assignment assignment =
                course.assignmentTracker.get(assignmentName);

            if (assignment == null) {
                throw new IllegalArgumentException(
                    assignmentName + " does not exist in " + courseCode
                );
            }

            System.out.print(
                "New assignment type (quiz/exam/homework/project): "
            );
            String newType = scanner.nextLine();

            manager.setAssignmentType(assignment, newType);

            System.out.println("Assignment type updated successfully.");
        }
        catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
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
