package classFlow;
import java.util.*;
import java.time.LocalDate;

// -------------------------------------------------------------------------
/**
 *  Primary driving class of classFlow. Includes all methods pertaining to the
 *  creation and editing of assignments and courses.
 * 
 *  @author Jack Jones (jakii)
 *  @version Sep 24, 2026
 */
public class AssignmentManager{
    
    public static HashMap<String, Object> courseTracker = new HashMap<>();
    
 // Public methods ........................................................
    
    //-------------- Adders and Removers --------------//
    
    /**
     * Handles input from UI and adds assignments to courses
     * @param assignment
     *                  assignment to be added to a course
     * @param courseCode
     *                  code of course assignment is being added under
     *            
     * @return True if and assignment is added, false if not
     * @throws IllegalArgumentException
     */
  //Name, type, points, score, dueDate, courseCode
    public boolean addAssignment(String name, 
                                Double points,
                                Double score, 
                                LocalDate dueDate, 
                                String assignmentType, 
                                String courseCode) 
                                    throws IllegalArgumentException{
        
        //name
        if (name == null) {
            
            throw new IllegalArgumentException("name cannot be null");
        }
        //Points
        if (points == null) {
            
            throw new IllegalArgumentException("Points cannot be null");
        }
        if (points <= 0) {
            
            throw new IllegalArgumentException("Points must be greater than 0");
        }
        //Score
        if (score == null) {
            
            throw new IllegalArgumentException("Score cannot be null");
        }
        if (score < 0) {
            
            throw new IllegalArgumentException("Score must be positive or 0");
        }
        //Due Date
        if (dueDate == null) {
            
            throw new IllegalArgumentException("Due Date cannot be null");
        }
        //Assignment Type (restricted to quiz, exam, homework, and project)
        if (assignmentType == null) {
            
            throw new IllegalArgumentException("Assignment type "
                + "cannot be null");
        }
        if (!assignmentType.equalsIgnoreCase("quiz") &&
            !assignmentType.equalsIgnoreCase("exam") &&
            !assignmentType.equalsIgnoreCase("homework") &&
            !assignmentType.equalsIgnoreCase("project")) {
            
            throw new IllegalArgumentException(
                "Please enter a valid Assignment type. "
                + "Valid assignment types:\n"
                + "- quiz\n"
                + "- exam\n"
                + "- homework\n"
                + "- project");
        }
        //Course Code
        if (courseCode == null) {
            
            throw new IllegalArgumentException("Course code cannot be null");
        }
        if (!(courseTracker.containsKey(courseCode))) {
            
            throw new IllegalArgumentException(courseCode + "does not exist!");
        }
        Assignment toAdd = new Assignment(name, 
                                        points, 
                                        score, 
                                        dueDate, 
                                        assignmentType, 
                                        courseCode);
        //Gets course to add assignment to
        Course toAddAssignment = (Course)courseTracker.get(courseCode);
        //adds assignment to Course's assignment tracker
        toAddAssignment.assignmentTracker.put(name, toAdd);
        return true;
    }
    /**
     * Removes an assignment from a course.
     * @param courseCode
     *              assignment to be removed
     * @param assignmentName
     *              name of course assignment is in
     * @return True if and assignment is removed, false if not
     * @throws IllegalArgumentException
     */
    public boolean removeAssignment(String assignmentName, String courseCode) 
        throws IllegalArgumentException{
        if (courseCode == null) {
            
            throw new IllegalArgumentException("Course code cannot be null");
        }
        if (!(courseTracker.containsKey(courseCode))) {
            
            throw new IllegalArgumentException(courseCode + " does noe exist!");
        }
        
        Course toRemoveAssignment = (Course)courseTracker.get(courseCode);
        if (assignmentName == null) {
            
            throw new IllegalArgumentException("Assignment name "
                                               + "cannot be null");
        }
        if (!(toRemoveAssignment.
            assignmentTracker.
            containsKey(assignmentName))) {
            
            throw new IllegalArgumentException(assignmentName 
                                  + " does not exist in " + courseCode);
        }
        
        toRemoveAssignment.assignmentTracker.remove(assignmentName);
        return true;
    }
    
