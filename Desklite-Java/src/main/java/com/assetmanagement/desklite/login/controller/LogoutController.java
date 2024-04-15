package com.assetmanagement.desklite.login.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.desklite.login.service.TokenBlackListService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/user")
@Log4j2
@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders ={"Content-type","Authorization"})
public class LogoutController {
	private final TokenBlackListService tokenBlacklistService;

    public LogoutController(TokenBlackListService tokenBlacklistService) {
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token) {
        tokenBlacklistService.blacklistToken(token);
        log.info("blacklisted the token");
        return token;
        
    }
}

