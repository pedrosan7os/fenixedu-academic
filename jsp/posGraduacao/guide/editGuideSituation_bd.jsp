<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.Date" %>
<%@ page import="net.sourceforge.fenixedu.presentationTier.Action.sop.utils.SessionConstants" %>
<%@ page import="net.sourceforge.fenixedu.dataTransferObject.InfoGuideSituation" %>
<%@ page import="net.sourceforge.fenixedu.util.Data" %>
<%@ page import="net.sourceforge.fenixedu.util.SituationOfGuide" %>

   		<bean:define id="infoGuide" name="<%= SessionConstants.GUIDE %>" scope="session"/>  		
   		<bean:define id="guideSituation" name="infoGuide" property="infoGuideSituation"/>  		
   		<bean:define id="days" name="<%= SessionConstants.MONTH_DAYS_KEY %>" scope="session"/>
   		<bean:define id="months" name="<%= SessionConstants.MONTH_LIST_KEY %>" scope="session"/>
   		<bean:define id="years" name="<%= SessionConstants.YEARS_KEY %>" scope="session"/>
        <bean:define id="number" name="infoGuide" property="number" />
        <bean:define id="year" name="infoGuide" property="year" />
        <bean:define id="version" name="infoGuide" property="version" />
    
    	<strong>
    	<bean:message key="label.masterDegree.administrativeOffice.guideInformation" 
    				   arg0='<%= pageContext.findAttribute("version").toString() %>'
    				   arg1='<%= pageContext.findAttribute("number").toString() %>' 
    				   arg2='<%= pageContext.findAttribute("year").toString() %>' 
    	 />
    	</strong>
    	 
      <br>
      <br>
      <span class="error"><html:errors/></span>
      <br>

	<bean:define id="action">/editGuideSituation.do?method=editGuideSituation<%= "&" %>year=<bean:write name="infoGuide" property="year"/><%= "&" %>number=<bean:write name="infoGuide" property="number"/><%= "&" %>version=<bean:write name="infoGuide" property="version"/>
	</bean:define>



  <html:form action='<%= pageContext.findAttribute("action").toString() %>'>
   	  <html:hidden property="page" value="1"/>  
   <strong><bean:message key="label.masterDegree.administrativeOffice.activeSituation" /></strong>
   <br>
   <br>
   <table border="0">
  	   
       <tr>
		<td><bean:message key="label.masterDegree.administrativeOffice.remarks" /></td>
        <td><bean:write name="guideSituation" property="remarks"/></td>
       </tr>
       <tr>
		<td><bean:message key="label.masterDegree.administrativeOffice.situation" /></td>
        <td><bean:write name="guideSituation" property="situation"/></td>
       </tr>
       <tr>
		<td><bean:message key="label.masterDegree.administrativeOffice.situationDate" /></td>
        <logic:present name="guideSituation" property="date" >
			<bean:define id="date" name="guideSituation" property="date" />
			<td><%= Data.format2DayMonthYear((Date) date) %></td>   
		</logic:present>
       </tr>
       <% 
       		if (((InfoGuideSituation) guideSituation).getSituation().equals(SituationOfGuide.PAYED_TYPE)) 
       		{ 
       %>
       			<tr>
    				<td><bean:message key="label.masterDegree.administrativeOffice.payment" /></td>
    				<td><bean:write name="infoGuide" property="paymentType"/></td>
    			</tr>
    			
    			<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>
			
				<tr>
					<td>
						<b><bean:message key="label.masterDegree.administrativeOffice.nonChangeableGuideSituation" /></b>
					</td>
				</tr>

     	<% 
     		}
     		else
     		{
     	%>
				<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>

	   			<!-- Guide Situation -->
       			<tr>
        			<td><bean:message key="label.masterDegree.administrativeOffice.remarks"/> </td>
       				<td><html:textarea property="remarks"/></td>
       			</tr>
       			<tr>
       				<td>&nbsp;</td>
       			</tr>
       			<tr>
        			<td>
        				<bean:message key="label.masterDegree.administrativeOffice.newGuideSituation" />
        			</td>
        			<td>
            			<html:select property="guideSituation">
           					<html:options collection="<%= SessionConstants.GUIDE_SITUATION_LIST %>" property="value" labelProperty="label" />
            			</html:select>          
       				</td>
       			</tr>

				<tr>
					<td> &nbsp;	</td>
				</tr>
		
				<!-- Payment Date -->
        		<tr>
         			<td><bean:message key="label.masterDegree.administrativeOffice.paymentDate" /></td>
          			<td>
          				<html:select property="paymentDateYear">
                			<html:options collection="<%= SessionConstants.YEARS_KEY %>" property="value" labelProperty="label"/>
	             		</html:select>
    	         		<html:select property="paymentDateMonth">
        	        		<html:options collection="<%= SessionConstants.MONTH_LIST_KEY %>" property="value" labelProperty="label"/>
            	 		</html:select>
             			<html:select property="paymentDateDay">
                			<html:options collection="<%= SessionConstants.MONTH_DAYS_KEY %>" property="value" labelProperty="label"/>
             			</html:select>
	          		</td>          
        		</tr>
	
				<tr>
					<td> &nbsp;	</td>
				</tr>

    			<tr>
    				<td>
    					<bean:message key="label.masterDegree.administrativeOffice.payment" />
    				</td>
    				<td>
    					<html:select property="paymentType">
    						<html:options collection="<%= SessionConstants.PAYMENT_TYPE %>" property="value" labelProperty="label" />
    					</html:select>     
    				</td>
    			</tr>

				<tr>
					<td> &nbsp;	</td>
				</tr>
				<tr>
					<td> &nbsp;	</td>
				</tr>
   				<tr>
   					<td>
   						<html:submit property="Alterar">Alterar Situa��o</html:submit>
   						<html:reset property="Reset">Dados Originais</html:reset>
   					</td>
   				</tr>
        		
        <%
     		}
        %>
        
   </table>

   
</html:form>     

      
      