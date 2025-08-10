

public class TennerGrid {

static final int ROWS = 3;
static final int COLS = 10;
static int[][] grid = new int [ROWS][COLS];
static boolean[][] used = new boolean[ROWS-1][COLS]; // check for row constraint

public static int [][] fillGrid1 () {

    int [][] grid = new int [ROWS][COLS];
    grid[0][0] = -1;   grid[1][0] = 7;    grid[2][0] = 15;
    grid[0][1] = 2;    grid[1][1] = -1;   grid[2][1] = 11;
    grid[0][2] = 3;    grid[1][2] = 8;    grid[2][2] = 11;
    grid[0][3] = -1;   grid[1][3] = -1;   grid[2][3] = 8;
    grid[0][4] = 0;    grid[1][4] = 3;    grid[2][4] = 3;
    grid[0][5] = -1;   grid[1][5] = -1;   grid[2][5] = 9;
    grid[0][6] = -1;   grid[1][6] = 2;    grid[2][6] = 8;
    grid[0][7] = -1;   grid[1][7] = -1;   grid[2][7] = 6;
    grid[0][8] = -1;   grid[1][8] = 0;    grid[2][8] = 9;
    grid[0][9] = -1;   grid[1][9] = -1;   grid[2][9] = 10;

    return grid;

 }

 public static int [][] fillGrid2 () {

    int [][] grid = new int [ROWS][COLS];
    grid[0][0] = 1;   grid[1][0] = -1;    grid[2][0] = 5;
    grid[0][1] = -1;    grid[1][1] = -1;   grid[2][1] = 12;
    grid[0][2] = 8;    grid[1][2] = -1;    grid[2][2] = 10;
    grid[0][3] = -1;   grid[1][3] = 5;   grid[2][3] = 12;
    grid[0][4] = 3;    grid[1][4] = 6;    grid[2][4] = 9;
    grid[0][5] = 4;   grid[1][5] = -1;   grid[2][5] = 4;
    grid[0][6] = -1;   grid[1][6] = -1;    grid[2][6] = 6;
    grid[0][7] = -1;   grid[1][7] = 9;   grid[2][7] = 9;
    grid[0][8] = 2;   grid[1][8] = -1;    grid[2][8] = 10;
    grid[0][9] = -1;   grid[1][9] = -1;   grid[2][9] = 13;

    return grid;

 }

 public static int [][] fillGrid3 () {

    int [][] grid = new int [ROWS][COLS];
    grid[0][0] = 7;   grid[1][0] = 6;    grid[2][0] = 13;
    grid[0][1] = -1;    grid[1][1] = 4;   grid[2][1] = 7;
    grid[0][2] = -1;    grid[1][2] = 0;    grid[2][2] = 8;
    grid[0][3] = -1;   grid[1][3] = -1;   grid[2][3] = 9;
    grid[0][4] = -1;    grid[1][4] = 7;    grid[2][4] = 16;
    grid[0][5] = -1;   grid[1][5] = 8;   grid[2][5] = 8;
    grid[0][6] = 2;   grid[1][6] = -1;    grid[2][6] = 7;
    grid[0][7] = -1;   grid[1][7] = -1;   grid[2][7] = 10;
    grid[0][8] = -1;   grid[1][8] = 2;    grid[2][8] = 6;
    grid[0][9] = -1;   grid[1][9] = -1;   grid[2][9] = 6;

    return grid;

 }

 public static int [][] fillGrid4 () {

   int [][] grid = new int [ROWS][COLS];
   grid[0][0] = 4;   grid[1][0] = -1;    grid[2][0] = 9;
   grid[0][1] = 2;    grid[1][1] = -1;   grid[2][1] = 9;
   grid[0][2] = 9;    grid[1][2] = -1;    grid[2][2] = 9;
   grid[0][3] = -1;   grid[1][3] = -1;   grid[2][3] = 9;
   grid[0][4] = 1;    grid[1][4] = 4;    grid[2][4] = 5;
   grid[0][5] = -1;   grid[1][5] = -1;   grid[2][5] = 3;
   grid[0][6] = -1;   grid[1][6] = -1;    grid[2][6] = 14;
   grid[0][7] = -1;   grid[1][7] = 1;   grid[2][7] = 4;
   grid[0][8] = -1;   grid[1][8] = -1;    grid[2][8] = 14;
   grid[0][9] = 8;   grid[1][9] = 6;   grid[2][9] = 14;

   return grid;

}

public static int [][] fillGrid5 () {

   int [][] grid = new int [ROWS][COLS];
   grid[0][0] = -1;   grid[1][0] = -1;    grid[2][0] = 7;
   grid[0][1] = 6;    grid[1][1] = 0;   grid[2][1] = 6;
   grid[0][2] = 2;    grid[1][2] = 1;    grid[2][2] = 3;
   grid[0][3] = 0;   grid[1][3] = 7;   grid[2][3] = 7;
   grid[0][4] = -1;    grid[1][4] = 8;    grid[2][4] = 17;
   grid[0][5] = -1;   grid[1][5] = -1;   grid[2][5] = 7;
   grid[0][6] = -1;   grid[1][6] = -1;    grid[2][6] = 8;
   grid[0][7] = 8;   grid[1][7] = -1;   grid[2][7] = 12;
   grid[0][8] = 5;   grid[1][8] = 9;    grid[2][8] = 14;
   grid[0][9] = 7;   grid[1][9] = -1;   grid[2][9] = 9;

   return grid;

}


public static void printGrid(int[][] grid) {
    for (int i = 0; i < TennerGrid.ROWS; i++) {
        System.out.print("|");
        for (int j = 0; j < TennerGrid.COLS; j++)
        if(grid[i][j] != -1)
        System.out.printf( "%2d ", grid[i][j]);
        else
        System.out.printf( "%2s ", " ");
        System.out.println("|");
        }
}

}
