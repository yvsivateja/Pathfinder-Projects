<%@ page import="org.mule.security.examples.oauth2.TweetBookClient"%>
<%@ page language="java"%>
<%
    String accessToken = request.getParameter("access_token");
%>
<head>
<title>Mule Bookstore</title>
</head>

<body link="#FFFFFF" vlink="#FFFFFF" alink="#FFFFFF" bgcolor="#000055"
 text="#FFFFFF">
 <center>
  <h3>Welcome to the Mule Bookstore</h3>
 </center>
 <p>
  <a href="index.jsp">Logout</a>
 </p>
 <hr />

 <h4>
  Bookshelf of: <%=TweetBookClient.getUserName(accessToken)%>
 </h4>
 <ul>
 <% for (String book:TweetBookClient.getBookshelf(accessToken)) { %>
 <li><%= book %></li>
 <% } %>
 </ul>
 <hr />
</body>
</html>
