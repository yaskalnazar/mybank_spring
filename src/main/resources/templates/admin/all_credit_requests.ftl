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
<div class="container" style="margin-top: 60px">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/admin/credit_requests/pending">Pending</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/credit_requests/rejected">Rejected</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/credit_requests/approved">Approved</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/credit_requests/all">All</a>
                </li>
            </ul>
        </div>
    </nav>
    <#if creditRequests?has_content>
        <table class="table">
            <thead>
            <tr>
                <th>RequestId</th>
                <th>Applicant Email</th>
                <th>CreditRate</th>
                <th>CreditLimit</th>
                <th>CreationDate</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <#list creditRequests as creditRequest>
                <tr>
                    <td><a href="/admin/credit_request/${creditRequest.getRequestId()}">
                            ${creditRequest.getRequestId()}</a></td>
                    <td><a href="/admin/user/${creditRequest.getApplicant().getUserId()}">
                            ${creditRequest.getApplicant().getEmail()}</a></td>
                    <td>${creditRequest.getCreditRate()}</td>
                    <td>${creditRequest.getCreditLimit()}</td>
                    <td>${creditRequest.getCreationDate()}</td>
                    <td>${creditRequest.getCreditRequestStatus()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <h3>No requests</h3>
    </#if>
</div>
</body>
</html>