package com.sparta.blog.controller;

import com.sparta.blog.dto.SignupRequestDto;
import com.sparta.blog.entity.User;
import com.sparta.blog.repository.UserRepository;
import com.sparta.blog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/auth/signup")
    // 회원가입 하는 코드
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
                return new ResponseEntity<>("상태코드 : " + HttpStatus.BAD_REQUEST.value() +", 메세지 : " +fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        // 중복된 사용자 처리
        String username = requestDto.getUsername();
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            String error = "중복된 사용자가 존재합니다.";
            return new ResponseEntity<>("상태코드 : " + HttpStatus.BAD_REQUEST.value() +", 메세지 : " + error , HttpStatus.BAD_REQUEST);
        }

        // 회원가입 진행
        userService.signup(requestDto);

        // 성공 메세지
        return new ResponseEntity<>("상태코드 : " + HttpStatus.OK.value() + ", 메세지 : 회원가입 성공", HttpStatus.OK);
    }






}
