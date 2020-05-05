<#import "partsFront/commonFront.ftl" as com>

<@com.pageFront>
    <#assign b=true>
    <div class="w3-row">
      <!-- Blog entries -->
      <#list quests as quest>
      <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px;">
        <!-- Blog entry -->
        <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important;">
          <img src="/static/uploads/${quest.filename}" alt="Nature" style="width:100%">
          <div class="w3-container">
            <h3><b>${quest.name}</b></h3>
            <h5><span class="w3-opacity"></span></h5>
          </div>
          <div class="w3-container">
            <p>${quest.shortDescription}</p>
            <div class="w3-row">
              <div class="w3-col m8 s12">
                  <form method="get" action="/moreInfo/${quest.id}">
                    <p><button class="w3-button w3-padding-large w3-white w3-border"><b>Зареєструватися »</b></button></p>
                  </form>
              </div>
            </div>
          </div>
        </div>
        </#list>
        <hr>
        <!-- END BLOG ENTRIES -->
      </div>
    </div>
   </div><br>

</@com.pageFront>

