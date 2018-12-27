package client.controllers.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VisitorSpamPreventable {
    boolean hasPerformedAction(HttpServletRequest request, HttpServletResponse response, String cookieName);
}
