<#macro head>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</#macro>
<#macro content>
    <#if info?has_content>
        <div class="reg-container">
            <p>info: ${info}</p>
        </div>
    <#else>
    </#if>
    <script type="application/javascript">
        function addUser() {
            // Create an FormData object
            var form = $("#f")[0];
            var data = new FormData(form);
            var address = "\"" + form.action + "\"";
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
                    alert(String(err.responseText) + " " + "Скорее всего такой логин уже сщуествует");
                }
            });
        }
    </script>
    <form id='f' action="/addUser" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value='' required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' required/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type='email' name='email' required/></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td>
                    <p><select type='text' name="role" required>
                            <option value="ADMIN">ADMIN</option>
                            <option value="TEACHER">TEACHER</option>
                            <option value="COMPANY">COMPANY</option>
                            <option value="STUDENT">STUDENT</option>
                        </select></p>
                </td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <tr>
                <td><button type="button" onclick="addUser()" value="submit"/></td>
            </tr>
        </table>
    </form>

    <a href="/panel">GET admin panel</a>

</#macro>
<#macro title>
    <title>Add user page</title>
</#macro>
<@display_page />