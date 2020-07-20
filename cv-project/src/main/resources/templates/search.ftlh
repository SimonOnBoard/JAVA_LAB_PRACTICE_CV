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
    <body>
    <script type="application/javascript">
        var selectedTags = [];
        var selectedLanguages = [];
        var selectedEducations = [];
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
    <div class="card-deck" style="margin: 2%; vertical-align: top">
        <form id='f' action="/api/search" method="post">
            <div class="card border-dark mb-3"
                 style="display: inline-block; max-height: 34em; overflow-y: scroll; border-radius: 6px 0 0 6px">
                <div class="card-header" style="font-size: x-large; text-align: center">Поиск</div>
                <div id="content">
                    <div class="card-body text-dark">
                        <h5 class="card-title" style="text-align: center">Выберите необходимые компетенции</h5>

                        <div class="input-group" style="width: 20rem; margin-left: auto; margin-right: auto;">
                            <select class="custom-select" name="comp" id="tagSelect">
                                <#list tags! as tag>
                                    <option id="${tag.name!}" value="${tag.name!}">${tag.name!}</option>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="button"
                                        onclick="addNewElement('tagSelect', 'tags')">Добавить
                                </button>
                            </div>
                        </div>

                        <div id="tags" style="display:flex; flex-wrap: wrap; margin-top: 15px">

                        </div>
                    </div>

                    <div class="dropdown-divider"></div>

                    <div class="card-body text-dark" style="">
                        <h5 class="card-title" style="text-align: center">Выберите необходимые языки</h5>

                        <div class="input-group" style="width: 20rem; margin-left: auto; margin-right: auto;">
                            <select class="custom-select" name="language" id="languageSelect">
                                <#list languages! as language>
                                    <option id="${language.language!}"
                                            value="${language.language!} ${language.level}">${language.language!} ${language.level}</option>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="button"
                                        onclick="addNewElement('languageSelect', 'languages')">Добавить
                                </button>
                            </div>
                        </div>

                        <div id="languages" style="display:flex; flex-wrap: wrap; margin-top: 15px">

                        </div>
                    </div>

                    <div class="dropdown-divider"></div>

                    <div class="card-body text-dark" style="">
                        <h5 class="card-title" style="text-align: center">Выберите необходимые уровни образования</h5>

                        <div class="input-group" style="width: 20rem; margin-left: auto; margin-right: auto;">
                            <select class="custom-select" name="education" id="educationSelect">
                                <#list educations! as education>
                                    <option id="${education!}" value="${education!}">${education!}</option>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="button"
                                        onclick="addNewElement('educationSelect', 'educations')">Добавить
                                </button>
                            </div>
                        </div>


                        <div id="educations" style="display:flex; flex-wrap: wrap; margin-top: 15px">

                        </div>

                    </div>

                    <div style="margin: 0 auto 1rem auto; text-align: center">
                        <button type="button" onclick="searchByFilters()" class="btn btn-success">Поиск</button>
                    </div>
                </div>
            </div>
        </form>


        <div id="students" class="list-group"
             style="display: inline-block; margin-left: 2%; height: 44rem; width: 48rem; border: 2px grey solid; border-radius: 6px 0 0 6px; overflow-y: scroll">
        </div>
    </div>

    </body>

    <script>
        function process(obj) {
            if (obj === null)
                return "";
            else
                return obj;
        }

        function searchByFilters() {
            $('#students').empty();
            var form = $("#f")[0];
            var data = new FormData(form);
            data.set("comp", selectedTags);
            data.set("language", selectedLanguages);
            data.set("education", selectedEducations);
            var address = "\"" + form.action + "\"";
            $.ajax({
                method: "POST",
                url: form.action,
                data: data,
                processData: false,
                contentType: false,
                success: function (response) {
                    var a = JSON.stringify(response);
                    var b = JSON.parse(a);
                    $.each(b, function (idx, student) {
                        var tags = '';
                        $.each(student['student']['competences'], function (idx, competence) {
                            tags = tags + '<span class="badge badge-info" style="margin-left: 5px; margin-top: 5px; font-size: medium">' + process(competence['tag']['name']) +
                                '</span>';
                        })
                        $('#students').first().append('<a href="/profile/' + student['student']['id'] + '" class="list-group-item list-group-item-action flex-column align-items-start">\n' +
                            '            <div class="d-flex w-100 justify-content-between">\n' +
                            '                <h5 class="mb-1">' +
                                            process(student['student']['firstName']) +
                                            ' ' + process(student['student']['lastName']) +
                                            ' ' + process(student['student']['patronymic']) + '</h5>\n' +
                            '            </div>\n' +
                            '            <div class="mb-1" style="display: flex; flex-wrap: wrap">' +
                            tags +
                            '            </div>' +
                            '            <u style="font-size: large">' + process(student['student']['education']) + '</u>\n' +
                            '        </a>'
                        )
                        ;
                    });
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }

        function addNewElement(selectId, divId) {
            var select = document.getElementById(selectId);
            var div = document.getElementById(divId);
            var name;
            if (divId.toString() === "tags") {
                name = 'tag';
                selectedTags.push(select.value);
            } else if (divId.toString() === "languages") {
                name = 'language';
                selectedLanguages.push(select.value);
            } else {
                name = 'education';
                selectedEducations.push(select.value);
            }
            if (select.value !== '') {
                div.innerHTML += '<button type="button" class="btn btn-info" name="'
                    + name
                    + '" style="margin: 5px" id="'
                    + select.value
                    + '" onclick="deleteElementFromDiv('
                    + '\'' + selectId + '\'' + ', \'' + select.value + '\', \'' + divId + '\''
                    + ')">'
                    + select.value
                    + '</button>';

                select.removeChild(document.getElementById(select.value));
            }
        }

        function deleteElementFromDiv(selectId, btnId, divId) {
            var select = document.getElementById(selectId);
            var btn = document.getElementById(btnId);
            selectedTags.splice(selectedTags.indexOf(btn.id), selectedTags.indexOf(btn.id) + 1);
            selectedLanguages.splice(selectedLanguages.indexOf(btn.id), selectedLanguages.indexOf(btn.id) + 1);
            selectedEducations.splice(selectedEducations.indexOf(btn.id), selectedEducations.indexOf(btn.id) + 1);
            select.innerHTML += '<option id="'
                + btnId
                + '" value="'
                + btnId
                + '">'
                + btnId
                + '</option>';
            document.getElementById(divId).removeChild(btn);
        }
    </script>
    </body>
</#macro>
<#macro title>
    <title>Поиск</title>
</#macro>
<@display_page />