package com.assetmanagement.desklite.base.jpaauditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		return Optional.of("spiderman");
	}
}

//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
//			return Optional.of("Anonymous user");
//		}
//		UserDetails userDeatils = (UserDetails) SecurityContextHolder.getContext().getAuthentication(). .getPrincipal();
//		return Optional.of(userDeatils.getUsername());



