<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.quartz.JobDetail" %>
<%@ page import="org.quartz.impl.JobDetailImpl" %>
<%@ page import="org.quartz.Trigger" %>

<portlet:defineObjects/>

<table border="1" id="dataTable">
<thead>
<tr>
 <th>Job Name</th>
 <th>Job Group</th>
 <th>Properties</th>
 <th>Next Execution</th>
</tr>
</thead>
<tbody>
<c:forEach items="${jobsList}" var="aJob">
 <tr>
  <td>${aJob.getJobDetail().getKey().getName()}</td>
  <td>${aJob.getJobDetail().getGroup()}</td>
  <td>${aJob.getProperties()}</td>
  <td>${aJob.getJobTrigger().getNextFireTime()}</td>
 </tr>
</c:forEach>
</tbody>
</table>
