<#macro content></#macro>
<#macro title></#macro>
<#macro head></#macro>
<#macro display_page>
    <!doctype html>
    <html>
    <head>
        <meta http-equiv="Cache-Control" content="no-cache">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css ">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <@title />
        <@head />

    </head>

    <body>
    <@content />
    </body>
    </html>
</#macro>