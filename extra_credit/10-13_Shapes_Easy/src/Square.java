public class Square extends TwoDimensionalShape {

    public int sideLength;

    public Square(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    double getArea() {
        return sideLength * sideLength;
    }
}
