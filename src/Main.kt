fun main() {
    val view = SudokuView()
    val rate = view.promptDifficulty()
    val model = SudokuBoard(rate)
    val controller = SudokuController(model, view)
    controller.startGame()
}
