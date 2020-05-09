package hr.ja.weboo.lib;

import hr.ja.weboo.ClickListener;
import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

public abstract class Widget implements HasId {

    @Getter
    private long id;



    public Widget() {
        id = RandomUtils.nextLong();
    }

    public abstract String html();

    @Override
    public String toString() {
        return TemplateParser.parseWidget(this);
    }


}
