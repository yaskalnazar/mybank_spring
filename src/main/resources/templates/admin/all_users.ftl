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
<#if users?has_content>

<table class="table">
    <thead>
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
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.getUserId()}</td>
        <td>${user.getFirstName()}</td>
        <td>${user.getLastName()}</td>
        <td>${user.getEmail()}</td>
        <td><#list user.getAuthorities() as authorities>
                ${authorities.getAuthority()}
        </#list></td>

        <td>${user.getPassword()}</td>
        <td>${user.isAccountNonExpired()?c}</td>
        <td>${user.isAccountNonLocked()?c}</td>
        <td>${user.isCredentialsNonExpired()?c}</td>
        <td>${user.isEnabled()?c}</td>
    </tr>
    </#list>
    </tbody>
</table>
<#else>
    <h1>You don`t have any users yet</h1>
</#if>
</body>
</html>