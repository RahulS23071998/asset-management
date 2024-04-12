package com.assetmanagement.desklite.login.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenBlackListService {
	 private Set<String> blacklist = new HashSet<>();

	    public void blacklistToken(String token) {
	        blacklist.add(token);
	    }

	    public boolean isTokenBlacklisted(String token) {
	        return blacklist.contains(token);
	    }

}
