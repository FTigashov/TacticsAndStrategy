package task1.polymorphism_demonstrate;

public class Square extends Figure {
    private int side;
    public Square(int side) {
        super("Square");
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    void findFigureSquare() {
        System.out.printf("Square equals %d * %d = %d\n", side, side, side * side);
    }
}
