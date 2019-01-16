<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Settlement Page</title>
</head>
<body>
<form action="SettlementServlet " method="get">
    <table border="2" bgcolor="#dc143c" cellpadding="2" cellspacing="0">
    <%--${userTrain}--%>
        <%
            out.print("Dear ");
            out.println(request.getParameter("firstName"));
            out.println(request.getParameter("lastName")+", ");
            out.println("Your ticket is");
        %>
    <thead>
    <tr>
        <th>#Train Id</th>
        <th>Train Number</th>
        <th>Initial Station</th>
        <th>End Station</th>
        <th>Cost</th>
        <th>Departure Date</th>
        <th>Departure Time</th>
        <th>Arrival Date</th>
        <th>Arrival Time</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td align="center">${userTrain.trainId}</td>
        <td align="center">${userTrain.trainNumber}</td>
        <td align="center">${userTrain.initialStation}</td>
        <td align="center">${userTrain.endStation}</td>
        <td align="center">${userTrain.cost}</td>
        <td align="center">${userTrain.departureDate}</td>
        <td align="center">${userTrain.departureTime}</td>
        <td align="center">${userTrain.arrivalDate}</td>
        <td align="center">${userTrain.arrivalTime}</td>
    </tr>
    </tbody>
        <tr>
            <td><input type="submit" value="Pay?" style="width: 100%"></td>
        </tr>
    </table>
</form>

</body>
</html>
