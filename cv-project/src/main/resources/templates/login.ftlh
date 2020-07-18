<#include "base.ftlh"/>
<#macro head>
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
    <#if info?has_content>
        <div class="reg-container">
            <p>info: ${info}</p>
        </div>
    <#else>
    </#if>
    <form style="position: fixed; top: 50%; left: 50%; width: 36rem; margin: -150px 0 0 -240px;
 background: #f0f0f0; text-align: center; border: 2px darkgray solid; padding: 2rem; border-radius: 6px;" name='f' action="/login" method='POST'>
        <div class="form-group">
            <label for="exampleInput1">Имя пользователя</label>
            <input type="text" class="form-control"  name='username' aria-describedby="emailHelp"
                   placeholder="Введите имя пользователя">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Пароль</label>
            <input type="password" class="form-control"  name='password' placeholder="Введите пароль">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
        <button type="submit" class="btn btn-primary" style="margin-top: 1rem">Войти</button>
    </form>
</#macro>
<#macro title>
    <title>Login</title>
</#macro>
<@display_page />