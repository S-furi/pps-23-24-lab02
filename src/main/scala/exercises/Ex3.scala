package exercises

object Ex3 {
    // Ex. a.I)
    def positive(n: Int): String = n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    // Ex. a.II)
    val positiveVal: (Int) => String = n => n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    // Ex. b.I)
    def neg(p: String => Boolean): String => Boolean = s => !p(s)

    // Ex. b.II)
    val negVal: (String => Boolean) => (String => Boolean) = p => (s => !p(s))

    // Ex c
    def negGeneric[X](p: X => Boolean): X => Boolean = s => !p(s)
}