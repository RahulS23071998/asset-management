package com.assetmanagement.desklite.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.desklite.login.models.CompanyDetailsModel;


@Repository
public interface ICompanyDao extends JpaRepository<CompanyDetailsModel,Integer>{
	  CompanyDetailsModel save(CompanyDetailsModel companyDetailsModel);

	CompanyDetailsModel findByCompanyName(String companyName);
	

}
