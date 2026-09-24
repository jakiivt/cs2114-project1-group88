package classFlow;
import java.time.LocalDate;

public class Assignment
{
    String name;
    double points;
    LocalDate dueDate;
    String assignmentType;
    String courseCode;
    double score;
    
    public Assignment(String name, double points, double score, LocalDate dueDate, 
        String type, String courseCode)
    {
            if (name == null)
            {
                throw new IllegalArgumentException();
            }

            if (points < 1)
            {
                throw new IllegalArgumentException();
            }

            if (score < 0)
            {
                throw new IllegalArgumentException();
            }

            if (!type.equalsIgnoreCase("quiz")
                && !type.equalsIgnoreCase("exam")
                && !type.equalsIgnoreCase("homework")
                && !type.equalsIgnoreCase("project"))
            {
                throw new IllegalArgumentException();
            }
        
            this.name = name;
            this.points = points;
            this.score = score;
            this.dueDate = dueDate;
            this.assignmentType = type;
            this.courseCode = courseCode;
    }
    
    public String getName()
    {
        return name;
    }

    public double getPoints()
    {
        return points;
    }

    public LocalDate getDueDate()
    {
        return dueDate;
    }

    public String getType()
    {
        return assignmentType;
    }
    
    public String getCourseCode()
    {
        return courseCode;
    }
    
    public double getScore()
    {
        return score;
    }
    
    /*
     * Sets a new course code attribute. Called by AssignmentManager class in 
     * the setCourseCode method
     * @param newCourseCode
     *                      course code to replace the old one.
     */
    public void setNewCourseCode(String newCourseCode) {
        this.courseCode = newCourseCode;
    }

}
