<%@ tag language="java" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ attribute name="paginator" 
	type="org.springframework.beans.support.PagedListHolder"
	rtexprvalue="true" 
	required="true" %>
	
<%@ attribute name="form" 
    type="java.lang.String"
    rtexprvalue="true"
    required="true" %>
	
<%@ attribute name="action" 
	type="java.lang.String"
	rtexprvalue="true"
	required="true" %>
<!-- инициализируем текущей страницей для сохранения значения между запросами -->
<input type="hidden" name="page" value="${ paginator.page }">
<div class="paginator">
   <c:if test="${!paginator.firstPage}">
    <span >
    <a class="control_text" 
        href="javascript:submitWithAction('${form}', '${action}', [{name: 'page', value:  0}])">
    &lt;
    </a>
    </span>
</c:if>
<c:if test="${paginator.firstPage}">
    <span class="paginator_selected">
    &lt;
    </span>
</c:if>
<c:forEach begin="${paginator.firstLinkedPage}"
    end="${paginator.lastLinkedPage}"
    var="i">
    <c:choose>
    <c:when test="${paginator.page == i}">
    <span class="paginator_selected">${i+1}</span>
    </c:when>
    <c:otherwise>
    <span >
    <a class="control_text" 
        href="javascript:submitWithAction('${form}', 
	      '${action}', 
              [{name: 'page', value: ${i}}])">
    ${i+1}
    </a>
    </span>
    </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${!paginator.lastPage}">
    <span >
    <a class="control_text" 
        href="javascript:submitWithAction('${form}', '${action}', [{name: 'page', value: ${paginator.pageCount - 1}}])">
    &gt;
    </a>
    </span>
</c:if>
<c:if test="${paginator.lastPage}">
    <span class="paginator_selected">
    &gt;
    </span>
</c:if>
</div>
	

