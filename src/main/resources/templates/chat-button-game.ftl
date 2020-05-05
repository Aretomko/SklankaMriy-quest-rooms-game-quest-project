        <#assign b=true>
               <#if newMessageAlert ==b>
                <a style="margin-top:40px;" target="_blank" href="/chat/${code}"><p><button type="submit" class="w3-button w3-padding-large w3-white w3-border"><b>Чат »</b></button></p></a>
               <#else>
                <a style="margin-top:40px;" target="_blank" href="/chat/${code}"><p><button type="submit" class="w3-button w3-padding-large w3-red w3-border"><b>Чат »</b></button></p></a>
               </#if>
