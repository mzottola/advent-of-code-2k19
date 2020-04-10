package exercise2

class IntCodeProgramReader(private val intCodeProgram: IntArray) {

    companion object {
        private val map = HashMap<Int, (Int, Int) -> Int>()

        init {
            map[1] = { i, j -> i + j }
            map[2] = { i, j -> i * j }
        }

        fun compute(operator: Int, param1: Int, param2: Int): Int = map[operator]!!.invoke(param1, param2)
    }

    fun executeProgram(valueReplaceConsumer: ((IntArray) -> (Unit))? = null): IntArray {
        val program = intCodeProgram.copyOf()
        val numberOfPositions = program.size / 4

        valueReplaceConsumer?.invoke(program)

        for (i in 0..numberOfPositions) {
            val position = i * 4
            val operator = program[position]
            if (operator == 99) {
                break
            }

            val index1 = program[position + 1]
            val index2 = program[position + 2]
            val index3 = program[position + 3]

            val result = compute(operator, program[index1], program[index2])
            program[index3] = result
        }
        return program
    }

    /**
     * The pair returned should contain:
     * - first: noun
     * - second: verb
     */
    fun findNounAndVerbForValue(value: Int): Pair<Int, Int> {
        for (i in 0..99) {
            for (j in 0..99) {
                val executedProgram = executeProgram { array -> array[1] = i; array[2] = j }
                if (executedProgram[0] == value) {
                    return Pair(i, j)
                }
            }
        }
        throw RuntimeException("Program not found")
    }
}
