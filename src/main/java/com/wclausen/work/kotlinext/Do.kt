package com.wclausen.work.kotlinext

/**
 * Enables forcing `when` statements to be exhaustive by turning them into expressions
 *
 * Usage:
 *   when ($sealedClassVar) {...}
 *   becomes
 *   Do exhaustive when ($sealedClassVar) {...}
 */
object Do {
    infix fun exhaustive(any: Any?) {
    }
}