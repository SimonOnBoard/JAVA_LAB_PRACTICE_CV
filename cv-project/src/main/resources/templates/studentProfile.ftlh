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
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</#macro>
<#macro content>
    <style>

        .profile {
            width: 90%;
            margin-top: 1%;
            margin-left: auto;
            margin-right: auto;
        }

        .scroll-teachers {
            overflow-y: scroll;
            max-height: 70px;
        }

        .btn {
            margin: 8px;
            border-radius: 6px !important;
            border: 1px grey solid;
        }
    </style>

    <script type="application/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
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
                            "                            <a class=\"nav-link\" href=\"/vacancies\">Вакансии</a>\n" +
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
                            "                                <a class=\"dropdown-item\" href=\"/studentAccount/projects\">Добавить проект</a>\n" +
                            "                            </div>\n" +
                            "                        </li>\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/search\">Поиск</a>\n" +
                            "                        </li>\n" +
                            "                        <li class=\"nav-item\" style=\"margin-left: 10px\">\n" +
                            "                            <a class=\"nav-link\" href=\"/vacancies\">Вакансии</a>\n" +
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
                            "                                <a class=\"dropdown-item\" href=\"/vacancies/newVacancy\">Добавить вакансию</a>\n" +
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
        getStudent(${id});
        function getStudent(id) {
            $.ajax({
                method: "GET",
                url: /students/ + String(id),
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    var res = '';
                    if (b.firstName !== null) res += b.firstName;
                    if (b.patronymic !== null) res += ' ' + b.patronymic;
                    if (b.lastName !== null) res += ' ' + b.lastName;
                    if (res !== '') {
                        $("#fio").text(res);
                        $("#fio_label").attr("hidden", false);
                    }
                    if (b.age !== null) {
                        $("#age").attr("hidden", false);
                        $("#age").text(b.age + ' лет');
                        $("#age_label").attr("hidden", false);
                    }
                    if (b.email !== null) {
                        $("#mail").attr("hidden", false);
                        $("#contacts-label").attr("hidden", false);
                        $("#mail").text(b.email);
                    }
                    if (b.phoneNumber !== null) {
                        $("#phone").attr("hidden", false);
                        $("#contacts-label").attr("hidden", false);
                        $("#phone").text(b.phoneNumber);
                    }
                    if (b.aboutMe !== null) {
                        $("#info-label").attr("hidden", false);
                        $("#info").attr("hidden", false);
                        $("#info").text(b.aboutMe);
                    }
                    if (b.education !== null) {
                        $("#education").attr("hidden", false);
                        let educationEnum = b.education;
                        let edu;
                        if (educationEnum === 'UNFINISHED_BACHELOR') {
                            edu = 'Бакалавр неоконченное';
                        } else if (educationEnum === 'BACHELOR') {
                            edu = 'Бакалавр';
                        } else if (educationEnum === 'UNFINISHED_MASTER') {
                            edu = 'Магистр неоконченное';
                        } else if (educationEnum === 'MASTER') {
                            edu = 'Магистр';
                        } else if (educationEnum === 'UNFINISHED_GRADUATE') {
                            edu = 'Аспирант неоконченное';
                        } else if (educationEnum === 'GRADUATE') {
                            edu = 'Аспирант';
                        }
                        $("#education").text(edu);
                    }
                    if (b.languages != null) {
                        b.languages.forEach(element => {
                            $("#languages").append("<h5 class=\"card-title\">" + element.language + "</h5>");
                            $("#languages").append(" <p class=\"card-text\">>" + element.level + "</p>");
                        });
                    }
                    if (b.certificates != null) {
                        b.certificates.forEach(element => {
                            $("#certificates").append("<h5 class=\"card-title\">" + element.description + "</h5>");
                            $("#certificates").append(" <p class=\"card-text\">>" + element.yearOfReceipt + "</p>");
                        });
                    }
                    if (b.competences != null) {
                        b.competences.forEach(element => {
                            if (element.confirmed === false) {
                                var last;
                                <#if isTeacher == true>
                                last = "<button type=\"button\" class=\"btn btn-info\" onclick=\"confirmCompetence(" + element.id +
                                    ")\">Подтвердить</button>\n" + "                        </div>\n</div>";
                                <#else>
                                last = "                       </div>\n</div>";
                                </#if>
                                $("#competences").append("  <div class=\"element-competence\">\n" +
                                    "                            <button type=\"button\" class=\"btn btn-secondary dropdown-toggle\" data-toggle=\"dropdown\"\n" +
                                    "                                    aria-haspopup=\"true\" aria-expanded=\"false\">" +
                                    element.tag.name + "</button>\n" +
                                    "                            <div class=\"dropdown-menu\">\n" +
                                    "                                <strong class=\"dropdown-item\">Не подтверждена</strong>" + last);
                            } else {
                                var result = '                        <div class="element-competence">\n' +
                                    '                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"\n' +
                                    '                                    aria-haspopup="true" aria-expanded="false">' + element.tag.name;
                                result += '</button>\n' +
                                    '                            <div class="dropdown-menu">\n' +
                                    '                                <strong class="dropdown-item">Подтверждена</strong>\n' +
                                    '                                <div class="dropdown-divider"></div>\n' +
                                    '                                <div class="scroll-teachers">';
                                console.log(result);
                                element.confirmedTeachers.forEach(element => {
                                    var res = '';
                                    if (element.name !== null) res += element.name;
                                    if (element.surname !== null) res += ' ' + element.surname;
                                    if (res === '') {
                                        res = 'Имя не указано'
                                    }
                                    result += "<a class=\"dropdown-item\" href=\"" + "/profile/" + element.id + "\">" + res + "</a>";
                                });
                                var x = "</div>\n" +
                                    "                            </div>\n" +
                                    "                        </div>\n";
                                <#if isTeacher == true>
                                result += "<button type=\"button\" class=\"btn btn-info\" onclick=\"confirmCompetence(" + element.id +
                                    ")\">Подтвердить</button>\n" + x;
                                <#else>
                                result += x;
                                </#if>
                                console.log(result);
                                $("#competences").append(result);
                            }
                        });
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
        function confirmCompetence(competenceId) {
            var url = "/${id}/confirm/" + String(competenceId);
            $.ajax({
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                method: "POST",
                url: url,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    window.location.replace("/profile/${id}");
                }
            });
        }
    </script>
    <div id="navigation"></div>

    <div><!--TODO Это нужно добавить-->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item active">Профиль</li>
                <li class="breadcrumb-item"><a href="/students/${id}/projects">Проекты</a></li>
                <li class="breadcrumb-item"><a href="/works/${id}">Работы</a></li>
            </ol>
        </nav>
    </div>
    <div class="profile">
        <div class="first-floor">
            <div class="card-deck">
                <div class="card border-dark mb-3">
                    <div class="card-header">Личные данные</div>
                    <div class="card-body text-dark" style="max-height: 30rem; overflow-y: scroll">
                        <h5 class="card-title" hidden="true" id="fio_label">ФИО</h5>
                        <p class="card-text" id="fio"></p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title" hidden="true" id="age_label">Возраст</h5>
                        <p class="card-text" id="age" hidden="true">20 лет</p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title" hidden="true" id="contacts-label">Контакты</h5>
                        <p class="card-text" hidden="true" id="phone"></p>
                        <p class="card-text" hidden="true" id="mail"></p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title" hidden="true" id="info-label">О себе</h5>
                        <p class="card-text" hidden="true" id="info"></p>
                    </div>
                </div>

                <div class="card border-dark mb-3" style="max-width: 28rem;">
                    <div class="card-header">Образование</div>
                    <div class="card-body text-dark" style="max-height: 30rem; overflow-y: scroll">
                        <#--                        <h5 class="card-title">Программная инженерия</h5>-->
                        <p class="card-text" hidden="true" id="education"></p>
                    </div>
                </div>

                <div class="card border-dark mb-3" style="max-width: 28rem;">
                    <div class="card-header">Знание языков</div>
                    <div class="card-body text-dark" style="max-height: 30rem; overflow-y: scroll" id="languages">
                    </div>
                </div>
            </div>
        </div>

        <div class="second-floor">

            <div class="card-deck">
                <div class="card border-dark mb-3">
                    <div class="card-header">Компетенции</div>
                    <div class="card-body text-dark" style="max-height: 13rem; overflow-y: scroll">

                        <div class="btn-group" style="display: flex; flex-wrap:wrap" id="competences">

                        </div>

                    </div>
                </div>

                <div class="card border-dark mb-3" style="">
                    <div class="card-header">Сертификаты</div>
                    <div class="card-body text-dark" style="max-height: 13rem; overflow-y: scroll" id="certificates">

                    </div>
                </div>
            </div>
        </div>

    </div>
</#macro>
<#macro title>
    <title>Профиль студента</title>
</#macro>
<@display_page />