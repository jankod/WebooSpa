package hr.ja.weboo.lib;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageHolder {
    
    private Class<? extends Page> page;

    public PageHolder(Class<? extends Page> page) {
        this.page = page;
    }

    @SneakyThrows
    public Page getPage() {
        return page.getConstructor().newInstance();
    }
}
