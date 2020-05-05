<#import "parts/common.ftl" as c>

<@c.page>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Додати користувача
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action="/user/admin/addNewUser"">
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Назва користувача" />
            </div>

            <div class="form-group">
                <input type="text" class="form-control" name="password" placeholder="Пароль користувача" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

List of users

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><a href="/user/admin/deleteUser/${user.id}"> delete</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
