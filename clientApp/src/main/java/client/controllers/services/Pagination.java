package client.controllers.services;

import client.services.search.LatestEditOrderable;
import org.springframework.ui.ModelMap;

import java.util.Map;

import static client.config.GlobalVariables.REGEX_NUMBER;
import static client.services.search.SearchUtils.firstMiddleLastOrOnlyPage;
import static client.services.search.SearchUtils.limit;

public class Pagination {

    public static void pagingJsp(Map<LatestEditOrderable, String> items, String page, int itemsOnPage, String url, ModelMap model) {
        if(items.size() == 0) {
            model.addAttribute("mainItems");
        } else {
            int pageNr = (page != null && page.matches(REGEX_NUMBER)) ? Integer.valueOf(page) : 1;
            model.addAttribute("showPagination", (items.size() < itemsOnPage) ? false : true);
            model.addAttribute("mainItems", limit(items, pageNr, itemsOnPage));
            model.addAttribute("previousNext", firstMiddleLastOrOnlyPage(items, pageNr, itemsOnPage));
            model.addAttribute("pageNr", pageNr);
            model.addAttribute("url", url);
        }
    }


}
