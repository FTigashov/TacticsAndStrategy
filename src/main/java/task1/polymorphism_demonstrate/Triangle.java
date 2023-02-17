package task1.polymorphism_demonstrate;

public class Triangle extends Figure {
    private int basis;
    private int height;

    public Triangle(int basis, int height) {
        super("Triangle");
        this.basis = basis;
        this.height = height;
    }

    @Override
    void findFigureSquare() {
        System.out.printf("Triangle square equals (%d * %d) / 2 = %d\n", basis, height, (basis * height) / 2);
    }


}
