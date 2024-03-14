package exercises

object Ex3 extends App {
    // Ex. a.I)
    def positive(n: Int): String = n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    // Ex. a.II)
    val positiveVal: (Int) => String = n => n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    // Ex. b.I)
    def neg(predicate: String => Boolean): String => Boolean = s => !predicate(s)

    // Ex. b.II)
    val negVal: (String => Boolean) => (String => Boolean) = pred => (s => !pred(s))

    // Ex c
    def negGeneric[X](p: X => Boolean): X => Boolean = s => !p(s)
}