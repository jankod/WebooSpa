package hr.ja.weboo.lib.widget;

import hr.ja.weboo.lib.AjaxRequestResponse;
import hr.ja.weboo.lib.ClickProducer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Button extends Widget implements ClickProducer {

    String label = "";
    private String onClickJsFunction;

    private JsEventBuilder builder;

    public Button(String label) {
        this.label = label;

        builder = JsEventBuilder.builder()
                .idWidget(getId())
                .build();
    }

    @Override
    public String getHtml() {
        //language=InjectedFreeMarker
        return """
                <button class='btn btn-primary' onclick='${js().sendToServer()};${js().callJs(onClickJsFunction)}' type='submit'>${label}</button>
                ${builder}
                """;
    }

    @Override
    public void click(AjaxRequestResponse r) {
        r.sendToJs("dela adobro");
        log.debug("Dobio od ajax ");
    }

    public void onClickCallJs(String onClickJsFunction) {
        this.onClickJsFunction = onClickJsFunction;
    }
}
