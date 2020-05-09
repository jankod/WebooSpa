package hr.ja.weboo;

import hr.ja.weboo.lib.WebSite;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyWebSite extends WebSite {

    public MyWebSite() {

        log.debug("delam web site");

        getNavigation()
                .addPage("Main", PageMain.class)
                .addPage("Second", PageSecond.class);

        setDefaultPage(PageMain.class);

        addPage("main", PageMain.class);
        addPage("second", PageSecond.class);
    }

}
