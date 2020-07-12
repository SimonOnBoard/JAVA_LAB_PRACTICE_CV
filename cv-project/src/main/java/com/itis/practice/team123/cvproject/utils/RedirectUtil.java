package com.itis.practice.team123.cvproject.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.net.URI;

/**
 * Утилита для редиректа
 * @author RenatBl
 * */

public class RedirectUtil {

    public static HttpHeaders getHttpHeaders(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        return headers;
    }

    public static HttpStatus getHttpStatus() {
        return HttpStatus.PERMANENT_REDIRECT;
    }
}
