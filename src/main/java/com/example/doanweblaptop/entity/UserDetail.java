package com.example.doanweblaptop.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetail implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> Authorities;

    public UserDetail() {
    }

    public UserDetail(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        Authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //xac dinh tai khoan da het han chua
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //xac dinh tai khoan nguoi dung co bi khoa k
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //xem thông tin đăng nhập của người dùng (ví dụ: mật khẩu) có hết hạn hay không.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //xác định xem tài khoản người dùng có được kích hoạt hay không.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
