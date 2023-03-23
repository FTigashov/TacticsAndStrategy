package task5_hibernate;

import task5_hibernate.entities.Student;
import task5_hibernate.services.StudentService;

public class SystemDemo {
    private StudentService studentService = null;

    public SystemDemo() {
        this.studentService = new StudentService();
    }

    public void startDemo() {
        System.out.println("Внесение в базу данных 1000 записей студентов");
        studentService.appendStudentsBatch(1000);
        studentService.getAmountOfAllStudents();
        System.out.println("-----------------------------------------");

        System.out.println("Добавление студента отдельно:");
        studentService.appendStudent(new Student("Boris", 5));
        studentService.getAmountOfAllStudents();
        System.out.println("-----------------------------------------");

        System.out.println("Поиск студента по индексу:");
        System.out.println(studentService.findStudent(1001));
        System.out.println("-----------------------------------------");

        System.out.println("Обновление записи студента:");
        System.out.println();
        System.out.println("Изменение имени:");
        studentService.updateStudent(1001, "Ivan");
        System.out.println(studentService.findStudent(1001));
        System.out.println("Изменение оценки:");
        studentService.updateStudent(1001, 4);
        System.out.println(studentService.findStudent(1001));
        System.out.println("-----------------------------------------");

        System.out.println("Удаление записи по индексу:");
        System.out.print("Было: ");
        studentService.getAmountOfAllStudents();
        studentService.deleteStudent(1001);
        System.out.print("Стало: ");
        studentService.getAmountOfAllStudents();

    }
}
