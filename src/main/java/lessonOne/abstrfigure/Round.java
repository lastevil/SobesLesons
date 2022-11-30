package lessonOne.abstrfigure;

public class Round extends Figure{
    private Point center;
    private int radius;

    public Round(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        calculatePerimeter();
        calculateArea();
    }

    @Override
    public double calculateArea() {
        area =  Math.PI * radius * radius;
        return area;
    }

    @Override
    public double calculatePerimeter() {
        perimeter = Math.PI * 2 * radius;
        return perimeter;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
