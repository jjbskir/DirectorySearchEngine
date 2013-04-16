<%-- 
    Document   : Search
    Created on : Mar 11, 2013, 9:33:42 PM
    Author     : Jeremy Bohrer 
--%>

<%@page import="searchengine.MaxHeap"%>
<%@page import="searchengine.SearchOutput"%>
<%@page import="searchengine.SearchEngineOnline"%>
<%@page import="java.util.HashMap"%>

<jsp:useBean id="search" scope="session" class="org.search.SearchHandler" />
<jsp:setProperty name="search" property="*"/> 
<%
    String searchInput = search.getSearch();
    SearchEngineOnline searchEngine = new SearchEngineOnline();
    MaxHeap searchResult = searchEngine.search(searchInput);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            
            h1 
            {
                text-align: center;
            }
            p
            {
                padding-right: 20px;
                padding-left: 20px;
            }
            
        </style>
        
    </head>
    <body>

        <h1> You searched: <% out.print(searchInput); %></h1>
        <%
            int count = 0;
            while (!searchResult.getHeap().isEmpty())
            {
                count++;
                out.println("<p> <a href='#'>" + searchResult.getMax().getArticle().getName() + "</a> </p>");
                out.println("<p>" + searchResult.getMax().getSnippet() + "</p>");
                out.print("<br>");
                searchResult.removeMax();
            }
            
        %>
        
    </body>
</html>
