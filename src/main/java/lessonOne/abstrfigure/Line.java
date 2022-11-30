package lessonOne.abstrfigure;

public class Line {
    public static double getLength(Point a, Point b) {
        return Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
    }
}
