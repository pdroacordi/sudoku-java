package entity;

import exception.NumberAlreadyPresent;
import exception.SquareOccupiedException;

public class Board {

    private final Tile[][] board;
    private StringBuilder boardString;
    private final int SIZE;
    private final int SUBGRID;

    public Board(int size, int subgrid) {
        this.board = new Tile[size][size];
        this.boardString = new StringBuilder();
        this.SIZE = size;
        this.SUBGRID = subgrid;
        this.updateBoardString();
    }

    public void add( int row, int col, int value ){
        if( value < 0 || value > this.SIZE ){
            return;
        }
        if(isNotValidTile(row, col)){
            return;
        }
        if( this.board[row][col] != null ){
            throw new SquareOccupiedException("Quadrado ocupado");
        }

        if( this.isInRow(row, value) ) throw new NumberAlreadyPresent("Número já presente na linha");

        if( this.isInColumn(col, value) ) throw new NumberAlreadyPresent("Número já presente na coluna");

        if( this.isInSubgrid(row, col, value) ) throw new NumberAlreadyPresent("Número já presente no quadrado");

        this.board[row][col] = new Tile.Builder()
                .value(value)
                .fixed(false)
                .build();
        this.updateBoardString();
    }

    public void remove( int row, int col){
        if(isNotValidTile(row, col)){
            return;
        }
        if( this.board[row][col] != null && this.board[row][col].isFixed() ){
            return;
        }

        this.board[row][col] = null;
        this.updateBoardString();
    }
    
    public boolean isInRow(int row, int value){
        for(int i = 0; i < this.SIZE; i++){
            if( this.board[row][i] != null && this.board[row][i].getValue() == value ) return true;
        }
        return false;
    }

    public boolean isInColumn(int col, int value){
        for(int i = 0; i < this.SIZE; i++){
            if( this.board[i][col] != null && this.board[i][col].getValue() == value ) return true;
        }
        return false;
    }

    public boolean isInSubgrid(int row, int col, int value){
        int startRow = (row / this.SUBGRID) * this.SUBGRID;
        int startCol = (col / this.SUBGRID) * this.SUBGRID;
        for(int i = 0; i < this.SUBGRID; i++){
            for(int j = 0; j < this.SUBGRID; j++){
                if( this.board[startRow + i][startCol + j] != null
                        && this.board[startRow + i][startCol + j].getValue() == value ) return true;
            }
        }
        return false;
    }

    public int getNumberAt( int row, int col){
        if(isNotValidTile(row, col)){
            return 0;
        }
        if( this.board[row][col] == null ){
            return 0;
        }
        return this.board[row][col].getValue();
    }

    public String getBoardString(){
        return this.boardString.toString();
    }

    public Board copy() {
        Board copyBoard = new Board(this.SIZE, this.SUBGRID);

        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                if (this.board[i][j] != null) {
                    copyBoard.board[i][j] = new Tile.Builder()
                            .value(this.board[i][j].getValue())
                            .fixed(this.board[i][j].isFixed())
                            .build();
                }
            }
        }
        copyBoard.updateBoardString();
        return copyBoard;
    }


    private boolean isNotValidTile(int row, int col){
        return row < 0 || row >= SIZE || col < 0 || col >= SIZE;
    }

    private void updateBoardString(){
        boardString.setLength(0);

        for(int i = 0; i < this.SIZE; i++){
            if( i % this.SUBGRID == 0 && i != 0 ){
                boardString.append("------+-------+------\n");
            }
            for (int j = 0; j < this.SIZE; j++) {
                if (j % this.SUBGRID == 0 && j != 0) {
                    boardString.append("| ");
                }
                boardString.append(this.board[i][j] == null ? ". " : board[i][j].getValue() + " ");
            }
            boardString.append("\n");
        }
    }
}
