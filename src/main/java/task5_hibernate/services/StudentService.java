package task5_hibernate.services;

import task5_hibernate.dao.StudentDAO;
import task5_hibernate.entities.Student;

import java.util.List;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public StudentService() {
    }

    public Student findStudent(int id) {
        return studentDAO.findById(id);
    }
    public List<Student> findAllStudents() {
        return studentDAO.findAllStudents();
    }
    public void appendStudent(Student student) {
        studentDAO.appendNewStudentRecord(student);
    }
    public void appendStudentsBatch(int amount) {
        studentDAO.appendNewStudentBatch(amount);
    }
    public void updateStudent(int id, String name) {
        studentDAO.updateStudentRecord(id, name);
    }
    public void updateStudent(int id, int mark) {
        studentDAO.updateStudentRecord(id, mark);
    }
    public void deleteStudent(int id) {
        studentDAO.deleteStudentRecord(id);
    }

    public void getAmountOfAllStudents() {
        System.out.printf("Общее количество записей в бд: %d\n", studentDAO.amountOfRecords());
    }



}
