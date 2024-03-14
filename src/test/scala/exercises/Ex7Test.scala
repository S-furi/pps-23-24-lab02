package exercises

import org.junit.*
import org.junit.Assert.*
import exercises.Ex6.gcd

import Ex7.Shape
import Ex7.Shape.*

class Ex7Test:
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