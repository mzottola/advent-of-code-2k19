package exercise3

import java.util.Objects
import kotlin.math.abs

class ManhattanDistanceWireComputer(private val paths: List<String>) {

    data class Operation(val direction: Char, val value: Int)
    data class Point(val x: Int, val y: Int, val distance: Int = 0) {
        override fun equals(other: Any?): Boolean {
            return if (other == null || other !is Point)
                false
            else
                this.x == other.x && this.y == other.y
        }

        override fun hashCode(): Int = Objects.hash(x, y)
    }

    companion object {
        private val map = HashMap<Char, (Point, Int) -> List<Point>>()

        init {
            map['R'] = { point, directionValue ->
                generateSequence(Pair(point.x + 1, point.distance + 1), { Pair(it.first + 1, it.second + 1) })
                    .map { Point(it.first, point.y, it.second) }
                    .take(directionValue)
                    .toList()
            }
            map['L'] = { point, directionValue ->
                generateSequence(Pair(point.x - 1, point.distance + 1), { Pair(it.first - 1, it.second + 1) })
                    .map { Point(it.first, point.y, it.second) }
                    .take(directionValue)
                    .toList()
            }
            map['U'] = { point, directionValue ->
                generateSequence(Pair(point.y + 1, point.distance + 1), { Pair(it.first + 1, it.second + 1) })
                    .map { Point(point.x, it.first, it.second) }
                    .take(directionValue)
                    .toList()
            }
            map['D'] = { point, directionValue ->
                generateSequence(Pair(point.y - 1, point.distance + 1), { Pair(it.first - 1, it.second + 1) })
                    .map { Point(point.x, it.first, it.second) }
                    .take(directionValue)
                    .toList()
            }
        }

        fun operationExecution(operation: Operation): (Point, Int) -> List<Point> = map[operation.direction]!!
    }

    fun computeCloserDistanceFromOrigin(): Int {
        val points = readPathsPoints()
        val commonPoints = points[0].intersect(points[1])
        return computeClosestIntersection(commonPoints)
    }

    fun computeCloserDistanceByPointDistance(): Int =
        readPathsPoints()
            .flatten()
            .filterNot { it.x == 0 && it.y == 0 }
            .groupBy { it }
            .filter { it.value.size == 2 }
            .map { it.value.map { it.distance }.reduce { acc, i -> acc + i } }
            .min()!!

    private fun readPathsPoints(): List<Set<Point>> = paths
        .map { createOperationsFromInput(it) }
        .map { computePoints(it) }

    private fun createOperationsFromInput(input: String): List<Operation> =
        input
            .split(",")
            .map { Operation(it.first(), it.substring(1).toInt()) }

    private fun computePoints(operations: List<Operation>): Set<Point> {
        val points = mutableListOf(Point(0, 0))

        operations.forEach {
            points.addAll(
                operationExecution(it).invoke(points.last(), it.value)
            )
        }

        return points.toSet();
    }

    private fun computeClosestIntersection(points: Set<Point>): Int =
        points
            .filterNot { it.x == 0 && it.y == 0 }
            .map { abs(it.x) + abs(it.y) }
            .min()!!
            .toInt()
}
