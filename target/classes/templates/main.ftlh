<#import "parts/common.ftlh" as c>

<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить
    </a>
    <div class="collapse <#if product??>show</#if>" id="collapseExample">
        <div class="form-group mt-4">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text"
                           class="form-control ${(urlError??)?string('is-invalid', '')}"
                           name="url"
                           placeholder="Введите ссылку" />
                    <#if urlError??>
                        <div class="invalid-feedback">
                            ${urlError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn-primary">Готово</button>
                </div>
            </form>
        </div>
    </div>

    <#include "parts/itemList.ftlh"/>

    <div class="row m-1">
        <a class="col">
            <div>
            <span style="font-size: 1.5em; color: Tomato;">
                <i class="fa-solid fa-fire fa-beat-fade"></i>
            </span>
                <text> - стало дешевле</text>
            </div>
            <div>
            <span style="font-size: 1.5em; color: #000000;">
                <i class="fa-solid fa-arrow-trend-up"></i>
            </span>
                <text> - стало дороже</text>
            </div>
        </a>
    </div>
</@c.page>
