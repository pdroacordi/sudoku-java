package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int SUBGRID = 3;
    private final int DIFFICULTY;

    private Board board;
    private Board solution;
    private Random random = new Random();

    public SudokuGenerator(int difficulty) {
        this.board = new Board(SIZE, SUBGRID);
        this.solution = new Board(SIZE, SUBGRID);

        this.DIFFICULTY = difficulty;


        generateFullBoard();
    }

    private void generateFullBoard(){
        fillDiagonalSubgrids();
        solve(0,0);

        this.solution = this.board.copy();

        removeNumbers();
    }


    private void fillDiagonalSubgrids(){
        for(int i = 0; i<SIZE; i += SUBGRID){
            fillSubgrid(i, i);
        }
    }

    private void fillSubgrid(int row, int col) {
        List<Integer> numbers = new ArrayList<>();

        for(int i = 1; i <= SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        int index = 0;
        for(int i = 0; i < SUBGRID; i++){
            for(int j = 0; j < SUBGRID; j++){
                int num;
                do {
                    num = numbers.get(index++);
                } while (board.isInSubgrid(row, col, num));
                board.add(row + i, col + j, num);
            }
        }
    }

    private boolean solve(int row, int col){
        if(row == SIZE) return true;
        if (col == SIZE) return solve(row + 1, 0);
        if (board.getNumberAt(row, col) != 0) return solve(row, col + 1);

        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <= SIZE; i++) numbers.add(i);
        Collections.shuffle(numbers);

        if (board.getNumberAt(row, col) == 0) {
            for (int num : numbers) {
                if (isSafe(row, col, num)) {
                    board.add(row, col, num);
                    if (solve(row, col + 1)) return true;
                    board.remove(row, col);
                }
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int num){
        if (board.getNumberAt(row, col) != 0) return false;
        return !board.isInRow(row, num) &&
                !board.isInColumn(col, num) &&
                !board.isInSubgrid(row, col, num);

    }

    public void removeNumbers(){
        int blanks = switch (this.DIFFICULTY){
            case 1 -> 50; // fácil
            case 2 -> 60; // Médio
            case 3 -> 64; // Díficil
            default -> 40;
        };

        int removed = 0;
        while(removed < blanks){
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if( board.getNumberAt(row, col) != 0 ){
                board.remove(row, col);
                removed++;
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Board getSolution() {
        return solution;
    }

}
