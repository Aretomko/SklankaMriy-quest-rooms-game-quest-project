<#import "parts/common.ftl" as c>

<@c.page>

<h1>Змінити порядок відображення питань тільки для команди ${teamName}</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Порядок відображення</th>
              <th>Назва</th>
              <th>Вигляд</th>
            </tr>
          </thead>
          <tbody>
          <#list pages as page>
            <tr>
                <td>
                    <#if numberError?? >
                        <label for="lname" style="color: red;">
                            ${numberError}
                        </label>
                    </#if>
                    <form action="/admin/changeOrderForPage/${page.id}" method="post">
                        <input type="text" id="fname" name="orderNumber" value="${page.orderNumber}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="btn btn-primary"><b>Змінити</b></button>
                    </form>
                </td>
                <td>${page.name}</td>
                <td><a href="/admin/previewPageGame/${page.id}"><button type="button" class="btn btn-success">Переглянути</button></a></td>
            </tr>
          </#list>
          </tbody>
        </table>
</@c.page>