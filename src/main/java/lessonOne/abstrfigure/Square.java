package lessonOne.abstrfigure;

public class Square extends Figure{
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Square(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        calculatePerimeter();
        calculateArea();
    }

    @Override
    public double calculateArea() {
        this.area = Line.getLength(a,b)*Line.getLength(a,d);
        return this.area;
    }

    @Override
    public double calculatePerimeter() {
        this.perimeter = Line.getLength(a,b)+Line.getLength(b,c)+Line.getLength(c,d)+Line.getLength(a,d);
        return this.perimeter;
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

    public Point getD() {
        return d;
    }

    public void setD(Point d) {
        this.d = d;
    }
}
