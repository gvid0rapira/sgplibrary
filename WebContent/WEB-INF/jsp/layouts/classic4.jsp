<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" 
      type="text/css" 
      href="${pageContext.request.contextPath}/css/general.css"/>
<link rel="Stylesheet" 
      type="text/css" 
      href="${pageContext.request.contextPath}/css/ui-lightness/jquery-ui-1.8.2.custom.css" /> 

<script type="text/javascript" 
        src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" 
        src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" 
        src="${pageContext.request.contextPath}/js/jquery/jquery-validate/jquery.validate.js"></script>
<script type="text/javascript" 
        src="${pageContext.request.contextPath}/js/jquery/jquery-validate/localization/messages_ru.js"></script>
<script type="text/javascript" 
        src="${pageContext.request.contextPath}/js/form.js"></script>
<tiles:insertAttribute name="script" defaultValue="" />
<tiles:insertAttribute name="css" defaultValue="" />        
<title><tiles:getAsString name="title"/></title>
</head>
<body>
<table style="width: 100%; height: 100%">
<colgroup>
<col width="100"/>
<col/>
</colgroup>
<tr>
<td id="banner" align="center">
  <tiles:insertAttribute name="header" />
</td>
<td id="main_menu" valign="bottom">
  <tiles:insertAttribute name="main_menu" />
</td>
</tr>
<tr>
<td colspan="2">
  <table style="width: 100%; height: 100%">
  <colgroup>
  <col width="200"/>
  <col />
  </colgroup>
  <tbody>
  <tr>
  	<td id="left_menu" valign="top">
  	<tiles:insertAttribute name="left_menu" />
  	</td>
  	<td id="body" valign="top">
  	<tiles:insertAttribute name="body" />
  	</td>
  </tr>
  </tbody>
  </table>
</td>
</tr>
<tr>
<td id="footer" colspan="2">
  <tiles:insertAttribute name="footer" />
</td>
</tr>
</table>
</body>
</html>
