import java.util.Scanner;

public class KnightMoves {
    static Scanner sc = new Scanner(System.in);

    static int [][] moves = {           {-2, +1},
                                        {-1, +2},
                                        {+1, +2},
                                        {+2, +1},
                                        {+2, -1},
                                        {+1, -2},
                                        {-1, -2},
                                        {-2, -1}  };

    public static void main(String[] args) {
        System.out.println("Welcome to the KnightMoves Game!");
        do {
            showKingMoves();
        } while (getYorN("Do it again?"));
    }

    public static void showKingMoves() {
        int [][]board = new int[8][8];

        String kSquare;
        Pos kPos;

        do {
            System.out.print("Enter knight's position: ");
            kSquare = sc.nextLine();
            kPos = convertSquareToPos(kSquare);
        } while (kPos == null);

        board[kPos.x][kPos.y] = 1;
        System.out.println("\nThe knights is at square" + convertPosToSquare(kPos));

        System.out.println("From here the knight can move to: ");

        for (int move = 0; move < moves.length; move++) {
            int x, y;
            x = moves[move][0];
            y = moves[move][1];

            Pos p = calculateNewPos(kPos, x, y);

            if (p != null) {
                System.out.println(convertPosToSquare(p));
                board[p.x][p.y] = 2;
            }
        }

        printBoard(board);
    }

    public static Pos convertSquareToPos(String square) {
        int x = -1;
        int y = -1;

        char rank, file;

        file = square.charAt(0);
        if (file == 'a') x = 0;
        if (file == 'b') x = 1;
        if (file == 'c') x = 2;
        if (file == 'd') x = 3;
        if (file == 'e') x = 4;
        if (file == 'f') x = 5;
        if (file == 'g') x = 6;
        if (file == 'h') x = 7;

        rank = square.charAt(1);
        if (file == '1') y = 0;
        if (file == '2') y = 1;
        if (file == '3') y = 2;
        if (file == '4') y = 3;
        if (file == '5') y = 4;
        if (file == '6') y = 5;
        if (file == '7') y = 6;
        if (file == '8') y = 7;

        if (x == -1 || y == -1) {
            return null;
        } else {
            return new Pos(x, y);
        }
    }

    public static String convertPosToSquare(Pos p) {
        String file = "";

        if (p.x == 0) file = "a";
        if (p.x == 1) file = "b";
        if (p.x == 2) file = "c";
        if (p.x == 3) file = "d";
        if (p.x == 4) file = "e";
        if (p.x == 5) file = "f";
        if (p.x == 6) file = "g";
        if (p.x == 7) file = "h";

        return file + (p.y + 1);
    }

    public static Pos calculateNewPos (Pos p, int x, int y) {
        if (p.x + x < 0)
            return null;
        if (p.x + x > 7)
            return null;
        if (p.y + y < 0)
            return null;
        if (p.y + y > 7)
            return null;

        return new Pos(p.x + x, p.y + y);
    }

    public static void printBoard(int [][] b) {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if (b[x][y] == 1) {
                    System.out.println(" X ");
                } else if (b[x][y] == 2) {
                    System.out.println(" ? ");
                } else {
                    System.out.println(" - ");
                }

                System.out.println();
            }
        }
    }

    public static boolean getYorN(String prompt) {
        while (true) {
            String answer;
            System.out.println("\n" + prompt + " (Y pr N) ");
            answer = sc.nextLine();

            if(answer.equalsIgnoreCase("Y")){
                return true;
            } else if (answer.equalsIgnoreCase("N")){
                return false;
            }
        }
    }

    static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
