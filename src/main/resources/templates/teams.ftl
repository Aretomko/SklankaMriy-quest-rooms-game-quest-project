
 <#import "parts/common.ftl" as c>
 <@c.page>
 <script>
 <#assign b = true>
         function myFunction() {
             var r = confirm("Press a button!");
             if (r == true) {
                 document.getElementById('deleteButton').click();
             }
         }
 </script>
        <h1>Список команд</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Назва</th>
              <th>Капітан</th>
              <th>Номер капітана</th>
              <th>Замістник</th>
              <th>Номер замістника</th>
              <th>Кількість гравців</th>
              <th>Дата</th>
              <th>Код команди</th>
              <th>Назва квесту</th>
              <th>Статус</th>
              <th>Статус</th>
              <th>Чат</th>
              <th>Змінити порядок питань</th>
              <th>Активувати гру</th>
              <th>Пауза</th>
              <th>Завершити</th>
              <th>Видалити</th>
            </tr>
          </thead>
          <tbody>
          <#list teams as team>
            <tr>
                <td>${team.teamName}</td>
                <td>${team.capName}</td>
                <td>${team.capNumber}</td>
                <td>${team.secondCapName}</td>
                <td>${team.secondCapNumber}</td>
                <td>${team.numberOfPlayers}</td>
                <td>${team.dateString}</td>
                <td>${team.code}</td>
                <td>${team.getQuest().getName()}</td>
                <td><#if team.getGame()??><#if team.getGame().getFinish() ??>Закінчена<#else>Не закінчена</#if><#else>Не закінчена</#if></td>
                <td><#if team.getGame()??><#if team.getGame().isStarted() ==b><#if team.getGame().isPaused() == b >На паузі<#else>Активована</#if><#else>Не активована</#if><#else>Не активована</#if></td>
                <td><a href="/admin/getMessages/${team.code}"><#if team.isChecked() == b><button type="button" class="btn btn-success">Чат</button><#else><button type="button" class="btn btn-danger">Чат</button></#if></a></td>
                <td><a href="/admin/changeSequence/${team.id}"><button type="button" class="btn btn-warning">Змінити</button></a></td>
                <td><a href="/admin/startGame/${team.id}"><button type="button" class="btn btn-success">Активувати</button></a></td>
                <td><a href="/admin/pause/${team.id}"><button type="button" class="btn btn-warning">Пауза</button></a></td>
                <td><a href="/admin/finishGame/${team.id}"><button type="button" class="btn btn-warning">Завершити</button></a></td>
                <td><a href="/admin/deleteTeam/${team.id}"><button id="deleteButton" style="display:none;" type="button">Видалити</button></a><button onclick="myFunction()" type="button" class="btn btn-danger">Видалити</button></td>
            </tr>
          </#list>
          </tbody>
        </table>
 </@c.page>