<#include "base.ftl"/>
<#macro head>

</#macro>
<#macro content>
    <h1>Company profile</h1>
    <#if name?has_content>
        <p> Name: ${name}</p>
    </#if>
    <#if address?has_content>
        <p> Address: ${address}</p>
    </#if>
    <#if description?has_content>
        <p> Info: ${description}</p>
    </#if>
    <#if phone?has_content>
        <p> Phone: ${phone}</p>
    </#if>
    <#if mail?has_content>
        <p> Email: ${mail}</p>
    </#if>
    <#if isOwner == true>
        <a href="/editCompanyProfile/${id}">Редактировать профиль</a>
    </#if>
</#macro>
<#macro title>
    <title>Login</title>
</#macro>а
<@display_page />