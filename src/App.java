import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args){
        // 0 - void, 1 - miss, 20 - ship length 2 alive, 21 - ship length 2 dead, and so on 
        Scanner input = new Scanner(System.in);
        boolean player1Hit = true;
        boolean player2Hit = true;
        List<int[][]> player1Ships = new ArrayList<>();
        List<int[][]> player2Ships = new ArrayList<>();
        
        

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


        showField(player1);
        System.out.println("Player 1, enter your ships");
        addShip(player1, 5, player1Ships, 1);
        //addShip(player1, 4, player1Ships, 1);
        //addShip(player1, 3, player1Ships, 1);
        addShip(player1, 2, player1Ships, 1);
        addShip(player1, 2, player1Ships, 2);
       
        
        

        

        showField(player2);
        System.out.println("Player 2, enter your ships");
        addShip(player2, 5, player2Ships, 1);
        //addShip(player2, 4, player2Ships, 1);
        //addShip(player2, 3, player2Ships, 1);
        //addShip(player2, 2, player2Ships, 1);
        //addShip(player2, 2, player2Ships, 2);
        
        

        while((player1Ships.size() > 0) && (player2Ships.size() > 0)){
            player1Hit = true;
            player2Hit = true;
            while(player1Hit){
                System.out.println("Player 1 turn");
                if(!boom(player2, player2Ships)){
                    player1Hit = false;
                }
                showField(player2);
                if(player2Ships.size() == 0){
                   
                    break;
                }
            }
            if(player2Ships.size() == 0){
                    System.out.println("Player 1 wins");
                    break;
                }

            while(player2Hit){
                System.out.println("Player 2 turn");
                if(!boom(player1, player1Ships)){
                    player2Hit = false;
                }
                showField(player1);
                if(player1Ships.size() == 0){
                    System.out.println("Player 2 wins");
                    break;
                }
            }
            
            
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

    public static void addShip(int[][] field, int length,  List<int[][]> player, int shipNumber){
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
            
            field[coordsRow[i]][coordsColumn[i]] = (length * 100) + (shipNumber * 10);
        }
        int[][] ship = new int[3][6];

        ship[0][0] = length;
        ship[1][0] = shipNumber;
        ship[2][0] = length;



        for(int i = 0; i < length; i++){
            ship[0][i + 1] = coordsRow[i];
            ship[1][i + 1] = coordsColumn[i];
            ship[2][i + 1] = 0;
        }

        
        player.add(ship);
        

        

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


    public static boolean boom(int [][] shotField, List<int[][]> shotPlayer){
        Scanner input = new Scanner(System.in);
        boolean shipIsDead = true;
        int shipNumber;
        int size = 0;
        int row;
        int column;
        System.out.println("Where do you want to shoot");
        row = input.nextInt();
        column = input.nextInt();
        shipNumber = (shotField[row][column] / 10) % 10;
        switch(shotField[row][column]){
            case 0:
                shotField[row][column] = 1;
                System.out.println("You missed");
                return false;
            case 1:
                System.out.println("You have already missed here");
                return false;
            case 511, 521, 411, 421, 311, 321, 211, 221:
                System.out.println("This square was already destroyed");
                return false;   
            default:
                System.out.println("Hit");
                if(shotField[row][column] % 10 == 1){
                    System.out.println("This part of ship is already dead");

                }
                else{
                    size = shotField[row][column] / 100;
                    //looks for the ship that got hit
                    for(int i = 0; i < shotPlayer.size(); i++){
                        if((shotPlayer.get(i)[0][0] == size) && shotPlayer.get(i)[1][0] == shipNumber){
                            //looks which square of the ship got hit
                            for(int u = 1; u < size + 1; u++){
                                //sets the hit square to "1" which means that it has been destroyed
                                if((shotPlayer.get(i)[0][u] == row) && (shotPlayer.get(i)[1][u] == column)){
                                    shotPlayer.get(i)[2][u] = 1;
                                    shotField[row][column] = shotField[row][column] + 1;
                                    //looks if the ship is fully destroyed
                                    for(int j = 1; j < size + 1; j++){
                                        if(shotPlayer.get(i)[2][j] == 0){
                                            shipIsDead = false;
                                            break;
                                        }
                                        
                                        
                                        
                                        
                                    }

                                } 
                            }


                        }
                        if(shipIsDead == true){
                            System.out.println("You have sunk a ship of length " + size);
                            shotPlayer.remove(i);
                            break;
                        }
                    }
                    
                }
                return true;
                
                


            
        }
        
    }


    public static void showField(int[][] field){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(field[i][j] == 0){
                    System.out.print("?" + " ");
                }
                if(field[i][j] == 1){
                    System.out.print("O" + " ");
                }
                if(field[i][j] > 1){
                    if(field[i][j] % 10 == 0){
                        System.out.print('\u25A0' + " ");
                    }
                    else{
                        System.out.print("x" + " ");
                    }
                }
            }
            System.out.println(" ");
        }
    }

    public static void showFieldNumbers(int[][] field){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println(" ");
        }
    }


    
}
