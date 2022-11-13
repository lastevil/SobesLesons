package lessonOne.check;

import lessonOne.check.abstraction.Car;

class Lorry extends Car {
    public Lorry(String name, String color, Engine engine) {
        super(name, color, engine);
    }

    public void move(){
        System.out.println("Car is moving");
    }

    public void stop(){
        System.out.println("Car is stop");
    }
    @Override
    public void open() {
        System.out.println("Car is open");
    }
}
