<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="form_title"> Список документов </div>

<form:form id="DocRegForm" 
    action="${pageContext.request.contextPath}/docs/register.do" method="POST">
<!-- form:hidden path="action"/-->
<div class="panel" style="width: 100%; text-align: right">
<span 
      id="filter_btn" 
      class="active button_general"> 
      [фильтровать] 
</span>
<span 
      class="active button_general"
      onclick="openUrl('${pageContext.request.contextPath}/docs/card/add.do', '0')">
       
      [добавить]
       
</span>


<span>&nbsp;&nbsp;</span>

<span 
      class="active button_general"
      onclick="submitWithAction('DocRegForm', 
      	'${pageContext.request.contextPath}/docs/register/delete.do', [])"> 
      
      [удалить]
       
</span>
</div>
<table style="width: 100%">
<colgroup>
<col width="30"/>
<col width="60"/>
<col width="190"/>
<col width="100">
<col />
</colgroup>
<thead>
<tr>
    <th class="column_header">
    </th>
    <th class="column_header">
    №
    </th>
    <th class="column_header">
    Код
    </th>
    <th class="column_header">
    Последняя версия
    </th>
    <th class="column_header">
    Название
    </th>
</tr>
</thead>
<tbody>
<c:forEach items="${reg_row_plh.pageList}" var="rec"
    varStatus="status">
<tr class="
    <c:if test="${status.count % 2 == 0}">row_even</c:if>
    <c:if test="${status.count % 2 != 0}">row_odd</c:if>
">
<td>
    <input name="selected" type="checkbox" value="0">
    <input name="selected" type="hidden"   value="1">
</td>
<td>
    <a href="${pageContext.request.contextPath}/docs/card/edit.do?card_id=${rec.cardId}">
    ${rec.regNum}
    </a>
</td>
<td>
    ${rec.docCode}
</td>
<td>
    ${rec.lastApproDate}
</td>
<td>
    ${rec.docName}
</td>
</tr>
</c:forEach>
</tbody>
<tfoot>
<tr>
<tr>
<td colspan="5">
    <ui:paginator form='DocRegForm' 
       action="${pageContext.request.contextPath}/docs/register/get_page.do" 
       paginator="${reg_row_plh}"/>
</td>
</tr>
</tfoot>
</table>
</form:form>

<!-- Диалог фильтрации -->
<div id="filter_dlg" style="width: 300">
<form:form id="FilterForm" 
    action="${pageContext.request.contextPath}/docs/register/filter.do" method="POST">
<table style="width: 100%; height: 100%">
<colgroup>
<col/>
<col/>
</colgroup>
<tbody>
<tr>
	<td>
	Код:
	</td>
	<td>
	<input type="text" id="doc_code" name="doc_code" >
	</td>
</tr>
<tr>
	<td>
	Название:
	</td>
	<td>
	<textarea id="doc_name" name="doc_name" rows="2" cols="50"></textarea>
	</td>
</tr>
<tr>
    <td colspan="2">
    <div class="panel" style="width: 100%; text-align: right">
    <span 
      class="active button_general"
      onclick="submitWithAction('FilterForm', '', [])">
       
      [применить]
       
    </span>
    <span 
      class="active button_general"
      onclick="$('#filter_dlg').dialog('close');return false;">
       
      [отмена]
       
    </span>
    </div>
    </td>
</tr>
</tbody>
</table>
</form:form>
</div>