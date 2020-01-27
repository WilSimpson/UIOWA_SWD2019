public class Cube extends ThreeDimensionalShape {

    private int sideLength;

    public Cube(int sideLength) {
        this.sideLength = sideLength;
    }


    @Override
    double getVolume() {
        return Math.pow(sideLength, 3);
    }

    @Override
    double getArea() {
        return sideLength * sideLength * 6;
    }
}
