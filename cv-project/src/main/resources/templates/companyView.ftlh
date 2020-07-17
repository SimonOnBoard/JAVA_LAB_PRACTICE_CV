<#include "base.ftlh"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
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

    <div class="card-deck">
        <div class="card border-dark mb-3" style="display: inline-block; margin: 2%; max-width: 32rem">
            <div class="card-header">Личные данные</div>
            <div class="card-body text-dark" style="max-height: 24rem; overflow-y: scroll">
                <#if name?has_content>
                    <h5 class="card-title">Название</h5>
                    <p class="card-text"> ${name}</p>
                </#if>

                <div style="margin-top: 25px"></div>

                <#if address?has_content>
                    <h5 class="card-title">Адрес компании</h5>
                    <p class="card-text">${address}</p>
                </#if>

                <div style="margin-top: 25px"></div>
                <h5 class="card-title">Контакты</h5>
                <#if phone?has_content>
                    <p class="card-text"><strong>Телефон: </strong>${phone}</p>
                </#if>
                <#if mail?has_content>
                    <p class="card-text"><strong>Email: </strong> ${mail}</p>
                </#if>

                <div style="margin-top: 25px"></div>
                <#if description?has_content>
                    <h5 class="card-title">О себе</h5>
                    <p class="card-text">${description}</p>
                </#if>
            </div>
        </div>

        <div class="card border-dark mb-3" style="display: inline-block; margin: 2%; max-width: 48rem">
            <div class="card-header">Новости компании</div>
            <div class="card-body text-dark" style="height: 24rem; overflow-y: scroll">
                <div class="list-group">
                    <#if posts?has_content>
                        <#list posts as post>
                            <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${post.getName()}</h5>
                                </div>
                                <p class="mb-1">${post.getDescription()}</p>
                                <#if isOwner == true>
                                    <form id="${post.getId()}" action="/removePost/${post.getId()}" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <button type="button" onclick="removePost(${post.getId()})">Удалить пост</button>
                                    </form>
                                </#if>
                            </a>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    <#if isOwner == true>
        <a href="/editCompanyProfile/${id}">Редактировать профиль</a>
    </#if>
</#macro>
<#macro title>
<title>Login</title>
</#macro>а
<@display_page />