package exercise2

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class IntCodeProgramReaderSpec : Spek({
    describe("basic tests to validate solution") {
        listOf(
            Pair("1,0,0,0,99", "2,0,0,0,99"),
            Pair("2,3,0,3,99", "2,3,0,6,99"),
            Pair("2,4,4,5,99,0", "2,4,4,5,99,9801"),
            Pair("1,1,1,4,99,5,6,0,99", "30,1,1,4,2,5,6,0,99")
        ).forEach {
            context("with intcode program ${it.first}") {
                val intCodeProgram = it.first
                    .split(",")
                    .map { it.toInt() }
                    .toIntArray()
                val executedProgram = IntCodeProgramReader(intCodeProgram)
                    .executeProgram()
                    .joinToString(separator = ",") { it.toString() }
                it("should be equal to ${it.second}") {
                    assertThat(executedProgram).isEqualTo(it.second)
                }
            }
        }
    }
    describe("Read inputs") {
        val intCodeProgram = IntCodeProgramReaderSpec::class.java.getResource("/input2-1.txt")
            .readText()
            .split(",")
            .map { it.trim() }
            .map { it.toInt() }
            .toIntArray()
        context("find solution 1 - replace some values according to rules") {
            val executedProgram = IntCodeProgramReader(intCodeProgram)
                .executeProgram { program -> program[1] = 12; program[2] = 2 }
            it("should have at first index the value 5534943") {
                assertThat(executedProgram[0]).isEqualTo(5534943)
            }
        }

        context("find solution 2 - the purpose is to look for corresponding noun and verb") {
            val pair = IntCodeProgramReader(intCodeProgram).findNounAndVerbForValue(19690720)
            it("should respect the answer corresponding to 100 * noun + verb") {
                assertThat(100 * pair.first + pair.second).isEqualTo(7603)
            }
        }
    }
})
