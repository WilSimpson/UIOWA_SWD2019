/**
 *
 *
 * @author Wil Simpson
 */
public class MazeTraversal
{
    /**
     * Example maze
     */
    private static char [][] maze = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#'},
            {'.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.'},
            {'#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '#'},
            {'#', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
            {'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
            {'#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
    };

    /**
     * Shows how the MazeSolver can be used to solve a given maze. No runtime arguments are needed.
     *
     * @param args runtime arguments
     *
     * @throws InterruptedException thrown if the sleep is interrupted
     */
    public static void main(String[] args) throws InterruptedException
    {
        int startRow = 2;
        int startCol = 0;
        char mazeWall = '#';
        char mazePath = '.';
        char movedChar = 'x';
        char deadEndChar = '0';
        long sleepTime = 000l;

        MazeSolver mazeSolver = new MazeSolver(mazeWall, mazePath, movedChar, deadEndChar, startRow, startCol, sleepTime);
        boolean solved = mazeSolver.mazeTraversal(maze, startRow, startCol);

        String outcome = solved ? "SOLVED!" : "NOT POSSIBLE TO SOLVE!";
        System.out.println("\n\n\n"+outcome);
        MazeSolver.printMaze(maze, deadEndChar, mazePath);
    }
}
