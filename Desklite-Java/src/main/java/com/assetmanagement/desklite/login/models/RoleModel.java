package com.assetmanagement.desklite.login.models;

import com.assetmanagement.desklite.organization.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name="role")
public class RoleModel extends BaseEntity {
	 	@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private long id;

	    @Column
	    private String name;

	    @Column
	    private String description;


}
