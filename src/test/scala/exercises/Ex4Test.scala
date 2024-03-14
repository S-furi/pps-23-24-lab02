package exercises

import org.junit.*
import org.junit.Assert.*

class Ex4Test {
    private val x = 10
    private val y = 20
    private val z = y

    @Test def testP1() = {
        this.testCurriedFun(Ex4.p1)
    }

    @Test def testP2() = {
        this.testNonCurriedFun(Ex4.p2)
    }

    @Test def testP3() = {
        this.testCurriedFun(Ex4.p3)
    }

    @Test def testP4() = {
        this.testNonCurriedFun(Ex4.p4)
    }

    private def testCurriedFun(f: Int => Int => Int => Boolean) = {
        assertTrue(f(x)(y)(z))
        // make condition (z == y) fail
        assertFalse(f(x)(y)(99))
        // make condition (x <= y) fail
        assertFalse(f(99)(y)(z))
    }

    private def testNonCurriedFun(f: (Int, Int, Int) => Boolean) = {
        assertTrue(f(x, y, z))
        // make condition (z == y) fail
        assertFalse(f(x, y, 99))
        // make condition (x <= y) fail
        assertFalse(f(99, y, z))
    }
}
