package task5_hibernate.dao;

import task5_hibernate.entities.Student;

import java.util.List;

public interface StudentDAOImpl {
    Student findById(int id);
    List<Student> findAllStudents();
    void appendNewStudentRecord(Student student);
    void updateStudentRecord(Student student);
    void deleteStudentRecord(Student student);
}
