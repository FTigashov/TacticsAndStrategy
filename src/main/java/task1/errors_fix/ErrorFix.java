package task1.errors_fix;

public class ErrorFix {}

// методы move и stop сделал default и реализовал в интерфейсах
interface Movable {
    default void move(){
        System.out.println("Car is moving");
    }
}
interface Stoppable {
    default void stop(){
        System.out.println("Car is stop");
    }
}

// добавил несуществующий класс Engine
class Engine {}

abstract class Car implements Movable, Stoppable {
    private Engine engine;
    private String color;
    private String name;

    // добавил конструктор при создании объектов Car и наследников Lorry и LightWeightCar
    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    public void start() {
        System.out.println("Car starting");
    }

    // метод open перенес в класс родитель, так как он задействуется также в наследниках, дабы не дублировать код
    // при необходимости его можно переопределить
    public void open() {
        System.out.println("Car is open");
    }
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

class LightWeightCar extends Car {
    public LightWeightCar(Engine engine, String color, String name) {
        super(engine, color, name);
    }
}
class Lorry extends Car {
    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }
}
