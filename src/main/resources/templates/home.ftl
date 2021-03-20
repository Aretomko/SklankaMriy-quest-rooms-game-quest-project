<!DOCTYPE html>
<html lang="en">
<head>
   <title>sklq</title>
   <meta charset="UTF-8">
   <link rel="icon" href="https://sklquest.com/static/uploads/icon.png">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
     .element{background-color:black !important; color:white !important;}
     .mob{background-color:black !important;}
     }
     @media (max-width: 990px){.descktop {display: none;}}
     @media (min-width: 990px){
       .element {width: 300px !important; height: 450px !important;}
       .pad {padding-left: 8%!important;}
       .img {width: 284px; height: 342px;}
       .mobile {display: none;}
       .flip-container {
 perspective: 1000 !important;
}
 /* переверните сторону при проведении мышью */
 .flip-container:hover .flipper, .flip-container.hover .flipper {
 transform: rotateY(180deg);
 }

  .flip-container, .front, .back {
  width: 100%;
  clear: both;
  }


/* здесь находится скорость перевертывания */
.flipper {
 transition: 0.6s;
 transform-style: preserve-3d;
 position: relative;
 width: 284px;
 height: 342px;
clear: both;

}

/* скройте обратную сторону во время переворота */
.front, .back {
 backface-visibility: hidden;
 position: absolute;
 top: 0;
 left: 0;
}

/* лицевая сторона размещена над обратной */
.front {
 z-index: 2;
}

/* обратная, изначально скрытая сторона */
.back {
 transform: rotateY(180deg);
}
span {color: orange!important;}
}
   </style>
</head>
<body class="w3" style="font-size:18px; background-color: rgb(0, 0, 0);">

<nav>
<div class="w3-top">
    <div class="w3-row w3-padding w3-black" style="padding-right:0px!important;padding-left:0px!important;  @media (max-width: 500px){font-size: 12px;}; background-color: white !important;">
      <div class="w3-col s4">
        <a href="/" class="w3-button w3-block w3-black" style="color: black!important;background-color: white !important">Квести</a>
      </div>
      <div class="w3-col s4">
        <a href="/aboutUs" class="w3-button w3-block w3-black" style="color: black!important;background-color: white !important">Про нас</a>
      </div>
      <div class="w3-col s4">
        <a href="/contacts" class="w3-button w3-block w3-black" style="color: black!important;background-color: white !important;">Контакти</a>
      </div>
    </div>
  </div>
</nav>
    <div class="w3-content" style="max-width:1400px;" id="mobile">

      <header class="w3-container w3-center w3-padding-32" style="padding-top: 80px!important; color: white;">
        <div>
            <h1><img src="/static/uploads/logo.png"  style="height:150px; width:100%; max-width: 350px; margin-left: -5px;"/></h1>
            <b>Квести для дітей, підлітків та їх батьків<span class="w3-tag"></span></b>
        </div>
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important;">
           <#list baners as baner > <img src="/static/uploads/${baner.filename}" alt="Nature" style="width:100%;"></#list>
        </div>
      </header>

    <div class="w3-row pad">
      <#list quests as quest>
      <div class="w3-col s12 m12 l4 element" style="padding: 8px; min-height:500px;">
        <div style="background-color: black;">
          <img src="/static/uploads/${quest.filename}" class="mobile" alt="Nature" style="width:100%;">
          <a href="/moreInfo/${quest.id}">
          <div class="flip-container descktop" ontouchstart="this.classList.toggle('hover');" style="background-color:black; color:white;">
            <div class="flipper">
              <div style=" transition: 0.6s;transform-style: preserve-3d; position: relative;">
                <div class="front">
                  <img src="/static/uploads/${quest.filenameSmall}" class="img" alt="Nature" style="width:100%;">
                </div>
                <div class="back" style="text-align: center;">
                  <h1 style="font-size: 18px; margin-top: 10%; "><font face="verdana">${quest.shortDescription}</font></h1>
                </div>
              </div>
            </div>
          </div>
          </a>
          <div class="w3-container mob" style="min-height:150px;">
            <h3><font face="verdana" style="color:white;">${quest.name}</font></h3>
            <h5><font face="verdana" style="color:white;">${quest.place}</font></h5>
          </div>
          <div class="w3-col m8 s12 mobile"  style="width: 100%; background-color: black; padding :16px;">
            <form method="get" action="/moreInfo/${quest.id}">
              <p><button class="w3-button w3-padding-large w3-white w3-border"><b>Детальніше »</b></button></p>
            </form>
          </div>
        </div>
      </div>
      </#list>
    </div>
    <hr>
</div>
   <br>
    <footer class="w3-content w3-padding-64 w3-text-grey w3-xlarge" style="max-width:100%">
        <div style="margin:20px">
            <a href="https://www.facebook.com/sklyanka.mriy"  style="color:#757575!important;"><i class="fab fa-facebook w3-hover-opacity"></i></a>
            <a href="https://www.instagram.com/sklyanka_mriy" style="color:#757575!important;"><i class="fab fa-instagram w3-hover-opacity"></i></a>
            <a href="viber://add?number=3800674656763" style="color:#757575!important;"><i class="fab fa-viber w3-hover-opacity"></i></a>
            <a href="https://t.me/sklquest" style="color:#757575!important;"><i class="fab fa-telegram w3-hover-opacity"></i></a>
        </div>
    </footer>
</div>
</body>
</html>