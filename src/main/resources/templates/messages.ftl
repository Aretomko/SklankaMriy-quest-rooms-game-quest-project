
 <#import "parts/common.ftl" as c>
 <@c.page>
 <#assign b = true>

        <h1>Команда: ${teamName}</h1>
        <table id="tablePreview" class="table table-striped">
          <thead>
            <tr>
              <th>Від</th>
              <th>Text</th>
              <th>Image</th>
            </tr>
          </thead>
          <tbody id="chat">
          <#list messages as message>
            <tr>
                <td><#if message.fromUser == b>User<#else>You</#if></td>
                <td>${message.string}</td>
                <td><#if message.filename??><img style="height:100px; width:150px;" src="/static/uploads/${message.filename}"/><#else> - </#if></td>
            </tr>
          </#list>
          </tbody>
        </table>
            <div class="form-group mt-3">
                <form method="post" action="/admin/addMessage/${code}" enctype="multipart/form-data">
                    <div class="form-group">
                         <input type="text" class="form-control" name="text" placeholder="тект" />
                    </div>
                    <div class="form-group">
                         <input type="file" class="form-control" name="file" placeholder="Картинки - png, jpg, gif, bpm" />
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group" style="margin-top:20px;">
                        <button type="submit" class="btn btn-primary">Надіслати</button>
                    </div>
                </form>
            </div>
               <script>
                    var count = 0;
                        function startTimerTimeLimit(){
                            time = setTimeout(function(){
                            count++;
                            console.log(count);
                            if (count==30){
                                count = 0;
                                showCustomer();
                                console.log("reload");
                            }
                                startTimerTimeLimit();
                            },1000);
                        }

                        function showCustomer() {
                          var xhttp;
                          xhttp = new XMLHttpRequest();
                          xhttp.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                              document.getElementById("chat").innerHTML = this.responseText;
                            }
                          };
                          xhttp.open("GET", "https://sklquest.com/admin/getMessagesRest/${code}", true);
                          xhttp.send();
                        }
               </script>
 </@c.page>