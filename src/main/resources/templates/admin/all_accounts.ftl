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
<#if accounts?has_content>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Owner ID</th>
        <th>Balance</th>
        <th>Status</th>
        <th>ClosingDate</th>
        <th>AccountType</th>
    </tr>
    </thead>
    <tbody>
    <#list accounts as account>
    <tr>
        <td>${account.getId()}</td>
        <td>${account.getOwnerId()}</td>
        <td>${account.getBalance()}</td>
        <td>${account.getStatus()}</td>
        <td>${account.getClosingDate()}</td>
        <td>${account.getAccountType()}</td>
    </tr>
    </#list>
    </tbody>
</table>
<#else>
    <h1>You don`t have any accounts yet</h1>
</#if>
</body>
</html>