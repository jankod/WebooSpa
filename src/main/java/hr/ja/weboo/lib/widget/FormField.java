package hr.ja.weboo.lib.widget;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FormField extends Widget {

    protected String helpMessage = "hel message";

    protected String placeholder = "unesi nesto";

}
