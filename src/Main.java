import entity.Board;
import entity.SudokuGenerator;
import exception.NumberAlreadyPresent;
import exception.SquareOccupiedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static Board SOLUTION;
    private static Board BOARD;

    public static void main(String[] args) {
        System.out.println("Qual dificuldade? \n1. Fácil\n2. Médio\n3. Difícil");
        int difficulty = readIntegerValue("Insira o valor correspondente:  ");

        SudokuGenerator sg = new SudokuGenerator(difficulty);

        BOARD = sg.getBoard();
        SOLUTION = sg.getSolution();

        menu();
    }

    private static void menu()  {
        System.out.println("O que deseja fazer?");
        do{
            System.out.println(BOARD.getBoardString());
            System.out.println();
            System.out.println("1. Posicionar número");
            System.out.println("2. Remover número");
            System.out.println("3. Ver solução");
            System.out.println("4. Sair");
            int option = readIntegerValue("Insira a opção desejada: ");
            switch (option) {
                case 1 -> positionNumber();
                case 2 -> removeNumber();
                case 3 -> {
                    showSolution();
                    return;
                }
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida. Tente novamente");
            }
        }while (true);
    }

    private static void positionNumber(){
        int row = readIntegerValue("Insira a linha a posicionar") - 1;
        int col = readIntegerValue("Insira a coluna a posicionar") - 1;
        int val = readIntegerValue("Insira a valor a posicionar");

        try {
            BOARD.add(row, col, val);
        }catch (SquareOccupiedException | NumberAlreadyPresent e){
            System.out.println(e.getMessage());
        }
    }

    private static void removeNumber(){
        int row = readIntegerValue("Insira a linha a posicionar") - 1;
        int col = readIntegerValue("Insira a coluna a posicionar") - 1;

        BOARD.remove(row, col);
    }

    private static void showSolution(){
        System.out.println( SOLUTION.getBoardString() );
    }

    private static int readIntegerValue(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        boolean loop = true;
        System.out.println(message);
        while(loop){
            try {
                result = Integer.parseInt(br.readLine());
                loop = false;
            }catch (IOException | NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente:");
            }
        }
        return result;
    }
}