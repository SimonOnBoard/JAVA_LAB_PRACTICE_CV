<#include "base.ftl"/>
<#macro head>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
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
    <#if languages?has_content>
        <#list languages as lang>
            <p>${lang.getLanguage()}</p>
            <p>${lang.getLevel().name()}</p>
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