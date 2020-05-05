<#import "partsFront/commonFront.ftl" as com>

<@com.pageFront>
<#assign b=true>
    <div class="w3-row" >
      <!-- Blog entries -->
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <!-- Blog entry -->
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important;">
          <div id="chat" class="w3-container" style="min-height:300px;">

              <#list messages as message>
                  <#if message.fromUser == b >
                       <div style="margin-left:50%; width="50%;">
                             <p align="right"><font face="verdana">${message.string}</font></p>
                             <#if message.filename??><img src="/static/uploads/${message.filename}" style="max-height:350px;width:100%"></img></#if>
                       </div>
                  <#else>
                  <div style="width:50%;">
                      <p><font face="verdana">${message.string}</font></p>
                      <#if message.filename??><img src="/static/uploads/${message.filename}" style="max-height:350px;width:100%"></img></#if>
                  </div>
                  </#if>
              </#list>
          </div>
          <hr style="border: 1px solid black;" />

            <div class="w3-row" style="margin:20px!important; min-height:140px;">
                  <div>
                  <form method="post" action="/chat/addMessage/${code}" enctype="multipart/form-data">
                  <div class="w3-row" style="width:100%!important;">
                    <div class="w3-col s8" style="padding-top:18px;">
                        <input id="visibleText" type="text" id="fname" name="text" style="width:100%;">
                    </div>
                    <div class="w3-col s2" style="padding-top:18px;">
                        <div class="image-upload" align="center">
                            <label for="file-input">
                                <img src="https://goo.gl/pB9rpQ" style="height:35px; width:50px;"/>
                            </label>
                            <input id="file-input" type="file" name="file">
                        </div>
                    </div>
                    <div class="desct">
                    <div class="w3-col s2">
                      <p align="right"><button class="w3-button w3-padding-small w3-white w3-border"><b>Надіслати »</b></button></p>
                    </div>
                    </div>
                  </div>
                    <div class="mobi">
                        <p><button class="w3-button w3-padding-large w3-white w3-border"><b>Надіслати »</b></button></p>
                    </div>
                  </form>
                  </div>
            </div>
        </div>
        <hr>
      </div>
    </div>
   </div><br>
   <script>
        var count = 0;
            function startTimerTimeLimit(){
                time = setTimeout(function(){
                count++;
                console.log(count);
                if (count==20){
                    count = 0;
                    showCustomer();
                    console.log("reload");
                }
                    startTimerTimeLimit();
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
              xhttp.open("GET", "https://31.31.199.46:8080/getMessagesRest/${code}", true);
              xhttp.send();
            }
   </script>
</@com.pageFront>