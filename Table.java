
import java.util.Scanner;

public class Table {

    private Scanner scanner; // 1st space=index[0][2],2nd=index[0][6],3rd=index[0][10]
    private String table[][] = { { "|", "  ", " ", "  ", "|", "  ", " ", "  ", "|", "  ", " ", "  ", "|" },
            { "-------------------" }, { "|", "  ", " ", "  ", "|", "  ", " ", "  ", "|", "  ", " ", "  ", "|" },
            { "-------------------" }, { "|", "  ", " ", "  ", "|", "  ", " ", "  ", "|", "  ", " ", "  ", "|" } };

    public Table() {
        this.scanner = new Scanner(System.in);
    }

    public void setMarker(String position, int i) {
        String marker;

        if (i % 2 == 1) {
            marker = "X";
        } else {
            marker = "O";
        }

        if (position.equals("1,1")) {
            table[0][2] = marker;
            
        } else if (position.equals("1,2")) {
            table[0][6] = marker;
            
        } else if (position.equals("1,3")) {
            table[0][10] = marker;
            
        } else if (position.equals("2,1")) {
            table[2][2] = marker;
            
        } else if (position.equals("2,2")) {
            table[2][6] = marker;
            
        } else if (position.equals("2,3")) {
            table[2][10] = marker;
            
        } else if (position.equals("3,1")) {
            table[4][2] = marker;
            
        } else if (position.equals("3,2")) {
            table[4][6] = marker;
            
        } else if (position.equals("3,3")) {
            table[4][10] = marker;
        }
    }



    public boolean isOkay(String input){

        if(input.equals("1,1")&&(table[0][2].equals(" "))){
            return true;
        }
        else if(input.equals("1,2")&&(table[0][6].equals(" "))){
            return true;
        }
        else if(input.equals("1,3")&&(table[0][10].equals(" "))){
            return true;
        }
        else if(input.equals("2,1")&&(table[2][2].equals(" "))){
            return true;
        }
        else if(input.equals("2,2")&&(table[2][6].equals(" "))){
            return true;
        }
        else if(input.equals("2,3")&&(table[2][10].equals(" "))){
            return true;
        }
        else if(input.equals("3,1")&&(table[4][2].equals(" "))){
            return true;
        }
        else if(input.equals("3,2")&&(table[4][6].equals(" "))){
            return true;
        }
        else if(input.equals("3,3")&&(table[4][10].equals(" "))){
            return true;
        }
        else{
            return false;
        }
    }

    public void start() {
    
        int i = 1;
        while (true) {
           
            if(i==10){
                System.out.println("It's a tie! Game over.");
                break;
            }
            else if (i % 2 == 1) {
                System.out.println("Player 1: you are X's. Make a move.");
            } else {
                System.out.println("Player 2: you are O's. Make a move.");
            }
            String input = scanner.nextLine();
            if (isOkay(input)==false) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            setMarker(input, i);

            rowCounter(0);
            print();

            if (checkWinner() && i % 2 == 1) {
                System.out.println("Player 1 wins!");
                break;
            } else if (checkWinner() && i % 2 == 0) {
                System.out.println("Player 2 wins!");
                break;
            }
            i++;
        }
    }

    public void print() {

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                System.out.print(table[row][column]);
            }
            System.out.println();
        }

    }

    public boolean checkWinner() {
        int c=2;
        for (int i = 0; i <= 4; i += 2) {
            if (rowCounter(i)) {
                return true;
            }
            else if(columnCounter(c)){
                return true;
            }
            else if(checkDiagonals()){
                return true;
            }
                c+=4;
        }
        return false;
    }

    public boolean rowCounter(int row) {
        int xSum = 0;
        int oSum = 0;

        for (int i = 2; i <= 10; i += 4) {
            if (table[row][i].equals("X")) {
                xSum += 1;
            } else if (table[row][i].equals("O")) {
                oSum += 1;
            }
        }
        if (xSum == 3 || oSum == 3) {
            return true;
        }
        return false;

    }

    public boolean columnCounter(int column) {
        int xSum = 0;
        int oSum = 0;

        for(int i=0;i<=4;i+=2){
            if(table[i][column].equals("X")){
                xSum+=1;
            }else if(table[i][column].equals("O")){
                oSum+=1;
            }
        }

        if (xSum == 3 || oSum == 3) {
            return true;
        }
        return false;
    }

    public boolean checkDiagonals(){
        int xSum1=0;
        int oSum1=0;

        //int r1 in loop
        int c1=2;

        int xSum2=0;
        int oSum2=0;

        int r2=0;
        int c2=10;

        for(int r1=0;r1<=4;r1+=2)
        {
                //table[0][2]
            if(table[r1][c1]=="X"){
                xSum1+=1;
            }
            else if(table[r1][c1]=="O"){
                oSum1+=1;
            }

            if(table[r2][c2]=="X"){
                xSum2+=1;
            }
            else if(table[r2][r2]=="O"){
                oSum2+=1;
            }
            
            c1+=4;
            r2+=2;
            c2-=4;

        }
        if(xSum1==3 || oSum1==3){
            return true;
        }
        else if(xSum2==3 || oSum2==3){
            return true;
        }
        return false;
        
    }
}

