package lessonOne.check;

import lessonOne.check.abstraction.Car;

public class CarFactory {
    public static Car makeCar(CarType carType, String name, String color, Engine engine) {
        if(carType.equals(CarType.LIGHTWEIGHT)) {return new LightWeightCar(name, color, engine);}
        if (carType.equals(CarType.LORRY)){return new Lorry(name,color,engine);}
        else throw new IllegalArgumentException("Unsupported car factory");
    }
}
