<#import "parts/common.ftl" as c>

<@c.page>
<script>
        var pshort =0;
        var bshort =0;
        var ishort =0;
        var spanshort =0;
        function myFunction() {
            var r = confirm("Press a button!");
            if (r == true) {
                document.getElementById('deleteButton').click();
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
<#list hints as hint>
<div class="form-group mt-3">
        <form method="post" action="/admin/editHintConfirmFile/${hint.id}">
            <#if timeError?? >
            <div class="form-group">
                 <input type="text" class="form-control" name="time" value="${hint.time}" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="time"  value="${hint.time}" />
            </div>
            </#if>
            <div class="form-group">
                 <input type="file" class="form-control" name="file" placeholder="Відео - mp4 , Картинки - png, jpg, gif, bpm" />
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group" style="margin-top:20px;">
                <button type="submit" class="btn btn-primary">Редагувати</button>
            </div>
        </form>
    </div>
</#list>
</@c.page>