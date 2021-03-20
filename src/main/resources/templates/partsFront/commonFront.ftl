<#macro pageFront>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>sklq</title>
        <link rel="icon" href="https://sklquest.com/static/uploads/icon.png">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/turbolinks/5.2.0/turbolinks.js"></script>
        <style>
            body,
            h1,
            h2,
            h3,
            h4,
            h5 {
                font-family: "Raleway", sans-serif
            }
             @media (max-width: 500px){
             .w3-black{font-size:10px;}
             .desct{display:none;}

             }
             @media (min-width: 500px){
                .mobi{display:none;}
             }
            span {color: orange!important;}
            .image-upload > input
            {
                visibility: hidden!important;
            }
            .image-upload img
            {
                width: 80px;
                cursor: pointer;
            }
        </style>
    </head>
    <body class="w3" style="font-size:18px; background-color: black;" onload="startTimerTimeLimit()">
    <#include "navbarFront.ftl">

    <#nested>

    <#include "footerFront.ftl">

    </body>
</html>
</#macro>