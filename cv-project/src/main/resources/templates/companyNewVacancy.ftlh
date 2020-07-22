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
    <style>
        .btn-info {
            margin: 5px;
        }
    </style>
    <body>
    <script type="application/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var selectedTags = [];
        getViewerInfo();

        function deleteVacancy(id) {
            var form = $("#deleteVacancy" + id)[0];
            var data = new FormData(form);
            $.ajax({
                method: "DELETE",
                url: form.action,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert("Successfully deleted: " + String(a));
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }

        function addVacancy() {
            // Create an FormData object
            var form = $("#addVacancy")[0];
            var data = new FormData(form);
            data.set("tags", selectedTags);
            console.log(data);
            console.log(selectedTags);
            $.ajax({
                method: "POST",
                url: form.action,
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert("Successfully added: " + String(a));
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }

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
    </script>
    <div id="navigation">
    </div>
    <div class="card"
         style="display: inline-block; width: 50%; margin-left: 2%; margin-top: 1%; border: 2px darkgray solid; border-radius: 6px 0 6px 6px">
        <div class="card-header" style="font-size: x-large; text-align: center">
            Вакансии
        </div>
        <div class="card-body" style="overflow-y: scroll; max-height: 41rem; padding-bottom: 5px">

            <div class="list-group">
                <#list vacancies as vacancy>
                    <div class="list-group-item list-group-item-action flex-column align-items-start">
                        <a href="/vacancies/${vacancy.id}" class="list-group-item-action flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">${vacancy.title}</h5>
                                <#if !vacancy.salaryMin?has_content && vacancy.salaryMax?has_content>
                                    <small class="text-muted">до ${vacancy.salaryMax} рублей</small>
                                <#elseif vacancy.salaryMin?has_content && !vacancy.salaryMax?has_content>
                                    <small class="text-muted">от ${vacancy.salaryMin} рублей</small>
                                <#elseif vacancy.salaryMin?has_content && vacancy.salaryMax?has_content>
                                    <small class="text-muted">от ${vacancy.salaryMin} до ${vacancy.salaryMax}
                                        рублей</small>
                                <#else>
                                    <small class="text-muted">з/п не указана</small>
                                </#if>
                            </div>
                            <p class="mb-1">${vacancy.description}</p>
                            <div class="mb-1" style="display: flex; flex-wrap: wrap">
                                <#list vacancy.tags as tag>
                                    <span class="badge badge-info"
                                          style="margin-left: 5px; margin-top: 5px; font-size: medium">${tag.name}</span>
                                </#list>
                            </div>
                        </a>
                        <div style="float: right">
                            <form method="post" id="deleteVacancy${vacancy.id}" action="/vacancies/deleteVacancy/${vacancy.id}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="button" onclick="deleteVacancy(${vacancy.id})" class="btn btn-outline-danger btn-sm">
                                    Удалить вакансию
                                </button>
                            </form>
                        </div>
                    </div>
                </#list>
            </div>

        </div>
    </div>


    <div class="card-desc"
         style="width: 35%; display: inline-block; margin-left: 2%; margin-top: 1%; vertical-align: top">
        <div class="card border-dark mb-3" style="width: 43rem; margin: 2%">
            <div class="card-header">Добавить вакансию</div>
            <div class="card-body text-dark" style="height: 35rem; overflow-y: scroll">
                <form id='addVacancy' action="/vacancies/newVacancy" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <h5 class="card-title">Должность</h5>
                    <p class="card-text">
                        <label>
                            <input class="form-control" type="text" name="title" size="47" required/>
                        </label>
                    </p>

                    <h5 class="card-title">Описание</h5>
                    <p class="card-text">
                        <label>
                <textarea class="form-control" name="description" id="exampleFormControlTextarea1" rows="3" cols="50"
                          style="resize: none" required></textarea>
                        </label>
                    </p>

                    <div style="margin-bottom: 25px">
                        <h5 class="card-title">Зарплата</h5>
                        <div style="display: inline-block; margin-right: 10px">
                            <p class="card-text" style="display: inline">От</p>
                            <label class="card-text">
                                <input class="form-control" type="text" name="salaryMin"/>
                            </label>
                        </div>

                        <div style="display: inline-block;">
                            <p class="card-text" style="display: inline">до</p>
                            <label class="card-text">
                                <input class="form-control" type="text" name="salaryMax"/>
                            </label>
                        </div>
                    </div>

                    <div>
                        <div style="display: inline-block; width: 30%; vertical-align: top">
                            <h6 class="card-title">Телефон</h6>
                            <label class="card-text" id="phones">
                                <input class="form-control" type="text" name="phoneNumbers" required/>
                            </label>
                            <button class="btn btn-secondary" onclick="addInput('phones', 'phoneNumbers')">+</button>
                        </div>

                        <div style="display: inline-block; border-left: 1px darkgray solid; margin: 10px"></div>

                        <div style="display: inline-block; width: 30%; vertical-align: top">
                            <h6 class="card-title">Email</h6>
                            <label class="card-text" id="emails">
                                <input class="form-control" type="text" name="emails" required/>
                            </label>
                            <button class="btn btn-secondary" onclick="addInput('emails', 'emails')">+</button>
                        </div>

                        <div style="display: inline-block; border-left: 1px darkgray solid; margin: 10px"></div>

                        <div style="display: inline-block; width: 30%; vertical-align: top">
                            <h6 class="card-title">Адрес</h6>
                            <label class="card-text" id="addresses">
                                <input class="form-control" type="text" name="addresses" required/>
                            </label>
                            <button class="btn btn-secondary" onclick="addInput('addresses', 'addresses')">+</button>
                        </div>
                    </div>

                    <div style="margin-top: 25px"></div>

                    <div class="input-group">
                        <select class="custom-select" id="tagsSelect">
                            <#list tags as tag>
                                <option id="${tag.name}" value="${tag.name}">${tag.name}</option>
                            </#list>
                        </select>
                        <div class="input-group-append">
                            <button type="button" class="btn btn-outline-primary"
                                    onclick="addNewElement('tagsSelect', 'tags')">Добавить
                            </button>
                        </div>
                    </div>

                    <div class="tags" id="tags" style="display: flex; flex-wrap:wrap; margin-top: 25px">

                    </div>

                    <div class="dropdown-divider"></div>

                    <div style="display: block; float:right; margin: 1%">
                        <button type="button" class="btn btn-primary" onclick="addVacancy()">Добавить вакансию</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>

    <script>
        function addNewElement(selectId, divId) {
            var select = document.getElementById(selectId);
            var div = document.getElementById(divId);
            var name = 'tag';
            selectedTags.push(select.value);
            if (select.value !== '') {
                div.innerHTML += '<button type="button" class="btn btn-info" name="'
                    + name
                    + '" id="'
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
            select.innerHTML += '<option id="'
                + btnId
                + '" value="'
                + btnId
                + '">'
                + btnId
                + '</option>';
            document.getElementById(divId).removeChild(btn);
        }

        function addInput(divId, name) {
            document.getElementById(divId).innerHTML += '<div class="input-group" style="margin-top: 10px; width: inherit">\n' +
                '  <input type="text" class="form-control" name="' + name + '">\n' +
                '  <div class="input-group-append">\n' +
                '    <button onclick="deleteInput(\'' + divId + '\')" class="input-group-text">-</button>\n' +
                '  </div>\n' +
                '</div>';
        }

        function deleteInput(divId) {
            var div = document.getElementById(divId);
            const length = div.querySelectorAll("div.input-group").length;
            var removableNode = document.querySelectorAll("div.input-group")[length - 1];
            div.removeChild(removableNode);
        }
    </script>
    </body>
</#macro>
<#macro title>
    <title>Добавить вакансию</title>
</#macro>
<@display_page />