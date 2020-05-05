<#import "parts/common.ftl" as c>

<@c.page>
<#list pages as page>
<div class="form-group mt-3">
        <form method="post" action="/admin/updatePageConfirm/${page.id}">
            <#if numberError??>
            <div class="form-group">
                 <label for="lname" style="color: red;">${numberError}</label>
                 <input type="text" class="form-control" name="number" value="${page.orderNumber}" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="number" value="${page.orderNumber}" />
            </div>
            </#if>
            <#if timeError??>
            <div class="form-group">
                 <label for="lname" style="color: red;">${timeError}</label>
                 <input type="text" class="form-control" name="time" placeholder="Максимальний час на відповідь" />
            </div>
            <#else>
            <div class="form-group">
                 <input type="text" class="form-control" name="time" value="${page.time}" />
            </div>
            </#if>
            <input type="text" class="form-control" name="name" value="${page.name}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Редагувати</button>
            </div>
        </form>
    </div>
</#list>
</@c.page>