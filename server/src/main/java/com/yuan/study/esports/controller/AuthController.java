package com.yuan.study.esports.controller;

import com.yuan.study.esports.controller.api.LoginCommand;
import com.yuan.study.esports.controller.api.LogoutCommand;
import com.yuan.study.esports.controller.api.RegisterCommand;
import com.yuan.study.esports.controller.api.Response;
import com.yuan.study.esports.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response<LoginCommand.Result>> login(@RequestBody LoginCommand command) {
        LoginCommand.Result login = authService.login(command);
        if (!login.getHasAccount() || login.getErrorPassword()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(Response.ofSuccess(login));
    }

    @PostMapping("/register")
    public Response<RegisterCommand.Result> register(@RequestBody RegisterCommand command) {
        return Response.ofSuccess(authService.register(command));
    }

    @PostMapping("/logout")
    public Response<?> logout(@RequestBody LogoutCommand command) {
        authService.logout(command);
        return Response.ofSuccess();
    }
}
