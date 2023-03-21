package task5_hibernate.dao;

import task5_hibernate.entities.Student;

import java.util.List;

public interface StudentDAOImpl {
    Student findById(int id);
    List<Student> findAllStudents();
    long amountOfRecords();
    void appendNewStudentRecord(Student student);
    void updateStudentRecord(int id, String name);
    void updateStudentRecord(int id, int mark);
    void deleteStudentRecord(int id);
}
