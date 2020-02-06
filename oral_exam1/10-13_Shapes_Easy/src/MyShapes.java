/**
 * This is a simple program that creates 3 simple 2D and 3 simple 3D objects of random dimensions then prints some of
 * their properties to the window
 *
 * @author Wil Simpson
 */
public class MyShapes
{
    /**
     * Runs the program. This program does not require any runtime arguments and does not check for them
     *
     * @param args command line arguments
     * @throws Exception Throws an exception if the shape isn't an instance of TwoDimensionalShape or
     *                   ThreeDimensionalShape due to the current requirements not having further implementations
     */
    public static void main(String[] args) throws Exception
    {
        //Creates one of each shape with random dimensions
        Shape[] allShapes =
        {
                new Circle(getRandomDimension()),
                new Cube(getRandomDimension()),
                new Sphere(getRandomDimension()),
                new Square(getRandomDimension()),
                new Tetrahedron(getRandomDimension()),
                new Triangle(getRandomDimension(), getRandomDimension())
        };

        //Loop through all of the shapes and print their area. If it is a 3D object print it's volume and surface area
        for(Shape shape : allShapes)
        {
            System.out.print("This shape is a "+shape+". \n\t");

            if(shape instanceof ThreeDimensionalShape)
            {
                ThreeDimensionalShape threeDS = (ThreeDimensionalShape) shape;
                System.out.printf("The surface is %f \n\tThe volume is %f\n",
                        threeDS.getArea(),
                        threeDS.getVolume());
            }
            else if(shape instanceof TwoDimensionalShape)
            {
                TwoDimensionalShape twoDS = (TwoDimensionalShape) shape;
                System.out.printf("The area is %f\n",
                        twoDS.getArea());
            }
            else
            {
                throw new Exception("The program is not handled to use the shape: "+shape);
            }

            System.out.println();
        }
    }

    /**
     * Creates a random number inclusively between 0 and 10
     *
     * @return a random number inclusively between 0 and 10
     */
    public static double getRandomDimension()
    {
        return Math.random()*11;
    }
}
