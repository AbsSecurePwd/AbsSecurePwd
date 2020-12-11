<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset="UTF-8">
<title>Day</title>

</head>
<body>
<div id="welcome-calendar" class="center">
<p>Hello, <%=session.getAttribute("userLogin").toString()%></p>

</div>
<div id="dday" class="center">
	<fmt:setLocale value="en_US" />
	<table class="tday">  
	<caption>
	<fmt:formatDate value="${lDay.dDay.date}" pattern="E dd MMMM"/>	
	</caption>
	<c:forEach begin="0" end="23" step="6" varStatus="i">       	
	<tr>    
		<c:forEach begin="0" end="5" varStatus="j">
			<c:choose>
				<c:when test="${day[j.index+i.index].active == false}">					
					<td class="notActive">
						<div class="time">${day[j.index+i.index].wtime}</div>		
					</td>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${day[j.index+i.index].isLocked == true}">					
							<td class="locked">
								<div class="time">${day[j.index+i.index].wtime}</div>		
							</td>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${day[j.index+i.index].user != null}">																					
									<td class="active">
										<div class="time">${day[j.index+i.index].wtime}</div>	
										<c:choose>	
										<c:when test="${day[j.index+i.index].user.id == userId}">								
											<div class="userbooked">${day[j.index+i.index].roomNumber}</div>
										</c:when>
										<c:otherwise>
											<div class="booked">${day[j.index+i.index].roomNumber}</div>
										</c:otherwise>
										</c:choose>																															
									</td>								 
								</c:when>
								<c:otherwise>
									<td class="active">
										<div class="time">${day[j.index+i.index].wtime}</div>
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