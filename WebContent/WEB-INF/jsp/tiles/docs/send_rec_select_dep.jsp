<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="form_title"> Выбор службы </div>

<form:form id="DepSelectForm" 
    action="${pageContext.request.contextPath}/docs/send_record/add.do"
    commandName="version" 
    method="POST">
<form:hidden path="id"/>
<table style="width: 100%">
<colgroup>
<col width="30"/>
<col width="70"/>
<col />
</colgroup>
<thead>
<tr>
    <th class="column_header">
    </th>
    <th class="column_header">
    Номер
    </th>
    <th class="column_header">
    Название
    </th>
</tr>
</thead>
<tbody>
<c:forEach items="${dep_plh.pageList}" var="dep"
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
    
    ${dep.number}
    
</td>
<td>
    ${dep.name}
</td>
</tr>
</c:forEach>
</tbody>
<tfoot>
<tr>
<tr>
<td colspan="5">
    <ui:paginator form='DepSelectForm' 
       action="${pageContext.request.contextPath}/docs/send_record/dep_list_page.do" 
       paginator="${dep_plh}"/>
</td>
</tr>
</tfoot>
</table>
<table>
<tr>
    <td align="right">
    <span 
      class="active button_general"
      onclick="submitWithAction('DepSelectForm', '', [])"> 
      [добавить] 
    </span>
    <span 
      class="active button_general"
      onclick="openUrl('${pageContext.request.contextPath}/docs/version/edit.do?ver_id=${version.id}', 0)"> 
      
      [отмена]
       
    </span>
   
    </td>
</tr>
</table>
</form:form>