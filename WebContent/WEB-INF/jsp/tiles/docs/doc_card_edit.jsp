<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<form:form id="CardForm" 
    action="${pageContext.request.contextPath}/docs/card/save.do" 
    method="post" commandName="card">
    
    <form:hidden path="id"/>
    <form:hidden path="regNum"/>
<table class="form">
<colgroup>
<col/>

<col/>
</colgroup>
<tbody>
<tr>
    <td colspan="2">
    <div class="form_title">
    Карточка документа
    </div>
    
    </td>
</tr>
<tr>
	<td colspan="2">
	<form:errors path="*" cssClass="error_box" element="div"/>
	</td>
</tr>
<tr>
    <td align="right">
    <label for="docCode">Код:</label>
    </td>
    <td>
    <form:input 
    	path="docCode" 
    	size="30" 
    	cssClass="required"/>
    </td>
</tr>
<tr>
    <td align="right" valign="top">
    <label for="name">Название:</label>
    </td>
    <td>
    <form:textarea 
    	path="name" 
    	rows="3" 
    	cols="80" 
    	cssClass="required" />
    </td>
</tr>

<!-- Версии -->
<tr>
    <td colspan="2">
	<div class="form_section_title"> Версии документа</div>
	
	
	<div class="panel" style="width: 100%; text-align: right">
	<c:if test="${card.id != null}">
	<span 
	      class="active button_general"
	      onclick="openUrl('${pageContext.request.contextPath}/docs/version/add.do?card_id=${card.id}', '0')">
	       
	      [добавить]
	       
	</span>
	
	
	<span>&nbsp;</span>
	
	<span 
	      class="active button_general"
	      onclick="submitWithAction('CardForm', '${pageContext.request.contextPath}/docs/card/versions/delete.do', [])">
	       
		[удалить]
		 
	</span>
	      
	</c:if>
	</div>
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
	    Дата введения
	    </th>
	    <th class="column_header">
	    Файл
	    </th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${ver_plh.pageList}" var="ver"
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
	   <a href="${pageContext.request.contextPath}/docs/version/edit.do?ver_id=${ver.id}">
	    ${ver.approDate}
	   </a>
	</td>
	<td>
	   <a  href="${pageContext.request.contextPath}/docs/version/download.do?version_id=${ver.id}">
        файл
        </a>
	</td>
	</tr>
	</c:forEach>
	</tbody>
	<tfoot>
	<tr>
	<tr>
	<td colspan="3">
	    <ui:paginator form='CardForm' 
	       action="${pageContext.request.contextPath}/docs/card/versions.do" 
	       paginator="${ver_plh}"/>
	</td>
	</tr>
	</tfoot>
	</table>
	</td>
</tr>
<!-- Конец: Версии -->
<tr>
    <td colspan="2" align="right">
    <span 
      class="active button_general"
      onclick="submitWithAction('CardForm', '', [])"> 
      [сохранить] 
    </span>
    <span 
      class="active button_general"
      onclick="openUrl('${pageContext.request.contextPath}/docs/register.do', 0)"> 
      
      [отмена]
       
    </span>
   
    </td>
</tr>
</tbody>
</table>
</form:form>