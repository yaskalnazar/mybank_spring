<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>

</head>
<body>
<#include "../parts/admin_navnar.ftl">
<h1>All accounts:</h1>
<#if creditRequests?has_content>

<table class="table">
    <thead>
    <tr>
        <th>equestId</th>
        <th>Applicant().getEmail</th>
        <th>CreditRate</th>
        <th>CreditLimit</th>
        <th>CreationDate</th>
    </tr>
    </thead>
    <tbody>
    <#list creditRequests as creditRequest>
    <tr>
        <td>${creditRequest.getRequestId()}</td>
        <td>${creditRequest.getApplicant().getEmail()}</td>
        <td>${creditRequest.getCreditRate()}</td>
        <td>${creditRequest.getCreditLimit()}</td>
        <td>${creditRequest.getCreationDate()}</td>
    </tr>
    </#list>
    </tbody>
</table>
<#else>
    <h1>You don`t have any accounts yet</h1>
</#if>
</body>
</html>