<%-- 
    Document   : index
    Created on : Mar 11, 2013, 9:18:58 PM
    Author     : Jeremy Bohrer 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            #search 
            {
                text-align: center;
            }
            #submit 
            { 
                width: 60;
                height: 20px;
                border: none;
                margin-top: 20px;
                background-color: lightgrey;
            }
            #text 
            {
                width: 400px;
            }
            
        </style>
        
    </head>
    <body>
        <div id="search">
            <h1>Search Basketball Database</h1>
            <form name="inputSearch" action="Search.jsp" method="post">
                <input type="text" name="search" id="text" />
                <br>
                <input type="submit" value="search" id="submit"/>
            </form>
        </div>
    </body>
</html>
