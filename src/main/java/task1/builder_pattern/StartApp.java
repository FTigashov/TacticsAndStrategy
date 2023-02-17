package task1.builder_pattern;

public class StartApp {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .addFirstName("Ivan")
                .addLastName("Ivanov")
                .addMiddleName("Ivanovich")
                .addAge(25)
                .addCountry("Russia")
                .addAddress("city Moscow")
                .addPhone("81280")
                .addGender("Male")
                .build();
    }
}
