package com.example.demo.com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Size(min=2,max=30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
	@Column(name = "phone")
    private String phone;
    
    @NotNull
    @Email(message = "Please enter a valid e-mail address")
    @Column(name = "email")
    private String email;
    
   
	@Column(length=45,nullable=true)
    private String logo;
     
	
	public Employee() {
		super();
	}

public Employee(int i, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}



public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    
	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Transient 
	public String getLogoImagePath() {
		if(logo==null || id==0) 
			return null;
		
		return "/brand-logos/" + id + "/" +logo;
	}
}
