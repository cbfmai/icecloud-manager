<div class="row-fluid">
    <div class="col-xs-12">
        <div class="space"></div>
        <form class="form-horizontal" id="sys_user_form">
            <input type="hidden" name="id" value="${(sysUser.id)!''}"/>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="username">用户名：</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" autocomplete="off" class="input-xlarge" id="username" name="username"
                           value="${(sysUser.username)!''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="realname">真实姓名：</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" autocomplete="off" class="input-xlarge" id="realname" name="realname"
                           value="${(sysUser.realname)!''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="mobile">手机</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" class="input-xlarge" id="mobile" name="mobile" value="${(sysUser.mobile)!''}">
                </div>
            </div>
            <#if !sysUser??>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="password">密码：</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="password" autocomplete="new-password" class="input-xlarge" id="password"
                           name="password"/>
                    <span class="help-block inline">（用户密码不能少于6位,修改时保留为空,则表示不修改!） </span>
                </div>
            </div>
            </#if>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right">性别：</label>
                <div class="radio">
                    <label>
                        <input name="gender" type="radio" class="ace"
                               <#if (sysUser.gender==1)??>checked="checked"</#if> value="1"/>
                        <span class="lbl">男</span>
                    </label>
                    <label>
                        <input name="gender" type="radio" class="ace"
                               <#if (sysUser.gender==0)??>checked="checked"</#if> value="0"/>
                        <span class="lbl">女</span>
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right">选择角色： </label>
                <div class="col-xs-12 col-sm-8">
                    <select name="roleIds" id="roleIds" class="selectpicker show-tick" data-live-search="true" multiple>
                        <#list sysRoleList as role>
                            <option value="${role.id}"
                                <#if sysUser??><#if sysUser.roleIds??>
                                    <#list sysUser.roleIds as roleId >
                                         <#if roleId == role.id>selected="selected"</#if>
                                    </#list>
                                </#if></#if>>${role.desc}</option>
                        </#list>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $('.selectpicker').selectpicker({
        style: 'btn-white',
        width: '270px'
    });
</script>