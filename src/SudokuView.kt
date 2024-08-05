class SudokuView {
    fun printBoard(board: SudokuBoard) {
        print("      ")
        for (j in 0..<board.size) {
            if (j % 3 == 0 && j != 0) print("  ")
            print("$j ")
        }
        println()
        for (i in board.board.indices) {
            if (i % 3 == 0) {
                println("    +-------+-------+-------+")
            }
            print("$i   ")
            for (j in board.board[i].indices) {
                if (j % 3 == 0) {
                    print("| ")
                }
                print(if (board.board[i][j] == 0) ". " else "${board.board[i][j]} ")
            }
            println("|")
        }
        println("    +-------+-------+-------+")
    }

    fun promptUserInput(): Triple<Int, Int, Int>? {
        print("Enter row, column, and number to place (or -1 to exit) like '1 1 1': ")
        val input = readlnOrNull() ?: return null
        if (input.contains("-1")) return null
        val (row, col, num) = input.split(" ").map { it.toInt() }
        return Triple(row, col, num)
    }

    fun promptPlayAgain(): Boolean {
        print("Do you want to play again? (y/n) ")
        val input = readlnOrNull() ?: return false
        return input.lowercase() == "y"
    }

    fun promptDifficulty(): Double {
        print("Enter rate of cells to show: ")
        return readlnOrNull()?.toDoubleOrNull() ?: 0.5
    }

    fun showInvalidMoveMessage() {
        println("Invalid move")
    }

    fun showGameEndMessage(isWin: Boolean) {
        println(if (isWin) "You Win!" else "Game Over!")
    }
}
