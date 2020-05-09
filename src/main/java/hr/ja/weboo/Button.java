package hr.ja.weboo;

import hr.ja.weboo.lib.ClickProducer;
import hr.ja.weboo.lib.Widget;
import lombok.Getter;

@Getter
public class Button extends Widget implements ClickProducer {

    String label = "";

    public Button() {
    }

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String html() {
        //return "<button type='submit' onclick='weboo.onClick(event, ${id?c})'>${label}</button>";
        return "<button class='btn btn-primary' type='submit' onclick='${onClickHandle()}'>${label}</button>";
    }

//    public String onClick() {
//        return JSUtil.getOnClick(this);
//    }

    public void addClickListener(ClickListener clickListener) {

    }

}
