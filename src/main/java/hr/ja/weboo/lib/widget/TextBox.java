package hr.ja.weboo.lib.widget;

import hr.ja.weboo.lib.JQuery;
import hr.ja.weboo.lib.WebSite;
import hr.ja.weboo.lib.widget.FormField;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextBox extends FormField {

    private String label = "";

    //language=InjectedFreeMarker
    private String html = """
            <div class='form-group'>
                <label for='${id}_txt'>${label}</label>
                <input class='form-control' id='${id}_txt' type='text' placeholder='${placeholder} '>
                <#if helpMessage?has_content>
                   <small class="form-text text-muted">
                      ${helpMessage}
                   </small>
                </#if>
                <div class="invalid-feedback">
                  
                </div>
            </div>
            """;

    public TextBox(String label) {
        this.label = label;
    }

    public void setLabel(String label) {
        this.label = label;
        if (WebSite.getContext().isAjax()) {
            JQuery.$("label[for='%s_txt']".formatted(getId())).val(label);
        }
    }
}
