public class Triangle extends TwoDimensionalShape {

    private int baseLength;
    private int height;

    public Triangle(int baseLength, int height) {
        this.baseLength = baseLength;
        this.height = height;
    }

    @Override
    double getArea() {
        return baseLength * height / 2.0;
    }
}
