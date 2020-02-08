import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * Creates predefined arrays with data and has methods to randomly get data from the arrays
 *
 * @author swd
 */
class DataGenerator
{
    // fields to hold example data
    /**
     * Used to generate random numbers
     */
    private Random rand;

    /**
     * Assorted names
     */
    private String[] names;

    /**
     * Assorted encryption schemes
     */
    private String[] encryption;

    /**
     * Assorted video titles
     */
    private String[] videoTitles;

    /**
     * Assorted file paths
     */
    private String[] filePaths;

    /**
     * Assorted file types
     */
    private String[] fileTypes;

    /**
     * Assorted colors
     */
    private String[] colors;

    /**
     * Initializes all of the dummy data
     */
    DataGenerator()
    {
        // initialize with example data
        this.rand = new Random();
        this.names = new String[]{"Tom", "Guadalupe", "Tina", "Markus", "Michael", "Diego", "Mr. Patel", "Alex"};
        this.encryption = new String[]{"Vigenere", "One Time Pad", "RSA", "Diffie-Hellman"};
        this.videoTitles = new String[]{"Top 10 Cutest Cat Videos", "ONE Simple Trick to Pass SWD", "Java 101", "Pythonistas Pythoning with Pythons", "P == NP?!?!?!"};
        this.filePaths = new String[]{"/Users/abpwrs/puppies", "/Users/tomc/backstroke", "/Users/swd_student/question", "/Users/cie_student/question", "/Users/student/stress"};
        this.fileTypes = new String[]{"txt", "csv", "pptx", "pdf", "hs", "gif", "png", "h5"};
        this.colors = new String[]{"red", "orange", "yellow", "green", "blue", "indigo", "violet", "egg shell"};
    }

    /**
     * Gets a random name from the names array
     *
     * @return random name
     */
    private String getRandName()
    {
        return this.names[this.rand.nextInt(this.names.length)];
    }

    /**
     * Gets a random color from the colors array
     *
     * @return random color
     */
    private String getRandColor()
    {
        return this.colors[this.rand.nextInt(this.colors.length)];
    }

    /**
     * Gets a random video title from the videoTitles array
     *
     * @return random video title
     */
    private String getRandVideoTitle()
    {
        return this.videoTitles[this.rand.nextInt(this.videoTitles.length)];
    }

    /**
     * Gets a random file type from the fileTypes array
     *
     * @return random file type
     */
    private String getRandFileType()
    {
        return this.fileTypes[this.rand.nextInt(this.fileTypes.length)];
    }

    /**
     * Gets a random file path from the filePaths array
     *
     * @return random file path
     */
    private String getRandFilePath()
    {
        return this.filePaths[this.rand.nextInt(this.filePaths.length)];
    }

    /**
     * Creates a random URL
     *
     * @return random url
     */
    public String getRandURL()
    {
        return "localhost:" + (((this.rand.nextInt(9) + 1) * 1000) + this.rand.nextInt(999));
    }

    /**
     * Creates a random payment
     *
     * @return random payment
     */
    public Payment getRandPayment()
    {
        return new Payment(this.getRandName(), this.rand.nextInt(10000), this.getRandName());
    }

    /**
     * Creates a random UUID
     *
     * @return a random uuid
     */
    public UUID getRandUUID()
    {
        return UUID.randomUUID();
    }

    /**
     * Gets a random encryption scheme from the encryptions array
     *
     * @return random encryption scheme
     */
    public String getRandEncryptionScheme()
    {
        return this.encryption[this.rand.nextInt(this.encryption.length)];
    }

    /**
     * Creates a random video
     *
     * @return random video
     */
    public Video getRandVideo()
    {
        return new Video(this.getRandURL(), this.getRandVideoTitle(), this.getRandName());
    }

    /**
     * Creates a random file
     *
     * @return random file
     */
    public File getRandFile()
    {
        return new File(this.getRandFilePath(), this.getRandFileType());
    }

    /**
     * Creates a random form with 3 fields: favorite color, favorite encryption scheme and name with random values for
     * each
     *
     * @return random form
     */
    public Form getRandForm()
    {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Favorite color", this.getRandColor());
        fields.put("Favorite encryption scheme", this.getRandEncryptionScheme());
        fields.put("Name", this.getRandName());
        return new Form(fields);
    }

    /**
     * Creates a random IPv4 address
     *
     * @return random IPv4 address
     */
    public String getRandIP()
    {
        return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
    }

}