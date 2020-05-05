          <#assign b = true>
          <#list messages as message>
            <tr>
                <td><#if message.fromUser == b>User<#else>You</#if></td>
                <td>${message.string}</td>
                <td><#if message.filename??><img style="height:100px; width:150px;" src="/static/uploads/${message.filename}"/><#else> - </#if></td>
            </tr>
          </#list>