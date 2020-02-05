import java.io.FileWriter;
import java.io.IOException;

public class MyClass
{
    public static final double k_eVK = 8.617*Math.pow(10, -5);
    public static final double k_JK = 1.380649*Math.pow(10, -23);
    public static final double e = 1.602*Math.pow(10, -19);

    public static final double Na = 5*Math.pow(10, 18);
    public static final double Nd = Math.pow(10, 16);
    //public static final double Na = Math.pow(10, 16);
    //public static final double Nd = Math.pow(10, 17);

    public static final double B = 2.1*Math.pow(10, 14);
    //public static final double B = 5.23*Math.pow(10, 15);

    public static final double Eg = 1.4;
    //public static final double Eg = 1.1;

    public static void main(String[] args) throws IOException
    {
        FileWriter csvWriter = new FileWriter("scratch\\test\\peihw2.csv");
        csvWriter.append("\"Temperature (K)\",\"Voltage (V)\"\n");


        for (int i=200; i<500; i+=25)
        {
            csvWriter.append(i+","+calculateVoltage(i));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public static double calculateVoltage(double T)
    {
        return ((k_JK*T)/e)*Math.log((Na*Nd)/Math.pow(calculateNi(T), 2));
    }

    public static double calculateNi(double T)
    {
        return B*Math.pow(T, 3/2.0)*Math.pow(Math.E, (-Eg)/(2.0*k_eVK*T));
    }
}
