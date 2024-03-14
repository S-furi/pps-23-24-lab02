package exercises

import org.junit.*
import org.junit.Assert.*
import exercises.Ex6.gcd

class Ex6Test {
    @Test def testGCDWhenFirstIsGreaterThanSecond() = {
        assertEquals(4, gcd(12, 8))
        assertEquals(7, gcd(14, 7))
    }

    @Test def testGCDWhenSecondIsGreaterThanFirst = {
        assertEquals(4, gcd(8, 12))
        assertEquals(7, gcd(7, 14))
    }
}
