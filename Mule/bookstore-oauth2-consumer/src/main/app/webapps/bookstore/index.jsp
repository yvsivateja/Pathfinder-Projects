<%@ page import="net.smartam.leeloo.client.request.*"%>
<%@ page import="net.smartam.leeloo.client.response.*"%>
<%@ page import="net.smartam.leeloo.common.message.types.GrantType"%>
<%@ page import="net.smartam.leeloo.client.*"%>
<%@ page import="org.mule.util.StringUtils"%>
<%@ page import="org.mule.security.examples.oauth2.TweetBookClient" %>
<%@ page language="java"%>
<%
String code = request.getParameter("code");

if (StringUtils.isNotBlank(code)) {
    OAuthClientRequest oAuthTokenRequest =
       OAuthClientRequest.tokenLocation(TweetBookClient.TOKEN_URL)
       .setGrantType(GrantType.AUTHORIZATION_CODE)
       .setCode(code)
       .setClientId(TweetBookClient.BOOKSTORE_CLIENT_ID)
       .setClientSecret(TweetBookClient.BOOKSTORE_CLIENT_SECRET)
       .setRedirectURI("http://localhost:8085/bookstore/index.jsp")
       .buildBodyMessage();
  
    OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
  
    OAuthAccessTokenResponse oAuthTokenResponse = oAuthClient.accessToken(oAuthTokenRequest);
    
    String accessToken = oAuthTokenResponse.getAccessToken();
    if (StringUtils.isNotBlank(accessToken)) {
        response.sendRedirect("home.jsp?access_token="+accessToken);
    }
}

%>
<head>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css">
    <title>Mule Bookstore</title>
</head>
<%
String authorizationRequestUrl =
  OAuthClientRequest
    .authorizationLocation(TweetBookClient.AUTHORIZE_URL)
    .setResponseType("code")
    .setClientId(TweetBookClient.BOOKSTORE_CLIENT_ID)
    .setRedirectURI("http://localhost:8085/bookstore/index.jsp")
    .setScope("READ_PROFILE READ_BOOKSHELF")
    .buildQueryMessage()
    .getLocationUri();
%>

<body>

<div class="title">
    <h3>Welcome to the Mule Bookstore</h3>
</div>
<hr />

<%
    String error = request.getParameter("error");
    String errorDescription = StringUtils.trimToEmpty(request.getParameter("error_description"));

    if (StringUtils.isNotBlank(error)) { %>
<h3>Login failed: <%= StringUtils.isNotBlank(errorDescription) ? errorDescription : error %></h3>
<% } %>


<form method="POST" name="login">
    <div class="content">
        <h2>Login</h2>
        <p><label for="username">Username:</label> <input type="text" id="username" /></p>
        <p><label for="password">Password:</label> <input type="password" id="password" /></p>
        <p class="submit"><button type="button" disabled="true">Login</button></p>
    </div>
    <div class="content">
        <h2>Or sign-in with</h2>
        <p class="signin"><a href="<%=authorizationRequestUrl%>"><b>Your TweetBook Account</p>
    </div>
</form>
<hr class="clear"/>
</body>
</html>