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
									<c:choose>
										<c:when test="${day[j.index+i.index].user.id == userId}">					
											<td>
												<div class="time">${day[j.index+i.index].wtime}</div>
												<div class="userbooked">${day[j.index+i.index].roomNumber}</div>												
												<div class="cancel">
													<form action="BookWindow" method="post">
														<input type="hidden" name="rwin" value="${day[j.index+i.index].id}" />
														<input type="hidden" name="book" value="false" />		
														<input type="hidden" name="rday" value="${rday}" />												
														<input type="submit" class="cancel" value="Cancel" />
													</form>	
												</div>																							
											</td>
										</c:when>
										<c:otherwise>
											<td>											
												<div class="time">${day[j.index+i.index].wtime}</div>												
												<div class="booked">${day[j.index+i.index].roomNumber}</div>																																		
											</td>
										</c:otherwise>
									</c:choose>	    
								</c:when>
								<c:otherwise>
									<td>										
										<div class="time">${day[j.index+i.index].wtime}</div>											
										<div class="book">											
												<form action="BookWindow" method="post">
													<input type="hidden" name="rwin" value="${day[j.index+i.index].id}" />
													<input type="hidden" name="rday" value="${rday}" />													
													<input type="hidden" name="book" value="true" />
													<input type="submit" class="book" value="Book" />
												</form>												
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
	<form hidden="true">
		<input id="message" type="hidden" name="message" value="${message}" />												
	</form>	
	<div id="message-dialog">
		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0; display: none;"></span></p>
	</div>
</div>	
		
</body>
</html>