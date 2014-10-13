<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="jquery.datatables.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page import="java.util.List" %>
  <%@ page import="org.quartz.JobDetail" %>
  <%@ page import="org.quartz.impl.JobDetailImpl" %>
  <%@ page import="org.quartz.Trigger" %>

 <script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
 <script src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js" type="text/javascript"></script>
 <link href="http://cdn.datatables.net/1.10.3/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript">
    $(document).ready(function() {
      $('#dataTable').dataTable();
    } );
  </script>
</head>
<body>
<portlet:defineObjects/>

<table border="1" id="dataTable">

  <tr >
    <th>Job Name</th>
    <th>Job Group</th>
    <th>Job Builder</th>
    <th>Job Class</th>
    <th>Job DataMap</th>
    <th>Job Triggers</th>
    <th>Fire Now</tr>

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
        <td><Button>${scheduler. scheduler.triggerJob(aJob.getKey())}</Button></td>
      </c:forEach>

    </tr>
  </c:forEach>

  </tbody>
</table>
</body>
</html>