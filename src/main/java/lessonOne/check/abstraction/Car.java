package lessonOne.check.abstraction;

import lessonOne.check.Engine;

public abstract class Car implements Moveable {
    private Engine engine;
    private String color;
    private String name;

    public Car(String name, String color,Engine engine) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    public void start() {
        if (engine.getStatus()) {
            System.out.println("The car is already running");
        } else {
            engine.launch();
        }
        System.out.println("Car starting");
    }

    public abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
