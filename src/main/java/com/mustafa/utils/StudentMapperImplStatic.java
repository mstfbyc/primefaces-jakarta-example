package com.mustafa.utils;

import com.mustafa.bean.StudentBean;
import com.mustafa.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class StudentMapperImplStatic implements StudentMapper  {
    public StudentMapperImplStatic() {
    }

    public StudentBean toStudentBean(Student student) {
        if (student == null) {
            return null;
        } else {
            StudentBean studentBean = new StudentBean();
            if (student.getId() != null) {
                studentBean.setId(student.getId());
            }

            studentBean.setName(student.getName());
            studentBean.setEmail(student.getEmail());
            studentBean.setPassword(student.getPassword());
            studentBean.setGender(student.getGender());
            studentBean.setAddress(student.getAddress());
            return studentBean;
        }
    }

    public Student toStudentEntity(StudentBean studentBean) {
        if (studentBean == null) {
            return null;
        } else {
            Student student = new Student();
            student.setId((long)studentBean.getId());
            student.setName(studentBean.getName());
            student.setEmail(studentBean.getEmail());
            student.setPassword(studentBean.getPassword());
            student.setGender(studentBean.getGender());
            student.setAddress(studentBean.getAddress());
            return student;
        }
    }

    public List<StudentBean> toStudentBeanList(List<Student> studentList) {
        if (studentList == null) {
            return null;
        } else {
            List<StudentBean> list = new ArrayList(studentList.size());
            Iterator var3 = studentList.iterator();

            while(var3.hasNext()) {
                Student student = (Student)var3.next();
                list.add(this.toStudentBean(student));
            }

            return list;
        }
    }
}
