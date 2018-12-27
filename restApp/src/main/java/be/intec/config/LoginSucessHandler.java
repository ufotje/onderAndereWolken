package be.intec.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		String defaultTargetUrl = "/admin/admin";

		String[] ignoreUrl = { "signup", "logout", "error" };

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		HttpSession session = request.getSession();
		if (session != null) {

			String redirectUrl = (String) session.getAttribute("url_prior_login");
			session.removeAttribute("url_prior_login");


			if (ignoreUrl(redirectUrl, ignoreUrl)) {

				redirectStrategy.sendRedirect(request, response, redirectUrl);
			} else {

				redirectStrategy.sendRedirect(request, response, defaultTargetUrl);
			}

		} else {
			
			redirectStrategy.sendRedirect(request, response, defaultTargetUrl);
		}

	}

	private boolean ignoreUrl(String redirectUrl, String[] ignoreUrl) {
		if (redirectUrl != null) {
			for (int i = 0; i < ignoreUrl.length; i++) {
				if (redirectUrl.contains(ignoreUrl[i])) {
					return false;
				}
			}

		} else {
			return false;
		}
		
		return true;

	}
}