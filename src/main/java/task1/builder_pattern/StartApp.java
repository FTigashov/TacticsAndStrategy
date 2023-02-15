package task1.builder_pattern;

public class StartApp {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder("Ivan",
                "Ivanov",
                "Ivanovich",
                "Russia",
                "city Moscow",
                "883531",
                25,
                "Male").build();
    }
}
