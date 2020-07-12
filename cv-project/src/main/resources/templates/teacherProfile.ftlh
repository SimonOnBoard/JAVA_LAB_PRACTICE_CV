<#include "base.ftl"/>
<#macro head>

</#macro>
<#macro content>
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
                <form action="/removeLanguage/${id}/${lang.getId()}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
                    <input type="submit" value="Удалить язык">
                </form>
            </#if>
            <br>
        </#list>
    </#if>
    <#if isOwner == true>
        <a href="/editTeacherProfile/${id}">Редактировать профиль</a>
    </#if>
</#macro>
<#macro title>
    <title>Login</title>
</#macro>
<@display_page />