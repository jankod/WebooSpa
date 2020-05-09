package hr.ja.weboo.lib;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import hr.ja.weboo.MyTextBox;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.io.Writer;

@Slf4j
public class TemplateParser {

    private static TemplateParser instance = new TemplateParser();
    private StringTemplateLoader templateLoader = new StringTemplateLoader();
    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);


    public TemplateParser() {
        cfg.setTemplateLoader(templateLoader);
    }

    public static String parseWidget(Widget w) {
        String templateID = w.getClass().getCanonicalName();
        return instance.parse(w.html(), w, templateID);
    }

    public static String parsePage(Page page) {
        String templateID = page.getClass().getCanonicalName();
        String template = page.html();
        return instance.parse(template, page, templateID);
    }

    public static void main(String[] args) {
        TemplateParser parser = new TemplateParser();
        MyTextBox myTextBox = new MyTextBox();
        String html = parser.parseWidget(myTextBox);
        log.debug(html);
    }

    @SneakyThrows
    public String parse(String template, Object model, String templateId) {
        if (templateLoader.findTemplateSource(templateId) == null)
            templateLoader.putTemplate(templateId, template);

        Template t = cfg.getTemplate(templateId);
        Writer out = new StringWriter();
        t.process(model, out);
        return out.toString();
    }


}
