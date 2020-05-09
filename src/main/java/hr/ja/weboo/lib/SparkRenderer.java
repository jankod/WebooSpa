package hr.ja.weboo.lib;

import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;
import spark.Spark;

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

        Spark.get("/", (req, res) -> {
            
            WebSite webSite = manager.getWebSite(req.raw());
            return webSite.renderWebSite(req.raw(), res.raw());
        });

        Spark.get("/page", (req, res) -> {
            return renderPage(req, res);
        });

        log.debug("dela");
    }

    private String renderPage(Request req, Response res) {
        WebSite webSite = manager.getWebSite(req.raw());
        return webSite.renderPage(req.raw(), res.raw());

    }


}
