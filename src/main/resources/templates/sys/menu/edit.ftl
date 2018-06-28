<div class="row-fluid">
    <div class="col-xs-12">
        <div class="space"></div>
        <form class="form-horizontal" id="editMenuForm">
            <input type="hidden" name="id" value="${(id)!''}"/>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="name">名称</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" class="input-xlarge" id="name" name="name"
                           value="${(name)!''}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="pname">上级菜单</label>
                <div class="col-xs-12 col-sm-8">
                    <select name="parentId" id="parentId" class="selectpicker show-tick" data-live-search="true" >
                        <option value="0">顶级</option>
                        <#list menuTree as item>
                            <option value="${item.id}" <#if (parentId==item.id)!false>selected</#if>>${item.name}</option>
                            <#if item.children??>
                                <#list item.children as citem>
                                <option value="${citem.id}" <#if (parentId==citem.id)!false>selected</#if>>
                                    &nbsp;&nbsp;${citem.name}</option>
                                    <#if citem.children??>
                                        <#list citem.children as c2item>
                                    <option value="${c2item.id}"
                                            <#if (parentId==c2item.id)!false>selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;${c2item.name}</option>
                                        </#list>
                                    </#if>
                                </#list>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="path">URL</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" class="input-xlarge" id="path" name="path" value="${(path)!''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="permission">权限</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" class="input-xlarge" id="permission" name="permission" value="${(permission)!''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="type">类型</label>
                <div class="radio">
                    <label>
                        <input name="type" type="radio" class="ace"
                               <#if (type=='menu')!true>checked="checked"</#if> value="menu"/>
                        <span class="lbl"> 菜单</span>
                    </label>
                    <label>
                        <input name="type" type="radio" class="ace"
                               <#if (type=='button')!false>checked="checked"</#if> value="button"/>
                        <span class="lbl"> 按钮</span>
                    </label>
                    <label>
                        <input name="type" type="radio" class="ace"
                               <#if (type=='rest')!false>checked="checked"</#if> value="rest"/>
                        <span class="lbl"> Rest接口</span>
                    </label>

                </div>
            </div>

        <#--<div class="form-group hidden">
            <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="name">排序</label>
            <div class="col-xs-12 col-sm-8">
                <input type="text" class="input-xlarge" id="name" name="sort"
                       value="${(sort)!''}"/>
            </div>
        </div>-->
        </form>
    </div>
</div>

<script type="text/javascript">
    $('.selectpicker').selectpicker({
        style: 'btn-white',
        width: '270px'
    });
</script>