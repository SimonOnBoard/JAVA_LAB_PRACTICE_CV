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
            margin: 4%;
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
        getStudent(${id})

        function getStudent(id) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
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
                        $("#education").text(b.education);
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
                                $("#competences").append("  <div class=\"element-competence\">\n" +
                                    "                            <button type=\"button\" class=\"btn btn-secondary dropdown-toggle\" data-toggle=\"dropdown\"\n" +
                                    "                                    aria-haspopup=\"true\" aria-expanded=\"false\">" +
                                    element.tag.name + "</button>\n" +
                                    "                            <div class=\"dropdown-menu\">\n" +
                                    "                                <strong class=\"dropdown-item\">Не подтверждена</strong></div>\n" +
                                    "                        </div>");
                            } else {
                                var result = '                        <div class="element-competence">\n' +
                                    '                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"\n' +
                                    '                                    aria-haspopup="true" aria-expanded="false">' + element.tag.name;
                                result +='</button>\n' +
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
                                result += "</div>\n" +
                                    "                            </div>\n" +
                                    "                        </div>\n"
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
    </script>
    <div class="profile">
        <div class="first-floor">
            <div class="card-deck">
                <div class="card border-dark mb-3" style="max-width: 28rem">
                    <div class="card-header">Личные данные</div>
                    <div class="card-body text-dark" style="max-height: 30rem; overflow-y: scroll">
                        <h5 class="card-title" hidden="true" id="fio_label">ФИО</h5>
                        <p class="card-text" id="fio">Блинов Ренат Константинович</p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title" hidden="true" id="age_label">Возраст</h5>
                        <p class="card-text" id="age" hidden="true">20 лет</p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title" hidden="true" id="contacts-label">Контакты</h5>
                        <p class="card-text" hidden="true" id="phone"><strong>Телефон: </strong>+79997892343</p>
                        <p class="card-text" hidden="true" id="mail"><strong>Email: </strong>aaa@a.ru</p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title" hidden="true" id="info-label">О себе</h5>
                        <p class="card-text" hidden="true" id="info">Студент, 20 лет опыта разработки програмного
                            обеспечения, люблю печенье</p>
                    </div>
                </div>

                <div class="card border-dark mb-3" style="max-width: 28rem;">
                    <div class="card-header">Образование</div>
                    <div class="card-body text-dark" style="max-height: 30rem; overflow-y: scroll">
                        <#--                        <h5 class="card-title">Программная инженерия</h5>-->
                        <p class="card-text" hidden="true" id="education">Бакалавр неоконченное</p>
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