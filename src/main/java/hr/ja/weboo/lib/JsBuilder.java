package hr.ja.weboo.lib;

public class JsBuilder {
    private String id;

    public JsBuilder(String id) {

        this.id = id;
    }

    public String callJs(String jsCallbackFunction) {
        return JSUtil.getOnClick(id, jsCallbackFunction);
    }

    public String sendToServer() {
        // TODO check if exist listener
        return JSUtil.getOnClick(id);
    }
}
