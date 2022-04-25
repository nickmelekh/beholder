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
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center" >
                        <span>Цена: ${item.price}</span>
                        <#if item.status="down">
                            <span style="font-size: 1.5em; color: Tomato;">
                                <i class="fas fa-fire"></i>
                            </span>
                        <#else>
                            <i class="far fa-heart"></i>
                        </#if>
                    </a>
                </div>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a href="/main/${item.id}/delete" class="btn">Удалить</a>
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
