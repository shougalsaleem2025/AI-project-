


public class ForwardChecking extends TennerGrid {

    static int FCconsistency = 0;
    static int FCassignments;
    static int [] finalAssignments  = new int [(ROWS-1)*COLS];
    static int counter = 0;

    static boolean forwardCheck(int row, int col, boolean[][][] possibilities) {

        if (row == ROWS) {
            col++;
            row = 0;
        }
        if (col == COLS) {
            return true;
        }

        if (grid[row][col] != -1)
            return forwardCheck(row + 1, col , possibilities);

        for (int num = 0; num < 10; num++) {
            if (possibilities[row][col][num] && isSafe(row, col, num)) {
                grid[row][col] = num;
                finalAssignments[++counter] = num;
                FCassignments++;
                updatePossibilities(row, col, possibilities, num, false);
                if (forwardCheck(row + 1, col, possibilities))
                    return true;
                grid[row][col] = -1;
                finalAssignments[counter--] = -1;
                updatePossibilities(row, col, possibilities, num, true);
            }
        }
        return false;
    }

    static boolean isSafe(int row, int col, int num) { // to checks if it's safe to place a number num at a position in the grid

        FCconsistency++;
        for (int x = 0; x < COLS; x++) { // Numbers appear only once in a row
            if (grid[row][x] == num) {
                return false;
            }
        }

        FCconsistency++;
        if (row > 0 && grid[row - 1][col] == num) { // Up
            return false;
        }
        FCconsistency++;
        if((row+1) != ROWS - 1 && grid[row+1][col] == num) { //  down
         return false;
        }
        FCconsistency++;
        if((col-1) != -1 && grid[row][col-1] == num){ //  left
           return false;
        }
        FCconsistency++;
        if((col+1) != COLS && grid[row][col+1] == num){ // right
           return false;
        }
        FCconsistency++;
        if((row-1) != -1 && (col-1) != -1 && grid[row-1][col-1] == num) { // upper left 
           return false; 
        } 
        FCconsistency++;
        if((row-1) != -1 && (col+1) != COLS && grid[row-1][col+1] == num) { // upper right 
           return false; 
        }
        FCconsistency++;
        if((row+1) != ROWS - 1 && (col-1) != -1 && grid[row+1][col-1] == num) { // lower left 
           return false; 
        }
        FCconsistency++;
        if((row+1) != ROWS - 1 && (col+1) != COLS && grid[row+1][col+1] == num) { // lower right 
           return false; 
        } 

        FCconsistency++;
        int sum = 0; 
        for (int i = 0; i < ROWS - 1; i++) { // Target sum 
            if (grid[i][col] != -1)
                sum += grid[i][col];
        }
        sum += num;
        return  (row == ROWS - 2 ? sum == grid[ROWS - 1][col] : true);
    }


    static void updatePossibilities(int row, int col, boolean[][][] possibilities, int num, boolean add) {

        for (int j = 0; j < COLS; j++) {
            FCconsistency++;
            possibilities[row][j][num] = add;
        }
        if (row > 0 && grid[row - 1][col] == num) {
            FCconsistency++;
            possibilities[row - 1][col][num] = add;}
    }




    public static void main(String[] args) {

         for (int i = 0; i < finalAssignments.length; i++) // initlize final assignments
         finalAssignments[i] = -1;
        
            boolean[][][] pos1 = new boolean[ROWS][COLS][10];  // to keep track of the possibilities 
            for (int i = 0; i < ROWS - 1; i++)
                for (int j = 0; j < COLS; j++)
                    for (int num = 0; num < 10; num++) {  // initially all values are possiable
                        pos1[i][j][num] = true;
                    }
                    
            grid = fillGrid5();
            System.out.print("\nSolving 10 by 3 Tenner Grid using Forward checking : ");
            System.out.println("\n--------Initial State---------");
            printGrid(grid);
            System.out.println(" ------------------------------");
            long startTime = System.nanoTime();
            if (forwardCheck(0, 0, pos1)) {
                long endTime = System.nanoTime();
                System.out.println("Assignments: " + FCassignments);                
                System.out.println("Consistency: " + FCconsistency);
                System.err.println("final CSP Tenner variable assignments:");
                for (int i = 0; i < finalAssignments.length; i++)
                if (finalAssignments[i]!= -1)
                System.out.print("Assignment " + i + " = " + finalAssignments[i]+" ");
                System.out.println("\n --------- Final State ---------");
                printGrid(grid);
                System.out.println(" ------------------------------");                
                System.out.println("Time used to solve the problem: " + (endTime - startTime) + " nanoseconds\n");
            } else {
                System.out.println("No Solution exists");
            }
        
    }
}
