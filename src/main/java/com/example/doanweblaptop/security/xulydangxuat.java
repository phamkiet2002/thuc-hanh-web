package com.example.doanweblaptop.security;

import com.example.doanweblaptop.service.Cart.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class xulydangxuat implements LogoutSuccessHandler {
    CartService cartService;
    @Autowired
    public xulydangxuat(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        cartService.clear();
        //response.sendRedirect(request.getContextPath() + "/");
        response.sendRedirect("home/list");
    }
}
