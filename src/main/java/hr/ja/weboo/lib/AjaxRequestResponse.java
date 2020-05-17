package hr.ja.weboo.lib;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;

@Getter
@Setter
public class AjaxRequestResponse extends RequestResponse {


    @SneakyThrows
    public void sendToJs(Object o) {
        String json = JSUtil.toJson(o);
        HttpServletResponse res = getRes();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().print(json);
    }
}
