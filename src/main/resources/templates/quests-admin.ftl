<#import "parts/common.ftl" as c>

<@c.page>
        <script>
        var pplace=0;
        var bplace =0;
        var iplace=0;
        var spanplace=0;
        var insplace=0;
        var insplace=0;
        var pname=0;
        var bname =0;
        var iname =0;
        var spanname=0;
        var insname=0;
        var pshort =0;
        var bshort =0;
        var ishort =0;
        var spanshort =0;
        var insshort = 0 ;
        var plong =0;
        var blong =0;
        var ilong =0;
        var spanlong =0;
        var inslong = 0;
        function myFunction() {
            var r = confirm("Press a button!");
            if (r == true) {
                document.getElementById('deleteButton').click();
            }
        }
        function addInsPlace(){
             if(insname ==1){
                document.getElementById("place").value += "</ins>"; insplace = 0;
             }else{
             document.getElementById("place").value += "<ins>";
             insplace++;
             }
        }
        function addPPlace(){
             if(pplace ==1){
                document.getElementById("place").value += "</p>"; pplace = 0;
             }else{
             document.getElementById("place").value += "<p>";
             pplace++;
             }

        }
        function addBPlace(){
             if (bplace ==1){ document.getElementById("place").value += "</b>"; bplace = 0;
             }else {
             document.getElementById("place").value += "<b>";
             bplace++;
             }
        }
        function addIPlace(){
             if (iplace == 1){
                document.getElementById("place").value += "</i>"; iplace = 0;
             }else {
             document.getElementById("place").value += "<i>";
             iplace++;
             }
        }
        function addSpanPlace(){
               if (spanplace == 1){
                 document.getElementById("place").value += "</span>"; spanplace = 0;
               }else{
              document.getElementById("place").value += "<span>";
              spanplace++;
              }
        }

        function addInsName(){
             if(insname ==1){
                document.getElementById("name").value += "</ins>"; insname = 0;
             }else{
             document.getElementById("name").value += "<ins>";
             insname++;
             }
        }
        function addPName(){
             if(pname ==1){
                document.getElementById("name").value += "</p>"; pname = 0;
             }else{
             document.getElementById("name").value += "<p>";
             pname++;
             }

        }
        function addBName(){
             if (bname ==1){ document.getElementById("name").value += "</b>"; bname = 0;
             }else {
             document.getElementById("name").value += "<b>";
             bname++;
             }
        }
        function addIName(){
             if (iname == 1){
                document.getElementById("name").value += "</i>"; iname = 0;
             }else {
             document.getElementById("name").value += "<i>";
             iname++;
             }
        }
        function addSpanName(){
               if (spanname == 1){
                 document.getElementById("name").value += "</span>"; spanname = 0;
               }else{
              document.getElementById("name").value += "<span>";
              spanname++;
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
        function addInsLong(){
                if (inslong == 1){
                   document.getElementById("long").value += "</ins>"; inslong = 0;
                }else {
               document.getElementById("long").value += "<ins>";
               inslong++;
               }
        }
        function addPLong(){
                if (plong == 1){
                   document.getElementById("long").value += "</p>"; plong = 0;
                }else {
               document.getElementById("long").value += "<p>";
               plong++;
               }
        }
        function addBLong(){
               if (blong == 1){
                   document.getElementById("long").value += "</b>"; blong = 0;
               }else{
               document.getElementById("long").value += "<b>";
               blong++;
               }
        }
        function addILong(){
                if (ilong == 1){
                document.getElementById("long").value += "</i>"; ilong =0;
                }else{
                document.getElementById("long").value += "<i>";
                ilong++;
                }
        }
        function addSpanLong(){
                if(spanlong ==1 ){
                document.getElementById("long").value += "</span>"; spanlong=0;
                }else{
                document.getElementById("long").value += "<span>";
                spanlong++;
                }
        }
        function addSpanLong(){
                if(spanlong ==1 ){
                document.getElementById("long").value += "</span>"; spanlong=0;
                }else{
                document.getElementById("long").value += "<span>";
                spanlong++;
                }
        }
        </script>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Додати квест
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form name="myform" method="post" enctype="multipart/form-data" action="/admin/addNewQuest">
            <div class="form-group">
                  <textarea id="name" class="form-control" name="name" rows="4">Назва</textarea>
            </div>
            <button type="button" onclick="addPName()" class="btn btn-primary">Абзац</button>
            <button type="button" onclick="addBName()" class="btn btn-primary">Жирний</button>
            <button type="button" onclick="addIName()" class="btn btn-primary">Курсив</button>
            <button type="button" onclick="addSpanName()" class="btn btn-primary">Оранжевий</button>
            <button type="button" onclick="addInsName()" class="btn btn-primary">Підкреслення</button>
            <div class="form-group" style="margin-top:20px;">
                <textarea id="place" class="form-control" name="place" rows="4">Місце проведення</textarea>
            </div>
            <button type="button" onclick="addPPlace()" class="btn btn-primary">Абзац</button>
            <button type="button" onclick="addBPlace()" class="btn btn-primary">Жирний</button>
            <button type="button" onclick="addIPlace()" class="btn btn-primary">Курсив</button>
            <button type="button" onclick="addSpanPlace()" class="btn btn-primary">Оранжевий</button>
            <button type="button" onclick="addInsPlace()" class="btn btn-primary">Підкреслення</button>
            <div class="form-group" style="margin-top:20px;">
                <textarea id="small" class="form-control" name="shortDescription" rows="4"></textarea>
            </div>
            <button type="button" onclick="addPShort()" class="btn btn-primary">Абзац</button>
            <button type="button" onclick="addBShort()" class="btn btn-primary">Жирний</button>
            <button type="button" onclick="addIShort()" class="btn btn-primary">Курсив</button>
            <button type="button" onclick="addSpanShort()" class="btn btn-primary">Оранжевий</button>
            <button type="button" onclick="addInsShort()" class="btn btn-primary">Підкреслення</button>
            <div class="form-group" style="margin-top:20px;">
                <textarea id="long" class="form-control" name="description" rows="6"></textarea>
            </div>
            <button type="button" onclick="addPLong()" class="btn btn-primary">Абзац</button>
            <button type="button" onclick="addBLong()" class="btn btn-primary">Жирний</button>
            <button type="button" onclick="addILong()" class="btn btn-primary">Курсив</button>
            <button type="button" onclick="addSpanLong()" class="btn btn-primary">Оранжевий</button>
            <button type="button" onclick="addInsLong()" class="btn btn-primary">Підкреслення</button>
            <div class="form-group" style="margin-top:20px;">
                 <label for="lname" style="color: green;">Картинки - png, jpg, gif, bpm мінімальна ширина 750px(велика картинка)</label>
                 <#if fileError??> <label for="lname" style="color: red;">${fileError}</label></#if>
                 <input type="file" class="form-control" name="file" placeholder="Картинки - png, jpg, gif, bpm" />
            </div>
            <div class="form-group" style="margin-top:20px;">
                 <label for="lname" style="color: green;">Картинки - png, jpg, gif, bpm мінімальна ширина 285px висота 340px(велика картинка)</label>
                 <input type="file" class="form-control" name="fileSmall" placeholder="Картинки - png, jpg, gif, bpm" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<h1>Існуючі квести</h1>
        <table id="tablePreview" class="table table-striped ">
        <!--Table head-->
          <thead>
            <tr>
              <th>Назва</th>
              <th>Редагувати</th>
              <th>Список питань</th>
              <th>Отання сторінка</th>
              <th>Статистика</th>
              <th>Команди на квеcт</th>
              <th>Зображення</th>
              <th>Видалення</th>
            </tr>
          </thead>
          <tbody>
          <#list quests as quest>
            <tr>
                <td>${quest.name}</td>
                <td><a href="/admin/updateQuest/${quest.id}"><button class="btn btn-primary">Редагувати</button></a></td>
                <td><a href="/admin/listOfPages/${quest.id}"><button class="btn btn-primary">Питання</button></a></td>
                <td><a href="/admin/finalPage/${quest.id}"><button class="btn btn-primary">Остання сторінка</button></a></td>
                <td><a href="/admin/getStatistics/${quest.id}"><button class="btn btn-primary">Статистика</button></a></td>
                <td><a href="/admin/getTeamsByQuest/${quest.id}"><button class="btn btn-primary">Команди</button></a></td>
                <td><img src="/static/uploads/${quest.filename}" style="height:80px; width:160px;"/></td>
                <td><a href="/admin/deleteQuest/${quest.id}"><button id="deleteButton" style="display:none;" type="button">Видалити</button></a><button onclick="myFunction()" type="button" class="btn btn-danger">Видалити</button></td>
            </tr>
          </#list>
          </tbody>
        </table>
</@c.page>