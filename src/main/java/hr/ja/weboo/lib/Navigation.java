package hr.ja.weboo.lib;


import hr.ja.weboo.lib.widget.Widget;

import java.util.HashMap;
import java.util.Map;

public class Navigation extends Widget {

    private Map<String, Class<? extends Page>> pages = new HashMap<>();

    //language=InjectedFreeMarker
    private String html = """
                 <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                      <!-- Add icons to the links using the .nav-icon class
                           with font-awesome or any other icon font library -->
                      <li class="nav-item has-treeview menu-open">
                        <ul class="nav nav-treeview">
                          <li class="nav-item">
                             <a href='#/main' data-goto='main' class="nav-link">
                              <i class="far fa-aviato nav-icon"></i>
                              <p>Home</p>
                            </a>
                          </li>
                          
                           <li class="nav-item">
                             <a href='#/second' data-goto='second' class="nav-link">
                              <i class="far fa-circle nav-icon"></i>
                              <p>Second</p>
                             </a>
                          </li>
                          
                        </ul>
                      </li>
                    </ul>
                  </nav>
            """;

    public Navigation addPage(String label, Class<? extends Page> pageClass) {
        pages.put(label, pageClass);
        return this;
    }

    @Override
    public String getHtml() {
        return html;
    }


}
