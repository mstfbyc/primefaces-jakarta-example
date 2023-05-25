package com.mustafa.view;

import com.mustafa.bean.StudentBean;
import com.mustafa.services.StudentServices;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@Named(value = "crudView")
@ViewScoped
public class StudentCrudView implements Serializable {
    private List<StudentBean> studentBeanList;
    private StudentBean selectedStudent;
    private List<StudentBean> selectedStudentList;

    @Inject
    private StudentServices studentServices;

    @PostConstruct
    public void init(){
        studentBeanList = studentServices.getStudentList();
    }
    public List<StudentBean> getStudents(){
        return studentBeanList;
    }

    public StudentBean getSelectedStudent(){
        return selectedStudent;
    }

    public List<StudentBean> getSelectedStudents(){
        return selectedStudentList;
    }

    public void setSelectedStudent(StudentBean selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public void setSelectedStudents(List<StudentBean> selectedStudentList) {
        this.selectedStudentList = selectedStudentList;
    }
    public void openNew() {
        this.selectedStudent = new StudentBean();
    }
    public void saveStudent() {
        if(selectedStudent.getId() == null){
            studentServices.addStudent(selectedStudent);
            FacesMessage msg = new FacesMessage("Student added");
        }else{
            studentServices.updateStudent(selectedStudent);
            FacesMessage msg = new FacesMessage("Student updated");
        }
        selectedStudent = null;
        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

    }

    public void deleteStudent() {
        studentServices.deleteStudent(selectedStudent.getId());
        this.studentBeanList.remove(this.selectedStudent);
        this.selectedStudentList.remove(this.selectedStudent);
        this.selectedStudent = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedStudent()) {
            int size = this.selectedStudentList.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedStudent() {
        return this.selectedStudentList != null && !this.selectedStudentList.isEmpty();
    }

    public void deleteSelectedStudents() {
        selectedStudentList.forEach(x-> studentServices.deleteStudent(x.getId()));
        this.studentBeanList.removeAll(this.selectedStudentList);
        this.selectedStudentList = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Students Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
}
