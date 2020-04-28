import java.nio.CharBuffer;
import java.util.Arrays;

public class MazeSolver
{
    private char mazeWall;
    private char mazePath;
    private char movedChar;
    private char deadEndChar;

    private long sleepTime;

    private int startingRow;
    private int startingCol;

    public boolean noMoreMovesPossible = false;
    public boolean isStartOfMaze = true;
    public boolean solved = false;

    public MazeSolver(char mazeWall, char mazePath, char movedChar, char deadEndChar, int startingRow, int startingCol, long sleepTime)
    {
        this.mazeWall = mazeWall;
        this.mazePath = mazePath;
        this.movedChar = movedChar;
        this.deadEndChar = deadEndChar;
        this.startingRow = startingRow;
        this.startingCol = startingCol;
        this.sleepTime = sleepTime;
    }

    /**
     *  A maze consists of a 2D char array. Increasing or decreasing the row traverses the maze down or up respectively.
     *  Increase or decreasing the col traverses the maze to the right or left respectively.
     *
     *
     * @param maze
     * @param row
     * @param col
     * @return whether the maze was solved or not
     */
    public boolean mazeTraversal(char[][] maze, int row, int col) throws InterruptedException
    {
        printMaze(maze);
        Thread.sleep(sleepTime);

        isStartOfMaze = startingRow == row && startingCol == col;

        //Maze solved if we reach an edge and we're not at the start
        if(!isStartOfMaze
                &&    (row == 0
                    || row == maze.length-1
                    || col == 0
                    || col == maze.length-1)
                      )
        {
            solved = true;
            maze[row][col] = movedChar;
            return true;
        }

        //Try to move to a new location
        tryMoveTo(maze, row, col, mazePath, movedChar);

        //Now we need to move backwards since no new moves are possible at the current location
        //Change marks from movedChar to deadEndChar
        //Only move if we haven't solved yet.
        if(!solved)
        {
            tryMoveTo(maze, row, col, movedChar, deadEndChar);
        }

        return solved;
    }

    private void tryMoveTo(char[][] maze, int row, int col, char movedChar, char charType) throws InterruptedException
    {
        //Check up
        if(canMoveTo(maze, row-1, col, movedChar))
        {
            maze[row][col] = charType;
            mazeTraversal(maze, row-1, col);
        }
        //Check right
        else if(canMoveTo(maze, row, col+1, movedChar))
        {
            maze[row][col] = charType;
            mazeTraversal(maze, row, col+1);
        }
        //Check down
        else if(canMoveTo(maze, row+1, col, movedChar))
        {
            maze[row][col] = charType;
            mazeTraversal(maze, row+1, col);
        }
        //Check left
        else if(canMoveTo(maze, row, col-1, movedChar))
        {
            maze[row][col] = charType;
            mazeTraversal(maze, row, col-1);
        }
    }

    public boolean canMoveTo(char[][] maze, int row, int col, char moveableChar)
    {
        try
        {
            return maze[row][col] == moveableChar;
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }
    }


    public static void printMaze(char[][] maze)
    {
        System.out.println("--------- PRINTED MAZE --------\n");
        for(int i=0; i<maze.length; i++)
        {
            for(int j=0; j<maze[i].length; j++)
            {
                System.out.print(String.format(" %c ", maze[i][j]));
            }
            System.out.println();
        }
        System.out.println("\n-------------------------------\n");
    }
    public static void printMaze(char[][] maze, char deadEndChar, char mazePath)
    {
        System.out.println("--------- PRINTED MAZE --------\n");
        for(int i=0; i<maze.length; i++)
        {
            for(int j=0; j<maze[i].length; j++)
            {
                System.out.print(String.format(" %c ", deadEndChar == maze[i][j] ? mazePath : maze[i][j]));
            }
            System.out.println();
        }
        System.out.println("\n-------------------------------\n");
    }
}
