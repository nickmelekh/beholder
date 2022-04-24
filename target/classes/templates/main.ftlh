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
                           class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if product??>${product.url}</#if>"
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
                    <button type="submit" class="btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
    <#list products as product>
        <div class="card my-2">
            <#if product.image??>
                <img src="${product.image}" class="card-img-top"/>
            </#if>
            <div class="m-2">
                <#if product.name??>
                    <span>${product.name}</span>
                </#if>
                <#if product.price??>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Цена: ${product.price}</li>
                    </ul>
                </#if>
                <div class="card-footer text-muted">
                    <button type="hide" class="btn">Удалить</button>
                    <#if product.url??>
                        <a href="${product.url}" class="btn btn-primary">В магазин</a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        Пока ничего нет
    </#list>
    </div>
</@c.page>
