<#import "parts/common.ftl" as c>

<@c.page>

<h1>Банер</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Фото</th>
              <th>Змінити</th>
            </tr>
          </thead>
          <tbody>
          <#list baners as baner>
            <tr>
                <td>
                    <img src="/static/uploads/${baner.filename}" style="height:300px; width:600px;"/>
                </td>
                <td>
                        <form name="myform" method="post" enctype="multipart/form-data" action="/admin/changeBaner/${baner.id}">
                                <div class="form-group">
                                     <input type="file" class="form-control" name="file" placeholder="Картинки - png, jpg, gif, bpm" />
                                </div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Добавить</button>
                                </div>
                            </form>
                </td>
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