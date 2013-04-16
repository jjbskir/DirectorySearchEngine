package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import searchengine.MaxHeap;
import searchengine.SearchOutput;
import searchengine.SearchEngineOnline;
import java.util.HashMap;

public final class Search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.search.SearchHandler search = null;
      synchronized (session) {
        search = (org.search.SearchHandler) _jspx_page_context.getAttribute("search", PageContext.SESSION_SCOPE);
        if (search == null){
          search = new org.search.SearchHandler();
          _jspx_page_context.setAttribute("search", search, PageContext.SESSION_SCOPE);
        }
      }
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("search"), request);
      out.write(' ');
      out.write('\n');

    String searchInput = search.getSearch();
    SearchEngineOnline searchEngine = new SearchEngineOnline();
    MaxHeap searchResult = searchEngine.search(searchInput);

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("            \n");
      out.write("            h1 \n");
      out.write("            {\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            p\n");
      out.write("            {\n");
      out.write("                padding-right: 20px;\n");
      out.write("                padding-left: 20px;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </style>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <h1> You searched: ");
 out.print(searchInput); 
      out.write("</h1>\n");
      out.write("        ");

            int count = 0;
            while (!searchResult.getHeap().isEmpty())
            {
                count++;
                out.println("<p> <a href='#'>" + searchResult.getMax().getArticle().getName() + "</a> </p>");
                out.println("<p>" + searchResult.getMax().getSnippet() + "</p>");
                out.print("<br>");
                searchResult.removeMax();
            }
            
        
      out.write("\n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
