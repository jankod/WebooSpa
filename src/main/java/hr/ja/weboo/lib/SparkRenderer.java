package hr.ja.weboo.lib;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EventListener;

@Slf4j
public class SparkRenderer {

    private final SiteSessionManager manager;
    private Class<? extends WebSite> webSite;

    public SparkRenderer(Class<? extends WebSite> webSite) {
        this.webSite = webSite;
        manager = new SiteSessionManager(webSite);
    }

    public void startServer(int port) {
        Spark.port(port);
        Spark.staticFiles.location("/public");

        Spark.before(new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                WebSite.set(request.raw(), response.raw());
            }
        });

        Spark.get("/", (req, res) -> {

            WebSite webSite = manager.getWebSite(req.raw());
            return webSite.renderWebSite(req.raw(), res.raw());
        });

        Spark.get("/page", (req, res) -> {
            return renderPage(req, res);
        });

        Spark.post("/ajax", (req, res) -> {
            return renderAjax(req.raw(), res.raw());
        });

        //log.debug("dela");
    }

    @SneakyThrows
    private String renderAjax(HttpServletRequest req, HttpServletResponse res) {

        WebSite webSite = manager.getWebSite(req);
        Page page = webSite.findPage(req);
        String body = IOUtils.toString(req.getReader());
        if ("close".equals(body)) {
         //   log.debug("Got close event bacon. Close website {} ", webSite.getTabId());
        }
       // log.debug("Got ajax body: '{}'", body);
        if ("click".equals(req.getHeader("eventType"))) {
            ClickEvent event = JSUtil.toObject(body);
            EventListener eventListener = JSUtil.getListeners().get(event.getWidgetId());
            if (eventListener == null) {
                log.error("Cannot find event listener {}", event.getWidgetId());
            } else {
                if (eventListener instanceof ClickListener) {
                    String json = ((ClickListener) eventListener).onClick(event);
                    return json;
                }
            }
          //  log.debug("Got {}", event);
        }

        return "ajax result from server";
    }

    private String renderPage(Request req, Response res) {
        WebSite webSite = manager.getWebSite(req.raw());
        return webSite.renderPage(req.raw(), res.raw());

    }


}
