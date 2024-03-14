package exercises

object Ex3 extends App {
    // Ex. a.I)
    def positive(n: Int): String = n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    assert(positive(10) == "positive")
    assert(positive(-10) == "negative")

    // Ex. a.II)
    val positiveVal: (Int) => String = n => n match
        case n if n >= 0 => "positive"
        case n if n < 0 => "negative"

    assert(positive(10) == "positive")
    assert(positive(-10) == "negative")

    def testNegativePredicateOnString(neg: (String => Boolean) => (String => Boolean)) = 
        val empty: String => Boolean = _ == ""
        val notEmpty = neg(empty)
        assert(notEmpty("foo"))
        assert(!notEmpty(""))
        assert(notEmpty("foo") && !notEmpty(""))

    // Ex. b.I)
    def neg(predicate: String => Boolean): String => Boolean = s => !predicate(s)
    testNegativePredicateOnString(neg)

    // Ex. b.II)
    val negVal: (String => Boolean) => (String => Boolean) = pred => (s => !pred(s))
    testNegativePredicateOnString(negVal)

    // Ex c
    def negGeneric[X](p: X => Boolean): X => Boolean = s => !p(s)
    testNegativePredicateOnString(negGeneric)

    val isPositive: Int => Boolean = _ > 0
    val isNegativeOrZero: Int => Boolean = negGeneric(isPositive)

    assert(!isNegativeOrZero(10))
    assert(isNegativeOrZero(-10))
    assert(isNegativeOrZero(0))
}