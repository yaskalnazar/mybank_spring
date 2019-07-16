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
<body ng-app="users_form" ng-controller="UserCtrl">
<#include "navbar.ftl">
<h1 data-ng-init="getUsers()"><@spring.message "all.users"/>:</h1>

<table ng-model="users"  class="table">
    <tr>
        <th><@spring.message "holder.id"/></th>
        <th><@spring.message "holder.first.name"/></th>
        <th><@spring.message "holder.last.name"/></th>
        <th><@spring.message "holder.email"/></th>
        <th><@spring.message "holder.role"/></th>
        <th><@spring.message "holder.password"/></th>
        <th><@spring.message "holder.accountNonExpired"/></th>
        <th><@spring.message "holder.accountNonLocked"/></th>
        <th><@spring.message "holder.credentialsNonExpired"/></th>
        <th><@spring.message "holder.enabled"/></th>
    </tr>
    <tr ng-repeat="u in users">
        <td>{{u.id}}</td>
        <td>{{u.firstName}}</td>
        <td>{{u.lastName}}</td>
        <td>{{u.email}}</td>
        <td>{{u.authorities}}</td>
        <td>{{u.password}}</td>
        <td>{{u.accountNonExpired}}</td>
        <td>{{u.accountNonLocked}}</td>
        <td>{{u.credentialsNonExpired}}</td>
        <td>{{u.enabled}}</td>
    </tr>
</table>
<script type="text/javascript" src="../js/users.js"></script>
</body>
</html>