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

</#macro>
<#macro content>
    <script type="application/javascript">
        function addLanguage() {
            var form = $("#x")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
            console.log(address);
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
                    alert(b);
                    $('#x')[0].reset();
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }

        function addTeacherInfo() {
            // Create an FormData object
            var form = $("#f")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
            console.log(address);
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
                    alert(b);
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }
    </script>
    <div style="display: inline-block">
        <form id='f' action="/editTeacherProfile" method='POST'>
            <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="card-header">Общая информация</div>
                <div class="card-body text-dark" style="max-height: 40rem">

                    <div style="overflow-y: scroll">

                    </div>

                    <div class="form-group">
                        <h5 class="card-title">ФИО</h5>
                        <div class="form-group">
                            <label class="card-text">
                                <strong>Фамилия</strong>
                                <#if surname?has_content>
                                    <td><input type='text' name='surname' value='${surname}'  class="form-control" required/>
                                <#else>
                                    <td><input type='text' name='surname' value='' class="form-control" required/>
                                </#if>
                            </label>

                            <label class="card-text" style="margin-left: 20px">
                                <strong>Имя</strong>
                                <#if name?has_content>
                                    <input type='text' name='name' class="form-control" value='${name}' required/>
                                <#else>
                                    <input type='text' name='name' class="form-control" value='' required/>
                                </#if>
                            </label>

                            <label class="card-text" style="margin-left: 20px">
                                <strong>Отчество</strong>
                                <#if patronymic?has_content>
                                    <input type='text' name='patronymic' class="form-control" value='${patronymic}' required/>
                                <#else>
                                    <input type='text' name='patronymic' class="form-control" value='' required/>
                                </#if>
                            </label>

                        </div>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title">Место преподавания</h5>
                        <#if institution?has_content>
                            <input type='text' class="form-control" name='institution' value='${institution}' required/>
                        <#else>
                            <input type='text' class="form-control" name='institution' value='' required/>
                        </#if>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title">О себе</h5>
                        <#if info?has_content>
                            <textarea class="form-control" name="info" rows="5" cols="70"
                                      style="resize: none" type='text'
                                      required>${info}</textarea>
                        <#else>
                            <textarea class="form-control" name="info" rows="5" cols="70"
                                      style="resize: none" value='' type='text'
                                      required></textarea>
                        </#if>
                        <div style="margin-top: 2rem; float: right">
                                <button type="button" onclick="addTeacherInfo()" class="btn btn-primary"
                                        value="Изменять"></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <form method="post" action="#" style="display: inline-block">
        <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
            <div class="card-header">Знание языков</div>
            <div class="card-body text-dark" style="max-height: 40rem">

                <div class="form-group">
                    <label for="languageSelect">Выберите язык из списка</label>
                    <select class="form-control" id="languageSelect">
                        <option>Татарский</option>
                        <!--                    ajax-->
                        <option>Китайский</option>
                        <option>Русский</option>
                        <option>Испанский</option>
                        <option>Английский</option>
                    </select>

                    <div style="margin: 1rem"></div>

                    <label for="exampleFormControlSelect2">Выберите уровень владения</label>
                    <select class="form-control" id="exampleFormControlSelect2">
                        <option value="A1">A1</option>
                        <!--                    ajax-->
                        <option value="A2">>A2</option>
                        <option value="B1">>B1</option>
                        <option value="B2">>B2</option>
                        <option value="C1">>C1</option>
                        <option value="C2">>C2</option>
                    </select>

                    <div style="margin-top: 1rem; float: right">
                        <input type="submit" class="btn btn-primary" value="Изменить">
                    </div>
                </div>

            </div>
        </div>
    </form>

    <a href="/profile">Go to profile</a>
</#macro>
<#macro title>
    <title>Личный кабинет</title>
</#macro>
<@display_page />