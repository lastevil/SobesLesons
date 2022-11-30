package lessonOne.abstrfigure;

public class Triangle extends Figure{
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        calculatePerimeter();
        calculateArea();
    }

    @Override
    public double calculateArea() {
        double p = this.perimeter/2;
        this.area = Math.sqrt(p*(p-Line.getLength(a,c))*(p-Line.getLength(b,b))*Line.getLength(b,c));
        return this.area;
    }

    @Override
    public double calculatePerimeter() {
        this.perimeter = Line.getLength(a,b)+Line.getLength(b,c)+Line.getLength(a,c);
        return perimeter;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }
}
