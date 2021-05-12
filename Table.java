import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    
    private Scanner scanner;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;
    private ArrayList<String> list4;
    private ArrayList<String> list5;

    public Table() {
        this.scanner = new Scanner(System.in);
        this.list1 = new ArrayList<String>();
        this.list2 = new ArrayList<String>();
        this.list3 = new ArrayList<String>();
        this.list4 = new ArrayList<String>();
        this.list5 = new ArrayList<String>();
    }

    
    public void setLists(){
        //make a loop to add quicker. and less autistic
        this.list1.add(" ");this.list1.add("|");this.list1.add(" ");this.list1.add("|");this.list1.add(" ");
        this.list2.add("-");this.list2.add("-");this.list2.add("-");this.list2.add("-");this.list2.add("-");
        this.list3.add(" ");this.list3.add("|");this.list3.add(" ");this.list3.add("|");this.list3.add(" ");
        this.list4.add("-");this.list4.add("-");this.list4.add("-");this.list4.add("-");this.list4.add("-");
        this.list5.add(" ");this.list5.add("|");this.list5.add(" ");this.list5.add("|");this.list5.add(" ");
    }

    public void input(){
        setLists();
        while(true){

            System.out.println("Player 1: make a move.");
            String input = scanner.nextLine();

            if(input.equals("-1")){
                break;
            }

            if(input.equals("0,0")){
                list1.set(0, "X");
                continue;
            }
            if(input.equals("0,1")){
                list1.set(3, "X");
            }

            print();
        }
        

       
    }
    public void print(){
        
        for(String x:list1){
            System.out.print(x);
        }
        System.out.println("");
        for(String x:list2){
            System.out.print(x);
        }
        System.out.println("");
        for(String x:list3){
            System.out.print(x);
        }
        System.out.println("");
        for(String x:list4){
            System.out.print(x);
        }
        System.out.println("");
        for(String x:list5){
            System.out.print(x);
        }
    }
}
