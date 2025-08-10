

public class SimpleBacktracking extends TennerGrid {

    static int BTconsistency;
    static int BTassignments;
    static int [] finalAssignments  = new int [(ROWS-1)*COLS];
    static int counter = 0;


        public static void main(String[] args) {

            for (int i = 0; i < finalAssignments.length; i++)
            finalAssignments[i] = -1;

            grid = fillGrid1();
            setDomain(grid);
            System.out.print("\nsolving 10 by 3 Tenner Grid using simple backtracking :");
            System.out.println("\n --------Initial State---------");
            printGrid(grid);
            System.out.println(" ------------------------------"); 
            long startTime = System.nanoTime();
            simpleBacktrak(0,0);
            long endTime = System.nanoTime();
            System.out.println("Assignments: " + BTassignments);
            System.out.println("Consistency: " + BTconsistency);
            System.err.println("final CSP Tenner variable assignments:");
            for (int i = 0; i < finalAssignments.length; i++)
            if (finalAssignments[i]!= -1)
            System.out.print("Assignment " + i + " = " + finalAssignments[i]+" ");            
            System.out.println("\n --------- Final State ---------");
            printGrid(grid);
            System.out.println(" ------------------------------"); 
            System.out.println("Time used to solve the problem: " + (endTime - startTime) + " nanoseconds\n");
        }
     
        public static boolean simpleBacktrak(int row, int col) {
        
            if (row == ROWS) { // go to next column
                col++;
                row = 0;
            }
            if (col == COLS) { // stopping case 1: assignemnet is complete

                return true;
            }
            if (row == ROWS-1 && !isValid(col)) { // stopping case 2: column is not valid
                return false; 
            }  
            if (grid[row][col] == -1) { // if unassigned
                for (int i = 0; i <= 9; i++) { 
                    if (!isUsed(row, i) && !isAdjacent(row, col, i)) { // if value in domain
                        used[row][i] = true; 
                        grid[row][col] = i;
                        finalAssignments[++counter] = i;
                        BTassignments++;
                        if(simpleBacktrak(row + 1, col )) {
                            return true;
                        } 
                        grid[row][col] = -1; // bakctrack if assignment fails
                        used[row][i] = false; 
                        BTconsistency++;
                        finalAssignments[counter--] = -1;
                    
                }
            }
            return false;
          }
        return simpleBacktrak(row + 1, col ); 
            
         
        }

        public static boolean isUsed(int r, int i) { // check for row constraint
        BTconsistency++;
        return used[r][i];
        }
         
         public static boolean isValid(int col) { // check for target sum constraint
            int sum = 0;
            for (int r = 0; r < ROWS-1; r++) {
                sum += grid[r][col];
            }
            BTconsistency++;
            return sum == grid[ROWS-1][col];
        }

        public static boolean isAdjacent (int r, int c, int i) { //check for connected cells constraint
             BTconsistency++;
             if ((r-1) != -1 && grid[r-1][c] == i) { //  up
               return true;
             }
             BTconsistency++;
             if((r+1) != ROWS - 1 && grid[r+1][c] == i) { //  down
              return true;
             }
             BTconsistency++;
             if((c-1) != -1 && grid[r][c-1] == i){ //  left
                return true;
             }
             BTconsistency++;
             if((c+1) != COLS && grid[r][c+1] == i){ // right
                return true;
             }
             BTconsistency++;
             if((r-1) != -1 && (c-1) != -1 && grid[r-1][c-1] == i) { // upper left 
                return true; 
             } 
             BTconsistency++;
             if((r-1) != -1 && (c+1) != COLS && grid[r-1][c+1] == i) { // upper right 
                return true; 
             }
             BTconsistency++;
             if((r+1) != ROWS - 1 && (c-1) != -1 && grid[r+1][c-1] == i) { // lower left 
                return true; 
             }
             BTconsistency++;
             if((r+1) != ROWS - 1 && (c+1) != COLS && grid[r+1][c+1] == i) { // lower right 
                return true; 
             }                                                    
             return false;
           }
        
 public static void setDomain (int[][] grid) { 

    for (int i = 0; i < ROWS-1; i++) {
        for (int j = 0; j < COLS; j++) {
            BTconsistency++;
             if (grid[i][j] != -1 ) 
             used[i][grid[i][j]] = true; 
        }
    }
 }
     }
        
    
        


    

    

