package lessonOne;

import lessonOne.abstrfigure.*;
import lessonOne.check.CarFactory;
import lessonOne.check.CarType;
import lessonOne.check.Engine;
import lessonOne.check.abstraction.Car;

public class Main {
    public static void main(String[] args) {
        Person p = Person.builder()
                .withFirstName("One")
                .withLastName("Two")
                .withAge(12)
                .withGender("Male")
                .build();
        System.out.println(p);
        System.out.println();

        Car light = CarFactory.makeCar(CarType.LIGHTWEIGHT,"Легковушка","синий",new Engine());
        Car lorry = CarFactory.makeCar(CarType.LORRY,"Грузовик","красный",new Engine());
        light.open();
        light.start();
        light.move();
        light.stop();

        lorry.start();
        lorry.move();
        lorry.stop();

        System.out.println();
        Point a = new Point(0,0);
        Point b = new Point(0,2);
        Point c = new Point(2,2);
        Point d = new Point(2,0);

        Figure square = new Square(a,b,c,d);
        Figure circle = new Round(a,4);
        Figure triangle = new Triangle(a,b,c);

        System.out.println("Площадь квадрата: "+square.getArea()+", его периметр: "+square.getPerimeter());
        System.out.println("Площадь круга: "+circle.getArea()+", его периметр: "+circle.getPerimeter());
        System.out.println("Площадь треугольник: "+triangle.getArea()+", его периметр: "+triangle.getPerimeter());
    }
}
