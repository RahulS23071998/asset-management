package com.assetcircle.assetmanagement.security.service;

import com.assetcircle.assetmanagement.security.dto.LoginDto;
import com.assetcircle.assetmanagement.security.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
