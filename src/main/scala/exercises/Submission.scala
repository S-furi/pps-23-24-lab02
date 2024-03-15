package exercises

object Ex3Sol:
    def positive(n: Int): String = n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    val positiveVal: (Int) => String = n => n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    def neg(p: String => Boolean): String => Boolean = s => !p(s)
    val negVal: (String => Boolean) => (String => Boolean) = p => (s => !p(s))
    def negGeneric[X](p: X => Boolean): X => Boolean = s => !p(s)

object Ex4Sol:
    private val predicate: (Int, Int, Int) => Boolean = (x, y, z) => z == y && x <= y
    val p1: Int => Int => Int => Boolean = x => y => z => predicate(x, y, z)
    val p2: (Int, Int, Int) => Boolean = (x, y, z) => predicate(x, y, z)
    def p3(x: Int)(y: Int)(z: Int): Boolean = predicate(x, y, z)
    def p4(x: Int, y: Int, z: Int): Boolean = predicate(x, y, z)

object Ex5Sol:
    def compose(f: Int => Int, g: Int => Int): Int => Int = n => f(g(n))
    /**
      * Output type of `g` must be compatible with `f`'s input type.
      */
    def composeGeneric[A, B, C](f: A => B, g: C => A): C => B = in => f(g(in))

object Ex6Sol:
    @annotation.tailrec
    def gcd(a: Int, b: Int): Int = (a, b) match
        case (a, b) if a == 0 || b == 0 => a
        case (a, b) if a > b => gcd(b, a % b)
        case (a, b) if a < b => gcd(a, b % a)

object Ex7Sol:
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

object Optionals:
  /**
   * Optional is a type that represents a value that may or may not be present.
   * Similar to Optional in Java but using the ADT concept.
   * Therefore, an Optional is a sum type with two cases: Maybe and Empty.
   * Maybe contains the value, and Empty represents the absence of a value.
   *
   * @tparam A
   */
  enum Optional[A]:
    case Maybe(value: A)
    case Empty()

  object Optional:
    /**
     * isEmpty returns true if the optional is Empty, false otherwise.
     * Example:
     *
     * isEmpty(Empty()) == true
     * isEmpty(Maybe(1)) == false
     *
     * @param optional the optional to check
     * @tparam A the type of the optional
     * @return true if the optional is Empty, false otherwise
     */
    def isEmpty[A](optional: Optional[A]): Boolean = optional match
      case Empty() => true
      case _ => false

    /**
     *
     * getOrElse returns the value of the optional if it is Maybe, otherwise it returns the default value.
     * Example:
     * orElse(Maybe(1), 0) == 1
     * orElse(Empty(), 0) == 0
     *
     * @param optional the optional to get the value from
     * @param default the default value to return if the optional is Empty
     * @tparam A the type of the optional
     * @tparam B the type of the default value
     * @return the value of the optional if it is Maybe, otherwise the default value
     */
    def orElse[A, B >: A](optional: Optional[A], default: B): B = optional match
      case Maybe(value) => value
      case Empty() => default

    /**
     * map applies the function f to the value of the optional if it is Maybe, otherwise it returns Empty.
     * Example:
     *
     * map(Maybe(1), (x: Int) => x + 1) == Maybe(2)
     * map(Empty(), (x: Int) => x + 1) == Empty()
     *
     *
     * @param optional the optional to apply the function to
     * @param f the function to apply to the value of the optional
     * @tparam A the type of the optional
     * @tparam B the type of the result of the function
     * @return the result of applying the function to the value of the optional if it is Maybe, otherwise Empty
     */
    def map[A, B](optional: Optional[A])(f: A => B): Optional[B] = optional match
      case Maybe(value) => Maybe(f(value))
      case Empty() => Empty()

    def filter[A](optional: Optional[A])(p: A => Boolean): Optional[A] = optional match
      case Maybe(value) if p(value) => Maybe(value)
      case _ => Empty()