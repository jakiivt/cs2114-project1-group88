package classFlow;

import java.time.LocalDate;
import java.util.ArrayList;
import student.TestCase;

/**
 * Tests the AssignmentManager class.
 *
 * @author Jack Jones
 * Created with the assistance of AI
 * @version Sep 24, 2026
 */
public class AssignmentManagerTest extends TestCase {

    private AssignmentManager manager;
    private Course course;
    private Assignment assignment;
    private LocalDate dueDate;


    /**
     * Sets up the objects needed before every test.
     */
    public void setUp() {
        AssignmentManager.courseTracker.clear();

        manager = new AssignmentManager();

        dueDate = LocalDate.of(2026, 10, 15);

        course = new Course(
            "Software Design",
            "CS2114",
            "Professor Smith"
        );

        AssignmentManager.courseTracker.put("CS2114", course);

        assignment = new Assignment(
            "Project 1",
            100.0,
            90.0,
            dueDate,
            "project",
            "CS2114"
        );

        course.assignmentTracker.put("Project 1", assignment);
    }


    // ----------------------------------------------------------
    // addAssignment
    // ----------------------------------------------------------

    /**
     * Tests adding a valid assignment.
     */
    public void testAddAssignment() {
        assertTrue(manager.addAssignment(
            "Homework 1",
            50.0,
            45.0,
            LocalDate.of(2026, 10, 20),
            "homework",
            "CS2114"
        ));

        assertTrue(
            course.assignmentTracker.containsKey("Homework 1")
        );
    }


