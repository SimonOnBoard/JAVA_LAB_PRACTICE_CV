package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.SignInDto;
import com.itis.practice.team123.cvproject.dto.TokenDto;
import com.itis.practice.team123.cvproject.services.interfaces.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JwtSignInController {
    private final SignInService signInService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<?> confirmLogin(@RequestBody SignInDto signInDto) {
        TokenDto token = signInService.signIn(signInDto);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Wrong data");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
