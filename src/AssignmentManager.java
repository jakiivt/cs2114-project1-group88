package classFlow;
import java.util.*;
import java.time.LocalDate;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author jack
 *  @version Sep 3, 2026
 */
public class AssignmentManager{
    
    HashMap<String, Object> courseTracker = new HashMap<>();
    
 // Public methods ........................................................
    
    //-------------- Adders and Removers --------------//
    
    /**
     * Adds an assignment to a course.
     * @param assignment
     *                  assignment to be added to a course
     *            
     * @return True if and assignment is added, false if not
     */
    public boolean addAssignment(Object assignment, Object course) 
        throws IllegalArgumentException{
        //Handles input to add assignment to a course
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment");
        }
        
        return false;
    }
    /**
     * Removes an assignment from a course.
     * @param an assignment
     *            
     * @return True if and assignment is removed, false if not
     */
    public boolean removeAssignment() {
        return false;
    }
    
    /**
     * Adds a new course to courseTracker.
     * @param courseName, courseCode, instructor
     *            
     * @return return "Course successfully added" if added
     * return "please enter a course name" if courseName is null
     * return "please enter course code" if courseCode is null
     * return "please enter instructor" if instructor is null
     * return "course already exists" if courseCode is contained in 
     * courseTracker
     */
    
                                                                                //Come back here during testing to try difference between throwing exception
                                                                                //and returning message
    public boolean addCourse(String courseName, 
        String courseCode, String instructor){
        //checks for null input
        if(courseName != null && courseCode != null && instructor != null) {
            //checks if the course already exists
            if(courseTracker.containsKey(courseCode)) {
                return false;
            }
            Course toAdd = new Course(courseName, courseCode, instructor);
            courseTracker.put(courseCode, toAdd);
            return true;
            
        } else if (courseName == null) {
            
            throw new IllegalArgumentException("Please enter a course name");
            
        } else if (courseCode == null) {
            
            throw new IllegalArgumentException("Please enter course code") ;
                
        } else if (instructor == null) {
            
            throw new IllegalArgumentException("Please enter intructor");
        }
        return false;
    }
    
    /**
     * Deletes a course. Can throw a NullPointerException since it directly
     * references a key in courseTracker
     * @param courseCode
     *            
     * @return True if course is removed, false if course is not removed
     */
    public boolean removeCourse(String courseCode){
        //checks for null input
        if (courseCode != null) {
            //checks courseTrakcer for courseCode key
            if (courseTracker.containsKey(courseCode)) {
                
                courseTracker.remove(courseCode);
                return true;
                
            }
            return false;
        }
        throw new IllegalArgumentException("Please enter a course code");
    }
   //-------------- Getters and Setters --------------//
    
    /**
     * Sets an assignment's due date, can throw IllegalArgumentException
     * exception to be caught by calling class
     * @param assignment, newDueDate
     *            
     * @return true if altered, false if not altered
     */
    public boolean setDueDate(Object assignment, String newDueDate)
    throws IllegalArgumentException{                                            //Store dueDate as string for now, convert to LocalDate if time allows
        
        if(newDueDate == null) {
            
            throw new IllegalArgumentException(
                "Please enter a valid due date");                               //define valid due dates later
        }
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (!(assignment instanceof Assignment)) {
                
            throw new IllegalArgumentException(
                "Assignment must be of type Assignment");
                
        } 
        
        Assignment cast = (Assignment)assignment;
        cast.dueDate = newDueDate;
        return true;
            
    }
    
    /**
     * Sets an assignment's score, can throw IllegalArgumentException
     * exception to be caught by calling class
     * @param assignment, score
     *            
     * @return true if set, false if not set
     */
    public boolean setScore(Object assignment, Double score)
    throws IllegalArgumentException{
        
        if(score == null) {
            
            throw new IllegalArgumentException(
                "Please enter a score");
        }
        if(score < 0) {
            
            throw new IllegalArgumentException("Please enter a positive score");
        }
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (!(assignment instanceof Assignment)) {
                
            throw new IllegalArgumentException(
                "Assignment must be of type Assignment");
                
        } 
        
        Assignment cast = (Assignment)assignment;
        cast.recievedScore = score;
        return true;
            
    }
    
    /**
     * Sets an assignment's points, can throw IllegalArgumentException.
     * exception to be caught by calling class
     * @param assignment, newPoints
     *            
     * @return true if altered, false if not altered
     */
    public boolean setPoints(Object assignment, Double newPoints)
    throws IllegalArgumentException{
        
        if(newPoints == null) {
            
            throw new IllegalArgumentException(
                "Please enter a point value");
        }
        if(newPoints < 1) {
            
            throw new IllegalArgumentException(
                "Please enter a valid point value");
        }
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (!(assignment instanceof Assignment)) {
                
            throw new IllegalArgumentException(
                "Assignment must be of type Assignment");
                
        } 
        
        Assignment cast = (Assignment)assignment;
        cast.points = newPoints;
        return true;
            
    }
    
    /**
    * Used to rename assignments. Replaces an assignment's current name with
    * newName
    * @param assignment, newName
    *            
    * @return true if set, false if not set
    */
    public Assignment setName(Object assignment, String newName)
    throws IllegalArgumentException{
        
        if(newName == null) {
            
            throw new IllegalArgumentException(
                "Please enter a name");
        }
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (!(assignment instanceof Assignment)) {
                
            throw new IllegalArgumentException(
                "Assignment must be of type Assignment");
                
        } 
        
        Assignment cast = (Assignment)assignment;
        String currCourseCode = cast.courseCode;
        Course currCourse = courseTracker.get(currCourseCode);
        //Checks for assignment with the same name 
        //(assumes HashMap implementation)
        if (currCourse.assignmentTracker.containsKey(newName)) {                //Revise if assignment list is implemented differently
            throw new IllegalArgumentException("There is already an"
                + "Assignment in this course with the name " + newName);
        }
        
        cast.name = newName;
        return true;
            
    }
    
    /**
    * Moves an assignment from one course to another. Can throw
    * IllegalArgumentException. Exception handled by calling class.
    * newName
    * @param assignment, newName
    *            
    * @return true if set, false if not set
    */
    public Assignment setCourseCode(Object assignment, String newCourseCode)
    throws IllegalArgumentException{                                            //
        
        if(newCourseCode == null) {
            
            throw new IllegalArgumentException(
                "Please enter a Course Code");
        }
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (!(assignment instanceof Assignment)) {
                
            throw new IllegalArgumentException(
                "Assignment must be of type Assignment");
                
        } 
        if (!courseTracker.containsKey(newCourseCode)) {
            
            throw new IllegalArgumentException(
                newCourseCode + " does not exist");
        }
        
        Assignment cast = (Assignment)assignment;
        String assignmentName = cast.name;
        String currCourseCode = cast.courseCode;
        Course currCourse = courseTracker.get(currCourseCode);
        Course newCourse = courseTracker.get(newCourseCode);
        
        // Check for duplicate assignments (Assumes HashMap assignment
        // tracker implementation)
        if (newCourse.assignmentTracker.containsKey(assignmentName)) {
            
            throw new IllegalArgumentException("An assignment with the name "
                + cast.name + "already exists in course " + newCourseCode);
        }
        
        //newCourse.assignmentTracker.put(assignmentName, cast);
        //currCourse.assignmentTracker.remove(assignmentName);
        
        //adds the assignment to the new course and removes it from the old one
        addAssignment(newCourse, cast);
        removeAssignment(currCourse, cast);
        return true;
            
    }
    
    /**
     * Sets an assignment's type, can throw IllegalArgumentException.
     * exception to be caught by calling class. Valid types are quiz, exam,
     * homework, and project.
     * @param assignment
     *              Assignment to be altered
     *              
     * @param newAssignmentType
     *              new Assignment Type
     *              
     * @return true if altered, false if not altered
     */
    public boolean setAssignmentType(Object assignment, 
        String newAssignmentType) throws IllegalArgumentException{
        
        if(newAssignmentType == null) {
            
            throw new IllegalArgumentException(
                "Please enter an assignment type");
        }
        if (assignment == null) {
            
            throw new IllegalArgumentException("Assignment cannot be null");
        }
        if (!(assignment instanceof Assignment)) {
                
            throw new IllegalArgumentException(
                "Assignment must be of type Assignment");
                
        } 
        if(newAssignmentType.toLowerCase() != "quiz" &&
            newAssignmentType.toLowerCase() != "exam" &&
            newAssignmentType.toLowerCase() != "homework" &&
            newAssignmentType.toLowerCase() != "project") {
            
            throw new IllegalArgumentException(
                "Please enter a valid Assignment type. "
                + "Valid assignment types:\n"
                + "- quiz\n"
                + "- exam\n"
                + "- homework\n"
                + "- project");
        }
        
        Assignment cast = (Assignment)assignment;
        cast.assignmentType = newAssignmentType;
        return true;
            
    }
    
    /**
     * Gets the name and course of an assignment as strings
     * @param assignment
     *              Assignment to get data from
     *              
     * @param newAssignmentType
     *              new Assignment Type
     *              
     * @return Returns assignment data in the form "name, course"
     */
    public String getAssignmentData(Object assignment) 
        throws IllegalArgumentException{
        if (assignment == null) {
            throw new IllegalArgumentException("Cannot get data of null"
                + " assignment");
        }
        if (!(assignment instanceof Assignment)) {
            throw new IllegalArgumentException("Assignment must be of type"
                + " Assignment");
        }
        
        Assignment cast = (Assignment)assignment;
        
        return cast.getName() + ", " + cast.getCourseCode();
    }
    
    /**
     * Gets the list of assignment objects for a specified course
     * @param course
     *              Course to get data from
     *              
     * @return returns an array of all assignment objects for the specified
     * course
     */
    public ArrayList<Assignment> getAssignmentList(Object course) 
        throws IllegalArgumentException{
        
        if (course == null) {
            throw new IllegalArgumentException("Cannot get data of null"
                + " course");
        }
        if (!(course instanceof Course)) {
            throw new IllegalArgumentException("Course must be of type"
                + " Course");
        }
        
        Course cast = (Course)course;
        
        if (cast.assignmentTracker.containsValue(null)) {
            throw new IllegalArgumentException("At least one of the"
                + "assignments in " + cast.getCourseCode() + " is null");
        }
        //converts course assignmentTracker values into an ArrayList
        ArrayList<Assignment> assignmentList = new 
            ArrayList<>(cast.assignmentTracker.values());
        
        return assignmentList;
    }
    
}