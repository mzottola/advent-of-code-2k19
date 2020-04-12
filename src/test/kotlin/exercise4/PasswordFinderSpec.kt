package exercise4

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class PasswordFinderSpec : Spek({
    describe("basic tests to validate solution 1") {
        listOf(
            Pair(111111, 1),
            Pair(223450, 0),
            Pair(123789, 0),
            Pair(122356, 1),
            Pair(123425, 0),
            Pair(112233, 1),
            Pair(123455, 1),
            Pair(344567, 1),
            Pair(123798, 0)
        ).forEach {
            context("with unique number ${it.first}") {
                val count = PasswordFinder(it.first, it.first).countValidPasswordsInIntervalSolution1()
                it("should be ${it.second}") {
                    assertThat(count).isEqualTo(it.second)
                }
            }
        }
    }

    describe("basic tests to validate solution 2") {
        listOf(
            Pair(112233, 1),
            Pair(123444, 0),
            Pair(111122, 1)
        ).forEach {
            context("with unique number ${it.first}") {
                val count = PasswordFinder(it.first, it.first).countValidPasswordsInIntervalSolution2()
                it("should be ${it.second}") {
                    assertThat(count).isEqualTo(it.second)
                }
            }
        }
    }

    describe("define input") {
        val range = "138307-654504"
        val rangeList = range.split("-").map { it.toInt() }
        context("find solution 1 - valid passwords in range $range") {
            val count = PasswordFinder(rangeList[0], rangeList[1]).countValidPasswordsInIntervalSolution1()
            it("should be equal to 1855") {
                assertThat(count).isEqualTo(1855)
            }
        }
        context("find solution 2 - valid passwords in range $range without large groups") {
            val count = PasswordFinder(rangeList[0], rangeList[1]).countValidPasswordsInIntervalSolution2()
            it("should be equal to 1855") {
                assertThat(count).isEqualTo(1855)
            }
        }
    }
})
