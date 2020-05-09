package hr.ja.weboo;

import hr.ja.weboo.lib.Page;
import hr.ja.weboo.lib.TextBox;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PageSecond extends Page {

    @Getter
    private String pageParam = "page param 1";

    @Getter
    private TextBox textBox = new TextBox();

    private Button button = new Button();

    public PageSecond() {
        log.debug("Delam page secoonbd");
    }

    @Override
    public void render(HttpServletRequest req, HttpServletResponse res) {
        pageParam = pageParam + " još ";
    }

    @Override
    public String html() {
        return
                """
                        <p>page ${pageParam} second</p>
                        <div>${textBox}</div>
                        """;
    }
}
