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
                method: "PUT",
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
    <div>
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
    <div style="display: inline-block; vertical-align: top">
        <form method="post" action="#">
            <div class="card border-dark mb-3" style="width: 43rem; margin: 2%">
                <div class="card-header">Личные данные</div>
                <div class="card-body text-dark" style="max-height: 40rem">

                    <div style="display: inline-block; margin-right: 5rem">

                        <h5 class="card-title">ФИО</h5>
                        <div class="form-group" style="text-align: left; max-width: 10rem">
                            <label class="card-text">
                                <strong>Фамилия</strong>
                                <input type="text" name="lastname" class="form-control" value="Блинов" required/>
                            </label>

                            <label class="card-text">
                                <strong>Имя</strong>
                                <input type="text" name="firstname" class="form-control" value="Ренат" required/>
                            </label>
                            <label class="card-text">
                                <strong>Отчество</strong>
                                <input type="text" name="patronomyc" class="form-control" value="Константинович" required/>
                            </label>
                        </div>

                        <div style="margin-top: 25px"></div>


                        <h5 class="card-title">Возраст</h5>
                        <p class="card-text">
                            <label>
                                <input class="form-control" type="text" name="age" value="20" style="width: 3rem" required/>
                            </label> лет
                        </p>

                    </div>

                    <div style="display: inline-block">

                        <h5 class="card-title">Контакты</h5>
                        <p class="card-text"><strong>Телефон: </strong>
                            <label>
                                <input class="form-control" type="text" name="phoneNumber" value="+79997892343" required/>
                            </label>
                        </p>
                        <p class="card-text"><strong>Email: </strong>
                            <label>
                                <input class="form-control" type="email" name="phoneNumber" value="aaa@a.ru" required/>
                            </label>
                        </p>

                        <div style="margin-top: 25px"></div>

                        <h5 class="card-title">О себе</h5>
                        <label>
                <textarea class="form-control" name="aboutMe" id="exampleFormControlTextarea1" rows="3" cols="50"
                          style="resize: none"
                          required>Студент, 20 лет опыта разработки програмного обеспечения, люблю печенье</textarea>
                        </label>

                    </div>
                    <div style="display: block; float:right; margin: 1%">
                        <input type="submit" class="btn btn-primary" value="Изменить"/>
                    </div>

                </div>
            </div>
        </form>


        <div class="card border-dark mb-3" style="width: 43rem; margin: 2%">
            <div class="card-header">Компетенции</div>
            <div class="card-body text-dark" style="max-height: 40rem">

                <div class="form-group">
                    <form method="post" action="#">
                        <label for="competenceSelect">Выберите компетенцию из списка</label>
                        <select class="form-control" id="competenceSelect">
                            <option>Java</option>
                            <!--                    ajax-->
                            <option>SQL</option>
                            <option>Lombok</option>
                            <option>Spring MVC</option>
                            <option>Spring Boot</option>
                        </select>

                        <div style="margin-top: 1rem; ">
                            <input type="submit" class="btn btn-primary" value="Добавить">
                        </div>
                    </form>

                    <div class="dropdown-divider"></div>

                    <div class="competences" style="display: flex; flex-wrap:wrap">
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Java</button>
                        <!--                ajax-->
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>JavaSE</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Java2EE</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Java Collections</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Java Stream API</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Java IO/NIO</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Java Exceptions</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Spring MVC</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Spring Boot</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Spring Security</button>
                        <button type="button" class="btn btn-info" style="margin: 5px" disabled>Spring Framework</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div style="display: inline-block; margin-left: 4%">
        <form method="post" action="#">
            <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
                <div class="card-header">Образование</div>
                <div class="card-body text-dark" style="max-height: 40rem">

                    <div class="form-group">
                        <label for="educationSelect">Выберите ваш уровень образования</label>
                        <select class="form-control" id="educationSelect">
                            <option value="Бакалавр неоконченное">Бакалавр неоконченное</option>
                            <!--                    ajax-->
                            <option value="Бакалавр">Бакалавр</option>
                            <option value="Магистр неоконченное">Магистр неоконченное</option>
                            <option value="Магистр">Магистр</option>
                            <option value="Аспирант неоконченное">Аспирант неоконченное</option>
                            <option value="Аспирант">Аспирант</option>
                        </select>

                        <div style="margin-top: 1rem; float: right">
                            <input type="submit" class="btn btn-primary" value="Изменить">
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
                        <label for="language">Выберите язык из списка</label>
                        <input type="text" name="language"/>

                        <div style="margin: 1rem"></div>

                        <label for="exampleFormControlSelect2">Выберите уровень владения</label>
                        <select class="form-control" id="level">
                            <option value="A1">A1</option>
                            <option value="A2">A2</option>
                            <option value="B1">B1</option>
                            <option value="B2">B2</option>
                            <option value="C1">C1</option>
                            <option value="C2">C2</option>
                        </select>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div style="margin-top: 1rem; float: right">
                            <button type="submit" class="btn btn-primary" onclick="addLanguage()" value="Обновить"></button>
                        </div>
                    </div>

                </div>
            </div>
        </form>


        <form>
            <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
                <div class="card-header">Сертификаты</div>
                <div class="card-body text-dark" style="max-height: 40rem">

<#--                    <div style="height: 4rem; overflow-y: scroll; border: 1px grey solid; border-radius: 3px">-->
<#--                        <h5 class="card-title" style="margin-left: 15px">ITIS</h5>-->
<#--                        <p class="card-text" style="margin-left: 15px">2019</p>-->
<#--                        <!--ajax&ndash;&gt;-->
<#--                        <div class="dropdown-divider"></div>-->

<#--                        <h5 class="card-title" style="margin-left: 15px">KFU</h5>-->
<#--                        <p class="card-text" style="margin-left: 15px">2018</p>-->

<#--                        <div class="dropdown-divider"></div>-->

<#--                        <h5 class="card-title" style="margin-left: 15px">Mera</h5>-->
<#--                        <p class="card-text" style="margin-left: 15px">2020</p>-->

<#--                        <div class="dropdown-divider"></div>-->

<#--                        <h5 class="card-title" style="margin-left: 15px">Simbirsoft</h5>-->
<#--                        <p class="card-text" style="margin-left: 15px">2021</p>-->
<#--                    </div>-->

                    <div class="form-group">

                        <label for="nameInput">Название сертификата</label>
                        <input class="form-control" type="text" id="nameInput" name="name" required/>

                        <label for="yearSelect">Выберите год получения</label>
                        <select class="form-control" id="yearSelect">
                            <option value="2020">2020</option>
                            <!--                    ajax-->
                            <option value="2019">2019</option>
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                        </select>

                        <div style="margin-top: 1rem; float: right">
                            <input type="submit" class="btn btn-primary" value="Добавить">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <a href="/profile">Go to profile</a>
</#macro>
<#macro title>
    <title>Личный кабинет</title>
</#macro>
<@display_page />