<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<#include "navbar.ftl">
<div class="container" style="margin-top: 60px">
    <div class="d-flex justify-content-center">
        <div class="col-md-8 col-md-offset-2">
    <h1><@spring.message "main.hello"/></h1>
    <button class="btn btn-primary" style="margin-top:30px" onclick="window.location.href = '/login';">
        <@spring.message "login.title"/>
    </button>
    <button class="btn btn-success" style="margin-top:30px" onclick="window.location.href = '/form';">
        <@spring.message "registration.button.title"/>
    </button>
        </div>
    </div>
</div>

</body>
</html>
