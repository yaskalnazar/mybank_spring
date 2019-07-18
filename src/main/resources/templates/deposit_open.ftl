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
            var temp = document.getElementById("monthsAmount").value;
            document.getElementById("depositRate").value = temp/200;
        }
    </script>
</head>
<body ng-app="login_form" ng-controller="AppCtrl">
<#include "parts/user_navbar.ftl">
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <#if success?has_content>
                <div class="alert alert-success" role="alert"><@spring.message "message.deposit.open.success"/></div>
            </#if>
            <#if error?has_content>
                <div class="alert alert-warning" role="alert"><@spring.message "message.deposit.open.error"/></div>
            </#if>
            <form method="post"  style="margin-bottom: 30px" name="form" autocomplete="off" ">
            <div class="form-group">
                <label>depositAmount<input type="number" min="1000" name="depositAmount" required></label>
                <input id="depositRate" type="number" name="depositRate" min="0" value="0.015" readonly>
                <select name="monthsAmount" id="monthsAmount" oninput="change()">
                    <option value=3>3 months</option>
                    <option value=6>6 months</option>
                    <option value=9>9 months</option>
                    <option value=12>12 months</option>
                </select>

            </div>
            <button type="submit" class="btn btn-success" style="margin-top:30px" >
                <@spring.message "holder.submit"/>
            </button>
            </form>

        </div>
    </div>
</div>
</body>
</html>