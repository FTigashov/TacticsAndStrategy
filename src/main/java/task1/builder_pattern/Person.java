package task1.builder_pattern;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public PersonBuilder addFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder addLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder addMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder addCountry(String country) {
            this.country = country;
            return this;
        }

        public PersonBuilder addAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder addPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public PersonBuilder addAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder addGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person();
        }
    }

}
