<#include "base.ftl"/>
<#macro head>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</#macro>
<#macro content>
    <#if info?has_content>
        <div class="reg-container">
            <p>info: ${info}</p>
        </div>
    <#else>
    </#if>
    <form name='f' action="/login" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form>
</#macro>
<#macro title>
    <title>Login</title>
</#macro>
<@display_page />