public class Sphere extends ThreeDimensionalShape {

    private int radius;

    public Sphere(int radius) {
        this.radius = radius;
    }

    /**
     *
     * @source Formula for calculating the volume of a cube courtesy of https://www.mathsisfun.com/geometry/sphere-volume-area.html
     * @return
     */
    @Override
    double getVolume() {
        return (4/3) * Math.PI * Math.pow(radius, 3);
    }

    /**
     *
     * @source Formula for calculating the area of a cube courtesy of https://www.mathsisfun.com/geometry/sphere-volume-area.html
     * @return
     */
    @Override
    double getArea() {
        return 4 * Math.PI * radius * radius;
    }
}
