package com.victolee.board.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
//final Static 대신  enum 을 통해 효과
// public static final String ADMIN = "ROLE_ADMIN";
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");
    //ctrl+shift+u 소문자 -> 대문자
    private String value;


}