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
        function addComment() {
            var form = $("#addCommentForm")[0];
            var data = new FormData(form);
            $.ajax({
                method: "POST",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                data: JSON.stringify(Object.fromEntries(data)),
                contentType: "application/json",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert("Successfully updated: " + String(a));
                    window.location.replace("/getProject/${id}")
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }
        getProject(${id});
        function getProject(id) {
            $.ajax({
                method: "GET",
                url: /projects/ + String(id),
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    if(b.title !== null) $("#title").text(b.title);
                    if(b.description !== null) $("#description").text(b.title);
                    if(b.links !== null){
                        var result = '';
                        b.links.forEach(element => {
                            result += "<a href=\"" + element + "\" class=\"card-text\" style=\"display: block\">" +
                                    element + "</a>";
                        });
                        $("#links").append(result);
                    }
                    if(b.projectComments != null){
                        var start = "    <div class=\"card-body text-dark\">\n" +
                            "                <a href=\"/profile/";
                        var middle1 = "\" class=\"card-title\">";
                        var middle2 = "</a>\n" +
                            "                <p class=\"card-text\">";
                        var end = "</p>\n" +
                            "            </div>\n" +
                            "\n" +
                            "            <div class=\"dropdown-divider\"></div>";
                        var result = "";
                        b.projectComments.forEach(element => {
                            var res = '';
                            if (element.teacher.name !== null) res += element.teacher.name;
                            if (element.teacher.patronymic !== null) res += ' ' + element.teacher.patronymic;
                            if (element.teacher.surname !== null) res += ' ' + element.teacher.name;
                            result += start + element.teacher.id + " " + middle1  + res + middle2 + element.comment + end;
                        });
                        $("#comments").append(result);
                    }


                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                    window.location.replace("/profile");
                }
            });
        }
        getViewerInfo();

        function getViewerInfo() {
            $.ajax({
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                method: "get",
                url: "/getMyProfileInfo",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    var result = '';
                    if (b.role === 'TEACHER' | b.role === 'ADMIN') {
                        result += "        <div class=\"navbar\" style=\"list-style: none; padding: 0; margin: 0\">\n" +
                            "            <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\" style=\"width: 100%; font-size: large\">\n" +
                            "                <a class=\"navbar-brand\" href=\"#\" style=\"font-size: x-large\">Curriculum vitae</a>\n" +
                            "                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\"\n" +
                            "                        aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                            "                    <span class=\"navbar-toggler-icon\"></span>\n" +
                            "                </button>\n" +
                            "                <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\" style=\"margin-left: 25px\">\n" +
                            "                    <ul class=\"navbar-nav\">\n" +
                            "\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/profile\">Профиль</a>\n" +
                            "                        </li>\n" +
                            "\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/editTeacherProfile/" + b.id + "\">Личный кабинет</a>\n" +
                            "                        </li>\n" +
                            "\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/search\">Поиск</a>\n" +
                            "                        </li>\n" +
                            "\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"#\">Вакансии</a>\n" +
                            "                        </li>\n" +
                            "                    </ul>\n" +
                            "                    <div class=\"form-inline\" style=\"margin-left: 62%; float: right\">\n" +
                            "                        <form method=\"get\" action=\"/logout\">\n" +
                            "                            <button class=\"btn btn-outline-info my-2 my-sm-0\" type=\"submit\" style=\"float: right\">Выйти</button>\n" +
                            "                        </form>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "            </nav>\n" +
                            "        </div>"
                        $("#navigation").append(result);
                    }
                    if (b.role === 'STUDENT') {
                        result += "        <div class=\"navbar\" style=\"list-style: none; padding: 0; margin: 0\">\n" +
                            "            <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\" style=\"width: 100%; font-size: large\">\n" +
                            "                <a class=\"navbar-brand\" href=\"#\" style=\"font-size: x-large\">Curriculum vitae</a>\n" +
                            "                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\"\n" +
                            "                        aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                            "                    <span class=\"navbar-toggler-icon\"></span>\n" +
                            "                </button>\n" +
                            "                <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\" style=\"margin-left: 25px\">\n" +
                            "                    <ul class=\"navbar-nav\">\n" +
                            "                        <li class=\"nav-item dropdown\">\n" +
                            "                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarStudentProfile\" data-toggle=\"dropdown\"\n" +
                            "                               aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                            "                                Профиль<span class=\"sr-only\">(current)</span>\n" +
                            "                            </a>\n" +
                            "                            <div class=\"dropdown-menu\" aria-labelledby=\"navbarStudentProfile\">\n" +
                            "                                <a class=\"dropdown-item\" href=\"/profile\">Общая информация</a>\n" +
                            "                                <a class=\"dropdown-item\" href=\"/studentAccount/projects\">Проекты</a>\n" +
                            "                                <a class=\"dropdown-item\" href=\"/works/";
                        result += b.id;
                        result += "\">Работы</a>\n" +
                            "                            </div>\n" +
                            "                        </li>\n" +
                            "                        <li class=\"nav-item dropdown\">\n" +
                            "                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarStudentAccount\" data-toggle=\"dropdown\"\n" +
                            "                               aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                            "                                Личный кабинет\n" +
                            "                            </a>\n" +
                            "                            <div class=\"dropdown-menu\" aria-labelledby=\"navbarStudentAccount\">\n" +
                            "                                <a class=\"dropdown-item\" href=\"/editStudentProfile/";
                        result += b.id;
                        result += "\">Изменить инфомацию</a>\n" +
                            "                                <a class=\"dropdown-item\" href=\"#\">Добавить проект</a>\n" +
                            "                            </div>\n" +
                            "                        </li>\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/search\">Поиск</a>\n" +
                            "                        </li>\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"#\">Вакансии</a>\n" +
                            "                        </li>\n" +
                            "                    </ul>\n" +
                            "                    <div class=\"form-inline\" style=\"margin-left: 62%; float: right\">\n" +
                            "                        <form method=\"get\" action=\"/logout\">\n" +
                            "                            <button class=\"btn btn-outline-info my-2 my-sm-0\" type=\"submit\" style=\"float: right\">Выйти</button>\n" +
                            "                        </form>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "            </nav>\n" +
                            "        </div>";
                        $("#navigation").append(result);
                    }
                    if (b.role === 'COMPANY') {
                        result += "    <div class=\"navbar\" style=\"list-style: none; padding: 0; margin: 0\">\n" +
                            "            <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\" style=\"width: 100%; font-size: large\">\n" +
                            "                <a class=\"navbar-brand\" href=\"#\" style=\"font-size: x-large\">Curriculum vitae</a>\n" +
                            "                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\"\n" +
                            "                        aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                            "                    <span class=\"navbar-toggler-icon\"></span>\n" +
                            "                </button>\n" +
                            "                <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\" style=\"margin-left: 25px\">\n" +
                            "                    <ul class=\"navbar-nav\">\n" +
                            "\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/profile\">Профиль</a>\n" +
                            "                        </li>\n" +
                            "\n" +
                            "                        <li class=\"nav-item dropdown\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarStudentAccount\" data-toggle=\"dropdown\"\n" +
                            "                               aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                            "                                Личный кабинет\n" +
                            "                            </a>\n" +
                            "                            <div class=\"dropdown-menu\" aria-labelledby=\"navbarStudentAccount\">\n" +
                            "                                <a class=\"dropdown-item\" href=\"/editCompanyProfile/";
                        result += b.id;
                        result += "\">Изменить инфомацию</a>\n" +
                            "                                <a class=\"dropdown-item\" href=\"#\">Добавить вакансию</a>\n" +
                            "                            </div>\n" +
                            "                        </li>\n" +
                            "\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/search\">Поиск</a>\n" +
                            "                        </li>\n" +
                            "\n" +
                            "                    </ul>\n" +
                            "                    <div class=\"form-inline\" style=\"margin-left: 70%; float: right\">\n" +
                            "                        <form method=\"get\" action=\"/logout\">\n" +
                            "                            <button class=\"btn btn-outline-info my-2 my-sm-0\" type=\"submit\" style=\"float: right\">Выйти</button>\n" +
                            "                        </form>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "            </nav>\n" +
                            "        </div>";
                        $("#navigation").append(result);
                    }
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    console.log(b);
                }
            });
        }
    </script>
    <div id="navigation">
    </div>
