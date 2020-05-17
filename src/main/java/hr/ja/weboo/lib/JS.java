package hr.ja.weboo.lib;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class JS {

    public static String callJs(String jsFunction, String... params) {
        return JSUtil.toJson(new JsFunction(jsFunction, Arrays.asList(params)));
    }

    @Data
    @AllArgsConstructor
    public static class JsFunction {
        String functionName;
        List<Object> parameters;
    }

}
