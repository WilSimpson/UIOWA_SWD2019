public class Circle extends TwoDimensionalShape {

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}