<div class="card-deck" style="margin: 2%">

    <div class="card border-dark mb-3"
         style="display: inline-block; max-width: 54rem; border-radius: 6px 0 0 6px">
        <div class="card-header" style="">Проект студента</div>
        <div class="card-body text-dark" style="overflow-y: scroll; height: 39em">
            <h5 class="card-title">Название</h5>
            <p class="card-text" id="title"></p>

            <h5 class="card-title">Описание</h5>
            <p class="card-text" id="description"></p>

            <h5 class="card-title" id="links">Ссылки</h5>
            <div id="links"></div>
        </div>
    </div>

    <div class="card border-dark mb-3"
         style="display: inline-block; width: 30rem; border-radius: 6px 0 0 6px">
        <div class="card-header">Комментарии</div>
        <div class="list group" style="overflow-y: scroll; height: 30em" id="comments" >

            <div class="dropdown-divider"></div>


        </div>

        <div style="border-top: 1px black solid; height: 6em">
            <form id="addCommentForm" method="post" action="/projects/${id}/newComment">
                <div class="input-group mb-3"
                     style="margin: 3em auto 0 auto; width: 90%; text-align: center">
                    <input type="text" class="form-control" placeholder="Комментарий" aria-label="Recipient's username"
                           aria-describedby="basic-addon2" name="comment">
                    <div class="input-group-append">
                        <button class="btn btn-outline-success" type="button" onclick="addComment()">Добавить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </#macro>
<#macro title>
    <title>Show students project</title>
</#macro>
<@display_page />