/**
 * Drives the simulation.
 *
 * @author Wil Simpson
 */
public class ChangeComputation
{
    /**
     * Start of the program. Initiates the simulation and runs it. No commandline arguments are used or checked.
     *
     * @param args commandline arguments
     */
    public static void main(String[] args)
    {
        ChangeSimulation changeSimulation = new ChangeSimulation();
        changeSimulation.run();
    }
}
