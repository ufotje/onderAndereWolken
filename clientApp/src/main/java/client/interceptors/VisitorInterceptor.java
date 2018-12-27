package client.interceptors;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static client.config.GlobalVariables.REGEX_NUMBER;
import static client.config.GlobalVariables.getTotalVisitorsCount;
import static client.config.GlobalVariables.setTotalVisitorsCount;

@Controller
public class VisitorInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        if(request != null && response != null) {
            Cookie[] cookies = request.getCookies();
            boolean returningVisitor = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("visitedBefore")) {
                        returningVisitor = true;
                        int countVisits = (cookie.getValue().matches(REGEX_NUMBER)) ? Integer.valueOf(cookie.getValue()) : 0;
                        cookie.setValue((countVisits + 1) + "");
                    }
                }
            }
            if(!returningVisitor) {
                Cookie visitedBefore = new Cookie("VisitedBefore", "0");
                visitedBefore.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(visitedBefore);
                setTotalVisitorsCount(getTotalVisitorsCount() + 1);
            }
        }
    }

}
