package client.handlers;

import client.services.implementation.AccountControlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccountLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    AccountControlServiceImpl accountControlService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String destination = "/";
        accountControlService.logout();
        httpServletResponse.sendRedirect(destination);
    }
}
