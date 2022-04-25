<#import "parts/common.ftlh" as c>

<@c.page>
<h5>Добро пожаловать</h5>
<div>Здесь ты можешь добавить интересующие тебя товары и следить за их ценой</div>
<div>Чтобы добавить товар нажми <a href="/main">сюда</a></div>
<#if !user??>
    <a href="/login">Войти</a>
    <a href="/registration">Регистрация</a>
</#if>
</@c.page>