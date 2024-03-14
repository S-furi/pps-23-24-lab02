package exercises

import org.junit.*
import org.junit.Assert.*

class Ex5Test:
    def testComposingIntegerFunctions(f: (Int => Int, Int => Int) => (Int => Int)): Unit = {
        val minusOne: Int => Int = _ - 1
        val multiplyByTwo: Int => Int = _ * 2
        val n = 5

        assertEquals(minusOne(multiplyByTwo(n)), f(minusOne, multiplyByTwo)(n))
    }

    @Test def testIntegerCompositionFunction() = {
        this.testComposingIntegerFunctions(Ex5.compose)
    }

    @Test def testGenericCompositionFunction() = {
        this.testComposingIntegerFunctions(Ex5.composeGeneric)
        val isGreaterThanZero: Int => Boolean = _ > 0
        val stringSize: String => Int = _.size
        assertTrue(Ex5.composeGeneric(isGreaterThanZero, stringSize)("foo"))
        assertFalse(Ex5.composeGeneric(isGreaterThanZero, stringSize)(""))
    }
