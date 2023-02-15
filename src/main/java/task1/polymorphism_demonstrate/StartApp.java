package task1.polymorphism_demonstrate;

public class StartApp {
    public static void main(String[] args) {
        Figure square = new Square(5);
        Figure triangle = new Triangle(2, 5);
        Figure circle = new Circle(5);

        square.findFigureSquare();
        triangle.findFigureSquare();
        circle.findFigureSquare();
    }
}
