package hr.ja.weboo.lib;

public interface ClickProducer extends HasId {


    default JsBuilder js() {
        return new JsBuilder(getId());
    }



    void click(AjaxRequestResponse r);

    default void addClickListener(ClickListener clickListener) {
        JSUtil.addClickListener(clickListener, getId());
    }


}
