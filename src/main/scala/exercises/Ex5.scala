package exercises

object Ex5 {
    def compose(f: Int => Int, g: Int => Int): Int => Int = n => f(g(n))
    /**
      * Output type of `g` must be compatible with `f`'s input type.
      */
    def composeGeneric[A, B, C](f: A => B, g: C => A): C => B = in => f(g(in))
}