package com.assetmanagement.desklite.login.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.assetmanagement.desklite.organization.model.BaseEntity;
import com.assetmanagement.desklite.organization.model.OrganizationModel;
import org.springframework.stereotype.Component;

import com.assetmanagement.desklite.base.jpaauditing.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@Component
public class EmployeeModel extends BaseEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 4468733825354239471L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private String workmail;

	private String password;

	private String workdept;


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<RoleModel> roles;

	@ManyToOne(targetEntity = OrganizationModel.class)
	@JoinColumn(name = "organizationId", nullable = false)
	@JsonBackReference
	private OrganizationModel organization;

}
