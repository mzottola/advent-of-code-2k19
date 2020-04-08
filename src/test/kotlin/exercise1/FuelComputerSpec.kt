package exercise1

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FuelComputerSpec : Spek({
    describe("compute necessary fuel given list of masses") {
        val listOfMasses = FuelComputerSpec::class.java.getResource("/input1-1.txt")
            .readText()
            .split("\n")
            .filter { it.isNotBlank() }
            .map(String::toInt)

        context("solution 1: using a naive fuel computer") {
            val totalFuel = FuelComputer(listOfMasses).solution1()
            it("should return 3256114 fuel") {
                assertThat(totalFuel).isEqualTo(3256114);
            }
        }

        context("solution 2: take in account new fuel mass to calculate necessary fuel") {
            val totalFuel = FuelComputer(listOfMasses).solution2()
            it("should return 4881302 fuel") {
                assertThat(totalFuel).isEqualTo(4881302);
            }
        }
    }
})
