<#import "partsFront/commonFront.ftl" as com>

<@com.pageFront>

    <div class="w3-row">
      <!-- Blog entries -->
      <#list quests as quest>
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <!-- Blog entry -->
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color: white!important; background-color:black !important;">
          <img src="/static/uploads/${quest.filename}" alt="Nature" style="width:100%">
          <div class="w3-container" style="text-align: center;">
            <h3><b><font face="verdana">${quest.name}</font></b></h3>
            <h5><span class="w3-opacity"></span></h5>
            <font face="verdana"><p>${quest.place}</p></font>
          </div>

          <div class="w3-container">
            <font><p>${quest.description}</p></font>
            <div class="w3-row">
              <div class="w3-col m8 s12">
                  <form method="get" action="/registration-of-team/${quest.id}">
                    <p><button class="w3-button w3-padding-large w3-white w3-border"><b>Реєстрація команди»</b></button></p>
                  </form>
              </div>
            </div>
            <div class="w3-row">
              <div class="w3-col m8 s12">
                  <form method="get" action="/apply">
                     <p><button class="w3-button w3-padding-large w3-white w3-border"><b>Залишити заявку»</b></button></p>
                  </form>
              </div>
            </div>
            <div class="w3-row" style="margin-bottom:20px;">
              <div class="w3-col m8 s12">
                  <p><button type="button" class="w3-button w3-padding-large w3-white w3-border" onclick="submitForm()"><b>Почати гру»</b></button></p>
                  <form name="myForm" id="startForm" method="post" action="/start">
                    <input type="text" id="code" name="code" placeholder="Код команди">
                  </form>
              </div>
            </div>
          </div>
        </div>
        </#list>
        <!-- END BLOG ENTRIES -->
      </div>
</div>
   </div><br>
<script>
    function submitForm()
    {
        console.log("submit");
        var code = document.getElementById("code").value;
        document.getElementById("startForm").action="/start/" + code;
        console.log(document.getElementById("startForm").action);
        document.myForm.submit();
    }
</script>
</@com.pageFront>