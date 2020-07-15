<#include "base.ftlh"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</#macro>
<#macro content>
    <script type="application/javascript">
        function removeLanguage(id) {
            // Create an FormData object
            var form = $('#' + id)[0];
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
                    window.location.replace("/profile");
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
    <h1>Teacher's profile</h1>
    <#if name?has_content>
        <p> Name: ${name}</p>
    </#if>
    <#if patronymic?has_content>
        <p> Patronymic: ${patronymic}</p>
    </#if>
    <#if surname?has_content>
        <p> Surname: ${surname}</p>
    </#if>
    <#if institution?has_content>
        <p> Organization: ${institution}</p>
    </#if>
    <#if info?has_content>
        <p> additional info: ${info}</p>
    </#if>
    <#if languages?has_content>
        <#list languages as lang>
            <br>
            <p>${lang.getLanguage()}</p>
            <p>${lang.getLevel().name()}</p>
            <#if isOwner == true>
                <form id="${lang.getId()}" action="/removeLanguage/${id}/${lang.getId()}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
                    <button type="button" onclick="removeLanguage(${lang.getId()})">Удачить язык</button>
                </form>
            </#if>
            <br>
        </#list>
        <br>
    </#if>
    <#if isOwner == true>
        <a href="/editTeacherProfile/${id}">Редактировать профиль</a>
    </#if>
</#macro>
<#macro title>
    <title>Login</title>
</#macro>
<@display_page />