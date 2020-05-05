             <#assign b=true>
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