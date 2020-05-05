<#assign b=true>
<!DOCTYPE html>
<html lang="en">
   <title>sklq</title>
   <meta charset="UTF-8">
   <link rel="icon" href="http://sklquest.com:8080/static/uploads/icon.png">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
        <script>
       <#list hints as hint>
            var minsHint${hint.id} = 0;
            var secondsHint${hint.id} =0;
            if (${secondsElapsed} > 0){
                    var minsHint${hint.id} = ${hint.time} - ${minsElapsed} -1;
                    var secondsHint${hint.id} = 60 - ${secondsElapsed};
            }
            if (${secondsElapsed} == 0 ){
                    var minsHint${hint.id} = ${hint.time} - ${minsElapsed} ;
            }
            function startTimerHint${hint.id}(){
            time = setTimeout(function(){
                if(secondsHint${hint.id} == 0){secondsHint${hint.id}=59; minsHint${hint.id}--;}
                if(minsHint${hint.id} < 0){document.getElementById('hintTimer${hint.id}').style.display = "none";}
                document.getElementById('timerMinutesHint${hint.id}').innerHTML = minsHint${hint.id} +" : ";
                document.getElementById('timerSecondsHint${hint.id}').innerHTML = secondsHint${hint.id};
                secondsHint${hint.id}--;
                if(minsHint${hint.id} ==0 ){if(secondsHint${hint.id} == 0){document.getElementById('${hint.id}').classList.remove('hide');}}
                startTimerHint${hint.id}();
              },1000);
            }
        </#list>
            var mins =0;
            var seconds =0;
            function startTimer(){
                timex = setTimeout(function(){
                    seconds++;
                    if(seconds > 59){seconds=0;mins++;}
                    startTimer();
                },1000);
            }
            function myFunction() {
              document.getElementById("invisibleText").value =  document.getElementById("visibleText").value;
              document.getElementById("answer").action = "/answer/${code}/";
              document.getElementById("invisibleButton").click();
            }
            var minsTimeLimit = ${timeLimitCounterMinutes};
            var secondsTimeLimit = ${timeLimitCounterSeconds};
            function startTimerTimeLimit(){
                time = setTimeout(function(){
                    if(minsTimeLimit == 0 ){if (secondsTimeLimit==0){document.getElementById("myCheck").click();}}
                    if(secondsTimeLimit == 0){secondsTimeLimit=59;minsTimeLimit--;}
                    document.getElementById('finishTimerMinutes').innerHTML = minsTimeLimit +" : ";
                    document.getElementById('finishTimerSeconds').innerHTML = secondsTimeLimit;
                    secondsTimeLimit--;
                    startTimerTimeLimit();
                },1000);
            }
            var minsElapsed = ${minsElapsed};
            var secondsElapsed = ${secondsElapsed};
            function elapsedTime(){
                    time = setTimeout(function(){
                        secondsElapsed++;
                        elapsedTime();
                      },1000);
                    }
        </script>
             <script>
                  var count = 0;
                      function startTimerButtonReload(){
                          time = setTimeout(function(){
                          count++;
                          console.log(count);
                          if (count==15){
                              count = 0;
                              showCustomer();
                              console.log("reload");
                          }
                              startTimerButtonReload();
                          },1000);
                      }

                      function showCustomer() {
                        var xhttp;
                        xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function() {
                          if (this.readyState == 4 && this.status == 200) {
                            document.getElementById("chat").innerHTML = this.responseText;
                          }
                        };
                        xhttp.open("GET", "http://31.31.199.46:8080/getButtonGame/${code}", true);
                        xhttp.send();
                      }
             </script>

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
     @media (max-width: 500px){.w3-black{font-size:10px;}}

     .hide {
      display: none;
     }
     span {color: orange!important;}
   </style>
