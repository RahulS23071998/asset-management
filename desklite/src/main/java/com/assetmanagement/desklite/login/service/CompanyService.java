package com.assetmanagement.desklite.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.desklite.login.models.CompanyDetailsModel;
import com.assetmanagement.desklite.login.repository.ICompanyDao;


@Service
public class CompanyService implements ICompanyService{
	
	@Autowired
	ICompanyDao companyDao;
	
    


	public CompanyDetailsModel saveCompany(CompanyDetailsModel companyDetails) {
		CompanyDetailsModel savedCompany = companyDao.save(companyDetails);
		
		
		
		return savedCompany;
	}

}