    /**
     * Adds a new course to courseTracker.
     * @param courseName
     * @param courseCode
     * @param instructor
     *            
     * @return true if added
     * @throws IllegalArgumentException
     */
    public boolean addCourse(String courseName, 
        String courseCode, String instructor) throws IllegalArgumentException{
        //checks for null input
        if (courseName == null) {
            
            throw new IllegalArgumentException("Please enter a course name");
        } 
        if (courseCode == null) {
            
            throw new IllegalArgumentException("Please enter course code") ;
        }
        if (instructor == null) {
            
            throw new IllegalArgumentException("Please enter intructor");
        } 
        if (courseTracker.containsKey(courseCode)) {
            
            throw new IllegalArgumentException(courseCode + " already"
                + " exists!");
        }
        
        Course toAdd = new Course(courseName, courseCode, instructor);
        courseTracker.put(courseCode, toAdd);
        return true;

    }
    
    /**
     * Deletes a course. Can throw a NullPointerException since it directly
     * references a key in courseTracker
     * @param courseCode
     *            
     * @return True if course is removed, false if course is not removed
     * @throws IllegalArgumentException
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
     * @throws IllegalArgumentException
     */
    public boolean setDueDate(Object assignment, LocalDate newDueDate)
    throws IllegalArgumentException{
        
        if(newDueDate == null) {
            
            throw new IllegalArgumentException(
                "Please enter a valid due date");
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
     * @throws IllegalArgumentException
     */
    public boolean setScore(Object assignment, Double recievedScore)
    throws IllegalArgumentException{
        
        if(recievedScore == null) {
            
            throw new IllegalArgumentException(
                "Please enter a score");
        }
        if(recievedScore < 0) {
            
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
        cast.score = recievedScore;
        return true;
            
    }
    
    /**
     * Sets an assignment's points, can throw IllegalArgumentException.
     * exception to be caught by calling class
     * @param assignment, newPoints
     *            
     * @return true if altered, false if not altered
     * @throws IllegalArgumentException
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
    * @throws IllegalArgumentException
    */
    public boolean setName(Object assignment, String newName)
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
        Course currCourse = (Course)courseTracker.get(currCourseCode);
        //Checks for assignment with the same name 
        //(assumes HashMap implementation)
        if (currCourse.assignmentTracker.containsKey(newName)) {
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
    * @throws IllegalArgumentException
    */
    public boolean setCourseCode(Object assignment, String newCourseCode)
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
        String assignmentName = cast.getName();
        String currCourseCode = cast.getCourseCode();
        Course currCourse = (Course)courseTracker.get(currCourseCode);
        Course newCourse = (Course)courseTracker.get(newCourseCode);
        
        // Check for duplicate assignments (Assumes HashMap assignment
        // tracker implementation)
        if (newCourse.assignmentTracker.containsKey(assignmentName)) {
            
            throw new IllegalArgumentException("An assignment with the name "
                + cast.getName() + "already exists in course " + newCourseCode);
        }

        cast.setNewCourseCode(newCourseCode);
        //adds the assignment to the new course and removes it from the old one
        newCourse.assignmentTracker.put(assignmentName, cast);
        currCourse.assignmentTracker.remove(assignmentName);
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
     * @throws IllegalArgumentException
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
        if(!newAssignmentType.equalsIgnoreCase("quiz") &&
            !newAssignmentType.equalsIgnoreCase("exam") &&
            !newAssignmentType.equalsIgnoreCase("homework") &&
            !newAssignmentType.equalsIgnoreCase("project")) {
            
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
     * @throws IllegalArgumentException
     */
    public ArrayList<Object> getAssignmentData(Object assignment) 
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
        ArrayList<Object> assignmentData = new ArrayList<>();
        //Name, type, points, score, dueDate, courseCode
        assignmentData.add(cast.getName());
        assignmentData.add(cast.getType());
        assignmentData.add(cast.getPoints());
        assignmentData.add(cast.getScore());
        assignmentData.add(cast.getDueDate());
        assignmentData.add(cast.getCourseCode());
        return assignmentData;
        
        
    }
    
    /**
     * Gets the list of assignment objects for a specified course
     * @param course
     *              Course to get data from
     *              
     * @return returns an array of all assignment objects for the specified
     * course
     * @throws IllegalArgumentException
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
