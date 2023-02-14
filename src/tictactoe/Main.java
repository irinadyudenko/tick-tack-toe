package tictactoe;
import java.util.Scanner;

public class Main {

    public static void printGameField(char[][] curGameField) {

        String[] strings = new String[3];

        for (int i=0; i<3; i++) {
            strings[i]= "| ";
            for (int j=0; j<3; j++) {
                strings[i] += curGameField[i][j]+" ";
            }
            strings[i] += "|";
        }

        System.out.println("---------");
        for (int i=0; i<3; i++) {
            System.out.println(strings[i]);
        }
        System.out.println("---------");
    }

    public static void initGameField(char[][] curGameField) {
        for(int i = 0; i < curGameField.length; i++)
            for(int j = 0; j < curGameField[i].length; j++)
                curGameField[i][j] = ' ';
    }

    public static char whoWins(char[][] curGameField) {
        for(int i=0; i<3; i++) {
            if((curGameField[i][0]==curGameField[i][1] && curGameField[i][1]==curGameField[i][2] &&
                    curGameField[i][1]!=' ')) {
                return curGameField[i][0];
            }
            else if(curGameField[0][i]==curGameField[1][i]&&curGameField[1][i]==curGameField[2][i] &&
                    curGameField[1][i]!=' ') {
                return curGameField[0][i];
            }
        }

        if(((curGameField[0][0]==curGameField[1][1]&&curGameField[1][1]==curGameField[2][2]) ||
                (curGameField[0][2]==curGameField[1][1]&&curGameField[1][1]==curGameField[2][0])) &&
                curGameField[1][1]!=' ') {
            return curGameField[1][1];
        }

        return 'N';
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);


        char[][] gameField = new char[3][3];
        int step;
        char curPlayer;
        char winner = 'N';

        int row, col;
        boolean isInputCorrect;
        String inputIsNotNum = "You should enter numbers!";
        String inputOutOfBounds = "Coordinates should be from 1 to 3!";
        String inputCellIsOccupied = "This cell is occupied! Choose another one!";

        initGameField(gameField);
        printGameField(gameField);

        for(step = 0; step < 9 && winner=='N'; step++) {
            if(step%2==0) curPlayer = 'X';
            else curPlayer = 'O';
            isInputCorrect = false;
            while(!isInputCorrect) {
                System.out.println(">");
                if(scanner.hasNextInt())
                    row = scanner.nextInt();
                else {
                    System.out.println(inputIsNotNum);
                    scanner.next();
                    continue;
                }
                if(scanner.hasNextInt())
                    col = scanner.nextInt();
                else {
                    System.out.println(inputIsNotNum);
                    scanner.next();
                    continue;
                }
                if(!((row>0 && row<4) && (col>0 && col<4))) {
                    System.out.println(inputOutOfBounds);
                    continue;
                }
                if (gameField[row-1][col-1]!=' ') {
                    System.out.println(inputCellIsOccupied);
                    continue;
                }
                gameField[row-1][col-1] = curPlayer;
                isInputCorrect = true;
            }
            printGameField(gameField);
            winner = whoWins(gameField);
        }
        if(winner=='N') System.out.println("Draw");
        else System.out.println(winner + " wins");


    }
}
