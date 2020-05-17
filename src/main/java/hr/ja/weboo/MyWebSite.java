package hr.ja.weboo;

import hr.ja.weboo.lib.WebSite;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyWebSite extends WebSite {

    public MyWebSite() {

        //   log.debug("delam web site, stari webSiteId "+ request.get().getHeader("tabId"));

        getNavigation()
                .addPage("Main", MyPage1.class)
                .addPage("Second", MyPage2.class);

        setDefaultPage(MyPage1.class);

        addPage("main", MyPage1.class);
        addPage("second", MyPage2.class);
    }

}
