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
        <h1>Заявки на квести</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Ім'я</th>
              <th>Номер</th>
              <th>Пошта</th>
              <th>Дата</th>
              <th>Час</th>
              <th>Видалення</th>
            </tr>
          </thead>
          <tbody>
          <#list applications as application>
            <tr>
                <td>${application.name}</td>
                <td>${application.number}</td>
                <td>${application.mail}</td>
                <td>${application.date}</td>
                <td>${application.time}</td>
                <td><a href="/admin/deleteApplication/${application.id}"><button id="deleteButton" style="display:none;" type="button">Видалити</button></a><button onclick="myFunction()" type="button" class="btn btn-danger">Видалити</button></td>
            </tr>
          </#list>
          </tbody>
        </table>
 </@c.page>