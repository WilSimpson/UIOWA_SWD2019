/**
 * Solves a 2D maze given an array of positions of the map.
 *
 * @author Wil Simpson
 */
public class MazeSolver
{
    /**
     * Char that cannot be traversed
     */
    private char mazeWall;

    /**
     * Char that can be traversed
     */
    private char mazePath;

    /**
     * Char to mark that as a correct maze path
     */
    private char movedChar;

    /**
     * Char to mark path leads to a deadend
     */
    private char deadEndChar;

    /**
     * Time to sleep between maze traversals
     */
    private long sleepTime;

    /**
     * Starting row position, starting index at 0
     */
    private int startingRow;

    /**
     * Starting col position, starting index at 0
     */
    private int startingCol;

    /**
     * Whether the current mazeTraversal is at the starting point
     */
    public boolean isStartOfMaze = true;

    /**
     * Whether the current mazeTraversal has been solved
     */
    public boolean solved = false;

    /**
     * Initiailize a MazeSolver with the given parameters
     *
     * @param mazeWall non-traversable position
     * @param mazePath traversable position
     * @param movedChar marker to show that moved to
     * @param deadEndChar marker to show a deadend has been reached on that path
     * @param startingRow starting row in maze
     * @param startingCol starting col in maze
     * @param sleepTime sleep time between maze traversals
     */
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
     * @param maze 2D maze to traverse
     * @param row current row position
     * @param col current col position
     * @return whether the maze was solved or not
     *
     * @throws InterruptedException thrown if thread is interrupted during sleep
     */
    public boolean mazeTraversal(char[][] maze, int row, int col) throws InterruptedException
    {
        printMaze(maze);
        Thread.sleep(sleepTime);

        boolean isStartOfMaze = startingRow == row && startingCol == col;

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

    /**
     * Moves to the position if possible and marks it with the given charType
     *
     * @param maze 2D maze to traverse
     * @param row current row to move from
     * @param col current col to move from
     * @param movedChar traversable char
     * @param charType char to use as mark
     *
     * @throws InterruptedException thrown if thread is interrupted during sleep
     */
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

    /**
     * Checks if the given char is traversable
     *
     * @param maze 2D maze to check against
     * @param row row to check
     * @param col col to check
     * @param moveableChar acceptable char to move to
     * @return true if it can move to
     */
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


    /**
     * Prints a given maze
     *
     * @param maze maze to print
     */
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

    /**
     * Prints a given maze and replaces instances of the deadEndChar with the mazePath char
     *
     * @param maze maze to print
     * @param deadEndChar char that will be replaced
     * @param mazePath char that will replace
     */
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
