<#include "base.ftl"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</#macro>
<#macro content>
    <script type="application/javascript">
        function addLanguage() {
            // Create an FormData object
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            var form = $("#x")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
            console.log(address);
            $.ajax({
                method: "POST",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
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
                    alert(err);
                }
            });
        }
    </script>
    <form name='f' action="/editTeacherProfile/${id}" method='POST'>
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
                <#то есelse>
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
                <td><input name="submit" type="submit" value="submit"/></td>
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
        <button type="button" onclick="addLanguage()" value="Submit"></button>
    </form>

    <a href="/profile">Go to profile</a>
</#macro>
<#macro title>
    <title>Edit teacher profile page</title>
</#macro>
<@display_page />