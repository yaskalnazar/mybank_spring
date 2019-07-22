<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Bank</title>
</head>
<body>
<#include "../parts/admin_navnar.ftl">
<#assign  user = applicant>
<div class="container" style="margin-top: 60px">
    <div class="col-md-8 col-md-offset-2">
        <#include "../parts/user_main_info.ftl">
        <#include "../parts/user_admin_info.ftl">
        <#if creditHistory?has_content>
            <h3>Credit hisory:</h3>
            <table class="table">
                <thead>
                <tr>
                    <th><@spring.message "holder.id"/></th>
                    <th><@spring.message "balance"/></th>
                    <th><@spring.message "status"/></th>
                    <th><@spring.message "closing.date"/></th>
                    <th><@spring.message "credit.limit"/></th>
                    <th><@spring.message "rate"/></th>
                </tr>
                </thead>
                <tbody>
                <#list creditHistory as account>
                    <tr>
                        <td>${account.getAccountId()}</td>
                        <td>${account.getBalance()}</td>
                        <td>${account.getAccountStatus()}</td>
                        <td>${account.getClosingDate()}</td>
                        <td>${account.getCreditLimit()}</td>
                        <td>${account.getCreditRate()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        <#else>
            <h3>No credits Yet</h3>
        </#if>


        <h3>Request:</h3>
        <table class="table">
            <thead>
            <tr>
                <th>RequestId</th>
                <th>CreditRate</th>
                <th>CreditLimit</th>
                <th>CreationDate</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${creditRequest.getRequestId()}</td>
                <td>${creditRequest.getCreditRate()}</td>
                <td>${creditRequest.getCreditLimit()}</td>
                <td>${creditRequest.getCreationDate()}</td>
            </tr>
            </tbody>
        </table>

        <form method="post" style="margin-bottom: 30px" name="form" autocomplete="off">
            <button name="action" value="APPROVED" type="submit" class="btn btn-success" style="margin-top:30px">
                Approve
            </button>
            <button name="action" value="REJECTED" type="submit" class="btn btn-danger" style="margin-top:30px">
                Reject
            </button>
        </form>
    </div>
</div>
</body>
</html>
