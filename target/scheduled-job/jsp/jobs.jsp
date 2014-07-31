
<html>
<head>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.quartz.JobDetail" %>
<%@ page import="org.quartz.impl.JobDetailImpl" %>
<%@ page import="org.quartz.Trigger" %>
<%@ page import ="org.quartz.Scheduler" %>

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0-beta.1/css/jquery.dataTables.css">

<script type="text/javascript" language="javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="//cdn.datatables.net/1.10-beta.1/js/jquery.dataTables.js"></script>
<script>
$(document).ready(function() {
    $('#dataTable').dataTable(paginate: false,
                              scrollY: 300);
} );


</script>

 </head>
 <body>
<portlet:defineObjects/>

<table border="1" id="dataTable">

<tr >
<th>Job Key</th>
<th>Job Name</th>
<th>Job Group</th>
<th>Job Builder</th>
<th>Job Class</th>
<th>Job DataMap</th>
<th>Job Triggers</th>
<th>Fire Job Now</th>
</tr>
<tbody>
<c:forEach items="${jobsList}" var="aJob">
<tr>
<td>${aJob.getKey()}</td>
<td>${aJob.getKey().getName()}</td>
<td>${aJob.getGroup()}</td>
<td>${aJob.getJobBuilder()}</td>
<td>${aJob.getJobClass()}</td>
<td>${aJob.getJobDataMap()}</td>
<c:forEach items="${triggerlist}" var="trigger">
<td>${trigger.getNextFireTime()}</td>
</c:forEach>
<td><Button type="button" value="<%scheduler.triggerJob(aJob.getKey())%>" onclick="alert(Job Fired);" style="width:180px; height:40px; ">Fire Now</Button></td>
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>