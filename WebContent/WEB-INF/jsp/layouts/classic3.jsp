<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/general.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form.js"></script>
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
<td id="body" valign="top" colspan="2">
  <tiles:insertAttribute name="body" />
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
