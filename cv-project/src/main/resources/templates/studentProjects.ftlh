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
    <body onload="loadProjects()">
    <script type="application/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function loadProjects() {
            var pathname = window.location.pathname
            $.ajax({
                method: "GET",
                url: '/load' + pathname,
                dataType: "json",
                contentType: "application/json",
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                success: function (response) {
                    console.log(response)
                    for (let i = response.length - 1; i >= 0; i--) {
                        var links = '';
                        for (let j = response[i]['links'].length - 1; j >= 0; j--) {
                            links = links + '<a href="#">' + response[i]['links'][j] + '</a>'
                        }
                        $('#projects').append('<div class="list-group">\n' +
                            '                    <div class="list-group-item list-group-item-action flex-column align-items-start">\n' +
                            '                        <div class="d-flex w-100 justify-content-between">\n' +
                            '                            <h5 class="mb-1">' + response[i]['title'] + '</h5>\n' +
                            '                        </div>\n' +
                            '                        <p class="mb-1">' + response[i]['description'] + '</p>\n' +
                            links +
                            '                    </div>\n' +
                            '                </div>')
                    }
                },
                error: function (err) {
                    var a = JSON.stringify(err);
                    var b = JSON.parse(a);
                    console.log(JSON.stringify(b))
                }
            });
        }
    </script>
    <div class="card-deck">
        <div class="card border-dark mb-3"
             style="display: inline-block; margin-left: 5%; margin-top: 2%; max-width: 60rem">
            <div class="card-header">Выполненные проекты</div>
            <div class="card-body text-dark" style="height: 32rem; overflow-y: scroll">

                <div id="projects" class="list-group">

                </div>

            </div>
        </div>
    </div>

    </body>
</#macro>
<#macro title>
    <title>Выполненные проекты</title>
</#macro>
<@display_page />