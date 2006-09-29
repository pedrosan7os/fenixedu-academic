<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>

<logic:present role="RESEARCHER">
	<bean:define id="result" name="result"/>
	<bean:define id="resultId" name="result" property="idInternal"/>
	<bean:define id="resultType" name="result" property="class.simpleName"/>
	<bean:define id="parameters" value="<%="resultId=" + resultId + "&resultType=" + resultType%>"/>

	<!-- Action paths definitions -->
	<bean:define id="backLink" value="<%="/resultParticipations/backToResult.do?" + parameters%>"/>
	
	<!-- Titles -->
	<logic:equal name="resultType" value="ResultPatent">
		<em>Patentes</em> <!-- tobundle -->
	</logic:equal>
	<logic:notEqual name="resultType" value="ResultPatent">
		<em>Publicações</em> <!-- tobundle -->
	</logic:notEqual>
	<h2><bean:message bundle="RESEARCHER_RESOURCES" key="researcher.ResultParticipation.useCase.title"/>: <fr:view name="result" property="title"/></h2>
	
	<ul class="list5 mvert2">
		<li>
			<html:link page="<%= backLink %>"><bean:message bundle="RESEARCHER_RESOURCES" key="link.goBackToView"/></html:link>
		</li>
	</ul>
	
	<!-- Warning/Error messages -->
	<logic:messagesPresent name="messages" message="true">
		<html:messages id="messages" message="true" bundle="RESEARCHER_RESOURCES">
			<p><span class="error0"><!-- Error messages go here --><bean:write name="messages"/></span></p>
		</html:messages>
	</logic:messagesPresent>
	
	<!-- Participation List -->
 	<logic:present name="removeOnly"><jsp:include page="editParticipationsRemove.jsp"/></logic:present>
	<logic:present name="alterOrder"><jsp:include page="editParticipationsOrder.jsp"/></logic:present>
	<logic:present name="editRoles"><jsp:include page="editParticipationsRole.jsp"/></logic:present>
 	
 	<!-- Create new Result Participation  -->
 	<logic:notPresent name="deleteConfirmation">
		<jsp:include page="createParticipation.jsp"/>
	</logic:notPresent>
	
	<!-- Go to previous page -->
	
</logic:present>
