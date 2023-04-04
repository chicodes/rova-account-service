package com.rova.accountService.service;

import com.rova.accountService.dto.AccountResponse;
import com.rova.accountService.dto.CreateAccountRequestDto;

public interface AccountService {

    AccountResponse createCurrentAccount(CreateAccountRequestDto request);
}
