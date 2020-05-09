package hr.ja.weboo.lib;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SiteSessionManager {

    private Class<? extends WebSite> webSite;
    private Map<String, WebSite> webSiteMap = new HashMap<>();

    public SiteSessionManager(Class<? extends WebSite> webSite) {
        this.webSite = webSite;
    }

    public WebSite getWebSite(HttpServletRequest req) {
        String tabId = req.getHeader("tabId");
        if (tabId != null) {
            if(webSiteMap.containsKey(tabId)) {
                return webSiteMap.get(tabId);
            }
        }
        WebSite website = instant(webSite);
        webSiteMap.put(website.getTabId(), website);
        return website;
    }

    @SneakyThrows
    private WebSite instant(Class<? extends WebSite> webSite) {
        return webSite.getConstructor().newInstance();
    }
}
