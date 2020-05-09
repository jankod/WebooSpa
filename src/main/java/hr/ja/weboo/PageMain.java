package hr.ja.weboo;

import hr.ja.weboo.lib.Page;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Getter
public class PageMain extends Page {

    Button b = new Button("klikni me");
    private String name = "janko";

    public PageMain() {
        b.addClickListener(() -> log.debug("click "));
    }

    public void render(HttpServletRequest req, HttpServletResponse res) {

    }

    public String html() {
        //language=InjectedFreeMarker
        return """
                <p>main page</p>
                <div>JA sam ${name}</div> 
                ${b}
                """;
    }


}
