package client.controllers.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Repository
public class VisitorControlImpl implements VisitorSpamPreventable {

    @Override
    public boolean hasPerformedAction(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)) {
                    return true;
                }
            }
        }
        Cookie actionPerformed = new Cookie(cookieName, "liked");
        actionPerformed.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(actionPerformed);
        return false;
    }

}
