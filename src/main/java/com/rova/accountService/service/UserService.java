package com.rova.accountService.service;

import com.rova.accountService.dto.AccountResponse;
import com.rova.accountService.dto.CreateAccountRequestDto;
import com.rova.accountService.dto.CreateUserRequestDto;

public interface UserService {

    AccountResponse createUser(CreateUserRequestDto request);
}
