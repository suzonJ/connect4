import java.util.Random;
import java.util.Scanner;

public class connect4 {
    static char letter_user = ' ';
    static char letter_computer = ' ';


    public static void main(String[] args) {

        String nom_user = "";
        Math.random();
        if (Math.random() > 0.5) {
            System.out.println("You are the X and the computer is O");
            letter_computer = 'O';
            letter_user = 'X';
        } else {
            System.out.println("You are the O and the computer is X");
            letter_computer = 'X';
            letter_user = 'O';
        }

        System.out.println("Connect 4 rules: Players must connect 4 of the same colored discs in a row to win.\n"
                + "Only one piece is played at a time.\n" + "Players can be on the offensive or defensive.\n"
                + "The game ends when there is a 4-in-a-row.\n \n \n \n " + "Welcome to connect 4, what is your name?");

        Scanner sc = new Scanner(System.in);
        nom_user = sc.next();


        char[][] grille_jeu = new char[6][7];

        // Scanner sc = new Scanner(System.in);
        int play_again = -1;
        do {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    grille_jeu[i][j] = '_';
                }
            }
            System.out.println("Hello " + nom_user + "!");


            System.out.println("You are " + letter_user);
            while (check_win(grille_jeu) == false) {
                display_grid(grille_jeu);
                playerTurn(grille_jeu);
                computerTurn(grille_jeu);
                if (grid_full(grille_jeu)) {
                    System.out.println("the game is a tie !!");
                }
            }

            do {
                System.out.println("If you want to play again press1, if not press 2");

                play_again = sc.nextInt();
            } while (play_again != 1 && play_again != 2);
        } while (play_again == 1);
        System.out.println("Goodbye I hope you enjoyed");
    }


    public static void display_grid(char[][] tab_2d) {
        String str = " | 1 |  | 2 |  | 3 |  | 4 |  | 5 |  | 6 |  | 7 |\n";
        for (int i = 0; i < tab_2d.length; i++) {
            for (int j = 0; j < tab_2d[0].length; j++) {
                str += " | " + tab_2d[i][j] + " | ";
            }
            str += "\n";
        }
        System.out.println(str);
    }


    public static void playerTurn(char[][] tab_2d) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a column from 1 to 7: ");
        int column = sc.nextInt();
        column -= 1;


        if (column_full(tab_2d, column)) {
            System.out.println("The column is full. Please choose a different one.");
            playerTurn(tab_2d);
        } else {

            for (int i = tab_2d.length - 1; i >= 0; i--) {
                if (tab_2d[i][column] == '_') {
                    tab_2d[i][column] = letter_user;
                    break;
                }
            }
        }
    }

    public static void computerTurn(char[][] tab_2d) {
        Random rand = new Random();

        int col = rand.nextInt(7);
        int row = tab_2d.length - 1;
        while (row >= 0 && tab_2d[row][col] != '_') {
            row--;
        }

        if (row >= 0) {
            tab_2d[row][col] = letter_computer;
        }
    }

    public static boolean grid_full(char[][] tab_2d) {
        for (int i = 0; i < tab_2d.length; i++) {
            for (int j = 0; j < tab_2d[i].length; j++) {
                if (tab_2d[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean check_win(char[][] tab_2d) {
        boolean[][] visited = new boolean[tab_2d.length][tab_2d[0].length];
        int count_x = 0;
        int count_o = 0;

        for (int i = 0; i < tab_2d.length; i++) {
            count_x = 0;
            count_o = 0;
            for (int j = 0; j < tab_2d[0].length; j++) {
                if (tab_2d[i][j] == 'X') {
                    count_x++;
                    if (count_x >= 4) {
                        System.out.println("X wins !!!! ");
                        return true;
                    }
                } else {
                    count_x = 0;
                }
                if (tab_2d[i][j] == 'O') {
                    count_o++;
                    if (count_o >= 4) {
                        System.out.println("O wins !!!! ");
                        return true;
                    }
                } else {
                    count_o = 0;
                }
            }
        }

        for (int j = 0; j < tab_2d[0].length; j++) {
            count_x = 0;
            for (int i = 0; i < tab_2d.length; i++) {
                if (tab_2d[i][j] == 'X') {
                    count_x++;
                    if (count_x >= 4) {
                        System.out.println("X wins!!");
                        return true;
                    }
                } else {
                    count_x = 0;
                }
                if (tab_2d[i][j] == 'O') {
                    count_o++;
                    if (count_o >= 4) {
                        System.out.println("X wins!!");
                        return true;
                    }
                } else {
                    count_o = 0;
                }
            }

        }
        return false;

    }
    public static boolean column_full(char[][] tab_2d, int column) {
        for (int i = 0; i < tab_2d.length; i++) {
            if (tab_2d[i][column] == '_') {

                return false;
            }
        }

        return true;
    }


}










