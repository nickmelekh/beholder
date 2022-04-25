<#include "security.ftlh">
<#import "pager.ftlh" as p>

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
                <div class="row m-1">
                    <#if item.status??>
                        <a class="col align-self-center">
                            <#if item.status="down">
                                <div>
                                    <span style="font-size: 2em; color: Tomato;">
                                        <i class="fa-solid fa-fire fa-beat-fade"></i>
                                    </span>
                                </div>
                            <#else>
                                <div>
                                    <span style="font-size: 1.5em; color: #000000;">
                                        <i class="fa-solid fa-arrow-trend-up"></i>
                                    </span>
                                </div>
                            </#if>
                        </a>
                    </#if>
                    <a class="col align-self-center">Цена: ${item.price}</a>
                    <a class="col align-self-center" href="/main/${item.id}/delete">
                        <span style="font-size: 2em;">
                            <i class="fas fa-trash-can"></i>
                        </span>
                    </a>
                    <#if item.url??>
                        <a class="btn btn-primary align-self-center" href="${item.url}">В магазин</a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        Пока ничего нет
    </#list>
</div>

<@p.pager url page />
