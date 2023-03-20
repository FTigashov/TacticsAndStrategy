package task5_hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import task5_hibernate.entities.Student;
import task5_hibernate.utils.SessionFactoryUtil;

import java.util.List;

public class StudentDAO implements StudentDAOImpl {
    @Override
    public Student findById(int id) {
        return SessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Student.class, id);
    }

    @Override
    public List<Student> findAllStudents() {
        List<Student> students = (List<Student>) SessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From Student")
                .list();
        return students;
    }

    @Override
    public void appendNewStudentRecord(Student student) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction appendTransaction = session.beginTransaction();
            session.persist(student);
            appendTransaction.commit();
        }
    }

    @Override
    public void updateStudentRecord(Student student) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction updateTransaction = session.beginTransaction();
            session.merge(student);
            updateTransaction.commit();
        }
    }

    @Override
    public void deleteStudentRecord(Student student) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction updateTransaction = session.beginTransaction();
            session.remove(student);
            updateTransaction.commit();
        }
    }
}
