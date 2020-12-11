<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset="UTF-8">
<title>Calendar</title>
</head>
<body>
<div id="welcome-calendar" class="center">
<p>Hello, <%=session.getAttribute("userLogin").toString()%></p>

</div>

<div id="dcalendar" class="center">
	<fmt:setLocale value="en_US" />
	<div>
		<s:form action="ACalendar">		
				<s:select name="floorId" list="floorList" listValue="floorNumber" listKey="Id" label="Select Floor"></s:select>
				<s:submit value="Show" name="submit"/>
		</s:form>	
	</div>
	<table class="calendar">  
	<tr> 
		<th>Monday</th>
		<th>Tuesday</th>
		<th>Wednesday</th>
		<th>Thursday</th>
		<th>Friday</th>
		<th>Saturday</th>
		<th>Sunday</th>
	</tr>   
    <c:forEach begin="0" end="34" step="7" varStatus="i">       	
		<tr>    
			<c:forEach begin="0" end="6" varStatus="j">		    	 
		    	<c:choose>
					<c:when test="${calendar[j.index+i.index].active == false}">
						<td class="notActive">
							<div>
								<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>
							</div>							
						</td>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${calendar[j.index+i.index].current == false}">
								<c:choose>
									<c:when test="${calendar[j.index+i.index].locked == false}">
										<td class="active">
											<div>
												<form action="ADay" method="post">
													<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
													<input type="submit" class="submitDay" value="<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>" />
												</form>												
											</div>
											<br>											
										</td>
									</c:when>
									<c:otherwise>
										<td class="locked">
											<div>
												<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>
											</div>																															
										</td>										
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${calendar[j.index+i.index].locked == false}">
										<td class="current">
											<div>
												<form action="ADay" method="post">
													<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
													<input type="submit" class="submitDayCurrent" value="<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>" />
												</form>
											</div>											
										</td>										
									</c:when>
									<c:otherwise>
										<td class="currentLocked">
											<div>										
												<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>
											</div>										
										</td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
		    </c:forEach>
		</tr>  		  
	</c:forEach>  
	</table>
</div>
</body>
</html>