package exercises

import org.junit.*
import org.junit.Assert.*

class Ex3TestSol:
    import Ex3Sol.*

    @Test def testPositiveUsingDef(): Unit = {
        this.testPositive(positive)
    }

    @Test def testPositiveUsingVal(): Unit = {
        this.testPositive(positiveVal)
    }

    @Test def testNegUsingDef(): Unit = {
        this.testNegativePredicateOnString(neg)
    }

    @Test def testNegUsingVal(): Unit = {
        this.testNegativePredicateOnString(negVal)
    }

    @Test def testGenericNeg(): Unit = {
        this.testNegativePredicateOnString(negGeneric)
        val isPositive: Int => Boolean = _ > 0
        val isNegativeOrZero: Int => Boolean = negGeneric(isPositive)
        assertFalse(isNegativeOrZero(10))
        assertTrue(isNegativeOrZero(-10))
        assertTrue(isNegativeOrZero(0))
    }

    private def testPositive(p: Int => String) = {
        assertEquals("positive", p(10))
        assertEquals("positive", p(0))
        assertEquals("negative", p(-10))
    }

    private def testNegativePredicateOnString(neg: (String => Boolean) => (String => Boolean)) = {
        val empty: String => Boolean = _ == ""
        val notEmpty = neg(empty)
        assert(notEmpty("foo"))
        assert(!notEmpty(""))
        assert(notEmpty("foo") && !notEmpty(""))
    }

class Ex4TestSol:
    import Ex4Sol.*
    private val x = 10
    private val y = 20
    private val z = y

    @Test def testP1() = {
        this.testCurriedFun(p1)
    }

    @Test def testP2() = {
        this.testNonCurriedFun(p2)
    }

    @Test def testP3() = {
        this.testCurriedFun(p3)
    }

    @Test def testP4() = {
        this.testNonCurriedFun(p4)
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

class Ex5TestSol:
    import Ex5Sol.*
    def testComposingIntegerFunctions(f: (Int => Int, Int => Int) => (Int => Int)): Unit = {
        val minusOne: Int => Int = _ - 1
        val multiplyByTwo: Int => Int = _ * 2
        val n = 5

        assertEquals(minusOne(multiplyByTwo(n)), f(minusOne, multiplyByTwo)(n))
    }

    @Test def testIntegerCompositionFunction() = {
        this.testComposingIntegerFunctions(compose)
    }

    @Test def testGenericCompositionFunction() = {
        this.testComposingIntegerFunctions(composeGeneric)
        val isGreaterThanZero: Int => Boolean = _ > 0
        val stringSize: String => Int = _.size
        assertTrue(composeGeneric(isGreaterThanZero, stringSize)("foo"))
        assertFalse(composeGeneric(isGreaterThanZero, stringSize)(""))
    }

class Ex6TestSol:
    import Ex6Sol.*
    @Test def testGCDWhenFirstIsGreaterThanSecond() = {
        assertEquals(4, gcd(12, 8))
        assertEquals(7, gcd(14, 7))
    }

    @Test def testGCDWhenSecondIsGreaterThanFirst = {
        assertEquals(4, gcd(8, 12))
        assertEquals(7, gcd(7, 14))
    }

class Ex7TestSol:
    import Ex7Sol.Shape
    import Ex7Sol.Shape.*

    private val r: Rectangle = Rectangle(10.0, 20.0)
    private val c: Circle = Circle(10.0)
    private val s: Square = Square(2.0)
    private val scalingFactor: Double = 2.0

    def checkShapeMatching(s: Shape) = s match
        case Rectangle(_, _) => true
        case Circle(_) => true
        case Square(_) => true

    @Test def checkShapesCreation() = {
        assertTrue(checkShapeMatching(r))
        assertTrue(checkShapeMatching(c))
        assertTrue(checkShapeMatching(s))
    }

    @Test def checkRectangleDimensions() = r match
        case Rectangle(width, height) => assertDoublesAreEquals(10.0, width); assertDoublesAreEquals(20.0, height)

    @Test def checkCircleRadius() = c match
        case Circle(radius) => assertDoublesAreEquals(10.0, radius)

    @Test def checkSquareEdge() = s match
        case Square(edge) => assertDoublesAreEquals(2.0, edge)

    @Test def checkRectanglePerimeter() = r match
        case Rectangle(width, height) => {
            val expectedPerimeter = (width * 2) + (height * 2)
            assertDoublesAreEquals(expectedPerimeter, perimeter(r))
        }

    @Test def checkCirclePerimeter() = c match
        case Circle(radius) => {
            val expectedPerimeter = 2 * Math.PI * radius
            assertDoublesAreEquals(expectedPerimeter, perimeter(c))
        }

    @Test def checkSquarePerimeter() = s match
        case Square(edge) => {
            val expectedPerimeter = Math.pow(edge, 2)
            assertDoublesAreEquals(expectedPerimeter, perimeter(s))
        }

    @Test def checkRectangleScaling() = r match
        case Rectangle(width, height) => {
            val expectedScaledRectangle = Rectangle(width * scalingFactor, height * scalingFactor)
            assertEquals(expectedScaledRectangle, scale(r, scalingFactor))
        }

    @Test def checkCircleScaling() = c match
        case Circle(radius) => {
            val expectedScaledCircle = Circle(radius * scalingFactor)
            assertEquals(expectedScaledCircle, scale(c, scalingFactor))
        }

    @Test def checkSquareScaling() = s match
        case Square(edge) => {
            val expectedScaledSquare = Square(edge * scalingFactor)
            assertEquals(expectedScaledSquare, scale(s, scalingFactor))
        }

    private def assertDoublesAreEquals(first: Double, second: Double) = {
        val doubleDelta: Double = 0.0001
        assertEquals(first, second, doubleDelta)
    }

class OptionalTestSol:
    import Optionals.*
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