<#include "base.ftl"/>
<#macro head>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</#macro>
<#macro content>


	<form action="/search" method="post" enctype="application/x-www-form-urlencoded">
        <#list tags! as tag>
            <input type="checkbox" name="comp" id='${tag.name!}' value='${tag.name!}'> <label for='${tag.name!}'>${tag.name!}</label><br/>
		</#list>
        <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
		<input type="submit" name="ok">
	</form>


	${selected!}
    <#list students! as student>
        <#if student?has_content>
            <a href="/students/${student.id!}">${student.firstName}</a>
            <br>
        <#else>
        </#if>
    </#list>



</#macro>
<#macro title>
    <title>Search</title>
</#macro>
<@display_page />
