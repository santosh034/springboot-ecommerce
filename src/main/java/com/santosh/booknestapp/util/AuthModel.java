package com.santosh.booknestapp.util;

import lombok.Data;

@Data
public class AuthModel {
    private Integer userId;
    private String accessToken;
}
