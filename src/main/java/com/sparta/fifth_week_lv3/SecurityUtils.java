package com.sparta.fifth_week_lv3;

public class SecurityUtils {
    public static boolean isAdmin(String token){
        if (token != null && token.startsWith("Bearer") && token.contains("admin")){
            return true;
        } else {
            return false;
        }
    }
}