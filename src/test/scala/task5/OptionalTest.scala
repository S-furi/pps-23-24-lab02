package task5

import org.junit.*
import org.junit.Assert.*
import Optionals.*

class OptionalTest:
  @Test def emptyOptionalShouldBeEmpty(): Unit = {
    val empty = Optional.Empty()
    assertTrue(Optional.isEmpty(empty))
  }

  @Test def nonEmptyOptionalShouldNotBeEmpty(): Unit = {
    val nonEmpty = Optional.Maybe(0)
    assertFalse(Optional.isEmpty(nonEmpty))
  }

  @Test def orElseShouldReturnDefaultWhenEmpty(): Unit = {
    val nonEmpty = Optional.Maybe(0)
    assertEquals(0, Optional.orElse(nonEmpty, 1))
  }

  @Test def orElseShouldReturnValueWhenNonEmpty(): Unit = {
    val empty = Optional.Empty()
    assertEquals(1, Optional.orElse(empty, 1))
  }

  /** Task 5 -- Look the behaviour of map operator */
  @Test def mapShouldReturnEmptyWhenEmpty(): Unit = {
    val empty: Optional[Int] = Optional.Empty()
    val result = Optional.map(empty)(_ + 1)
    assertTrue(Optional.isEmpty(result))
  }

  @Test def mapShouldReturnTransformedValueWhenNonEmpty(): Unit = {
    val nonEmpty = Optional.Maybe(0)
    val result = Optional.map(nonEmpty)( _ + 1)
    assertEquals(1, Optional.orElse(result, 1))
  }

  @Test def mapShouldApplyTypesTransformation(): Unit = {
    val opt = Optional.Maybe("foo")
    val result = Optional.map(opt)(_.length())
    val expectedLength = "foo".length()
    assertEquals(expectedLength, Optional.orElse(result, expectedLength))
  }

  @Test def filterOnEmptyShouldReturnEmpty(): Unit = {
    val predicate: Int => Boolean = _ > 0
    val empty = Optional.Empty()
    val filteredValue = Optional.filter(empty)(predicate)
    assertTrue(Optional.isEmpty(filteredValue))
  }

  @Test def filterTruePredicateOnNonEmptyShouldReturnNonEmpty(): Unit = {
    val n = 5
    val predicate: Int => Boolean = _ > 0
    val nonEmpty = Optional.Maybe(n)
    val filteredValue = Optional.filter(nonEmpty)(predicate)
    assertFalse(Optional.isEmpty(filteredValue))
    assertEquals(Optional.Maybe(n), filteredValue)
  }

  @Test def filterFalsePredicateOnNonEmptyShouldReturnEmpty(): Unit = {
    val n = -1 
    val predicate: Int => Boolean = _ > 0
    val nonEmpty = Optional.Maybe(n)
    val filteredValue = Optional.filter(nonEmpty)(predicate)
    assertTrue(Optional.isEmpty(filteredValue))
    assertEquals(Optional.Empty(), filteredValue)
  }