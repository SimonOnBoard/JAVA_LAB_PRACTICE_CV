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
    <script type="application/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        getStudent(${id})

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
                    if (b.lastName !== null) $('#lastname').attr('value',b.lastName);
                    if (b.patronymic !== null) $('#patronymic').attr('value',b.patronymic);
                    if (b.firstName !== null)  $('#firstName').attr('value',b.firstName);
                    if (b.age !== null) {
                        $("#age").attr('value',b.age);
                    }
                    if (b.email !== null) {
                        $("#email").attr('value',b.email);
                    }
                    if (b.phoneNumber !== null) {
                        $("#phoneNumber").attr('value',b.phoneNumber);
                    }
                    if (b.aboutMe !== null) {
                        $("#additionalInfo").text(b.aboutMe);
                    }
                    if (b.competences != null) {
                        b.competences.forEach(element => {
                            $("#competenceList").append("<button type=\"button\" class=\"btn btn-info\" style=\"margin: 5px\" disabled>" + element.tag.name + "</button>")
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

        getCompetences();

        function getCompetences() {
            $.ajax({
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                method: "GET",
                url: "/search/tags",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    var x = document.getElementById("competenceSelect");
                    b.forEach(element => {
                        var option = document.createElement("option");
                        option.text = element.name;
                        x.add(option);
                    });
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                    window.location.replace("/profile");
                }
            });
        }

        function addLanguage() {
            var form = $("#x")[0];
            var data = new FormData(form);
            $.ajax({
                method: "POST",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                data: JSON.stringify(Object.fromEntries(data)),
                contentType: "application/json",
                processData: false,
                cache: false,
                timeout: 60000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                    $('#x')[0].reset();
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }

        function addCompetence() {
            var form = $("#co")[0];
            var data = new FormData(form);
            $.ajax({
                method: "PUT",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                data: JSON.stringify(Object.fromEntries(data)),
                contentType: "application/json",
                processData: false,
                cache: false,
                timeout: 60000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    var x =  $("#competenceSelect option:selected").text();
                    alert(b);
                    $('#competenceList').append("  <button type=\"button\" class=\"btn btn-info\" style=\"margin: 5px\" disabled>"
                        + x + "</button>");
                    $('#co')[0].reset();
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }

        function addCertificates() {
            var form = $("#c")[0];
            var data = new FormData(form);
            $.ajax({
                method: "PUT",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                data: JSON.stringify(Object.fromEntries(data)),
                contentType: "application/json",
                processData: false,
                cache: false,
                timeout: 60000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                    $('#c')[0].reset();
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }

        function addEducation() {
            var form = $("#e")[0];
            var data = new FormData(form);
            $.ajax({
                method: "PUT",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                data: JSON.stringify(Object.fromEntries(data)),
                contentType: "application/json",
                processData: false,
                cache: false,
                timeout: 60000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                    $('#e')[0].reset();
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }

        function updateInfo() {
            // Create an FormData object
            var form = $("#upd")[0];
            var data = new FormData(form);
            $.ajax({
                method: "PUT",
                url: form.action,
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                data: JSON.stringify(Object.fromEntries(data)),
                contentType: "application/json",
                processData: false,
                cache: false,
                timeout: 60000,
                success: function (data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
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
    <div id="navigation"></div>
    <div style="display: inline-block; vertical-align: top; margin-left: 5%">
        <form method="post" action="/studentAccount/info/update" id="upd">
            <div class="card border-dark mb-3" style="width: 43rem; margin: 2%">
                <div class="card-header">Личные данные</div>
                <div class="card-body text-dark" style="max-height: 40rem">

                    <div style="display: inline-block; margin-right: 5rem">

                        <h5 class="card-title">ФИО</h5>
                        <div class="form-group" style="text-align: left; max-width: 10rem">
                            <label class="card-text">
                                <strong>Фамилия</strong>
                                <input type="text" name="lastName" id="lastname" class="form-control" value="" required/>
                            </label>

                            <label class="card-text">
                                <strong>Имя</strong>
                                <input type="text" name="firstName" id="firstName" class="form-control" value="" required/>
                            </label>
                            <label class="card-text">
                                <strong>Отчество</strong>
                                <input type="text" name="patronymic" id="patronymic" class="form-control" value=""
                                       required/>
                            </label>
                        </div>

                        <div style="margin-top: 25px"></div>


                        <h5 class="card-title">Возраст</h5>
                        <p class="card-text">
                            <label>
                                <input class="form-control" id="age" type="text" name="age" value="" style="width: 3rem"
                                       required/>
                            </label> лет
                        </p>

                    </div>

                    <div style="display: inline-block">

                        <h5 class="card-title">Контакты</h5>
                        <p class="card-text"><strong>Телефон: </strong>
                            <label>
                                <input class="form-control" type="text" name="phoneNumber" id="phoneNumber" value=""
                                       required/>
                            </label>
                        </p>
                        <p class="card-text"><strong>Email: </strong>
                            <label>
                                <input class="form-control" type="email" name="email" id="email" value="" required/>
                            </label>
                        </p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title">О себе</h5>
                        <label>
                <textarea class="form-control" name="aboutMe" id="additionalInfo" name="aboutMe" rows="3" cols="50"
                          style="resize: none"
                          required></textarea>
                        </label>

                    </div>
                    <div style="display: block; float:right; margin: 1%">
                        <button type="button" class="btn btn-primary" onclick="updateInfo()">Изменить</button>
                    </div>

                </div>
            </div>
        </form>


        <div class="card border-dark mb-3" style="width: 43rem; margin: 2%">
            <div class="card-header">Компетенции</div>
            <div class="card-body text-dark" style="max-height: 40rem">

                <div class="form-group">
                    <form id="co" action="/studentAccount/competences/update">
                        <label for="competenceSelect">Выберите компетенцию из списка</label>
                        <select class="form-control" id="competenceSelect" name="name">

                        </select>

                        <div style="margin-top: 1rem; ">
                            <button type="button" class="btn btn-primary" onclick="addCompetence()">Добавить</button>
                        </div>
                    </form>

                    <div class="dropdown-divider"></div>

                    <div class="competences" style="display: flex; flex-wrap:wrap" id="competenceList">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div style="display: inline-block; margin-left: 4%">
        <form id="e" action="/studentAccount/education/update">
            <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
                <div class="card-header">Образование</div>
                <div class="card-body text-dark" style="max-height: 40rem">

                    <div class="form-group">
                        <label for="educationSelect">Выберите ваш уровень образования</label>
                        <select class="form-control" id="educationSelect" name="name">
                            <option value="Бакалавр неоконченное">Бакалавр неоконченное</option>
                            <!--                    ajax-->
                            <option value="Бакалавр">Бакалавр</option>
                            <option value="Магистр неоконченное">Магистр неоконченное</option>
                            <option value="Магистр">Магистр</option>
                            <option value="Аспирант неоконченное">Аспирант неоконченное</option>
                            <option value="Аспирант">Аспирант</option>
                        </select>

                        <div style="margin-top: 1rem; float: right">
                            <button type="button" class="btn btn-primary" onclick="addEducation()">Добавить</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>


        <form id="x" action="/studentAccount/languages/update">
            <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
                <div class="card-header">Знание языков</div>
                <div class="card-body text-dark" style="max-height: 40rem">

                    <div class="form-group">
                        <label for="language">Введите язык</label>
                        <input class="form-control" type="text" name="language" id="language"/>

                        <div style="margin: 1rem"></div>

                        <label for="exampleFormControlSelect2">Выберите уровень владения</label>
                        <select class="form-control" id="level" name="level">
                            <option value="A1">A1</option>
                            <option value="A2">A2</option>
                            <option value="B1">B1</option>
                            <option value="B2">B2</option>
                            <option value="C1">C1</option>
                            <option value="C2">C2</option>
                        </select>
                        <div style="margin-top: 1rem; float: right">
                            <button type="button" class="btn btn-primary" onclick="addLanguage()">Добавить</button>
                        </div>
                    </div>

                </div>
            </div>
        </form>


        <form id="c" action="/studentAccount/certificates/update">
            <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
                <div class="card-header">Сертификаты</div>
                <div class="card-body text-dark" style="max-height: 40rem">


                    <div class="form-group">

                        <label for="nameInput">Название сертификата</label>
                        <input class="form-control" type="text" id="nameInput" name="name" id="name" required/>

                        <label for="yearSelect">Выберите год получения</label>
                        <select class="form-control" id="year" name="year">
                            <option value="2020">2020</option>
                            <option value="2019">2019</option>
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                        </select>

                        <div style="margin-top: 1rem; float: right">
                            <button type="button" class="btn btn-primary" onclick="addCertificates()">Добавить</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</#macro>
<#macro title>
    <title>Личный кабинет</title>
</#macro>
<@display_page />