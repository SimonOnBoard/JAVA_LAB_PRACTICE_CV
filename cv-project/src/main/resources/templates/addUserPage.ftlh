<#include "base.ftlh"/>
<#macro head>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
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
    <#if info?has_content>
        <div class="reg-container">
            <p>info: ${info}</p>
        </div>
    <#else>
    </#if>
    <script type="application/javascript">
        function addUser() {
            // Create an FormData object
            var form = $("#f")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
            $.ajax({
                method: "POST",
                url: form.action,
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert('Successfully created: ' + String(a));
                    $('#f')[0].reset();
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(String(err.responseText) + " " + "Скорее всего такой логин уже сщуествует");
                }
            });
        }
    </script>
    <div class="navbar" style="list-style: none; padding: 0; margin: 0">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="width: 100%; font-size: large">
            <a class="navbar-brand" href="#" style="font-size: x-large">Curriculum vitae</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown" style="margin-left: 25px">
                <ul class="navbar-nav">

                    <li class="nav-item" style="margin-left: 10px">
                        <a class="nav-link" href="/panel">Панель администратора</a>
                    </li>

                    <li class="nav-item" style="margin-left: 10px">
                        <a class="nav-link" href="/getStudentsPage">Студенты</a>
                    </li>

                    <li class="nav-item" style="margin-left: 10px">
                        <a class="nav-link" href="/getTeachersPage">Преподаватели</a>
                    </li>

                    <li class="nav-item" style="margin-left: 10px">
                        <a class="nav-link" href="/getCompaniesPage">Компании</a>
                    </li>

                </ul>
                <div class="form-inline" style="margin-left: 53%; float: right">
                    <form method="get" action="/logout">
                        <button class="btn btn-outline-info my-2 my-sm-0" type="submit" style="float: right">Выйти
                        </button>
                    </form>
                </div>
            </div>
        </nav>
    </div>

<#--    <form id='f' action="/addUser" method='POST'>-->
<#--        <table>-->
<#--            <tr>-->
<#--                <td>User:</td>-->
<#--                <td><input type='text' name='username' value='' required></td>-->
<#--            </tr>-->
<#--            <tr>-->
<#--                <td>Password:</td>-->
<#--                <td><input type='password' name='password' required/></td>-->
<#--            </tr>-->
<#--            <tr>-->
<#--                <td>Email:</td>-->
<#--                <td><input type='email' name='email' required/></td>-->
<#--            </tr>-->
<#--            <tr>-->
<#--                <td>Role:</td>-->
<#--                <td>-->
<#--                    <p><select type='text' name="role" required>-->
<#--                            <option value="ADMIN">ADMIN</option>-->
<#--                            <option value="TEACHER">TEACHER</option>-->
<#--                            <option value="COMPANY">COMPANY</option>-->
<#--                            <option value="STUDENT">STUDENT</option>-->
<#--                        </select></p>-->
<#--                </td>-->
<#--            </tr>-->
<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
<#--            <tr>-->
<#--                <td>-->
<#--                    <button type="button" onclick="addUser()" value="submit"/>-->
<#--                </td>-->
<#--            </tr>-->
<#--        </table>-->
<#--    </form>-->
    <div class="card bg-light mb-3"
         style="width: 60%; margin-left: auto; margin-right: auto; margin-top: 1%; text-align: center">
        <div class="card-header">Новый пользователь</div>
        <div class="card-body">
            <form id='f' action="/addUser" method='POST'>
                <h5 class="card-title">Имя пользователя</h5>
                <p class="card-text">
                    <label>
                        <input class="form-control" type="text" name="username" size="50" required/>
                    </label>
                </p>

                <div class="dropdown-divider"></div>

                <h5 class="card-title">Email</h5>
                <p class="card-text"><label>
                        <input class="form-control" type="email" name="email" size="50"/>
                    </label></p>

                <div class="dropdown-divider"></div>

                <h5 class="card-title">Пароль</h5>
                <p class="card-text">
                    <label>
                        <input class="form-control" type="password" name="password" size="50"/>
                    </label>
                </p>

                <div class="dropdown-divider"></div>

                <h5 class="card-title">Роль</h5>
                <p class="card-text">
                    <select type='text' name="role" required>
                        <option value="ADMIN">ADMIN</option>
                        <option value="TEACHER">TEACHER</option>
                        <option value="COMPANY">COMPANY</option>
                        <option value="STUDENT">STUDENT</option>
                    </select>
                </p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="button" class="btn btn-primary" onclick="addUser()" value="">Добавить</button>
            </form>

        </div>
    </div>

</#macro>
<#macro title>
    <title>Add user page</title>
</#macro>
<@display_page />