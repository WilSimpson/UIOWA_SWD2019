/**
 * @TODO Write javadocs
 *
 * @author Wil Simpson
 */
public class MyShapes {
    public static void main(String[] args) {
        Shape[] allShapes = {
                new Circle(1),
                new Cube(2),
                new Sphere(3),
                new Square(4),
                new Tetrahedron(5),
                new Triangle(6, 7)
        };

        for(Shape shape : allShapes) {
            String textOutput;
            System.out.print("This shape is a "+shape.getClass().getName()+". ");

            if(shape instanceof ThreeDimensionalShape) {
                ThreeDimensionalShape threeDS = (ThreeDimensionalShape) shape;
                System.out.printf("The surface is %f and the volume is %f\n",
                        threeDS.getArea(),
                        threeDS.getVolume());
            } else if(shape instanceof TwoDimensionalShape) {
                TwoDimensionalShape twoDS = (TwoDimensionalShape) shape;
                System.out.printf("The area is %f\n",
                        twoDS.getArea());
            } else {
                System.out.println("ERROR!!");
            }
        }
    }
}
