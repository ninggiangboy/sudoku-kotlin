class SudokuController(private val model: SudokuBoard, private val view: SudokuView) {
    fun startGame() {
        do {
            model.generateSudokuBoard()
            view.printBoard(model)
            var isWin = false
            while (true) {
                val input = view.promptUserInput() ?: break
                val (row, col, num) = input
                if (model.isValidMove(row, col, num)) {
                    model.placeNumber(row, col, num)
                    view.printBoard(model)
                } else {
                    view.showInvalidMoveMessage()
                }
                if (model.isBoardFull()) {
                    isWin = true
                    break
                }
            }
            view.showGameEndMessage(isWin)
            if (!isWin) {
                model.solve()
                view.printBoard(model)
            }
        } while (view.promptPlayAgain())
    }
}
