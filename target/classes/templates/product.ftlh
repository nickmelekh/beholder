#import "parts/common.ftlh" as c>

<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new product
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-4">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text"
                           class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>"
                           name="text"
                           placeholder="Ввести ссылку" />
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
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

    </div>
</@c.page>
