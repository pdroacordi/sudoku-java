# Sudoku Console Game

This is a console-playable Sudoku game developed in Java. The game allows users to choose difficulty levels, place and remove numbers, and view the complete solution of the board.

## 🚀 Features
- Automatically generates valid Sudoku boards.
- Difficulty selection: Easy, Medium, or Hard.
- Sudoku rule validation to prevent invalid moves.
- Ability to remove placed numbers.
- Displays the board in a textual format.
- Option to view the complete solution.

## 📋 Requirements
- Java 8 or higher installed on your machine.

## 🛠 How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/sudoku-console.git
   ```
2. Navigate to the project directory:
   ```bash
   cd sudoku-console
   ```
3. Compile the Java files:
   ```bash
   javac -d out src/**/*.java
   ```
4. Run the game:
   ```bash
   java -cp out Main
   ```

## 🎮 How to Play
1. Choose the difficulty level at the start of the game.
2. The board is generated with some numbers hidden.
3. Use the menu options to add or remove numbers.
4. The game automatically checks if a number can be placed in the chosen position.
5. You can check the full Sudoku solution at any time.

## 📂 Project Structure
```
📂 sudoku-console
├── 📂 src
│   ├── 📂 entity
│   │   ├── Board.java
│   │   ├── Tile.java
│   │   ├── SudokuGenerator.java
│   ├── 📂 exception
│   │   ├── NumberAlreadyPresent.java
│   │   ├── SquareOccupiedException.java
│   ├── Main.java
└── README.md
```

## 🤝 Contribution
If you would like to contribute, feel free to open an issue or submit a pull request!

## 📝 License
This project is under the MIT license. Feel free to use and modify it as you like.

---
Developed by [Pedro Acordi](https://github.com/pdroacordi) 🎲🔥

