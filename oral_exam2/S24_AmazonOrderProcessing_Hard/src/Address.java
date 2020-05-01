/**
 * Represents a house address
 *
 * @author Wil Simpson
 */
public class Address
{
    /**
     * Street address
     */
    private String address;

    /**
     * Name of city
     */
    private String city;

    /**
     * Name of state
     */
    private String state;

    /**
     * Address zip code
     */
    private String zip;

    /**
     * Initializes the member variables with the given parameters
     *
     * @param address street address
     * @param city city name
     * @param state state name
     * @param zip address zip
     */
    public Address(String address, String city, String state, String zip)
    {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Gets the address
     *
     * @return street address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Sets the address to the given address
     *
     * @param address new address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Gets the city
     *
     * @return city name
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city to the given city
     *
     * @param city new city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets the state
     *
     * @return state name
     */
    public String getState()
    {
        return state;
    }

    /**
     * Sets the state to the given state
     *
     * @param state new state
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Gets the zip
     *
     * @return zip code
     */
    public String getZip()
    {
        return zip;
    }

    /**
     * Sets the zip to the given zip
     *
     * @param zip new zip
     */
    public void setZip(String zip)
    {
        this.zip = zip;
    }

    /**
     * Generates a human readable string from the instance variables
     *
     * @return string representation
     */
    @Override
    public String toString()
    {
        return "Address: "+address+", "+city+", "+state+" "+zip;
    }
}
