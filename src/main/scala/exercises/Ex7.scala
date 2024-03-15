package exercises

object Ex7 {
    enum Shape:
        case Rectangle(w: Double, h: Double)
        case Circle(r: Double)
        case Square(e: Double)

    object Shape:
        def perimeter(s: Shape): Double = s match
            case Rectangle(w, h) => (w * 2) + (h * 2)
            case Circle(r) => 2 * Math.PI * r
            case Square(e) => Math.pow(e, 2)

        def scale(s: Shape, alpha: Double): Shape = s match
            case Rectangle(w, h) => Rectangle(w * alpha, h * alpha)
            case Circle(r) => Circle(r * alpha)
            case Square(e) => Square(e * alpha)
}
