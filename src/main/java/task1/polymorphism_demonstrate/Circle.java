package task1.polymorphism_demonstrate;

public class Circle extends Figure {
    private int radius;

    public Circle(int radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    void findFigureSquare() {
        System.out.printf("Circle square equals %f * (%d)^2 = %.2f\n", Math.PI, radius, Math.PI * Math.pow(radius, 2));
    }
}
