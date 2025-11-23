import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int[][] field = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        String orientation; 
        int[] coordsRow = new int[5];
        int[] coordsColumn = new int[5];
        boolean is_a_ship = false;
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(field[i][j] == 0){
                    System.out.print("?" + " ");
                }
            }
            System.out.println(" ");
        }

        System.out.println("You are entering a ship with length of 5. Enter if H if you want it to be horizontal of V if vertical");
        orientation = input.next();
        System.out.println("Enter the coordinates of the each square of the ship");
        for(int i = 0; i < 5; i++){
                coordsRow[i] = input.nextInt();
                coordsColumn[i] = input.nextInt();
        }
        
        if(orientation.equals("H")){
            is_a_ship = isAShip(coordsRow, coordsColumn,5);
        }
        else if(orientation.equals("V")){
            is_a_ship = isAShip(coordsColumn, coordsRow,5);
        }
        else{
            System.out.println("You did not choose an orientation");
        }
        if(is_a_ship){
            for(int i = 0; i < 5; i++){
                
                field[coordsRow[i]][coordsColumn[i]] = 5;
            }
            System.out.println("Ship placed succesfully");
        }




        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(field[i][j] == 0){
                    System.out.print("?" + " ");
                }
                if(field[i][j] == 5){
                    System.out.print('\u25A0' + " ");
                }
            }
            System.out.println(" ");
        }



        

    }


    public static boolean isAShip(int[] row, int[] column, int length){
        
        
        for(int i = 0; i < length - 1; i++){
            if(row[i] != row[i + 1]){
                return false;
            }
            if(column[i] != column[i + 1] - 1){
                return false;
            }
        }
        return true;
        
    

        
    }

}
