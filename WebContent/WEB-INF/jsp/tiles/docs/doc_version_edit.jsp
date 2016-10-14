<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<form:form id="VersionForm" 
    action="${pageContext.request.contextPath}/docs/version/save.do" 
    method="post" commandName="version"
    enctype="multipart/form-data">
    
    <form:hidden path="id"/>
    <form:hidden path="card.id"/>
<table class="form">
<colgroup>
<col/>

<col/>
</colgroup>
<tbody>
<tr>
    <td colspan="2" >
    <div class="form_title">
    Версия документа: ${version.card.docCode}
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
    <label for="approDate"> Дата введения: </label>
    </td>
    <td>
    <form:input path="approDate" size="10"/>
    </td>
</tr>
<tr>
    <td align="right">
    <label for="approDate"> Файл: </label>
    </td>
    <td>
    <c:if test="${version.content != null}">
    <a  href="${pageContext.request.contextPath}/docs/version/download.do?version_id=${version.id}"
        style="margin-left: 3;margin-right: 3;">
    файл
    </a>
    </c:if>
    <input type="file" name="file" />
    </td>
</tr>

<!-- Записи рассылки -->
<tr>
    <td colspan="2">
    <div class="form_section_title"> Записи рассылки </div>
    
    
    <div class="panel" style="width: 100%; text-align: right">
    <span 
          class="active button_general"
          onclick="openUrl('${pageContext.request.contextPath}/docs/send_record/select_dep.do?version_id=${version.id}', '0')">
           
          [добавить]
           
    </span>
    
    <span>&nbsp;&nbsp;</span>
    
    <span 
          class="active button_general"
          onclick="submitWithAction('VersionForm', 
            '${pageContext.request.contextPath}/docs/version/send_records/delete.do', 
            [])">
             
          [удалить]
           
    </span>
    </div>
    <table style="width: 100%">
    <colgroup>
    <col width="30"/>
    <col width="70"/>
    <col width="100"/>
    <col />
    </colgroup>
    <thead>
    <tr>
        <th class="column_header">
        </th>
        <th class="column_header">
        Дата
        </th>
        <th class="column_header">
        Номер службы
        </th>
        <th class="column_header">
        Название службы
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${send_recs_plh.pageList}" var="send_rec"
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
        ${send_rec.date}
    </td>
    <td>
        ${send_rec.dep.number}
    </td>
    <td>
        ${send_rec.dep.name}
    </td>
    </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
    <tr>
    <td colspan="4">
        <ui:paginator form='VersionForm' 
           action="${pageContext.request.contextPath}/docs/version/send_records/get_page.do" 
           paginator="${send_recs_plh}"/>
    </td>
    </tr>
    </tfoot>
    </table>
    </td>
</tr>
<!-- Конец: Записи рассылки -->

<tr>
    <td colspan="2" align="right">
    <span 
      class="active button_general"
      onclick="submitWithAction('VersionForm', '', [])"> 
      [сохранить] 
    </span>
    <span 
      class="active button_general"
      onclick="openUrl('${pageContext.request.contextPath}/docs/card/edit.do?card_id=${version.card.id}', 0)"> 
      
      [отмена]
       
    </span>
   
    </td>
</tr>
</tbody>
</table>
</form:form>