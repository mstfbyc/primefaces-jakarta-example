package com.mustafa.bean;


import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import lombok.Data;
import java.io.Serializable;


@Model
@Data
@RequestScoped
public class StudentBean implements Serializable {
    private static final long serialVersionUID = -1129087208680119407L;
    private Long id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
}
