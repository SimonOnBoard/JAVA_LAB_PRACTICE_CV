<#include "base.ftlh"/>
<#macro head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
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
    <div class="card-deck">
        <div class="card border-dark mb-3" style="display: inline-block; margin: 2%; max-width: 48rem">
            <div class="card-header">Личные данные</div>
            <div class="card-body text-dark" style="max-height: 22rem; overflow-y: scroll">
                <#if name?has_content>
                    <h5 class="card-title">Имя</h5>
                    <p class="card-text">${name}</p>
                </#if>
                <#if patronymic?has_content>
                    <h5 class="card-title">Отчество</h5>
                    <p class="card-text"> ${patronymic}</p>
                </#if>
                <#if surname?has_content>
                    <h5 class="card-title">Фамилия</h5>
                    <p class="card-text">${surname}</p>
                </#if>
                <div style="margin-top: 25px"></div>

                <#if institution?has_content>
                    <h5 class="card-title">Место преподавания</h5>
                    <p class="card-text">${institution}</p>
                </#if>

                <div style="margin-top: 25px"></div>

                <#if mail?has_content>
                    <h5 class="card-title">Контакты</h5>
                    <p class="card-text">Email: ${mail}</p>
                </#if>

                <div style="margin-top: 25px"></div>
                <#if info?has_content>
                    <h5 class="card-title">О себе</h5>
                    <p class="card-text">${info}</p>
                </#if>
            </div>
        </div>

        <div class="card border-dark mb-3" style="display: inline-block; max-width: 28rem; margin: 2%">
            <div class="card-header">Знание языков</div>
            <div class="card-body text-dark" style="height: 22rem; overflow-y: scroll">
                <#if languages?has_content>
                    <#list languages as lang>
                        <h5 class="card-title">${lang.getLanguage()}</h5>
                        <p class="card-text">${lang.getLevel().name()}</p>
                        <#if isOwner == true>
                            <form id="${lang.getId()}" action="/removeLanguage/${lang.getId()}" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
                                <button type="button" onclick="removeLanguage(${lang.getId()})">Удачить язык</button>
                            </form>
                        </#if>
                        <br>
                    </#list>
                    <br>
                </#if>
            </div>
        </div>
    </div>
    <#if isOwner == true>
        <a href="/editTeacherProfile/${id}">Редактировать профиль</a>
    </#if>
</#macro>
<#macro title>
    <title>Профиль учителя</title>
</#macro>
<@display_page />