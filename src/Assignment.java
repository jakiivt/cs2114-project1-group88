import java.time.LocalDate;

public class Assignment
{
    private String name;
    private double points;
    private LocalDate dueDate;
    private String type;
    private String courseCode;
    private double score;
    
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
            this.type = type;
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
        return type;
    }
    
    public String getCourseCode()
    {
        return courseCode;
    }
    
    public double getScore()
    {
        return score;
    }

}
