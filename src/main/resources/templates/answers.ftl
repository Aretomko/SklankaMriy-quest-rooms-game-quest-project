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
<#assign b = true>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Додати відповідь
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" action="/admin/addAnswer/${pageId}">
            <div class="form-group">
                 <input type="text" class="form-control" name="answer" placeholder="Відповідь" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<h1>Елементи обраного питання</h1> <a href="/admin/previewPage/${pageId}"><button type="button" class="btn btn-success">Превью сторінки</button></a>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Відповідь</th>
              <th>Видалити</th>
            </tr>
          </thead>
          <tbody>
          <#list answers as answer>
            <tr>
                <td>${answer.answers}</td>
                <td><a href="/admin/deleteAnswer/${answer.id}/${pageId}"><button type="button" class="btn btn-danger">Видалити</button></a></td>
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