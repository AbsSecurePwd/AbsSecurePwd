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
										<td class="active" id="dayCell">
											<div>
												<form action="ADay" method="post">
													<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
													<input type="submit" class="submitDay" value="<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>" />
												</form>	
											</div>
											<br>
											<div id="supervisorDayMenu">
												<c:choose>
													<c:when test="${calendar[j.index+i.index].yesterday == false}">
														<div class="dayMenu">
															<form action="LockAllDay" method="post">
																<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
																<input type="hidden" name="lock" value="true" />
																<input type="submit" class="dayMenu" value="Lock" />
															</form>
														</div>
														<c:choose>
															<c:when test="${calendar[j.index+i.index].bookedWinNum > 0}">
																<div class="dayMenu">
																	<form action="CancelAllDay" method="post">
																		<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />													
																		<input type="submit" class="dayMenu" value="Cancel" />
																	</form>												
																</div>
															</c:when>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${calendar[j.index+i.index].bookedWinNum > 0}">
																<div class="dayMenu">
																	<form action="CancelAllDay" method="post">
																		<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />													
																		<input type="submit" class="dayMenu" value="Cancel" />
																	</form>												
																</div>
															</c:when>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</div>
										</td>
									</c:when>
									<c:otherwise>
										<td class="locked" id="dayCell">
											<div>
												<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>
											</div>
											<br>
											<div id="supervisorDayMenu">
												<div class="dayMenu">
													<form action="LockAllDay" method="post">
														<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
														<input type="hidden" name="lock" value="false" />
														<input type="submit" class="dayMenu" value="UnLock" />
													</form>											
												</div>	
												<c:choose>
													<c:when test="${calendar[j.index+i.index].bookedWinNum > 0}">
														<div class="dayMenu">
															<form action="CancelAllDay" method="post">
																<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />													
																<input type="submit" class="dayMenu" value="Cancel" />
															</form>												
														</div>
													</c:when>
												</c:choose>	
											</div>																										
										</td>										
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${calendar[j.index+i.index].locked == false}">
										<td class="current" id="dayCell">
											<div>
												<form action="ADay" method="post">
													<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
													<input type="submit" class="submitDayCurrent" value="<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>" />
												</form>
											</div>
											<br>
											<div id="supervisorDayMenu">
												<div class="dayMenu">
													<form action="LockAllDay" method="post">
														<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
														<input type="hidden" name="lock" value="true" />
														<input type="submit" class="dayMenu" value="Lock" />
													</form>
												</div>
												<c:choose>
													<c:when test="${calendar[j.index+i.index].bookedWinNum > 0}">
														<div class="dayMenu">
															<form action="CancelAllDay" method="post">
																<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />													
																<input type="submit" class="dayMenu" value="Cancel" />
															</form>												
														</div>
													</c:when>
												</c:choose>
											</div>
										</td>										
									</c:when>
									<c:otherwise>
										<td class="currentLocked" id="dayCell">
											<div>										
												<fmt:formatDate value="${calendar[j.index+i.index].date}" pattern="dd MMM"/>
											</div>
											<br>
											<div id="supervisorDayMenu">
												<div class="dayMenu">
													<form action="LockAllDay" method="post">
														<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />
														<input type="hidden" name="lock" value="false" />
														<input type="submit" class="dayMenu" value="UnLock" />
													</form>
												</div>	
												<c:choose>
													<c:when test="${calendar[j.index+i.index].bookedWinNum > 0}">
														<div class="dayMenu">
															<form action="CancelAllDay" method="post">
																<input type="hidden" name="rday" value="${calendar[j.index+i.index].id}" />													
																<input type="submit" class="dayMenu" value="Cancel" />
															</form>												
														</div>
													</c:when>
												</c:choose>		
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
	
	
</div>
</body>
</html>


