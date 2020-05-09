package hr.ja.weboo;

import hr.ja.weboo.lib.SparkRenderer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

        SparkRenderer sparkRenderer = new SparkRenderer(MyWebSite.class);
        sparkRenderer.startServer(80);

    }
}
