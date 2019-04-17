import java.lang.management.GarbageCollectorMXBean;
import java.util.Scanner;

public class MyTicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int GAME_SIZE = 3;

    char[][] game = new char[GAME_SIZE][GAME_SIZE];

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void showGame() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void initBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';
            }
        }
    }


    public Move readMove() { // realizam un obiect pentru a putea returna 2 valori.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introducem mutarea: ");
        String myMove = scanner.nextLine();
        String[] myString = myMove.split("-");
        int myLine = Integer.valueOf(myString[0]);
        int myCol = Integer.valueOf(myString[1]);

        Move move = new Move(myLine, myCol);// creaza obiectul move
        return move; // returneaza myLine si myCol

    }

    public boolean validateInput(Move move) {
        boolean isValidate = true;
        if (move.line >= GAME_SIZE || move.col >= GAME_SIZE) {
            isValidate = false;
        }
        return isValidate;
    }

    public boolean validateMove(Move move) {
        boolean isValidate = true;
        if (game[move.line][move.col] == SYMBOL_0 || game[move.line][move.col] == SYMBOL_X) {
            isValidate = false;
        }
        return isValidate;
    }

    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;

    }

    public boolean isWinLine(int line, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[line][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag1(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag2(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][GAME_SIZE - i - 1] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinCol(int col, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][col] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWin(Move move, char symbol) {
        boolean isWin = false;

        // testez linii
        isWin = isWinLine(move.line, symbol);

        // testez coloane
        if (isWin == false) {
            isWin = isWinCol(move.col, symbol);
        }

        // testez diag1
        if (isWin == false && move.line == move.col) {
            isWin = isWinDiag1(symbol);

        }

        // testez diag2
        if (isWin == false && move.line == GAME_SIZE - move.col - 1) { // pe diagonala secundara formula este i = n - j -1; n = GAME_SIZE
            isWin = isWinDiag2(symbol);
        }

        return isWin;
    }


    public void playGame() {
        initBoard();
        System.out.println("Incepe jocul");
        showGame();
        Player currentPlayer = player1;
        char currentSymbol = SYMBOL_X;
        int nrMoves = 0;
        boolean isWin = false; // la inceput nu avem un castigator

        while (isWin == false && nrMoves < (GAME_SIZE * GAME_SIZE)) {

            // citesc mutarea
            Move move = readMove();
            System.out.println(move.line);
            System.out.println(move.col);

            //validez mutarea si apoi efectuez mutarea

            if (!validateInput(move)) {
                System.out.print("Reluam mutarea. ");
                System.out.println("Introdu valori mai mici decat " + GAME_SIZE+ " !");
                showGame();

            } else if (!validateMove(move)) {
                System.out.println("Pozitia este deja ocupata, introdu o alta pozitie!");
                showGame();

            } else {
                makeMove(move, currentSymbol);
                showGame();

                //numar mutarea
                nrMoves++;

                if (nrMoves >= (2 * GAME_SIZE - 1)) {
                    isWin = isWin(move, currentSymbol);
                }
                //testez daca avem stare de win

                isWin = isWin(move, currentSymbol);
                //daca nu e win sau mai multe mutari -- schimb jucatorul
                if (!isWin) {

                    if (currentPlayer == player1) {
                        currentPlayer = player2;
                        currentSymbol = SYMBOL_0;
                    } else {
                        currentPlayer = player1;
                        currentSymbol = SYMBOL_X;
                    }
                }
            }
        }

            // afisez un mesaj corespunzator daca exista situatie de win
            if (isWin == true) {
                System.out.println("Castigatorul este " + currentPlayer.name);
            } else {
             System.out.println("Nu exista niciun castigator!");
            }

        }
    }


