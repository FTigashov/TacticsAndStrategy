package task5_hibernate;

import task5_hibernate.entities.Student;
import task5_hibernate.services.StudentService;

public class StartApp {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        for (int i = 1; i <= 1000; i++) {
            studentService.appendStudent(new Student(String.format("Student_%d", i), (int) (Math.random() * 6) + 2));
        }


    }
}
