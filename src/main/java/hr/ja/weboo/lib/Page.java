package hr.ja.weboo.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Page extends Widget {

    // public abstract String html();
    public abstract void render(HttpServletRequest req, HttpServletResponse res);
}
