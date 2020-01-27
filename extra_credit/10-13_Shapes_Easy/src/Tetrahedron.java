public class Tetrahedron extends ThreeDimensionalShape {

    private int edgeLength;

    public Tetrahedron(int edgeLength) {
        this.edgeLength = edgeLength;
    }

    /**
     * @source Formula for calculating volume area of a tetrahedron courtesy of https://study.com/academy/lesson/volume-surface-area-of-a-tetrahedron.html
     * @return
     */
    @Override
    double getVolume() {
        return Math.sqrt(2) * Math.pow(edgeLength, 3) / 12;
    }

    /**
     * @source Formula for calculating volume area of a tetrahedron courtesy of https://study.com/academy/lesson/volume-surface-area-of-a-tetrahedron.html
     * @return
     */
    @Override
    double getArea() {
        return Math.sqrt(3) * edgeLength * edgeLength;
    }
}
