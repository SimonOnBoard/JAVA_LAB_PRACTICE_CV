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
    <form name='f' action="/addUser" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value='' required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' required /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type='email' name='email' required /></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td>  <p><select type='text' name="role" required>
                            <option value="ADMIN">ADMIN</option>
                            <option value="TEACHER">TEACHER</option>
                            <option value="COMPANY">COMPANY</option>
                        </select></p></td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form>
</#macro>
<#macro title>
    <title>Add user page</title>
</#macro>
<@display_page />