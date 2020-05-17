package hr.ja.weboo;

import hr.ja.weboo.lib.Page;
import hr.ja.weboo.lib.widget.Button;
import hr.ja.weboo.lib.widget.TextBox;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Getter
public class MyPage2 extends Page {

    String pageParam = "page param 1";

    TextBox textBox = new TextBox();

    Button button = new Button("Submit");

    public MyPage2() {

    }

    @Override
    public void prepare(HttpServletRequest req, HttpServletResponse res) {
        pageParam = pageParam + " još ";
    }

    @Override
    public String getHtml() {
        //language=InjectedFreeMarker
        return
                """
                        <p>page ${pageParam} second</p>
                        <form action='' onsubmit='weboo.onSubmit()'>
                                                
                            ${textBox}
                            
                            ${button}
                        </form>
                        """;
    }
}
