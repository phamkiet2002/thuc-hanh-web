package com.example.doanweblaptop.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    private xylydangnhap xylydangnhap;
    private yeucauquen yeucauquen;
    private xulydangxuat xulydangxuat;
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, com.example.doanweblaptop.security.xylydangnhap xylydangnhap, com.example.doanweblaptop.security.yeucauquen yeucauquen, com.example.doanweblaptop.security.xulydangxuat xulydangxuat) {
        this.userDetailsService = userDetailsService;
        this.xylydangnhap = xylydangnhap;
        this.yeucauquen = yeucauquen;
        this.xulydangxuat = xulydangxuat;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(config ->
                        config
                                //khach hang
                                .requestMatchers("/*").permitAll()
                                .requestMatchers("/home/list").permitAll()
                                .requestMatchers("/home/store").permitAll()
                                .requestMatchers("/home/update").permitAll()
                                .requestMatchers("/detail/**").permitAll()
                                .requestMatchers("/account/**").permitAll()

                                //css admin
                                .requestMatchers("/admin/vendor/fontawesome-free/css/all.min.css",
                                        "/admin/css/sb-admin.css",
                                        "/bootstrap5/css/bootstrap.min.css",
                                        "/admin/vendor/jquery/jquery.min.js",
                                        "/admin/vendor/bootstrap/js/bootstrap.bundle.min.js",
                                        "admin/vendor/jquery-easing/jquery.easing.min.js",
                                        "/admin/js/sb-admin.min.js").permitAll()
                                //css khach hang
                                .requestMatchers("/khachhang/css/**", "/khachhang/js/**",
                                        "/khachhang/img/**", "/khachhang/fonts/**").permitAll()
                                //hình
                                .requestMatchers("/getimage/**").permitAll()



                                //admin
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/laptops/**").hasRole("ADMIN")
                                .requestMatchers("/danhmuc/**").hasRole("ADMIN")
                                .requestMatchers("/nhacungcap/**").hasRole("ADMIN")
                                .requestMatchers("/khachhang/**").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login
                                .loginPage("/account/loginpage")
                                .loginProcessingUrl("/xylydangnhap")
                                .successHandler(xylydangnhap)
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(xulydangxuat)
                        .permitAll())
                .exceptionHandling(config -> config.accessDeniedHandler(yeucauquen));
        return httpSecurity.build();
    }
}
