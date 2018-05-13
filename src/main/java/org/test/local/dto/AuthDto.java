package org.test.local.dto;

public class AuthDto {

    public String account;

    public String password;

    public String atoken;

    @Override
    public String toString() {
        return "AuthDto [account=" + account + ", password=" + password + ", atoken=" + atoken + "]";
    }
}
