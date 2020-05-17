package hr.ja.weboo.lib;

public class JQuery {

    private String query;

    private JQuery() {
    }

    public JQuery(String query) {
        this.query = query;
    }

    public static JQuery $(String query) {
        JQuery instance = new JQuery(query);
        return instance;
    }

    public JQuery val(String value) {
        return this;
    }
}
