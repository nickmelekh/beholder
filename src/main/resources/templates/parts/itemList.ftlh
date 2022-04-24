<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page />

<div class="card-columns" id="message-list">
    <#list page.content as item>
        <div class="card my-3" data-id="${item.id}">
            <#if item.image??>
                <img src="${item.image}" class="card-img-top"/>
            </#if>
            <div class="m-2">
                <#if item.name??>
                    <span>${item.name}</span>
                </#if>
                <#if item.price??>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Цена: ${item.price}</li>
                    </ul>
                </#if>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <button type="hide" class="btn">Удалить</button>
                    <#if item.url??>
                        <a href="${item.url}" class="btn btn-primary">В магазин</a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        Пока ничего нет
    </#list>
</div>

<@p.pager url page />
