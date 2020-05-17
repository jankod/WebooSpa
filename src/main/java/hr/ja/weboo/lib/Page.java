package hr.ja.weboo.lib;

import hr.ja.weboo.lib.widget.Widget;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Page extends Widget {

    public abstract void prepare(HttpServletRequest req, HttpServletResponse res);
}
