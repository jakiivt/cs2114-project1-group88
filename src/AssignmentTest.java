package src;
public class AssignmentTest
{
    package classFlow;

    import static org.junit.Assert.*;
    import org.junit.Test;
    import java.time.LocalDate;

    public class AssignmentTest
    {
        @Test
        public void testValidAssignment()
        {
            Assignment a = new Assignment("Homework 1", 10, 8,
                LocalDate.of(2026, 9, 16), "homework", "CS2114");
            assertEquals("Homework 1", a.getName());
            assertEquals(10, a.getPoints(), 0.001);
            assertEquals(8, a.getScore(), 0.001);
            assertEquals("homework", a.getType());
            assertEquals("CS2114", a.getCourseCode());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNegativePointsRejected()
        {
            new Assignment("Homework 1", -10, 0,
                LocalDate.of(2026, 9, 16), "homework", "CS2114");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testEmptyNameRejected()
        {
            new Assignment("", 10, 0,
                LocalDate.of(2026, 9, 16), "homework", "CS2114");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testInvalidTypeRejected()
        {
            new Assignment("Homework 1", 10, 0,
                LocalDate.of(2026, 9, 16), "banana", "CS2114");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNegativeScoreRejected()
        {
            new Assignment("Homework 1", 10, -5,
                LocalDate.of(2026, 9, 16), "homework", "CS2114");
        }
  }
