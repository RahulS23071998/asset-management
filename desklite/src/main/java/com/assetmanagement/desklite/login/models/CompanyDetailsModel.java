package com.assetmanagement.desklite.login.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="companydetails")
public class CompanyDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String companyName;
	private String companyMail;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private int pincode;
	private String website;
	private String contactNumber;
	
	@OneToMany(mappedBy = "companyId", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<EmployeeModel> employees;

}
