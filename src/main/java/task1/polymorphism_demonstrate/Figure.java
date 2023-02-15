package task1.polymorphism_demonstrate;

public abstract class Figure {
    private String figureName;

    public Figure(String figureName) {
        this.figureName = figureName;
    }

    public String getFigureName() {
        return figureName;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    abstract void findFigureSquare();
}
