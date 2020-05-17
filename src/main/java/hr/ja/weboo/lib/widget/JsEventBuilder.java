package hr.ja.weboo.lib.widget;

import lombok.Builder;
import lombok.Getter;

@Builder
public class JsEventBuilder extends Widget {

    @Getter
    protected String idWidget;

    @Override
    public String getHtml() {
        //language=InjectedFreeMarker
        return """
                <script>
                        console.log("dala builder ID widget ", "${idWidget}");
                </script>
                """;
    }

}