<body class="w3" style="font-size:16px;LINE-HEIGHT:12px; background-color: black;" onload="startTimer(), startTimerTimeLimit(), startTimerButtonReload() <#list hints as hint>,startTimerHint${hint.id}()</#list>, elapsedTime()">

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
  <!-- Header -->
  <header class="w3-container w3-center w3-padding-32" style="padding-top: 80px!important; color: white;">
    <h1><img src="/static/uploads/logo.png"  style="height:150px; width:360px;"/></h1>
    <b>Квести для дітей, підлітків та їх батьків<span class="w3-tag"></span></b>
  </header>

  <div class="w3-row">
    <!-- Start of the elements list -->
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color: white !important; background-color:black !important;">
            <div class="w3-container">
               <p><font face="verdana">Команда: ${teamName}</font></p>
               <p><font face="verdana">Завдання: ${pageName}</font></p>
            </div>
         </div>
      </div>
    <#list elements as element>
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color: white !important; background-color:black !important;">
          <#if element.isText() == b>
            <div class="w3-container">
               <p><font face="verdana" style="line-height: 22px; !important;">${element.string}</font></p>
            </div>
          </#if>
          <#if element.isImage() == b>
                <img src="/static/uploads/${element.fileName}" alt="Nature" style="width:100%">
          </#if>
          <#if element.isVideo() == b>
                <video controls preload style="width:100%;"><source src="/static/uploads/${element.fileName}" type="video/mp4"/></video>
          </#if>
        </div>
      </div>
    </#list>

          <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
            <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color: white !important; background-color:black !important;">
               <div class="w3-container" style="padding-top:20px">
               <p><font face="verdana">Треба знайти відповідей: ${numberOfAnswers}</font></p>
               <#if multiQuestionAnswers??>
               <p><font face="verdana">Знайдені відповіді</font></p>
               <#list multiQuestionAnswers as multiQuestionAnswer>
                <p style="color:green;">${multiQuestionAnswer.answers}</p>
                </#list>
                </#if>
                <#if wrongAnswer??> <p style="color:red;">Невірна відповідь</p></#if>
              <form id="answer" action="" method="post" style="display:none;">
                <input id="invisibleText" type="text" id="fname" name="answer">
                <p><button id="invisibleButton" type="submit" class="w3-button w3-padding-large w3-white w3-border"><b>Відповісти »</b></button></p>
              </form>

              <form id="updateTimeForm" action="" method="get" style="display:none;">
                <p><button id="updateTimeButton" type="submit" ></button></p>
              </form>

               <label for="fname">Відповідь</label>
               <input id="visibleText" type="text" id="fname" name="answer">
               <p><button onclick="myFunction()" type="submit" class="w3-button w3-padding-large w3-white w3-border"><b>Відповісти »</b></button></p>

              <form action="/noAnswer/${code}" method="get" style="display:none;">
                <p><button id="myCheck" type="submit" ></button></p>
              </form>
              </form>
              </div>
            </div>
          </div>
    <#list hints as hint>
      <div id="${hint.id}" class="w3-col l8 s12 hide" style="width:100%;padding-left:20px; padding-right:20px;">
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color:orange !important; background-color:black !important;">
          <#if hint.isText() == b>
            <div class="w3-container">
               <p><font face="verdana" style="line-height: 22px; !important;">${hint.string}</font></p>
            </div>
          </#if>
          <#if hint.isImage() == b>
            <div class="w3-container">
                <img src="/static/uploads/${hint.fileName}" alt="Nature" style="width:100%">
            </div>
          </#if>
          <#if hint.isVideo() == b>
            <div class="w3-container">
                <video controls preload style="width:100%;"><source src="/static/uploads/${hint.fileName}" type="video/mp4"/></video>
            </div>
          </#if>
        </div>
      </div>
    </#list>
    <#if shownHints??>
    <#list shownHints as shownHint>
        <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
                <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color:orange !important; background-color:black !important;">
                  <#if shownHint.isText() == b>
                    <div class="w3-container">
                       <p><font face="verdana" style="line-height: 22px; !important;">${shownHint.string}</font></p>
                    </div>
                  </#if>
                  <#if shownHint.isImage() == b>
                    <div class="w3-container">
                        <img src="/static/uploads/${shownHint.fileName}" alt="Nature" style="width:100%">
                    </div>
                  </#if>
                  <#if shownHint.isVideo() == b>
                    <div class="w3-container">
                        <video controls preload style="width:100%;"><source src="/static/uploads/${shownHint.fileName}" type="video/mp4"/></video>
                    </div>
                  </#if>
                </div>
              </div>
    </#list>
    </#if>
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color: white !important; background-color:black !important;">
            <div class="w3-container">
               <p><font face="verdana">До автопереходу залишилось: <label id="finishTimerMinutes"></font></label><label id="finishTimerSeconds"></label></p>
               <#list hints as hint>
                <p id="hintTimer${hint.id}"><font face="verdana">До підказки залишилось: <label id="timerMinutesHint${hint.id}"></label><label id="timerSecondsHint${hint.id}"></font></label></p>
               </#list>
               <div id="chat">
               <#if newMessageAlert ==b>
                <a style="margin-top:40px;" target="_blank" href="/chat/${code}"><p><button type="submit" class="w3-button w3-padding-large w3-white w3-border"><b>Чат »</b></button></p></a>
               <#else>
                <a style="margin-top:40px;" target="_blank" href="/chat/${code}"><p><button type="submit" class="w3-button w3-padding-large w3-red w3-border"><b>Чат »</b></button></p></a>
               </#if>
               </div>
            </div>

         </div>
      </div>
  </div>

<hr>

<footer class="w3-content w3-padding-64 w3-text-grey w3-xlarge" style="max-width:100%">
    <div style="margin:20px">
        <a href="https://www.facebook.com/sklyanka.mriy"><i class="fab fa-facebook w3-hover-opacity"></i></a>
        <a href="https://www.instagram.com/sklyanka_mriy"><i class="fab fa-instagram w3-hover-opacity"></i></a>
        <a href="viber://add?number=3800674656763"><i class="fab fa-viber w3-hover-opacity"></i></a>
        <a href="https://t.me/sklquest"><i class="fab fa-telegram w3-hover-opacity"></i></a>
    </div>
</footer>
</div>
</body>
</html>
