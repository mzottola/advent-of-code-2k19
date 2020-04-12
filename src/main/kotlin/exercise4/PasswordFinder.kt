package exercise4

class PasswordFinder(private val from: Int, private val to: Int) {

    fun countValidPasswordsInIntervalSolution1(): Int {
        return IntRange(from, to)
            .map { it.toString() }
            .filter { hasDigitsNeverDecreasing(it) }
            .filter { hasAtLeastOneDouble(it) }
            .count()
    }

    fun countValidPasswordsInIntervalSolution2(): Int {
        return IntRange(from, to)
            .map { it.toString() }
            .filter { hasDigitsNeverDecreasing(it) }
            .filter { hasAtLeastOneDoubleWithoutPartOfLargerGroup(it) }
            .count()
    }

    private fun hasDigitsNeverDecreasing(value: String) =
        value
            .map { Character.getNumericValue(it) }
            .sorted()
            .map { it.toString() }
            .reduce { acc, s -> acc + s } == value

    private fun hasAtLeastOneDouble(value: String) =
        value
            .mapIndexed { index, c -> Pair(c, index) }
            .groupBy({ it.first }, { it.second })
            .filter { it.value.size > 1 }
            .count() > 0

    private fun hasAtLeastOneDoubleWithoutPartOfLargerGroup(value: String) =
        value
            .mapIndexed { index, c -> Pair(c, index) }
            .groupBy({ it.first }, { it.second })
            .filter { it.value.size == 2 }
            .count() > 0
}
