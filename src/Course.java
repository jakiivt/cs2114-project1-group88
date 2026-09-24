package classFlow;

import java.util.HashMap;

// -------------------------------------------------------------------------
/**
 *  Represents a single course. Stores course info plus the assignments
 *  that belong to it, keyed by assignment name for fast lookup
 *  (AssignmentManager checks for duplicate names/moves assignments by name).
 *
 *  @author tanush
 *  @version Sep 24, 2026
 */
public class Course
{
    private String courseName;
    private String courseCode;
    private String instructor;

    // package-private (no "private") on purpose — AssignmentManager
    // accesses this directly as a field (e.g. currCourse.assignmentTracker)
    HashMap<String, Assignment> assignmentTracker = new HashMap<>();

    public Course(String courseName, String courseCode, String instructor)
    {
        if (courseName == null)
        {
            throw new IllegalArgumentException("Please enter a course name");
        }
        if (courseCode == null)
        {
            throw new IllegalArgumentException("Please enter course code");
        }
        if (instructor == null)
        {
            throw new IllegalArgumentException("Please enter instructor");
        }

        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getCourseCode()
    {
        return courseCode;
    }

    public String getInstructor()
    {
        return instructor;
    }

    /**
     * Returns this course's assignments, keyed by assignment name.
     * ClassFlowUI calls .values() on this result to loop through them.
     */
    public HashMap<String, Assignment> getAssignmentList()
    {
        return assignmentTracker;
    }

    /**
     * Adds an assignment to this course's tracker.
     * @return true if added, false if an assignment with that name
     * already exists in this course
     */
    public boolean addAssignment(Assignment assignment)
    {
        if (assignment == null)
        {
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (assignmentTracker.containsKey(assignment.getName()))
        {
            return false;
        }
        assignmentTracker.put(assignment.getName(), assignment);
        return true;
    }

    /**
     * Removes an assignment from this course's tracker by name.
     * @return true if removed, false if no assignment with that name existed
     */
    public boolean removeAssignment(Assignment assignment)
    {
        if (assignment == null)
        {
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        return assignmentTracker.remove(assignment.getName()) != null;
    }
}
