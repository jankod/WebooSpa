package hr.ja.weboo.lib;

import hr.ja.weboo.MyPage1;
import hr.ja.weboo.lib.widget.Widget;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class WebSite extends Widget {

    protected static ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();
    protected static ThreadLocal<HttpServletResponse> response = new ThreadLocal<>();
    @Getter
    private final String tabId;
    private Map<String, PageHolder> pageNames = new HashMap<>();
    @Getter
    private Navigation navigation = new Navigation();

    @Getter
    private Page currentPage;

    private Class<MyPage1> defaultPage;

    //language=InjectedFreeMarker
    @SuppressWarnings("preview")
    private String html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="utf-8">
              <meta name="viewport" content="width=device-width, initial-scale=1">
              <meta http-equiv="x-ua-compatible" content="ie=edge">
              <title>AdminLTE 3 | Starter</title>
              <link rel="stylesheet" href="dist/css/adminlte.min.css">
              <script>
              var TAB_ID = "${tabId}";           
              </script>
            </head>
            <body class="sidebar-mini">

                  <div class="wrapper">
                       
              <!-- Navbar -->
              <nav class="main-header navbar navbar-expand navbar-white navbar-light">
                <!-- Left navbar links -->
                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
                  </li>
                  <li class="nav-item d-none d-sm-inline-block">
                    <a href="index3.html" class="nav-link">Home</a>
                  </li>
                  <li class="nav-item d-none d-sm-inline-block">
                    <a href="#" class="nav-link">Contact</a>
                  </li>
                </ul>
                        
              </nav>
              <!-- /.navbar -->
                        
              <aside class="main-sidebar sidebar-dark-primary elevation-4">
                <!-- Brand Logo -->
                <a href="index3.html" class="brand-link">
                  <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
                  <span class="brand-text font-weight-light">AdminLTE 3</span>
                </a>
                        
                <div class="sidebar">
                    ${navigation} 
                </div>
                
              </aside>
                        
              <!-- Content Wrapper. Contains page content -->
              <div class="content-wrapper" style="min-height: 416px;">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                  <div class="container-fluid">
                    <div class="row mb-2">
                      <div class="col-sm-6">
                        <h1 class="m-0 text-dark">Starter Page</h1>
                      </div><!-- /.col -->
                      <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                          <li class="breadcrumb-item"><a href="#">Home</a></li>
                          <li class="breadcrumb-item active">Starter Page</li>
                        </ol>
                      </div><!-- /.col -->
                    </div><!-- /.row -->
                  </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->
                        
                <!-- Main content -->
                <div class="content">
                  <div class="container-fluid" id='content'>
                   <#-- ${currentPage} -->
                    <!-- /.row -->
                  </div><!-- /.container-fluid -->
                </div>
                <!-- /.content -->
              </div>
              <!-- /.content-wrapper -->
                        
              <!-- Control Sidebar -->
              <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
                <div class="p-3">
                  <h5>Title</h5>
                  <p>Sidebar content</p>
                </div>
              </aside>
              <!-- /.control-sidebar -->
                        
              <!-- Main Footer -->
              <footer class="main-footer">
                <!-- To the right -->
                <div class="float-right d-none d-sm-inline">
                  Anything you want
                </div>
                <!-- Default to the left -->
                <strong>Copyright © 2014-2019 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
              </footer>
            <div id="sidebar-overlay"></div></div>
            <!-- ./wrapper -->

            <script src="plugins/jquery/jquery.js"></script>
            <script src="plugins/bootstrap/js/bootstrap.bundle.js"></script>
            <script src="dist/js/adminlte.min.js"></script> 
            <script src="location-bar.js"></script>
            <script src="main.js"></script>
            </body>
            </html>            
            """;

    public WebSite() {
        this.tabId = "tabId_" + Math.abs(RandomUtils.nextLong());
    }

    public static void set(HttpServletRequest request, HttpServletResponse response) {
        WebSite.request.set(request);
        WebSite.response.set(response);
    }

    public static Context getContext() {
        return new Context();
    }

    public void setDefaultPage(Class<MyPage1> defaultPage) {
        this.defaultPage = defaultPage;
    }

    public void addPage(String pageName, Class<? extends Page> page) {
        pageNames.put(pageName, new PageHolder(page));
    }


    public String renderWebSite(HttpServletRequest req, HttpServletResponse res) {
        Page page = findPage(req);
        currentPage = page;
        return TemplateParser.parseWidget(this);
    }

    public String renderPage(HttpServletRequest req, HttpServletResponse res) {

        Page page = findPage(req);
        page.prepare(req, res);
        return TemplateParser.parsePage(page);
    }

    public String getHtml() {
        return html;
    }

    protected Page findPage(HttpServletRequest req) {
        String pageName = req.getParameter("name");
        // String path = req.getPathInfo();
        PageHolder pageHolder = pageNames.get(pageName);
        if (pageHolder == null) {
            // log.warn("cannot find page {}", pageName);
            //pageHolder = pageNames.get("main");
            // TODO, uvjek se instancira
            pageHolder = new PageHolder(defaultPage);
            //throw new RuntimeException("Not find page for path: " + path);
        }

        Page page = pageHolder.getPage();
        // log.debug("found page {}", page);
        return page;
    }


}