    /**
     * Tests null assignment name.
     */
    public void testAddAssignmentNullName() {
        Exception exception = null;

        try {
            manager.addAssignment(
                null,
                50.0,
                45.0,
                dueDate,
                "homework",
                "CS2114"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests null points.
     */
    public void testAddAssignmentNullPoints() {
        Exception exception = null;

        try {
            manager.addAssignment(
                "Homework",
                null,
                45.0,
                dueDate,
                "homework",
                "CS2114"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests invalid points.
     */
    public void testAddAssignmentInvalidPoints() {
        Exception exception = null;

        try {
            manager.addAssignment(
                "Homework",
                0.0,
                45.0,
                dueDate,
                "homework",
                "CS2114"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests a negative score.
     */
    public void testAddAssignmentNegativeScore() {
        Exception exception = null;

        try {
            manager.addAssignment(
                "Homework",
                50.0,
                -1.0,
                dueDate,
                "homework",
                "CS2114"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests invalid assignment type.
     */
    public void testAddAssignmentInvalidType() {
        Exception exception = null;

        try {
            manager.addAssignment(
                "Homework",
                50.0,
                40.0,
                dueDate,
                "essay",
                "CS2114"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests adding an assignment to a nonexistent course.
     */
    public void testAddAssignmentMissingCourse() {
        Exception exception = null;

        try {
            manager.addAssignment(
                "Homework",
                50.0,
                40.0,
                dueDate,
                "homework",
                "CS9999"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // removeAssignment
    // ----------------------------------------------------------

    /**
     * Tests removing an assignment.
     */
    public void testRemoveAssignment() {
        assertTrue(
            manager.removeAssignment("CS2114", "Project 1")
        );

        assertFalse(
            course.assignmentTracker.containsKey("Project 1")
        );
    }


    /**
     * Tests removing a nonexistent assignment.
     */
    public void testRemoveAssignmentDoesNotExist() {
        Exception exception = null;

        try {
            manager.removeAssignment(
                "CS2114",
                "Fake Assignment"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests a null course code.
     */
    public void testRemoveAssignmentNullCourse() {
        Exception exception = null;

        try {
            manager.removeAssignment(null, "Project 1");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // addCourse
    // ----------------------------------------------------------

    /**
     * Tests adding a new course.
     *
     * NOTE: AssignmentManager.addCourse() must have its
     * containsKey check corrected for this test to pass.
     */
    public void testAddCourse() {
        assertTrue(manager.addCourse(
            "Intro to Computer Science",
            "CS1114",
            "Professor Jones"
        ));

        assertTrue(
            AssignmentManager.courseTracker
                .containsKey("CS1114")
        );
    }


    /**
     * Tests null course name.
     */
    public void testAddCourseNullName() {
        Exception exception = null;

        try {
            manager.addCourse(
                null,
                "CS1114",
                "Professor Jones"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests null course code.
     */
    public void testAddCourseNullCode() {
        Exception exception = null;

        try {
            manager.addCourse(
                "Intro to CS",
                null,
                "Professor Jones"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests null instructor.
     */
    public void testAddCourseNullInstructor() {
        Exception exception = null;

        try {
            manager.addCourse(
                "Intro to CS",
                "CS1114",
                null
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // removeCourse
    // ----------------------------------------------------------

    /**
     * Tests removing an existing course.
     */
    public void testRemoveCourse() {
        assertTrue(manager.removeCourse("CS2114"));

        assertFalse(
            AssignmentManager.courseTracker
                .containsKey("CS2114")
        );
    }


    /**
     * Tests removing a course that does not exist.
     */
    public void testRemoveCourseDoesNotExist() {
        assertFalse(manager.removeCourse("CS9999"));
    }


    /**
     * Tests null course code.
     */
    public void testRemoveCourseNull() {
        Exception exception = null;

        try {
            manager.removeCourse(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // setDueDate
    // ----------------------------------------------------------

    /**
     * Tests changing the due date.
     */
    public void testSetDueDate() {
        LocalDate newDate =
            LocalDate.of(2026, 11, 1);

        assertTrue(
            manager.setDueDate(assignment, newDate)
        );

        assertEquals(
            newDate,
            assignment.getDueDate()
        );
    }


    /**
     * Tests a null due date.
     */
    public void testSetDueDateNull() {
        Exception exception = null;

        try {
            manager.setDueDate(assignment, null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests a null assignment.
     */
    public void testSetDueDateNullAssignment() {
        Exception exception = null;

        try {
            manager.setDueDate(null, dueDate);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests passing the wrong object type.
     */
    public void testSetDueDateWrongType() {
        Exception exception = null;

        try {
            manager.setDueDate("Not Assignment", dueDate);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // setScore
    // ----------------------------------------------------------

    /**
     * Tests changing an assignment score.
     */
    public void testSetScore() {
        assertTrue(
            manager.setScore(assignment, 95.0)
        );

        assertEquals(
            95.0,
            assignment.getScore(),
            0.001
        );
    }


    /**
     * Tests score of zero.
     */
    public void testSetScoreZero() {
        assertTrue(
            manager.setScore(assignment, 0.0)
        );

        assertEquals(
            0.0,
            assignment.getScore(),
            0.001
        );
    }


    /**
     * Tests a negative score.
     */
    public void testSetScoreNegative() {
        Exception exception = null;

        try {
            manager.setScore(assignment, -1.0);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests a null score.
     */
    public void testSetScoreNull() {
        Exception exception = null;

        try {
            manager.setScore(assignment, null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // setPoints
    // ----------------------------------------------------------

    /**
     * Tests changing assignment points.
     */
    public void testSetPoints() {
        assertTrue(
            manager.setPoints(assignment, 150.0)
        );

        assertEquals(
            150.0,
            assignment.getPoints(),
            0.001
        );
    }


    /**
     * Tests invalid assignment points.
     */
    public void testSetPointsInvalid() {
        Exception exception = null;

        try {
            manager.setPoints(assignment, 0.0);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests null assignment points.
     */
    public void testSetPointsNull() {
        Exception exception = null;

        try {
            manager.setPoints(assignment, null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // setName
    // ----------------------------------------------------------

    /**
     * Tests changing an assignment name.
     */
    public void testSetName() {
        assertTrue(
            manager.setName(assignment, "Project One")
        );

        assertEquals(
            "Project One",
            assignment.getName()
        );
    }


    /**
     * Tests null assignment name.
     */
    public void testSetNameNull() {
        Exception exception = null;

        try {
            manager.setName(assignment, null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests changing a name to one already used.
     */
    public void testSetNameDuplicate() {
        Assignment second =
            new Assignment(
                "Project 2",
                100.0,
                80.0,
                dueDate,
                "project",
                "CS2114"
            );

        course.assignmentTracker.put(
            "Project 2",
            second
        );

        Exception exception = null;

        try {
            manager.setName(
                assignment,
                "Project 2"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // setCourseCode
    // ----------------------------------------------------------

    /**
     * Tests moving an assignment between courses.
     */
    public void testSetCourseCode() {
        Course secondCourse =
            new Course(
                "Data Structures",
                "CS3114",
                "Professor Doe"
            );

        AssignmentManager.courseTracker.put(
            "CS3114",
            secondCourse
        );

        assertTrue(
            manager.setCourseCode(
                assignment,
                "CS3114"
            )
        );

        assertEquals(
            "CS3114",
            assignment.getCourseCode()
        );

        assertFalse(
            course.assignmentTracker
                .containsKey("Project 1")
        );

        assertTrue(
            secondCourse.assignmentTracker
                .containsKey("Project 1")
        );
    }


    /**
     * Tests moving to a nonexistent course.
     */
    public void testSetCourseCodeInvalid() {
        Exception exception = null;

        try {
            manager.setCourseCode(
                assignment,
                "CS9999"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // setAssignmentType
    // ----------------------------------------------------------

    /**
     * Tests changing assignment type.
     */
    public void testSetAssignmentType() {
        assertTrue(
            manager.setAssignmentType(
                assignment,
                "exam"
            )
        );

        assertEquals(
            "exam",
            assignment.getType()
        );
    }


    /**
     * Tests case-insensitive assignment types.
     */
    public void testSetAssignmentTypeCase() {
        assertTrue(
            manager.setAssignmentType(
                assignment,
                "QUIZ"
            )
        );

        assertEquals(
            "QUIZ",
            assignment.getType()
        );
    }


    /**
     * Tests invalid assignment type.
     */
    public void testSetAssignmentTypeInvalid() {
        Exception exception = null;

        try {
            manager.setAssignmentType(
                assignment,
                "essay"
            );
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // getAssignmentData
    // ----------------------------------------------------------

    /**
     * Tests retrieving assignment information.
     */
    public void testGetAssignmentData() {
        ArrayList<Object> data =
            manager.getAssignmentData(assignment);

        assertEquals(6, data.size());
        assertEquals("Project 1", data.get(0));
        assertEquals("project", data.get(1));
        assertEquals(100.0, data.get(2));
        assertEquals(90.0, data.get(3));
        assertEquals(dueDate, data.get(4));
        assertEquals("CS2114", data.get(5));
    }


    /**
     * Tests getting data from null.
     */
    public void testGetAssignmentDataNull() {
        Exception exception = null;

        try {
            manager.getAssignmentData(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests getting data from an invalid object.
     */
    public void testGetAssignmentDataWrongType() {
        Exception exception = null;

        try {
            manager.getAssignmentData("hello");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    // getAssignmentList
    // ----------------------------------------------------------

    /**
     * Tests getting the assignments from a course.
     */
    public void testGetAssignmentList() {
        ArrayList<Assignment> assignments =
            manager.getAssignmentList(course);

        assertEquals(1, assignments.size());

        assertTrue(
            assignments.contains(assignment)
        );
    }


    /**
     * Tests getting an empty assignment list.
     */
    public void testGetAssignmentListEmpty() {
        course.assignmentTracker.clear();

        ArrayList<Assignment> assignments =
            manager.getAssignmentList(course);

        assertTrue(assignments.isEmpty());
    }


    /**
     * Tests passing null as the course.
     */
    public void testGetAssignmentListNull() {
        Exception exception = null;

        try {
            manager.getAssignmentList(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests passing the wrong object type.
     */
    public void testGetAssignmentListWrongType() {
        Exception exception = null;

        try {
            manager.getAssignmentList("CS2114");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }
}
