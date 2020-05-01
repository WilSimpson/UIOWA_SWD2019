public class DebugUtil
{
    public static final MessagePriority minPriority = MessagePriority.NORMAL;

    public static void debug(String message, MessagePriority priority)
    {
        if(priority.getValue() <= minPriority.getValue()) System.out.println(message);
    }

    public enum MessagePriority
    {
        HIGH(1), NORMAL(2), LOW(3);

        int value;

        MessagePriority(int value) {this.value=value;}

        public int getValue() {return value;}
    }
}
