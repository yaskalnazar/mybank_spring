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
<div class="container" style="margin-top: 60px">
    <div class="col-md-8 col-md-offset-2">
        <#include "../parts/user_main_info.ftl">
        <#include "../parts/user_admin_info.ftl">
    </div>
</div>
</body>
</html>
