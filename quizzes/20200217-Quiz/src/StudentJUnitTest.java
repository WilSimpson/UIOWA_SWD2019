import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class StudentJUnitTest
{
    private Student student = new Student("", 0);

    @Test
    void testSetName()
    {
        student.setName("Tod");
        assertEquals("Tod", student.getName());
    }

    @Test
    void setAverageZero()
    {
        student.setAverage(0);
        assertEquals(0, student.getAverage());
    }

    @Test
    void setAverageHundred()
    {
        student.setAverage(100);
        assertEquals(100, student.getAverage());
    }
}
