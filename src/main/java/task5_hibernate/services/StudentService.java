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
    public void updateStudent(Student student) {
        studentDAO.updateStudentRecord(student);
    }
    public void deleteStudent(Student student) {
        studentDAO.deleteStudentRecord(student);
    }

}
