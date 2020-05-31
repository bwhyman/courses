package com.se.courses.controller;

import com.se.courses.component.CommonComponent;
import com.se.courses.component.Constant;
import com.se.courses.component.EncryptorComponent;
import com.se.courses.component.ObjectMapperComponent;
import com.se.courses.entity.User;
import com.se.courses.exception.CourseException;
import com.se.courses.exception.UnauthorizedException;
import com.se.courses.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private ObjectMapperComponent oComponent;


    @PostMapping("/login")
    public Map postLogin(@RequestBody Map<String, String> user) {
        User u = Optional.ofNullable(userService.getUser(user.get("number")))
                .orElseThrow(() -> new UnauthorizedException(UnauthorizedException.LOGIN_ERROR));
        if (!passwordEncoder.matches(user.get("password"), u.getPassword())) {
            throw new UnauthorizedException(UnauthorizedException.LOGIN_ERROR);
        }
        setAuthorized(u);
        // 是否启用cookie免登录
        if ("true".equals(user.get("checked"))) {
            commonComponent.getCurrentResponse().addCookie(createCookieNumber(user.get("number"), user.get("password")));
            commonComponent.getCurrentResponse().addCookie(createCookieUser(u));
        }
        return Map.of("role", getRoleHex(u.getAuthority().getId()));
    }

    @PostMapping("/cookielogin")
    public Map postCookieLogin(@CookieValue(Constant.COOKIS_NUMBER) Optional<String> cookie) {
        User user = cookie.map(c -> {
            var cookieMap = encryptorComponent.decrypt(c);
            User user1 = new User();
            user1.setNumber(cookieMap.get("number").toString());
            user1.setPassword(cookieMap.get("password").toString());
            return user1;
        }).or(() -> {
            throw new UnauthorizedException(UnauthorizedException.COOKIE_PARES_ERROR);
        }).get();

        User u = Optional.ofNullable(userService.getUser(user.getNumber()))
                .orElseThrow(() -> new UnauthorizedException(UnauthorizedException.LOGIN_ERROR));
        if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
            throw new UnauthorizedException(UnauthorizedException.LOGIN_ERROR);
        }
        setAuthorized(u);
        return Map.of("role", getRoleHex(u.getAuthority().getId()));
    }
    private String getRoleHex(long uid) {
        return uid ==1 ? Constant.ADMIN_ROLE : Constant.STUDENT_ROLE;
    }

    /**
     * 设置Authorization token
     *
     * @param user
     */
    private void setAuthorized(User user) {
        var resultMap =
                Map.of("uId", user.getId(), "aId", user.getAuthority().getId());
        commonComponent.getCurrentResponse()
                .setHeader(Constant.AUTHORIZATION, encryptorComponent.encrypt(resultMap));
    }

    private Cookie createCookieNumber(String number, String password) {
        var cookieMap = Map.of("number", number, "password", password);
        var cookie = new Cookie(Constant.COOKIS_NUMBER, encryptorComponent.encrypt(cookieMap));
        int expiry = 60 * 60 * 24 * 365;
        cookie.setMaxAge(expiry);
        cookie.setPath("/");
        return cookie;
    }
    private Cookie createCookieUser(User user) {
        Cookie cookie = null;
        try {
            var json = oComponent.writeValueAsString(Map.of("name", user.getName(),"role",
                    user.getAuthority().getId()));
            cookie = new Cookie(Constant.COOKIS_USER, URLEncoder.encode(json, "UTF-8"));
            int expiry = 60 * 60 * 24 * 365;
            cookie.setMaxAge(expiry);
            cookie.setPath("/");
        } catch (UnsupportedEncodingException e) {
            throw new CourseException("创建cookie错误！");
        }
        return cookie;
    }
    @GetMapping("/logout")
    public Map logout() {
        commonComponent.getCurrentResponse().setHeader(Constant.AUTHORIZATION, "");
        return null;
    }
}
