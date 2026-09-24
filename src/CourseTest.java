package src;
public class CourseTest
{
    package classFlow;

    import static org.junit.Assert.*;
    import org.junit.Test;

    public class CourseTest
    {
        @Test
        public void testValidCourse()
        {
            Course c = new Course("Software Design", "CS2114", "Dr. Smith");
            assertEquals("Software Design", c.getCourseName());
            assertEquals("CS2114", c.getCourseCode());
            assertEquals("Dr. Smith", c.getInstructor());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testEmptyCourseNameRejected()
        {
            new Course("", "CS2114", "Dr. Smith");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNullCourseCodeRejected()
        {
            new Course("Software Design", null, "Dr. Smith");
        }
    }
}
