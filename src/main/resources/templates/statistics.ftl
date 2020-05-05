<#import "parts/common.ftl" as c>

<@c.page>
<a class="btn btn-primary" href="/admin/quests">
    < Назад
</a>
      <#list quests as quest>
        <h1>${quest.name}</h1>
        <table id="tablePreview" class="table table-striped">
        <!--Table head-->
          <thead>
            <tr>
                <th>Команда</th>
                <th>Старт</th>
                <#list pages as page>
                <th>${page.name}</th>
                </#list>
                <th>Финиш</th>
                <th>Сумма часу</th>
                <th>Пауза</th>
            </tr>
          </thead>
          <tbody>
          <#if quest.getTeams()??>
           <#list quest.getTeams() as team>
            <tr>
                <td>${team.teamName}</td>
                <td><#if team.getGame()??><#if team.getGame().getStart() ??>${team.getGame().getStart() }<#else>Не почата</#if><#else>Не розпочата</#if></td>
                <#if team.getGame()??><#if team.getGame().getPageGames()??>
                <#list pages as page>
                    <#list team.getGame().sortedPages() as pageGame>
                        <#if page.name == pageGame.name><td>${pageGame.answerTime}</td></#if>
                    </#list>
                </#list>
                <#else>
                <td>Гра ще не почалася</td>
                </#if>
                </#if>
                <td><#if team.getGame()??><#if team.getGame().getFinish() ??>${team.getGame().getFinish()}<#else>Не закінчена</#if></#if></td>
                <td><#if team.getGame()??><#if team.getGame().getSum() ??>${team.getGame().getSum()}<#else>Не закінчкна</#if></#if></td>
                <td><#if team.getGame()??><#if team.getGame().getPause() ??>${team.getGame().getPause()}<#else> - </#if></#if></td>
            </tr>
            </#list>
           <#else>
           <tr>
            <td>
               Поки немає зареєстрованих команд
            </td>
           </tr>
           </#if>
          </tbody>
        </table>
       </#list>
</@c.page>