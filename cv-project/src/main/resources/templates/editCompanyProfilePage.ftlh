<#include "base.ftlh"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</#macro>
<#macro content>
    <script type="application/javascript">
        function editCompany() {
            // Create an FormData object
            var form = $("#x")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
            console.log(address);
            $.ajax({
                method: "POST",
                url: form.action,
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success : function(data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                },
                error : function(err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }
        function addPost() {
            // Create an FormData object
            var form = $("#f")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
            console.log(address);
            $.ajax({
                method: "POST",
                url: form.action,
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success : function(data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                },
                error : function(err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }
    </script>
    <form id='x' action="/editCompanyProfile" method='POST'>
        <table>
            <tr>
                <td>Name:</td>
                <#if name?has_content>
                    <td><input type='text' name='name' value='${name}' required></td>
                <#else>
                    <td><input type='text' name='name' value='' required></td>
                </#if>
            </tr>
            <tr>
                <td>Phone:</td>
                <#if phone?has_content>
                    <td><input type='text' name='phone' value='${phone}'></td>
                <#else>
                    <td><input type='text' name='phone' value=''></td>
                </#if>
            </tr>
            <tr>
                <td>Description:</td>
                <#if description?has_content>
                    <td><input type='text' name='description' value='${description}'></td>
                <#else>
                    <td><input type='text' name='description' value=''></td>
                </#if>
            </tr>
            <tr>
                <td>Address:</td>
                <#if address?has_content>
                    <td><input type='text' name='address' value='${address}'></td>
                    <#else>
                    <td><input type='text' name='address' value=''></td>
                </#if>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <tr>
                <td><button type="button" onclick="editCompany()" value="Отправить"/></td>
            </tr>
        </table>
    </form>
    <br>
    <form id="f" action="/addPost">
        <#--        <input type="hidden" name="id" value="null"><br>-->
        <p>Name: </p>
        <input type="text" name="name"/>
        <p>Description: </p>
        <p><textarea rows="10" cols="45" name="description"></textarea></p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="button" onclick="addPost()" value="Submit"></button>
    </form>
    <a href="/profile">Go to profile</a>
</#macro>
<#macro title>
    <title>Edit company profile page</title>
</#macro>
<@display_page />