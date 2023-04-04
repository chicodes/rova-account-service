package com.rova.accountService.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.google.gson.Gson;
import com.rova.accountService.dto.AccountResponse;
import com.rova.accountService.dto.CreateAccountRequestDto;
import com.rova.accountService.dto.CreateUserRequestDto;
import com.rova.accountService.exception.ResourceNotFoundException;
import com.rova.accountService.http.HttpClient;
import com.rova.accountService.model.Account;
import com.rova.accountService.model.User;
import com.rova.accountService.repository.AccountRepository;
import com.rova.accountService.repository.UserRepository;
import com.rova.accountService.util.ResponseHelper;
import com.rova.accountService.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.rova.accountService.util.Constants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ResponseHelper responseHelper;
    private final HttpClient httpClient;
    @Value("${transactionServiceUrl}")
    private String transactionServiceUrl;

    @Value("${sqs.transaction.queue.url}")
    private String sqsUrl;

    private final AmazonSQS amazonSQS;

    private final Gson gson;

    @Override
    public AccountResponse createUser(CreateUserRequestDto request){
        try {

            User checkUserExist = userRepository.findByEmail(request.getEmail());
            if(!Objects.isNull(checkUserExist))
                throw  new ResourceNotFoundException("User with email already exist");


            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastname(request.getLastname());
            user.setPhone(request.getPhone());
            user.setEmail(request.getEmail());
            user.setAddress(request.getAddress());
            user.setState(request.getState());
            user.setGender(request.getGender());
            user.setDob(request.getDob());
            user.setDateCreated(Utility.getCurrentDate());
            user.setLastname(request.getLastname());
            user.setLastname(request.getLastname());
            user.setLastname(request.getLastname());
            userRepository.save(user);
            return responseHelper.getResponse(SUCCESS_CODE, SUCCESS, user, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return responseHelper.getResponse(FAILED_CODE, FAILED, e.getStackTrace(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    private Map<String, String> getHeader(){
        return Map.of(
                "Content-Type", "application/json; charset=utf-8",
                "Accept", "application/json"
        );
    }
}
