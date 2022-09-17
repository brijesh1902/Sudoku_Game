package com.brizzs.sudoku.libs;

import java.util.ArrayList;

public class Solver {

    private int selected_row, selected_column;

    int[][] board;
    ArrayList<ArrayList<Object>> emptyBoxIndex;

    public Solver() {
        selected_row = -1;
        selected_column = -1;

        board = new int[9][9];

        for (int r=0; r<9; r++)
            for (int c=0; c<9; c++)
                board[r][c] = 0;

        emptyBoxIndex = new ArrayList<>();
    }

    public void resetBoard(){
        for (int r=0; r<9; r++)
            for (int c=0; c<9; c++)
                board[r][c] = 0;

        emptyBoxIndex = new ArrayList<>();
    }

    public void getEmptyBoxIndexes() {
        for (int r=0; r<9; r++) {
            for (int c=0; c<9; c++) {
                if (board[r][c] == 0) {
                    emptyBoxIndex.add(new ArrayList<>());
                    emptyBoxIndex.get(emptyBoxIndex.size()-1).add(r);
                    emptyBoxIndex.get(emptyBoxIndex.size()-1).add(c);
                }
            }
        }
    }

    private boolean check(int row, int col){
        if (board[row][col] > 0) {
            for (int i=0; i<9; i++) {
                if (board[i][col] == board[row][col] && row != i) //horizontal check
                    return false;
                if (board[row][i] == board[row][col] && col != i) //vertical check
                    return false;
            }

            int boxRow = row/3, boxCol = col/3;

            // box check
            for (int r=boxRow*3; r<boxRow*3 + 3; r++){
                for (int c=boxCol*3; c<boxCol*3 + 3; c++){
                    if (board[r][c] == board[row][col] && row != r && col != c)
                        return false;
                }
            }
        }
        return true;
    }

    public boolean solve(SudokuBoard sudokuBoard) {
        int row = -1, col = -1;

        for (int r=0; r<9; r++)
            for (int c=0; c<9; c++)
                if (board[r][c] == 0){
                    row = r;
                    col = c;
                    break;
                }

        if (row == -1) return true;

        for (int i=1; i<10; i++){
            board[row][col] = i;
            sudokuBoard.invalidate();

            if (check(row, col))
                if (solve(sudokuBoard))
                    return true;

            board[row][col] = 0;
        }
        return false;
    }

    public ArrayList<ArrayList<Object>> getEmptyBoxIndex() {
        return emptyBoxIndex;
    }

    public void setNumberPosition(int num){
        if (selected_row!=-1 && selected_column!=-1){
            if (board[selected_row-1][selected_column-1] == num)
                board[selected_row-1][selected_column-1] = 0;
            else
                board[selected_row-1][selected_column-1] = num;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getSelectedRow() {
        return selected_row;
    }

    public void setSelectedRow(int selected_row) {
        this.selected_row = selected_row;
    }

    public int getSelectedColumn() {
        return selected_column;
    }

    public void setSelectedColumn(int selected_column) {
        this.selected_column = selected_column;
    }
}
