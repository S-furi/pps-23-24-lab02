package exercises

object Ex7 {
    enum Shape:
        case Rectangle(width: Double, height: Double)
        case Circle(radius: Double)
        case Square(edge: Double)

    object Shape:
        def perimeter(s: Shape): Double = s match
            case Rectangle(width, height) => (width * 2) + (height * 2)
            case Circle(radius) => 2 * Math.PI * radius
            case Square(edge) => Math.pow(edge, 2)

        def scale(s: Shape, alpha: Double): Shape = s match
            case Rectangle(width, height) => Rectangle(width * alpha, height * alpha)
            case Circle(radius) => Circle(radius * alpha)
            case Square(edge) => Square(edge * alpha)
}
