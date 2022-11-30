package lessonOne.abstrfigure;

public abstract class Figure {
    protected double area;
    protected double perimeter;

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}
