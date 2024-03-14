package exercises

import scala.annotation.tailrec

object Ex6 {
    @tailrec
    def gcd(a: Int, b: Int): Int = (a, b) match
        case (a, b) if a == 0 || b == 0 => a
        case (a, b) if a > b => gcd(b, a % b)
        case (a, b) if a < b => gcd(a, b % a)

    println((gcd(12, 8), gcd(14, 7)))
}