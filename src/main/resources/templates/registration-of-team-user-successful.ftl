<#import "partsFront/commonFront.ftl" as com>

<@com.pageFront>

    <div class="w3-row">
      <!-- Blog entries -->
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <!-- Blog entry -->
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important;">
          <div class="w3-container">
            <h3><b>Команда зареєстрована</b></h3>
            <h5><span class="w3-opacity"></span></h5>
          </div>
          <div class="w3-container">
            <p>Дякуємо, з нетерпінням чекаємо на початок гри</p>
            <div class="w3-row">
            </div>
          </div>
            <div class="w3-row" style="margin:20px!important;">
              <div class="w3-col m8 s12" style="margin-bottom: 20px!important;">
                  <p><button type="button" class="w3-button w3-padding-large w3-white w3-border" onclick="submitForm()"><b>Почати гру»</b></button></p>
                  <form name="myForm" id="startForm" method="post" action="/start">
                    <input type="text" id="code"  name="code" placeholder="Код команди">
                  </form>
              </div>
            </div>
        </div>
        <hr>
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