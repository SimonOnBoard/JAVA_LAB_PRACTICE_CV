<#macro content></#macro>
<#macro title></#macro>
<#macro head></#macro>
<#macro enumSelect selectName enumValues>
    <select name="${selectName}">
        <#list enumValues as enum>
            <option value="${enum}">${enum.description}</option>
        </#list>
    </select>
</#macro>
<#macro display_page>
    <!doctype html>
    <html>
    <head>
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <@title />
        <@head />

    </head>

    <body>
    <@content />
    </body>
    </html>
</#macro>