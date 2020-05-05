<#import "parts/common.ftl" as c>

<@c.page>
<#assign b=true>
<#if numberError??>
            <div class="form-group">
                 <label for="lname" style="color: red;">${numberError}</label>
            </div>
</#if>
<a class="btn btn-primary" href="/admin/quests">
    < Назад
</a>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Додати елемент-файл
</a>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample2" role="button" aria-expanded="false" aria-controls="collapseExample2">
    Додати елемент-текст
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" action="/admin/addFinalElementFile/${questId}">
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
            <div class="form-group">
                 <input type="file" class="form-control" name="file" placeholder="Відео - mp4 , Картинки - png, jpg, gif, bpm" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>
<div class="collapse" id="collapseExample2">
    <div class="form-group mt-3">
        <form method="post" action="/admin/addFinalElementText/${questId}">
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
            <div class="form-group">
                 <input type="text" class="form-control" name="text" placeholder="Текст питання" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<h1>Елементи обраного питання</h1> <a href="/admin/previewFinalPage/${questId}"><button type="button" class="btn btn-success">Превью сторінки</button></a>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Порядок відображення</th>
              <th>Вид (текст/файл)</th>
              <th>Вміст</th>

              <th>Видалити</th>
            </tr>
          </thead>
          <tbody>
          <#list elements as element>
            <tr>
                <td>${element.orderNumber}</td>
                <td><#if element.isText() == b>Текст<#else>Файл</#if></td>
                <td>
                    <#if element.isText() == b>${element.string}</#if>
                    <#if element.isImage() ==b><img src="/static/uploads/${element.fileName}" style="height:100px; width:200px;"/></#if>
                    <#if element.isVideo() == b><video controls style="height:100px; width:200px;"><source src="/static/uploads/${element.fileName}" type="video/mp4"/></video></#if>
                </td>
                <td><a href="/admin/deleteFinalElement/${element.id}/${questId}"><button onclick="myFunction()" type="button" class="btn btn-danger">Видалити</button></a></td>
            </tr>
          </#list>
          </tbody>
        </table>

        <script>
        function myFunction() {
          confirm("Підтвердіть видалення");
        }
        </script>
</@c.page>