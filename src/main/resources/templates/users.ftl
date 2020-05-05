
 <#import "parts/common.ftl" as c>

 <@c.page>
        <h1>Список команд</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
              <th>Ім'я</th>

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
                <td>${team.code}</td>
                <td>${team.getQuest().getName()}</td>
                <td><#if team.getGame()??>Активована<#else>Не активована</#if></td>
                <td><a href="/admin/changeSequence/${team.id}"><button type="button" class="btn btn-warning">Змінити</button></a></td>
                <td><a href="/admin/startGame/${team.id}"><button type="button" class="btn btn-success">Активувати</button></a></td>
                <td><a href="/admin/deleteTeam/${team.id}"><button type="button" class="btn btn-danger">Видалити</button></a></td>
            </tr>
          </#list>
          </tbody>
        </table>
 </@c.page>