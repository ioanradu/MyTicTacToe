import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Numele primului jucator: ");
        String player1Name = scanner.nextLine();

        System.out.print("Numele celui de-al doilea jucator: ");
        String player2Name = scanner.nextLine();

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        // construim jocul
        MyTicTacToe myTicTacToe = new MyTicTacToe(player1, player2);
        myTicTacToe.playGame();

        boolean newGame = true;
        while (newGame) {
           System.out.println("Doriti sa incepeti un nou joc?");
           System.out.println("Pentru DA apasati tasta y iar pentru nu apasati orice alta tasta");
           String d = scanner.nextLine();

            if (d.equals("y")) {
                myTicTacToe.playGame();
            } else {
                System.out.println("Jocul s-a incheiat! ");
                System.out.println("Va multumim!");
                newGame = false;
            }
        }
    }
}
