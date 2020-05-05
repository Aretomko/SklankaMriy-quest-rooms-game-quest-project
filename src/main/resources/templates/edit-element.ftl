  <#import "parts/common.ftl" as c>

  <@c.page>
  <script>
          var pshort =0;
          var bshort =0;
          var ishort =0;
          var spanshort =0;
          var insshort = 0;
          var ashort = 0;
          function myFunction() {
              var r = confirm("Press a button!");
              if (r == true) {
                  document.getElementById('deleteButton').click();
              }
          }
        function addAShort(){
             if(ashort ==1){
                document.getElementById("small").value += "</a>"; ashort = 0;
             }else{
             document.getElementById("small").value += "<a href=''>";
             ashort++;
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
  <#assign b=true>
  <#list elements as element>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" action="/admin/editElementTextConfirm/${element.id}">
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
                <textarea id="small" class="form-control" name="text" rows="4">${element.string}</textarea>
            </div>
            <button type="button" onclick="addPShort()" class="btn btn-primary">Абзац</button>
            <button type="button" onclick="addBShort()" class="btn btn-primary">Жирний</button>
            <button type="button" onclick="addIShort()" class="btn btn-primary">Курсив</button>
            <button type="button" onclick="addSpanShort()" class="btn btn-primary">Оранжевий колір</button>
            <button type="button" onclick="addInsShort()" class="btn btn-primary">Підкреслення</button>
            <button type="button" onclick="addAShort()" class="btn btn-primary">Посилання</button>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group" style="margin-top:20px;">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
  </#list>
  </@c.page>