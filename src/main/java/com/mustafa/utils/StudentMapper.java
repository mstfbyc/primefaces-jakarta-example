package com.mustafa.utils;

import com.mustafa.bean.StudentBean;
import com.mustafa.entity.Student;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentBean toStudentBean(Student student);
    Student toStudentEntity(StudentBean studentBean);
    List<StudentBean> toStudentBeanList(List<Student> studentList);
}
