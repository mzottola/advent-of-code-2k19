package exercise1

import java.util.ArrayDeque

class FuelComputer(private val listOfMasses: List<Int>) {

    fun solution1(): Int {
        return listOfMasses
            .map { it.div(3) }
            .map { it.minus(2) }
            .reduce { acc, i -> acc + i }
    }

    fun solution2(): Int {
        return listOfMasses
            .map { computeFuel(it) }
            .reduce { acc, i -> acc + i }
    }

    private fun computeFuel(mass: Int): Int {
        val stack = ArrayDeque<Int>()
        do {
            val massToCompute = if (stack.isEmpty()) mass else stack.first
            val computedFuel = massToCompute.div(3).minus(2)
            stack.push(computedFuel)
        } while (stack.first > 0)
        return stack
            .filter { it > 0 }
            .reduce { acc, i -> acc + i };
    }
}
