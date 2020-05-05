<#import "partsFront/commonFront.ftl" as com>

<@com.pageFront>
<#assign b=true>
    <div class="w3-row">
        <div class="w3-col l8 s12" style="width:100%;padding-left:20px; padding-right:20px; ">
            <#list elements as element>
            <div class="w3-card-4 w3-margin w3-white" style="max-width: 750px; margin-left: auto !important; margin-right: auto !important; color: white !important; background-color:black !important; line-height: 10p; !important;">
            <#if element.isImage() == b>
                <img src="/static/uploads/${element.fileName}" alt="Nature" style="width:100%">
            </#if>
            <#if element.isVideo() == b>
                <video controls preload style="width:100%;"><source src="/static/uploads/${element.fileName}" type="video/mp4"/></video>
            </#if>
            <#if element.isText() ==b>
                <div class="w3-container">
                    <p>${element.string}</p>
                </div>
            </#if>
            </div>
            </#list>
        <hr>
      </div>
   </div><br>

</@com.pageFront>