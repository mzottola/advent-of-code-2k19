package exercise3

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class ManhattanDistanceWireComputerSpec : Spek({
    describe("basic tests to validate solution 1") {
        listOf(
            Pair(listOf("R8,U5,L5,D3", "U7,R6,D4,L4"), 6),
            Pair(listOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"), 159),
            Pair(listOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), 135)
        ).forEach {
            context("with wire paths ${it.first[0]}, ${it.first[1]}") {
                val distance = ManhattanDistanceWireComputer(it.first).computeCloserDistanceFromOrigin()
                it("should be equal to ${it.second}") {
                    assertThat(distance).isEqualTo(it.second)
                }
            }
        }
    }
    describe("basic tests to validate solution 2") {
        listOf(
            Pair(listOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"), 610),
            Pair(listOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), 410)
        ).forEach {
            context("with wire paths ${it.first[0]}, ${it.first[1]}") {
                val distance = ManhattanDistanceWireComputer(it.first).computeCloserDistanceByPointDistance()
                it("should be equal to ${it.second}") {
                    assertThat(distance).isEqualTo(it.second)
                }
            }
        }
    }
    describe("Read inputs") {
        val wirePaths = ManhattanDistanceWireComputerSpec::class.java.getResource("/input3-1.txt")
            .readText()
            .split("\n")
            .filterNot { it.isBlank() }
            .map { it.trim() }
        context("find solution 1 - compute Manhattan distance") {
            val distance = ManhattanDistanceWireComputer(wirePaths).computeCloserDistanceFromOrigin()
            it("should return distance 1211") {
                assertThat(distance).isEqualTo(1211)
            }
        }

        context("find solution 2 - compute real distance") {
            val closierDistance = ManhattanDistanceWireComputer(wirePaths).computeCloserDistanceByPointDistance()
            it("should return distance 101386") {
                assertThat(closierDistance).isEqualTo(101386)
            }
        }
    }
})
