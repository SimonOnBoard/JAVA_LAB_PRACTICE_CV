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
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</#macro>
<#macro content>
    <script type="application/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        getTeachers();
        function getTeachers() {
            $.ajax({
                method: "GET",
                url: "/allCompanies",
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    var start = "<a href=\"#\" class=\"list-group-item list-group-item-action flex-column align-items-start\">\n" +
                        "                <div style=\"display: inline-block\">\n" +
                        "                    <h5 class=\"mb-1\">";
                    var middle1 = "   </h5>\n" +
                        "                </div><div style=\"display: inline-block; float: right\">\n" +
                        "                    <button type=\"button\" class=\"btn btn-outline-info\" onclick=\"goToProfile(";
                    var middle2 = ")\">Профиль</button>\n" +
                        "                    <button type=\"button\" class=\"btn btn-outline-warning\" onclick=\"editPage(";
                    var end = ")\">Редактировать</button>\n" +
                        "                </div>\n" +
                        "            </a>";
                    var result = "";
                    b.forEach(element => {
                        var res = "";
                        result += start + element.name + middle1 + element.id + middle2 + element.id + end;
                    });
                    $("#teachers").append(result);
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                    window.location.replace("/profile");
                }
            });
        }
        function goToProfile(id) {
            window.location.replace("/profile/" + String(id));
        }
        // function editPage(id) {
        //     window.location.replace("/editTeacherProfile/" + String(id));
        // }
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
                        <button class="btn btn-outline-info my-2 my-sm-0" type="submit" style="float: right">Выйти</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
    <div class="card"
         style="width: 96%; margin-right: auto; margin-left: auto; height: 46rem; border: 2px darkgray solid; border-top: 0;border-radius: 0 0 6px 6px">
        <div class="card-header" style="font-size: x-large; text-align: center">Компании</div>
        <div class="card-body" style="overflow-y: scroll">

            <div class="list-group" id="teachers">
            </div>
        </div>
    </div>
</#macro>
<#macro title>
    <title>Students list</title>
</#macro>
<@display_page />