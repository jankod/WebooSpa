package hr.ja.weboo.lib.widget;

import hr.ja.weboo.lib.HasId;
import hr.ja.weboo.lib.TemplateParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
public abstract class Widget implements HasId {

    @Getter
    private String id;

    public Widget() {
        id = RandomUtils.nextLong() + "";
        Class<?> widgetClass = getClass();
        log.debug("Create Widget {} ID {}", widgetClass, id);
    }

    /**
     * Must be fixed template
     */
    public abstract String getHtml();

    // TODO: move to template interface
    @Override
    public String toString() {
        return TemplateParser.parseWidget(this);
    }


}
