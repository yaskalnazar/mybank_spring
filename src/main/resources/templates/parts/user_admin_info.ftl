<#import "/spring.ftl" as spring/>
<div>
    <h3>Additional info:</h3>
    <table class="table table-bordered ">
        <tbody>
        <tr>
            <th style="width: 30%"><@spring.message "holder.id"/>:</th>
            <td>${user.getUserId()}</td>
        </tr>
        <tr>
            <th><@spring.message "holder.role"/>:</th>
            <td><#list user.getAuthorities() as authorities>
                    ${authorities.getAuthority()}
                </#list></td>
        </tr>
        <tr>
            <th><@spring.message "holder.accountNonExpired"/>:</th>
            <td>${user.isAccountNonExpired()?c}</td>
        </tr>
        <tr>
            <th><@spring.message "holder.accountNonLocked"/>:</th>
            <td>${user.isAccountNonLocked()?c}</td>
        </tr>
        <tr>
            <th><@spring.message "holder.credentialsNonExpired"/>:</th>
            <td>${user.isCredentialsNonExpired()?c}</td>
        </tr>
        <tr>
            <th><@spring.message "holder.enabled"/>:</th>
            <td>${user.isEnabled()?c}</td>
        </tr>
        </tbody>
    </table>
</div>

