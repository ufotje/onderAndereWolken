package client.interceptors;

import client.config.GlobalVariables;
import client.entities.SiteStatistics;
import client.services.SiteStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SiteStatisticsInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SiteStatisticsService siteStatisticsService;


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            SiteStatistics siteStatistics = siteStatisticsService.getAllStatistics();
            siteStatistics.setAllVisitors(GlobalVariables.getTotalVisitorsCount()); //Todo write visitor count on rest side
            modelAndView.getModelMap().addAttribute("SiteStatistics", siteStatistics);
        }
    }


}
