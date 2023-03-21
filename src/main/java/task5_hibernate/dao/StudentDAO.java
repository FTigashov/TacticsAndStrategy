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
        Transaction appendTransaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            appendTransaction = session.beginTransaction();
            session.persist(student);
            appendTransaction.commit();
        } catch (Exception e) {
            if (appendTransaction != null) appendTransaction.rollback();
        }
    }

    @Override
    public void appendNewStudentBatch(int amount) {
        Transaction appendBatchTransaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            appendBatchTransaction = session.beginTransaction();
            for (int i = 1; i <= amount; i++) {
                session.persist(new Student(String.format("Student_%d", i), (int) (Math.random() * 6) + 2));
            }
        } catch (Exception e) {
            if (appendBatchTransaction != null) appendBatchTransaction.rollback();
        }
    }

    @Override
    public long amountOfRecords() {
        return findAllStudents().size();
    }

    @Override
    public void updateStudentRecord(int id, String name) {
        Transaction updateTransaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            updateTransaction = session.beginTransaction();
            Student student = findById(id);
            student.setName(name);
            session.saveOrUpdate(student);
            updateTransaction.commit();
        } catch (Exception e) {
            if (updateTransaction != null) updateTransaction.rollback();
        }
    }

    @Override
    public void updateStudentRecord(int id, int mark) {
        Transaction updateTransaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            updateTransaction = session.beginTransaction();
            Student student = findById(id);
            student.setMark(mark);
            session.saveOrUpdate(student);
            updateTransaction.commit();
        } catch (Exception e) {
            if (updateTransaction != null) updateTransaction.rollback();
        }
    }

    @Override
    public void deleteStudentRecord(int id) {
        Transaction deleteTransaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession();) {
            deleteTransaction = session.beginTransaction();
            Student student = findById(id);
            session.delete(student);
            deleteTransaction.commit();
        } catch (Exception e) {
            if (deleteTransaction != null) deleteTransaction.rollback();
        }
    }
}
