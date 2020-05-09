package hr.ja.weboo.lib;

import lombok.Getter;

import java.time.LocalDateTime;

public class TextBox extends Widget {

    @Getter
    private String placeholder = "unesi nesto";

    private String html = """
            <div>${date()}</div>
            <input type='text' placeholder='${placeholder} '>
            """;

    public String date() {
        return LocalDateTime.now().toString();
    }

    @Override
    public String html() {
        return html;
    }
}
