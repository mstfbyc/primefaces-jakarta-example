package com.mustafa.services;

import com.mustafa.bean.StudentBean;

import com.mustafa.repostory.StudentRepostory;
import com.mustafa.utils.StudentMapper;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;


import java.io.Serializable;
import java.util.List;

@SessionScoped
@Model
public class StudentServices implements Serializable {

    @Inject
    StudentRepostory studentRepostory;


    StudentMapper studentMapper;

    public StudentServices() {
        studentMapper = StudentMapper.INSTANCE;
    }

    public StudentBean addStudent(StudentBean studentBean){
       return studentMapper.toStudentBean(studentRepostory.addStudent(studentMapper.toStudentEntity(studentBean)) );
    }

    public StudentBean updateStudent(StudentBean studentBean){
        return studentMapper.toStudentBean(studentRepostory.updateStudent(studentMapper.toStudentEntity(studentBean)) );
    }

    public Long deleteStudent(StudentBean studentBean){
        return studentRepostory.deleteStudent(studentBean.getId());
    }
    public Long deleteStudent(Long id){
        return studentRepostory.deleteStudent(id);
    }

    public List<StudentBean> getStudentList(){
        return studentMapper.toStudentBeanList(studentRepostory.getAllStudent());
    }
}
