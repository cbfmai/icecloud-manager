package com.mmtech.icecloud.manager.server.sys.service.impl;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.util.SnowFlake;
import com.mmtech.icecloud.manager.server.sys.dao.SysRolePermissionMapper;
import com.mmtech.icecloud.manager.server.sys.entity.SysRolePermission;
import com.mmtech.icecloud.manager.server.sys.entity.SysRolePermissionCriteria;
import com.mmtech.icecloud.manager.server.sys.entity.SysRolePermissionCriteria.Criteria;
import com.mmtech.icecloud.manager.server.sys.service.SysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    private static final Logger logger = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    private void setCriteria(Criteria criteria, Map<String, Object> params) {
        if(!StringUtils.isEmpty(params.getOrDefault("id","").toString()))
            criteria.andIdEqualTo(Long.parseLong(params.get("id").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanid","").toString()))
            criteria.andIdGreaterThan(Long.parseLong(params.get("greaterThanid").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanid","").toString()))
            criteria.andIdLessThan(Long.parseLong(params.get("lessThanid").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("roleId","").toString()))
            criteria.andRoleIdEqualTo(Long.parseLong(params.get("roleId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanroleId","").toString()))
            criteria.andRoleIdGreaterThan(Long.parseLong(params.get("greaterThanroleId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanroleId","").toString()))
            criteria.andRoleIdLessThan(Long.parseLong(params.get("lessThanroleId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("permissionId","").toString()))
            criteria.andPermissionIdEqualTo(Long.parseLong(params.get("permissionId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanpermissionId","").toString()))
            criteria.andPermissionIdGreaterThan(Long.parseLong(params.get("greaterThanpermissionId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanpermissionId","").toString()))
            criteria.andPermissionIdLessThan(Long.parseLong(params.get("lessThanpermissionId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("status","").toString()))
            criteria.andStatusEqualTo(Integer.parseInt(params.get("status").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanstatus","").toString()))
            criteria.andStatusGreaterThan(Integer.parseInt(params.get("greaterThanstatus").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanstatus","").toString()))
            criteria.andStatusLessThan(Integer.parseInt(params.get("lessThanstatus").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThancreatedTime","").toString()))
            criteria.andCreatedTimeGreaterThan(new Date(Long.parseLong(params.get("greaterThancreatedTime").toString()) ));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThancreatedTime","").toString()))
            criteria.andCreatedTimeLessThan(new Date(Long.parseLong(params.get("lessThancreatedTime").toString()) ));
    }

    public SysRolePermission addSysRolePermission(SysRolePermission record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(SnowFlake.getId());
        }
        if (this.sysRolePermissionMapper.insert(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    public boolean deleteSysRolePermission(Long id) {
        return this.sysRolePermissionMapper.deleteByPrimaryKey(id) == 1;
    }

    public SysRolePermission modifySysRolePermission(SysRolePermission record) {
        if (StringUtils.isEmpty(record.getId())) {
            return this.addSysRolePermission(record);
        }
        if (this.sysRolePermissionMapper.updateByPrimaryKeySelective(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    public SysRolePermission getSysRolePermission(Long id) {
        return this.sysRolePermissionMapper.selectByPrimaryKey(id);
    }

    public List<SysRolePermission> getSysRolePermissions(Map<String, Object> params) {
        SysRolePermissionCriteria criteria = new SysRolePermissionCriteria();
        Criteria cri = criteria.createCriteria();
        setCriteria(cri, params);
        return this.sysRolePermissionMapper.selectByCondition(criteria);
    }

    public Pager<SysRolePermission> getPagerSysRolePermission(Map<String, Object> params) {
        SysRolePermissionCriteria criteria = new SysRolePermissionCriteria();
        Pager<SysRolePermission> pager = new Pager<SysRolePermission>();
        Criteria cri = criteria.createCriteria();
        if (params.containsKey("offset")) {
            int offset = Integer.parseInt(params.get("offset").toString());
            int limit = Integer.parseInt(params.get("limit").toString());
            pager.setCurrentPage(offset / limit + 1);
        }
        if (params.containsKey("limit")) {
            pager.setSize(Integer.parseInt(params.get("limit").toString()));
        }
        setCriteria(cri, params);
        pager.setData(sysRolePermissionMapper.selectByCondition(criteria));
        pager.setTotal(sysRolePermissionMapper.countByCondition(criteria));
        pager.setTotalPage((int) Math.ceil((double) pager.getTotal() / pager.getSize()));
        return pager;
    }
}