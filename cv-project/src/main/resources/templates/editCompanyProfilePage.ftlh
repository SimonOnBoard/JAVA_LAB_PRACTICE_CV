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

</#macro>
<#macro content>
    <script type="application/javascript">
        function editCompany() {
            // Create an FormData object
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
                success : function(data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                },
                error : function(err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }
        function addPost() {
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
                success : function(data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    alert(b);
                    $('#f')[0].reset();
                },
                error : function(err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    alert(b);
                }
            });
        }
    </script>
    <form  id='x' action="/editCompanyProfile" method='POST'>
        <div class="card border-dark mb-3" style="min-width: 36rem; margin: 2%">
            <div class="card-header">Общая информация</div>
            <div class="card-body text-dark" style="max-height: 40rem">

                <div style="overflow-y: scroll">

                </div>

                <div class="form-group">
                    <h5 class="card-title">Название компании</h5>
                    <#if name?has_content>
                        <input type='text' class="form-control" name='name' value='${name}' required/>
                    <#else>
                        <input type='text' class="form-control" name='name' value='' required/>
                    </#if>

                    <div style="margin-top: 25px"></div>

                    <h5 class="card-title">Номер телефона</h5>
                    <tr>
                        <#if phone?has_content>
                            <input type='text' class="form-control" name='phone' value='${phone}'/>
                        <#else>
                            <input type='text' class="form-control" name='phone' value=''/>
                        </#if>
                    </tr>

                    <div style="margin-top: 25px"></div>

                    <h5 class="card-title">Адрес компании</h5>
                    <#if address?has_content>
                        <input type='text' class="form-control"  name='address' value='${address}' required/>
                    <#else>
                        <input type='text' class="form-control"  name='address' value='' required/>
                    </#if>

                    <div style="margin-top: 25px"></div>

                    <h5 class="card-title">О себе</h5>
                    <#if description?has_content>
                        <textarea class="form-control" name="description" rows="5" cols="70"
                                  style="resize: none"
                                  required>${description}</textarea>
                    <#else>
                        <textarea class="form-control" name="description" rows="5" cols="70"
                                  style="resize: none" value=''
                                  required></textarea>
                    </#if>
                    <div style="margin-top: 2rem; float: right">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="button" onclick="editCompany()" class="btn btn-primary" value="Изменить"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <br>
    <form id="f" action="/addPost">
        <#--        <input type="hidden" name="id" value="null"><br>-->
        <p>Name: </p>
        <input type="text" name="name"/>
        <p>Description: </p>
        <p><textarea rows="10" cols="45" name="description"></textarea></p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="button" onclick="addPost()" value="Submit"></button>
    </form>
    <a href="/profile">Go to profile</a>
</#macro>
<#macro title>
    <title>Edit company profile page</title>
</#macro>
<@display_page />