<div class="row-fluid">
    <div class="col-xs-12">
        <div class="space"></div>
        <form class="form-horizontal" id="editRoleForm">
            <input type="hidden" name="id" id="id" value="${(sysRole.id)!''}"/>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="group.description">名称</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" class="input-xlarge" id="name" name="name" value="${(sysRole.name)!''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label no-padding-right" for="group.note">描述</label>
                <div class="col-xs-12 col-sm-8">
                    <input type="text" class="input-xlarge" id="desc" name="desc" value="${(sysRole.desc)!''}"/>
                </div>
            </div>
        </form>
    </div>
</div>