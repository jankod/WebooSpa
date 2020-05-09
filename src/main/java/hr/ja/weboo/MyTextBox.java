package hr.ja.weboo;

import hr.ja.weboo.lib.Widget;
import lombok.Getter;
import lombok.Setter;

public class MyTextBox extends Widget {

    @Getter
    @Setter
    private String name = "janko";

    @Override
    public String html() {
        //language=InjectedFreeMarker
        String s = """                                     
                <script>${name}</script>
                """;

        return s;
    }
}