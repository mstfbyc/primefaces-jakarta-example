package com.mustafa.repostory;

import com.mustafa.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Named
public class StudentRepostory {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;


    public List<Student> getAllStudent(){
        getConnection();
        TypedQuery<Student> studentTypedQuery = entityManager.createQuery("SELECT s from Student s ",Student.class);
        final List<Student> studentList = studentTypedQuery.getResultList();
       complateConnection();
       return studentList;
    }

    public Student getById(Long id){
        getConnection();
        var studentTypedQuery = entityManager.createQuery("SELECT s from Student s where s.id = ?1",Student.class);
        studentTypedQuery.setParameter(1,id);
        final Student student = studentTypedQuery.getSingleResult();
        complateConnection();
        return student;
    }


    @Transactional
    public Student addStudent(Student student){
            getConnection();
            entityManager.persist(student);
            complateConnection();
            return student;
    }

    @Transactional
    public Student updateStudent(Student student){
        getConnection();
        entityManager.merge(student);
        complateConnection();
        return student;
    }

    public Long deleteStudent(Student student){
        getConnection();
        entityManager.remove(student);
        complateConnection();
        return student.getId();
    }

    public Long deleteStudent(Long id){
        getConnection();
        var studentQuery = entityManager.createQuery("DELETE from Student s where s.id = ?1");
        studentQuery.setParameter(1,id);
        studentQuery.executeUpdate();
        complateConnection();
        return id;
    }

    private void complateConnection(){
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private void getConnection(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
        transaction.begin();
    }
}
