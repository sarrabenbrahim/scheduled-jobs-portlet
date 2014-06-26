<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.quartz.JobDetail" %>
<%@ page import="org.quartz.impl.JobDetailImpl" %>
<%@ page import="org.quartz.Trigger" %>

<portlet:defineObjects/>

<table border="1" id="dataTable">

<tr >
<th>Job Name</th>
<th>Job Group</th>
<th>Job Builder</th>
<th>Job Class</th>
<th>Job DataMap</th>
<th>Job Triggers</th>
</tr>
<tbody>
<c:forEach items="${jobsList}" var="aJob">
<tr>
<td>${aJob.getKey().getName()}</td>
<td>${aJob.getGroup()}</td>
<td>${aJob.getJobBuilder()}</td>
<td>${aJob.getJobClass()}</td>
<td>${aJob.getJobDataMap()}</td>
<c:forEach items="${triggerlist}" var="trigger">
<td>${trigger.getNextFireTime()}</td>
</c:forEach>
</tr>
</c:forEach>
</tbody>
</table>