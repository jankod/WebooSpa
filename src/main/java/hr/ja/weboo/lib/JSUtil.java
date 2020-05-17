package hr.ja.weboo.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.ja.weboo.lib.ClickEvent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JSUtil {


    private static ObjectMapper objectMapper = new ObjectMapper();
    @Getter
    private static Map<String, EventListener> listeners = new HashMap<>();

    public static String getOnClick(String id) {
        //language=js
        return
                "weboo.onClick(event, \"%s\")".formatted(id);
    }

    public static String getOnClick(String id, String jsCallbackFunction) {
        //language=js
        return
                "weboo.onClick(event, \"%s\", \"%s\")".formatted(id, jsCallbackFunction);
    }

    @SneakyThrows
    public static String toJson(Object o) {
        return objectMapper.writeValueAsString(o);
    }

    @SneakyThrows
    public static ClickEvent toObject(String body) {
        return objectMapper.readerFor(ClickEvent.class).readValue(body);
    }

    public static void addClickListener(EventListener clickListener, String widgetID) {
        //  log.debug("Add listener {} ID {}", clickListener.getClass().getSuperclass(), widgetID);
        listeners.put(widgetID, clickListener);
    }


}
