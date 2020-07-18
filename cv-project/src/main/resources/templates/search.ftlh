<#include "base.ftlh"/>
<#macro head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

</#macro>
<#macro content>

 <script type="application/javascript">
     // getTags();
     function searchByFilters() {
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
             success : function(response) {
                 var a = JSON.stringify(response);
                 var b = JSON.parse(a);
                 // console.log(b);
                 $.each(b, function (idx, student) {
                     $('#students').first().append('<td><a href=\"/profile/'+  student['student']['id'] +'\">' + student['student']['firstName']  + '</a></td>');


                 });
                 // $('#students').innerHtml = JSON.parse(a);
             },
             error : function(err) {
                 alert(err.responseText);
             }
         });
     }
     /*function getTags() {
         // Create an FormData object
         $.ajax({
             method: "GET",
             url: "/api/search",
             dataType: "json",
             contentType: "application/json",
             success : function(response) {
                 var a = JSON.stringify(response);
                 var b = JSON.parse(a);
                 $.each(response, function () {
                     $('#tags').first().append('<td><input type="checkbox" name="comp"  id=' +
                             this.name+ 'value=' + this.name + '> <label for=' + this.name + '>' + this.name + '</label><br/></td>');
                 });
             },
         });
     }*/
 </script>





	<form  id='f' action="/api/search" method="post">
        <table id="filters">
            <tr id="tags">
                <td>Tags</td>
                <#list tags! as tag>
                    <td><input type="checkbox" name="comp" id='${tag.name!}' value='${tag.name!}'> <label for='${tag.name!}'>${tag.name!}</label><br/></td>
                </#list>
            </tr>
            <tr id="education">
                <td>Education Level</td>
                <#list educations! as education>
                    <td><input type="checkbox" name="education" id='${education!}' value='${education!}'> <label for='${education!}'>${education!}</label><br/></td>
                </#list>
            </tr>
            <tr id="language">
                <td>Languages</td>
                <#list languages! as language>
                    <td><input type="checkbox" name="language" id='${language.language!}' value='${language.language!}'> <label for='${language.language!}'>${language.language!}</label><br/></td>
                </#list>
            </tr>
        </table>
        <#--<label>Tags</label>-->
        <#--<#list tags! as tag>-->
            <#--<input type="checkbox" name="comp" id='${tag.name!}' value='${tag.name!}'> <label for='${tag.name!}'>${tag.name!}</label><br/>-->
		<#--</#list>-->
        <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
        <button type="button" onclick="searchByFilters()" name="ok">Search</button>
	</form>


    <table>
        <tr id="students"><td>Students</td></tr>
    </table>
    <#--<#list students! as student>-->
        <#--<#if student?has_content>-->
            <#--<a href="/students/${student.id!}">${student.firstName}</a>-->
            <#--<br>-->
        <#--<#else>-->
        <#--</#if>-->
    <#--</#list>-->



</#macro>
<#macro title>
    <title>Search</title>
</#macro>
<@display_page />
