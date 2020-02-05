/**
 * A Team is very basic and only is associated with a name. A Team is used in conjunction with a Game object to track
 * their progress. A single team can be in multiple games at once.
 *
 * @author Wil Simpson
 */
public class Team
{
    /**
     * Name of the team
     */
    private String name;

    /**
     * Create a new team given a team name
     *
     * @param name name of the team
     */
    public Team(String name)
    {
        this.name = name;
    }

    /**
     * Get the name of the team
     *
     * @return name of the team
     */
    public String getName()
    {
        return name;
    }
}
