package hr.ja.weboo;

import hr.ja.weboo.lib.*;
import hr.ja.weboo.lib.widget.Button;
import hr.ja.weboo.lib.widget.TextBox;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Getter
public class MyPage1 extends Page {

    Button button = new Button("klikni me");
    TextBox textBox = new TextBox("Unesi ime");

    private String name = "Page 1";

    public MyPage1() {
        button.onClickCallJs("ja_buttonClick");
        button.addClickListener(MyPage1::buttonClick);
    }

    public static String buttonClick(ClickEvent event) {
        log.debug("button click {}", event);
        return JS.callJs("myfunc", "pozvan si hahaha");
    }

    public void prepare(HttpServletRequest req, HttpServletResponse res) {
        name += RandomUtils.nextInt(1, 8);
    }

    public String getHtml() {
        //language=InjectedFreeMarker
        return """
                <script>
                 function myfunc(param1) {
                     console.log("Call my func " );
                     console.log("text id "+ ${textBox.getId()});
                     console.log("param 1 "+ param1);
                     weboo.sendToServer()
                     weboo.sendToServer(param1)
                }
                                
                function ja_buttonClick(event) {
                  console.log("evo pozvao", event);
                }
                                
                </script>
                <p>main page</p>
                <div >JA sam ${name}</div> 
                    ${textBox}
                    ${button}
                """;
    }

}
