  <#import "parts/common.ftl" as c>

  <@c.page>
  <#assign b=true>
  <#list elements as element>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" action="/admin/editElementImageConfirm/${element.id}">
            <#if numberError??>
            <div class="form-group">
                 <label for="lname" style="color: red;">${numberError}</label>
                 <input type="text" class="form-control" name="number" placeholder="Номер відображення" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="number" value="${element.orderNumber}" />
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
  </#list>
  </@c.page>