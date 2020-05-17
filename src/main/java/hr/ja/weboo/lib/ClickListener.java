package hr.ja.weboo.lib;

import hr.ja.weboo.lib.ClickEvent;

import java.util.EventListener;

public interface ClickListener extends EventListener {
    /**
     * Returned JSON
     * @param event
     * @return
     */
    String onClick(ClickEvent event);
}
