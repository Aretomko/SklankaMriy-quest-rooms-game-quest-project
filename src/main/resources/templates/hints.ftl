<#import "parts/common.ftl" as c>

<@c.page>
<script>
        var pshort =0;
        var bshort =0;
        var ishort =0;
        var insshort = 0;
        var spanshort =0;
        function myFunction() {
            var r = confirm("Press a button!");
            if (r == true) {
                document.getElementById('deleteButton').click();
            }
        }
        function addInsShort(){
             if(insshort ==1){
                document.getElementById("small").value += "</ins>"; insshort = 0;
             }else{
             document.getElementById("small").value += "<ins>";
             insshort++;
             }
        }
        function addPShort(){
             if(pshort ==1){
                document.getElementById("small").value += "</p>"; pshort = 0;
             }else{
             document.getElementById("small").value += "<p>";
             pshort++;
             }

        }
        function addBShort(){
             if (bshort ==1){ document.getElementById("small").value += "</b>"; bshort = 0;
             }else {
             document.getElementById("small").value += "<b>";
             bshort++;
             }
        }
        function addIShort(){
             if (ishort == 1){
                document.getElementById("small").value += "</i>"; ishort = 0;
             }else {
             document.getElementById("small").value += "<i>";
             ishort++;
             }
        }
        function addSpanShort(){
               if (spanshort == 1){
                 document.getElementById("small").value += "</span>"; spanshort = 0;
               }else{
              document.getElementById("small").value += "<span>";
              spanshort++;
              }
        }
        </script>
<#assign b = true>
<#if numberError??>
    <div class="form-group">
        <label for="lname" style="color: red;">${numberError}</label>
    </div>
</#if>
<#if timeError??>
    <div class="form-group">
        <label for="lname" style="color: red;">${timeError}</label>
    </div>
</#if>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Додати підказку-файл
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" action="/admin/addNewHintFile/${pageId}">
            <#if timeError?? >
            <div class="form-group">
                 <label for="lname" style="color: red;">${timeError}</label>
                 <input type="text" class="form-control" name="time" placeholder="Хвилин до появи" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="time" placeholder="Число вилин до появи" />
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
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample2" role="button" aria-expanded="false" aria-controls="collapseExample2">
    Додати підказку-текст
</a>
<div class="collapse" id="collapseExample2">
    <div class="form-group mt-3">
        <form method="post" action="/admin/addNewHintText/${pageId}">
            <#if timeError?? >
            <div class="form-group">
                 <input type="text" class="form-control" name="time" placeholder="Число хвилин до появи" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="time" placeholder="Число хвилин до появи" />
            </div>
            </#if>
            <div class="form-group">
                <textarea id="small" class="form-control" name="text" rows="4"></textarea>
            </div>
            <button type="button" onclick="addPShort()" class="btn btn-primary">Абзац</button>
            <button type="button" onclick="addBShort()" class="btn btn-primary">Жирний</button>
            <button type="button" onclick="addIShort()" class="btn btn-primary">Курсив</button>
            <button type="button" onclick="addSpanShort()" class="btn btn-primary">Оранжевий колір</button>
            <button type="button" onclick="addInsShort()" class="btn btn-primary">Підкреслення</button>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group" style="margin-top:20px;">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<h1>Елементи обраного питання</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Час появи</th>
              <th>Вид (текст/файл)</th>
              <th>Вміст</th>
              <th>Редагувати</th>
              <th>Видалити</th>
            </tr>
          </thead>
          <tbody>
          <#list hints as hint>
            <tr>
                <td>${hint.time}</td>
                <td><#if hint.isText() == b>Текст<#else>Файл</#if></td>
                <td>
                    <#if hint.isText() == b>${hint.string}</#if>
                    <#if hint.isImage() == b><img src="/static/uploads/${hint.fileName}" style="height:100px; width:200px;"/></#if>
                    <#if hint.isVideo() == b><video controls style="height:100px; width:200px;"><source src="/static/uploads/${hint.fileName}" type="video/mp4"/></video></#if>
                </td>
                <#if hint.isText() == b><td><a href="/admin/editHintText/${hint.id}"><button type="button" class="btn btn-warning">Редагувати</button></a></td>
                <#else>
                <td><a href="/admin/editHintFile/${hint.id}"><button type="button" class="btn btn-warning">Редагувати</button></a></td>
                </#if>
                <td><a href="/admin/deleteHint/${hint.id}/${pageId}"><button onclick="myFunction()"type="button" class="btn btn-danger">Видалити</button></a></td>
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