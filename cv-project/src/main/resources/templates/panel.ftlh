<#include "base.ftlh"/>
<#macro head>

</#macro>
<#macro content>
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
                        <button class="btn btn-outline-info my-2 my-sm-0" type="submit" style="float: right">Выйти</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
    <a href="/addUser"> Add user page</a>
</#macro>
<#macro title>
    <title>Panel</title>
</#macro>
<@display_page />