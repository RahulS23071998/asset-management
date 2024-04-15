package com.assetmanagement.desklite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.assetmanagement.desklite.base.jpaauditing.Auditable;

@SpringBootApplication
//@ComponentScan
//@EntityScan(basePackages={"com.assetmanagement.desklite.login.models"})
//@EnableJpaRepositories(basePackages={"com.assetmanagement.desklite.login.repository"})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class DeskliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeskliteApplication.class, args);
	}

}
