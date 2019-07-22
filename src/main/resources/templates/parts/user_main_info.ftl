<#import "/spring.ftl" as spring/>
<div>
        <h3>Main info:</h3>
        <table class="table table-bordered ">
            <tbody>
            <tr>
                <th style="width: 30%"><@spring.message "holder.first.name"/>:</th>
                <td>${user.getFirstName()}</td>
            </tr>
            <tr>
                <th><@spring.message "holder.last.name"/>:</th>
                <td>${user.getLastName()}</td>
            </tr>
            <tr>
                <th><@spring.message "holder.email"/>:</th>
                <td>${user.getEmail()}</td>
            </tr>
            </tbody>
        </table>
</div>