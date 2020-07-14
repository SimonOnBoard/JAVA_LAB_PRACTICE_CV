<#include "base.ftl"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</#macro>
<#macro content>
    <script type="application/javascript">
        function removePost(id) {
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
    <#if posts?has_content>
        <#list posts as post>
            <br>
            <p>${post.getName()}</p>
            <p>${post.getDescription()}</p>
            <#if isOwner == true>
                <form id="${post.getId()}" action="/removePost/${post.getId()}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="button" onclick="removePost(${post.getId()})">Удачить пост</button>
                </form>
            </#if>
            <br>
        </#list>
        <br>
    </#if>
    <#if isOwner == true>
        <a href="/editCompanyProfile/${id}">Редактировать профиль</a>
    </#if>
</#macro>
<#macro title>
<title>Login</title>
</#macro>а
<@display_page />