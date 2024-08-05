import kotlin.random.Random

class SudokuBoard(private val rateCellsToShow: Double) {
    val size = 9
    val board: Array<Array<Int>> = Array(size) { Array(size) { 0 } }

    fun isValidMove(row: Int, col: Int, num: Int): Boolean {
        for (i in 0..<size) {
            if (board[row][i] == num || board[i][col] == num) return false
        }
        val startRow = row - row % 3
        val startCol = col - col % 3
        for (i in startRow..<startRow + 3) {
            for (j in startCol..<startCol + 3) {
                if (board[i][j] == num) return false
            }
        }
        return true
    }

    fun placeNumber(row: Int, col: Int, num: Int) {
        board[row][col] = num
    }

    private fun removeNumber(row: Int, col: Int) {
        board[row][col] = 0
    }

    fun solve(row: Int = 0, col: Int = 0): Boolean {
        if (row == size) return true
        if (col == size) return solve(row + 1, 0)
        if (board[row][col] != 0) return solve(row, col + 1)
        for (num in 1..9) {
            if (isValidMove(row, col, num)) {
                placeNumber(row, col, num)
                if (solve(row, col + 1)) return true
                removeNumber(row, col)
            }
        }
        return false
    }

    private fun removeRandomCells(rate: Double) {
        val totalCells = size * size
        val cellsToRemove = totalCells - (totalCells * rate).toInt()
        var removedCells = 0

        while (removedCells < cellsToRemove) {
            val row = Random.nextInt(size)
            val col = Random.nextInt(size)
            if (board[row][col] != 0) {
                removeNumber(row, col)
                removedCells++
            }
        }
    }

    private fun shuffleArray(array: IntArray) {
        for (i in array.indices) {
            val randomIndex = Random.nextInt(array.size)
            val temp = array[i]
            array[i] = array[randomIndex]
            array[randomIndex] = temp
        }
    }

    private fun generate() {
        val numbers = IntArray(size) { it + 1 }
        shuffleArray(numbers)
        solveWithNumbers(numbers)
    }

    private fun solveWithNumbers(numbers: IntArray, row: Int = 0, col: Int = 0): Boolean {
        if (row == size) return true
        if (col == size) return solveWithNumbers(numbers, row + 1, 0)
        if (board[row][col] != 0) return solveWithNumbers(numbers, row, col + 1)
        for (num in numbers) {
            if (isValidMove(row, col, num)) {
                placeNumber(row, col, num)
                if (solveWithNumbers(numbers, row, col + 1)) return true
                removeNumber(row, col)
            }
        }
        return false
    }

    fun generateSudokuBoard() {
        generate()
        removeRandomCells(rateCellsToShow)
    }

    fun isBoardFull(): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell == 0) return false
            }
        }
        return true
    }
}
