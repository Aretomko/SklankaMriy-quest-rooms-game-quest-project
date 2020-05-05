<#import "parts/common.ftl" as c>

<@c.page>
<script>
        function myFunction() {
            var r = confirm("Press a button!");
            if (r == true) {
                document.getElementById('deleteButton').click();
            }
        }
</script>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Додати питання
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action="/admin/addNewPage/${questId}">
            <#if numberError??>
            <div class="form-group">
                 <label for="lname" style="color: red;">${numberError}</label>
                 <input type="text" class="form-control" name="number" placeholder="Номер відображення" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="number" placeholder="Номер відображення" />
            </div>
            </#if>
            <#if timeError??>
            <div class="form-group">
                 <label for="lname" style="color: red;">${timeError}</label>
                 <input type="text" class="form-control" name="time" placeholder="Максимальний час на відповідь" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="time" placeholder="Максимальний час на відповідь формат '2'або '2.30'" />
            </div>
            </#if>
            <input type="text" class="form-control" name="name" placeholder="Назва питання" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<h1>Питання до квесту ${questName}</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Порядок відображення</th>
              <th>Час на відповідь</th>
              <th>Назва</th>
              <th>Відповіді</th>
              <th>Редагувати питання</th>
              <th>Елементи</th>
              <th>Редагувати підказки</th>
              <th>Переглянути вигляд</th>
              <th>Видалення</th>
            </tr>
          </thead>
          <tbody>
          <#list pages as page>
            <tr>
                <td>${page.orderNumber}</td>
                <td>${page.getTime()}</td>
                <td>${page.name}</td>
                <td><a href="/admin/answers/${page.id}"><button type="button" class="btn btn-warning">Редагувати</button></a></td>
                <td><a href="/admin/updatePage/${page.id}"><button type="button" class="btn btn-warning">Редагувати</button></a></td>
                <td><a href="/admin/modifyPage/${page.id}"><button type="button" class="btn btn-warning">Елементи</button></a></td>
                <td><a href="/admin/hints/${page.id}"><button type="button" class="btn btn-warning">Редагувати</button></a></td>
                <td><a href="/admin/previewPage/${page.id}"><button type="button" class="btn btn-success">Переглянути</button></a></td>
                <td><a href="/admin/deletePage/${page.id}"><button id="deleteButton" style="display:none;" type="button">Видалити</button></a><button onclick="myFunction()" type="button" class="btn btn-danger">Видалити</button></td>
            </tr>
          </#list>
          </tbody>
        </table>
</@c.page>