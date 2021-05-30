
import java.util.Scanner;
import java.util.ArrayList;

public class Table {

    private Scanner scanner;

    ArrayList<String>[] al = new ArrayList[100];

    public Table() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        
        int n=returnN();
        int i = 1;
        while (true) {
            System.out.println(iChecker(i, n));
            String input = scanner.nextLine();
            if (input.equals("-1")) {
                System.out.println("Game over, please come again!");
                break;
            } else if (!isOkay(input, n)) {
                System.out.println("Invalid input, please try again!");
                continue;
            }
            setMarker(input, i);
            printTable(n);
            if (checkWinner(n) && i % 2 == 1) {
                System.out.println("Player 1 wins!");
                break;
            } else if (checkWinner(n) && i % 2 == 0) {
                System.out.println("Player 2 wins!");
                break;
            }
            i++;
        }
    }

    public int returnN(){
        
        System.out.print("Size of table? ");
        while(true){
            try {
                int n= Integer.valueOf(scanner.nextLine());
                if(n<1){
                    System.out.println("Please enter a size of 1 or more");
                        continue;
                }
                makeTable(n);
                    return n;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
        }
        
    }

    public String iChecker(int i,int n){
        if (i == (n * n) + 1) {// 3*3+1. 4*4+1=17 /if(i==length)
            return "It's a tie! Game over.";
            
        } else if (i % 2 == 1) {
            return "Player 1: you are X's. It's your turn!";
        } else {
            return "Player 2: you are O's. It's your turn!";
        }
    }

    public void makeTable(int n) {

        int rows = n;
        int columns = n * 2;

        // initializing lists
        for (int i = 0; i < rows; i += 1) {
            al[i] = new ArrayList<String>();
        }

        for (int a = 0; a < rows; a += 1) {
            for (int b = 0; b <= columns; b += 1)

                if (b % 2 == 0) {
                    al[a].add("|");
                } else {
                    al[a].add("     ");
                }
        }
        printTable(rows);
    }

    public void printTable(int n) {
        int rows = n;
        int columns = n * 2;
        int dashes = ((5 * n) + (n + 1));

        printDashes(dashes);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= columns; c++) {
                if (c == columns) {
                    System.out.println(al[r].get(c));
                    printDashes(dashes);
                    continue;
                }

                System.out.print(al[r].get(c));
            }
        }
        checkWinner(rows);
    }

    public void printDashes(int dashes) {
        for (int i = 0; i < dashes; i++) {
            System.out.print("-");
        }
        System.out.println("");

    }

    public boolean isOkay(String input, int n) {

        if (!input.contains(",")) {
            return false;
        } else {
            try {
                String[] fullPosition = input.split(",");
                int row = Integer.valueOf(fullPosition[0]);
                int column = Integer.valueOf(fullPosition[1]);
                if (row <= 0 || column <= 0) {
                    return false;
                } else if (al[row - 1].get(column * 2 - 1).equals("  X  ")
                        || al[row - 1].get(column * 2 - 1).equals("  O  ")) {
                    return false;
                }
                return row <= n && column <= n;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public void setMarker(String position, int i) {
        String[] fullPosition = position.split(",");
        String marker;
        int row = Integer.valueOf(fullPosition[0]);
        int column = Integer.valueOf(fullPosition[1]);
        if (i % 2 == 1) {
            marker = "  X  ";
        } else {
            marker = "  O  ";
        }

        al[row - 1].set((column * 2) - 1, marker);

    }

    public boolean checkWinner(int size) {
        int c = 1;
        for (int i = 0; i < size; i += 1) {
            if (rowCounter(i, size)) {
                return true;
            } else if (columnCounter(c, size)) {
                return true;
            } 
            c += 2;
        }
         if (checkDiagonals(size)) {
            return true;
        }
        return false;
    }

    public boolean rowCounter(int row, int size) {
        int xSum = 0;
        int oSum = 0;

        for (String position : al[row]) {
            if (position.equals("  X  ")) {
                xSum += 1;
            } else if (position.equals("  O  ")) {
                oSum += 1;
            }
        }

        return xSum == size || oSum == size;

    }

    public boolean columnCounter(int column, int size) {
        int xSum = 0;
        int oSum = 0;

        for (int i = 0; i < size; i += 1) {
            if (al[i].get(column).equals("  X  ")) {
                xSum += 1;
            } else if (al[i].get(column).equals("  O  ")) {
                oSum += 1;
            }
        }

        return xSum == size || oSum == size;
    }



    public boolean checkDiagonals(int size) {
        int xSum1 = 0;
        int oSum1 = 0;

        // int r1 in loop //diagnol NW to SE
        int c1 = 1;

        int xSum2 = 0;
        int oSum2 = 0;

        int r2 = size-1;      //diagnol SW to NE
        int c2 = 1;

        for (int r1 = 0; r1 < size; r1 += 1) {
            // table[0][2]
            if (al[r1].get(c1).equals("  X  ")) {
                xSum1 += 1;
            } else if (al[r1].get(c1).equals("  O  ")) {
                oSum1 += 1;
            }

            if (al[r2].get(c2).equals("  X  ")) {
                xSum2 += 1;
            } else if (al[r2].get(c2).equals("  O  ")) {
                oSum2 += 1;
            }

            c1 += 2;
            r2 -= 1;
            c2 +=2;

        }
        if (xSum1 == size || oSum1 == size) {
            return true;
        } else if (xSum2 == size || oSum2 == size) {
            return true;
        }
        return false;

    }
}


