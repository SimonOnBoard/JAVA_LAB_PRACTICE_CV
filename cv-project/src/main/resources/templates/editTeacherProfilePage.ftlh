<#include "base.ftlh"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</#macro>
<#macro content>
    <script type="application/javascript">
        function addLanguage() {
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
                    alert(err.responseText);
                }
            });
        }
        function addTeacherInfo() {
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
    <form id='f' action="/editTeacherProfile/${id}" method='POST'>
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
                <td>Patronymic:</td>
                <#if patronymic?has_content>
                    <td><input type='text' name='patronymic' value='${patronymic}'></td>
                <#else>
                    <td><input type='text' name='patronymic' value=''></td>
                </#if>
            </tr>
            <tr>
                <td>Surname:</td>
                <#if surname?has_content>
                    <td><input type='text' name='surname' value='${surname}'></td>
                <#else>
                    <td><input type='text' name='surname' value=''></td>
                </#if>
            </tr>
            <tr>
                <td>Institution:</td>
                <#if institution?has_content>
                    <td><input type='text' name='institution' value='${institution}'></td>
                <#else>
                    <td><input type='text' name='institution' value=''></td>
                </#if>
            </tr>
            <tr>
                <td>Additional info:</td>
                <#if info?has_content>
                    <td><input type='text' name='info' value='${info}'></td>
                <#else>
                    <td><input type='text' name='info' value=''></td>
                </#if>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <tr>
                <td> <button type="button" onclick="addTeacherInfo()" value="Submit"></button></td>
            </tr>
        </table>
    </form>
    <h2> Add language form</h2>
    <form id="x" action="/addLanguage/${id}">
<#--        <input type="hidden" name="id" value="null"><br>-->
        <p>Language: </p>
        <input type="text" name="language"/>
        <p>Level: </p>
        <@enumSelect "level" myEnum/><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="button" onclick="addLanguage()" value="Submit"></button>
    </form>

    <a href="/profile">Go to profile</a>
</#macro>
<#macro title>
    <title>Edit teacher profile page</title>
</#macro>
<@display_page />