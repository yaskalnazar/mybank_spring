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
</head>
<body ng-app="login_form" ng-controller="AppCtrl">
<#include "parts/guest_navbar.ftl">
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><@spring.message "login.title"/></h2>

            <#if logout>
                <div class="alert alert-success" role="alert"><@spring.message "message.logout"/></div>
            </#if>
            <#if error>
                <div class="alert alert-warning" role="alert"><@spring.message "message.login.error"/></div>
            </#if>
            <form method="post" style="margin-bottom: 30px" name="form" autocomplete="off" ">
                <div class="form-group">
                    <label for="exampleInputEmail1"><@spring.message "holder.email"/></label>
                    <input type="email"
                           name="email"
                           class="form-control"
                           id="exampleInputEmail1"
                           placeholder=<@spring.message "holder.email"/>
                           required
                           ng-model="auth.email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1"><@spring.message "holder.password"/></label>
                    <input type="password"
                           class="form-control"
                           name="password"
                           id="exampleInputPassword1"
                           placeholder=<@spring.message "holder.password"/>
                           required
                           ng-model="auth.password">
                </div>
                <button type="submit" class="btn btn-success" style="margin-top:30px" >
                    <@spring.message "holder.submit"/>
                </button>
                <button class="btn btn-secondary" style="margin-top:30px" onclick="window.location.href = '/form';">
                    <@spring.message "registration.button.title"/>
                </button>
<#--
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden" />
-->
            </form>
        </div>
    </div>
</div>
</body>
</html>