public class Assignment
{
    private String name;
    private double points;
    private String dueDate;
    private String type;
    private String courseCode;
    
    public Assignment(String name, double points, String dueDate, 
        String type, String courseCode)
    {
        this.name = name;
        this.points = points;
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

    public String getDueDate()
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

}
