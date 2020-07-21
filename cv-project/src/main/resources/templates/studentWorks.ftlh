<#include "base.ftlh"/>
<#macro head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
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
    <body onload="loadWorks()">
    <script type="application/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function loadWorks() {
            var pathname = window.location.pathname
            $.ajax({
                method: "GET",
                url: '/load' + pathname,
                dataType: "json",
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                success: function (response) {
                    console.log(response)
                    for (let i = 0; i < response.length; i++) {
                        var tags = '';
                        var links = '';
                        for (let j = response[i]['work']['links'].length - 1; j >= 0; j--) {
                            links = links + '<a href="' + response[i]['work']['links'][j] + '">' + response[i]['work']['links'][j] + '</a><br>\n';
                        }
                        for (let j = response[i]['work']['tags'].length - 1; j >= 0; j--) {
                            tags = tags + '<button type="button" class="btn btn-info" style="margin: 5px" disabled>' + response[i]['work']['tags'][j]['name'] + '</button>';
                        }
                        $('#works').append('<div class="list-group">\n' +
                            '                                    <div class="list-group-item list-group-item-action flex-column align-items-start"\n' +
                            '                                         style="margin-bottom: 20px; border: 1px grey solid">\n' +
                            '                                        <div class="d-flex w-100 justify-content-between">\n' +
                            '                                            <h5 class="mb-1">' + response[i]['work']['title'] + '</h5>\n' +
                            '                                        </div>\n' +
                            '                                        <p class="mb-1">' + response[i]['work']['description'] + '</p>\n' +
                            links +
                            '                                        <div style="display: flex; flex-wrap: wrap">\n' +
                            tags +
                            '                                        </div>\n' +
                            '                                       <a href="/profile/' + response[i]['work']['teacher']['id'] + '">' +
                            '                                       <u style="font-size: large">' + response[i]['work']['teacher']['surname'] + ' ' +
                            response[i]['work']['teacher']['name'] + ' ' +
                            response[i]['work']['teacher']['patronymic'] + '</u></a>\n' +
                            '                                    </div>\n' +
                            '                                </div>');
                    }
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    console.log(JSON.stringify(b))
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
    <div id="navigation"></div>
    <div class="card-deck">
        <div class="card border-dark mb-3"
             style="display: inline-block; margin-left: 5%; margin-top: 2%; max-width: 60rem">
            <div class="card-header">Выполненные проекты</div>
            <div id="works" class="card-body text-dark" style="height: 45rem; overflow-y: scroll">

            </div>
        </div>
    </div>
    </body>
</#macro>
<#macro title>
    <title>Работы студента</title>
</#macro>
<@display_page />