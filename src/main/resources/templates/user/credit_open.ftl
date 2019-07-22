<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script>
        function change() {
            var temp = document.getElementById("creditLimit").value;
            document.getElementById('requestTooLarge').innerHTML = '';
            document.getElementById("submit").disabled = false;
            if (temp<5000)
                document.getElementById("creditRate").value = 0.05;
            else if (temp<10000)
                document.getElementById("creditRate").value = 0.04;
            else if (temp<50000)
                document.getElementById("creditRate").value = 0.03;
            else {
                document.getElementById("creditRate").value = 0;
                document.getElementById('requestTooLarge').innerHTML = 'credit request too large';
                document.getElementById("submit").disabled = true;
            }
        }
    </script>
</head>
<body ng-app="login_form" ng-controller="AppCtrl">
<#include "../parts/user_navbar.ftl">
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <#if success?has_content>
                <div class="alert alert-success" role="alert">Credit success</div>
            </#if>
            <#if error?has_content>
                <div class="alert alert-warning" role="alert">Credit Eror</div>
            </#if>
            <#if activeCreditAccounts?has_content>
                <h1>You alredy have activeCreditAccounts</h1>
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
                    <#list activeCreditAccounts as account>
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
                <form method="post"  style="margin-bottom: 30px" name="form" autocomplete="off" ">
                <div class="form-group">
                    <p>
                        <label>Credit limit:<input id="creditLimit" class="form-control" type="number" min="1000" name="creditLimit" required oninput="change()"></label>
                    </p>
                    <label><@spring.message "rate"/>
                        <input class="form-control" id="creditRate" type="number" name="creditRate" min="0" value="0.05" readonly>
                    </label>
                    <label id="requestTooLarge"></label>
                   <#-- <label><@spring.message "months.amount"/>
                        <select class="form-control" name="monthsAmount" id="monthsAmount" oninput="change()">
                            <option value=5>5 <@spring.message "months"/></option>
                            <option value=6>6 <@spring.message "months"/></option>
                            <option value=9>9 <@spring.message "months"/></option>
                            <option value=12>12 <@spring.message "months"/></option>
                        </select>
                    </label>-->
                </div>
                <button type="submit" id="submit" class="btn btn-success" style="margin-top:30px" >
                    <@spring.message "holder.submit"/>
                </button>
                </form>
            </#if>

        </div>
    </div>
</div>
</body>
</html>