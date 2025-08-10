

public class ForwardCheckingMRV extends TennerGrid {

    static int consistency;
    static int assignemnets = 0;
    static int [] finalAssignments  = new int [(ROWS-1)*COLS];
    static int counter = 0;


    static boolean inDomain(int row, int col, int num) { // to checks if it's safe to place a number num at a position in the grid

        
        for (int x = 0; x < COLS; x++) { // Numbers appear only once in a row
            if (grid[row][x] == num) {
                return false;
            }
        }

        
        if (row > 0 && grid[row - 1][col] == num) { // Up
            return false;
        }
        
        if((row+1) != ROWS && grid[row+1][col] == num) { //  down
         return false;
        }
        
        if((col-1) != -1 && grid[row][col-1] == num){ //  left
           return false;
        }
        
        if((col+1) != COLS && grid[row][col+1] == num){ // right
           return false;
        }
        
        if((row-1) != -1 && (col-1) != -1 && grid[row-1][col-1] == num) { // upper left 
           return false; 
        } 
        
        if((row-1) != -1 && (col+1) != COLS && grid[row-1][col+1] == num) { // upper right 
           return false; 
        }
        
        if((row+1) != ROWS && (col-1) != -1 && grid[row+1][col-1] == num) { // lower left 
           return false; 
        }
        
        if((row+1) != ROWS && (col+1) != COLS && grid[row+1][col+1] == num) { // lower right 
           return false; 
        } 

        
        int sum = 0; 
        for (int i = 0; i < ROWS - 1; i++) { // Target sum 
            if (grid[i][col] != -1)
                sum += grid[i][col];
        }
        sum += num;
        return sum <= grid[ROWS - 1][col] ; 
    }


    static boolean isSafe(int row, int col, int num) { // to checks if it's safe to place a number num at a position in the grid

        consistency++;
        for (int x = 0; x < COLS; x++) { // Numbers appear only once in a row
            if (grid[row][x] == num) {
                return false;
            }
        }

        consistency++;
        if (row > 0 && grid[row - 1][col] == num) { // Up
            return false;
        }
        consistency++;
        if((row+1) != ROWS - 1 && grid[row+1][col] == num) { //  down
         return false;
        }
        consistency++;
        if((col-1) != -1 && grid[row][col-1] == num){ //  left
           return false;
        }
        consistency++;
        if((col+1) != COLS && grid[row][col+1] == num){ // right
           return false;
        }
        consistency++;
        if((row-1) != -1 && (col-1) != -1 && grid[row-1][col-1] == num) { // upper left 
           return false; 
        } 
        consistency++;
        if((row-1) != -1 && (col+1) != COLS && grid[row-1][col+1] == num) { // upper right 
           return false; 
        }
        consistency++;
        if((row+1) != ROWS - 1 && (col-1) != -1 && grid[row+1][col-1] == num) { // lower left 
           return false; 
        }
        consistency++;
        if((row+1) != ROWS - 1 && (col+1) != COLS && grid[row+1][col+1] == num) { // lower right 
           return false; 
        } 

        
        int sum = 0; 
        for (int i = 0; i < ROWS - 1; i++) { // Target sum 
            if (grid[i][col] != -1)
                sum += grid[i][col];
        }
        sum += num;
        return sum <= grid[ROWS - 1][col] ; 
    }

    static void updatePossibilities(int row, int col, boolean[][][] possibilities, int num, boolean add) {
        consistency++;
        for (int j = 0; j < COLS; j++) {
            possibilities[row][j][num] = add;
        }
        if (row > 0 && grid[row - 1][col] == num)
            possibilities[row - 1][col][num] = add;
    }

    static boolean forwardCheckMVR(int row, int col, boolean[][][] possibilities) {

        if (row == ROWS) {
            col++;
            row = 0;
        }
        if (col == COLS) {
            return true;
        }
   /*  if (row == ROWS - 1 && col == COLS)
        return true;
    if (col == COLS) {
        row++;
        col = 0;
    }*/

    
    int[] nextEmptyCell = findNextEmptyCellWithMRV(possibilities);// Find the variable with MRV 
    row = nextEmptyCell[0];
    col = nextEmptyCell[1];

    if (row == -1 && col == -1) {// No more variables, the grid is solved
        return true;
    }

    for (int num = 0; num < 10; num++) {
        if (possibilities[row][col][num] && isSafe(row, col, num) && isValid(col)) {
            grid[row][col] = num;
            assignemnets++;
            finalAssignments[++counter] = num;
            updatePossibilities(row, col, possibilities, num, false);
            if (forwardCheckMVR(row + 1, col , possibilities))
                return true;
            grid[row][col] = -1;
            finalAssignments[counter--] = -1;
            updatePossibilities(row, col, possibilities, num, true);
        }
    }
    return false;
}

    static int[] findNextEmptyCellWithMRV(boolean[][][] possibilities) { // loop throug the grid and find the one with MRV
    int minRemainingValues = Integer.MAX_VALUE;
    int[] nextCell = {-1, -1}; // row, col

    for (int row = 0; row < ROWS - 1; row++) {
        for (int col = 0; col < COLS; col++) {
            if (grid[row][col] == -1) {
                int remainingValues = countRemainingValues(possibilities, row, col);
                if (remainingValues < minRemainingValues) {
                    minRemainingValues = remainingValues;
                    nextCell[0] = row;
                    nextCell[1] = col;
                }
            }
        }
    }

    return nextCell;
}

    static int countRemainingValues(boolean[][][] possibilities, int row, int col) {
    int count = 0;
    for (int num = 0; num < 10; num++) {
        if (possibilities[row][col][num] && inDomain(row, col, num)) {
            count++;
        }
    }
    return count;
}

public static boolean isValid(int col) { // check for target sum constraint
    consistency++;
    int sum = 0;
    for (int r = 0; r < ROWS-1; r++) {
        if (grid[r][col] == -1)
        return true;
        sum += grid[r][col];
    }
    return sum == grid[ROWS-1][col];
} 


public static void main(String[] args) {

    for (int i = 0; i < finalAssignments.length; i++)
    finalAssignments[i] = -1;
   
       boolean[][][] pos1 = new boolean[ROWS][COLS][10];  // to keep track of the possibilities for each cell in the grid
       for (int i = 0; i < ROWS - 1; i++)
           for (int j = 0; j < COLS; j++)
               for (int num = 0; num < 10; num++)
                   pos1[i][j][num] = true;
        grid = fillGrid1();
        
       System.out.print("\nSolving 10 by 3 Tenner Grid using Forward checking with MVR huristic : ");
       System.out.println("\n--------Initial State---------");
       printGrid(grid);
       System.out.println(" ------------------------------");
       long startTime = System.nanoTime();
       if (forwardCheckMVR(0, 0, pos1)) {
           long endTime = System.nanoTime();
           System.out.println("Assignments: " + assignemnets);                
           System.out.println("Consistency: " + consistency);
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
   
} }
