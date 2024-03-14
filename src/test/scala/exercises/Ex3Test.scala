package exercises

import org.junit.*
import org.junit.Assert.*

class Ex3Test:
  @Test def testPositiveUsingDef(): Unit = {
    this.testPositive(Ex3.positive)
  }

  @Test def testPositiveUsingVal(): Unit = {
    this.testPositive(Ex3.positiveVal)
  }

  @Test def testNegUsingDef(): Unit = {
    this.testNegativePredicateOnString(Ex3.neg)
  }

  @Test def testNegUsingVal(): Unit = {
    this.testNegativePredicateOnString(Ex3.negVal)
  }

  @Test def testGenericNeg(): Unit = {
    this.testNegativePredicateOnString(Ex3.negGeneric)
    val isPositive: Int => Boolean = _ > 0
    val isNegativeOrZero: Int => Boolean = Ex3.negGeneric(isPositive)
    assertFalse(isNegativeOrZero(10))
    assertTrue(isNegativeOrZero(-10))
    assertTrue(isNegativeOrZero(0))
  }

  private def testPositive(p: Int => String) = {
    assertEquals("positive", p(10))
    assertEquals("positive", p(0))
    assertEquals("negative", p(-10))
  }

  private def testNegativePredicateOnString(neg: (String => Boolean) => (String => Boolean)) = 
    val empty: String => Boolean = _ == ""
    val notEmpty = neg(empty)
    assert(notEmpty("foo"))
    assert(!notEmpty(""))
    assert(notEmpty("foo") && !notEmpty(""))
