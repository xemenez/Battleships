import java.util.Scanner;


public class App {
    public static void main(String[] args){
        // 0 - void, 1 - miss, 20 - ship length 2 alive, 21 - ship length 2 dead, and so on 
        Scanner input = new Scanner(System.in);
        
        

        int[][] player1 = {
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


        int[][] player2 = {
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


        System.out.println("Player 1 field");
        for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(player1[i][j] == 0){
                        System.out.print("?" + " ");
                    }
                }
                System.out.println(" ");
        }


        System.out.println("Player 1, please enter your ships");

        addShip(player1, 5);
        addShip(player1, 4);
        addShip(player1, 3);
        addShip(player1, 2);
        addShip(player1, 2);

        
        System.out.println("Player 2 field");
        for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(player2[i][j] == 0){
                        System.out.print("?" + " ");
                    }
                }
                System.out.println(" ");
        }


        System.out.println("Player 2, please enter your ships");

        addShip(player2, 5);
        addShip(player2, 4);
        addShip(player2, 3);
        addShip(player2, 2);
        addShip(player2, 2);
        


        

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


    public static boolean canPlace(int[][] field, int[] row, int[] column, int length){
        for(int i = row[0] - 1; i <= row[length - 1] + 1; i++){
            for(int j = column[0] - 1; j <= column[length - 1] + 1; j++){
                if((row[0] - 1 < 0) || (row[length - 1] + 1 > 9) || (column[0] - 1 < 0) || (column[length - 1] + 1 > 9)) {
                    continue;
                }
                if(field[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void addShip(int[][] field, int length){
        Scanner input = new Scanner(System.in);
        String orientation; 
        int[] coordsRow = new int[5];
        int[] coordsColumn = new int[5];
        boolean is_a_ship = false;
        int goodRow;
        int goodColumn;
        // outer ship entering cycle for ship length 5
        do{
            is_a_ship = false;
            

            System.out.println("You are entering a ship with length of " + length + ". Enter if H if you want it to be horizontal of V if vertical");
            orientation = input.next();
            while(!orientation.equals("H") && !orientation.equals("V") ){
                System.out.println("Choose either H or V");
                orientation = input.next();
                
            }
            System.out.println("Enter the coordinates of the each square of the ship");
            while(!is_a_ship){
                for(int i = 0; i < length; i++){
                    goodRow = input.nextInt();
                    while(goodRow < 0 || goodRow >= 10){
                        System.out.println("Out of bounds; enter a number from 0 to 9");
                        goodRow = input.nextInt();
                    }
                    coordsRow[i] = goodRow;
                    goodColumn = input.nextInt();
                    while(goodColumn < 0 || goodColumn >= 10){
                        System.out.println("Out of bounds; enter a number from 0 to 9");
                        goodColumn = input.nextInt();
                    }
                    coordsColumn[i] = goodColumn;
                }
                
                if(orientation.equals("H")){
                    is_a_ship = isAShip(coordsRow, coordsColumn,length);
                }


                else{
                    is_a_ship = isAShip(coordsColumn, coordsRow,length);
                }
                if(!is_a_ship){
                    System.out.println("Not a ship. Enter the coordinates again");
                }
                
            }
            
            if(!canPlace(field, coordsRow, coordsColumn, length)) {
                System.out.println("No space. Enter the ship again");
                
            }
        
            


        }while(!canPlace(field, coordsRow, coordsColumn, length));
                
        for(int i = 0; i < length; i++){
            
            field[coordsRow[i]][coordsColumn[i]] = length * 10;
        }
        System.out.println("Ship placed succesfully");
        
        
        
        




        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(field[i][j] == 0){
                    System.out.print("?" + " ");
                }
                if(field[i][j] > 1){
                    System.out.print('\u25A0' + " ");
                }
            }
            System.out.println(" ");
        }
        
        
        System.out.println("");	    
        System.out.println("");	
        System.out.println("");	
    }
}
