package lessonOne.check;

import lessonOne.check.abstraction.Car;

class LightWeightCar extends Car {
    public LightWeightCar(String name, String color, Engine engine) {
        super(name, color, engine);
    }

    @Override
    public void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }
}