package com.sparta.fifth_week_lv3.entity;

public enum AdminRoleEnum {
    MANAGER(Authority.MANAGER),  // 사용자 권한
    STAFF(Authority.STAFF);  // 관리자 권한

    private final String authority;

    AdminRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}