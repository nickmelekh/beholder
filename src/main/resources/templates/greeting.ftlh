<#import "parts/common.ftlh" as c>

<@c.page>
<h5>Добро пожаловать</h5>
<div>Для добавления перейди на вкладку добавления</div>
    <#if user??>
    <#else>
        <a href="/login">Войти</a>
        <a href="/registration">Регистрация</a>
    </#if>
</@c.page>