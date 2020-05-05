<#import "partsFront/commonFront.ftl" as com>


<@com.pageFront>

    <div class="w3-row">
      <!-- Blog entries -->
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <!-- Blog entry -->
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important;">
          <div class="w3-container">
            <h3><b>Гра на паузі</b></h3>
            <h5><span class="w3-opacity"></span></h5>
          </div>
          <div class="w3-container">

            <div class="w3-row">
                    <form method="get" action="/start/${code}">
                          <p><button class="w3-button w3-padding-large w3-white w3-border"><b>Продовжити гру »</b></button></p>
                    </form>
            </div>
          </div>
        </div>
        <hr>
      </div>
    </div>
   </div><br>

</@com.pageFront>