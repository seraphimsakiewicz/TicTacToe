
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Table {

    private Scanner scanner;

    ArrayList<String>[] al = new ArrayList[100];

    public Table() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        
        int n=returnSize();
        int p= setPlayers();
        String[]symbols= setSymbols(p);
        System.out.println(Arrays.toString(symbols));
        int i = 1;
        int player=1;
        while (true) {
            if(Tie(i, n)){
                break;
            }
            System.out.println("Player " + player + " please make a move.");
            String input = scanner.nextLine();
            if (input.equals("-1")) {
                System.out.println("Game over, please come again!");
                break;
            } else if (!isOkay(input, n)) {
                System.out.println("Invalid input, please try again!");
                continue;
            }
            setMarker(input, i,symbols[player-1]);

            printTable(n);
            if (aPlayerWon(n, symbols)) {
                break;
            }

            i++;
            player++;
            if(player==symbols.length+1){
                player=1;
            }

            
        }
    }

    public int returnSize(){
        
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

    public int setPlayers(){
        System.out.print("How many players? ");
        while(true){
            try {
                int p= Integer.valueOf(scanner.nextLine());
                if(p<2){
                    System.out.println("Game needs 2 or more players.");
                        continue;
                }
                 return p;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
        }
    }

    public String[] setSymbols(int p){
        String[]players = new String[p];
        for(int i=0;i<p;i++){
            System.out.print("Player "+ (i+1) +" set your symbol. ");
            players[i]="  " + scanner.nextLine() + "  ";
        }
        return players;
    }

    public boolean Tie(int i,int n){
        if (i == (n * n) + 1) {// 3*3+1. 4*4+1=17 /if(i==length)
            System.out.println("It's a tie! Game over.");
            return true;
    }
    return false;
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
    }

    public void printDashes(int dashes) {
        for (int i = 0; i < dashes; i++) {
            System.out.print("-");
        }
        System.out.println("");

    }

    public boolean isOkay(String input, int n) {
            try {
                String[] fullPosition = input.split(",");
                int row = Integer.valueOf(fullPosition[0]);
                int column = Integer.valueOf(fullPosition[1]);
                if (row <= 0 || column <= 0) {
                    return false;
                } else if (!al[row - 1].get(column * 2 - 1).equals("     ")
                        || !al[row - 1].get(column * 2 - 1).equals("     ")) {
                    return false;
                }
                return row <= n && column <= n;
            } catch (Exception e) {
                return false;
            }
        }
    

    public void setMarker(String position, int i,String symbol) {

        //need to make dynamic for any symbol add symbol to parameter.
        String[] fullPosition = position.split(",");
        int row = Integer.valueOf(fullPosition[0]);
        int column = Integer.valueOf(fullPosition[1]);
        

        al[row - 1].set((column * 2) - 1, symbol);

    }

    public boolean aPlayerWon(int size, String[]symbols){
        for(int i=0;i<symbols.length;i++){
            if(won(size,symbols[i])){
                System.out.println("Player " + (i+1)+ " wins!");
                return true;
            }
        }
        return false;

    }

    public boolean won(int size,String symbol) {
        int c = 1;
        for (int i = 0; i < size; i += 1) {
            if (rowCounter(i, size,symbol)) {
                return true;
            } else if (columnCounter(c, size,symbol)) {
                return true;
            } 
            c += 2;
        }
         if (checkDiagonals(size,symbol)) {
            return true;
        }
        return false;
    }

    public boolean rowCounter(int row, int size, String symbol) {//need to make dynamic for any symbol add symbol to parameter.
        int sum = 0;

        for (String position : al[row]) {
            if (position.equals(symbol)) {
                sum += 1;
        }
    }
        return sum == size;

    }

    public boolean columnCounter(int column, int size,String symbol) {//need to make dynamic for any symbol add symbol to parameter.
        int sum = 0;


        for (int i = 0; i < size; i += 1) {
            if (al[i].get(column).equals(symbol)) {
                sum += 1;
        }
    }

        return sum == size;
    }



    public boolean checkDiagonals(int size,String symbol) {//need to make dynamic for any symbol add symbol to parameter.
        int sum1 = 0;

        // int r1 in loop //diagnol NW to SE
        int c1 = 1;

        int sum2 = 0;

        int r2 = size-1;      //diagnol SW to NE
        int c2 = 1;

        for (int r1 = 0; r1 < size; r1 += 1) {
            // table[0][2]
            if (al[r1].get(c1).equals(symbol)) {
                sum1 += 1;
            }

            if (al[r2].get(c2).equals(symbol)) {
                sum2 += 1;
            }

            c1 += 2;
            r2 -= 1;
            c2 +=2;

        }
      
        return sum1 == size || sum2 == size;
        

    }
}


