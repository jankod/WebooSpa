package hr.ja.weboo.lib;

import hr.ja.weboo.JSUtil;

public interface ClickProducer extends HasId {

    default String onClickHandle() {
        return JSUtil.getOnClick(getId());
    }


}
