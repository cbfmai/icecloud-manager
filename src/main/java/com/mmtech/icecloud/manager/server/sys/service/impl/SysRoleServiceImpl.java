package com.mmtech.icecloud.manager.server.sys.service.impl;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.entity.enums.StatusEnum;
import com.mmtech.icecloud.manager.common.util.SnowFlake;
import com.mmtech.icecloud.manager.server.sys.dao.SysPermissionMapper;
import com.mmtech.icecloud.manager.server.sys.dao.SysRoleMapper;
import com.mmtech.icecloud.manager.server.sys.dao.SysRolePermissionMapper;
import com.mmtech.icecloud.manager.server.sys.entity.*;
import com.mmtech.icecloud.manager.server.sys.entity.SysRoleCriteria.Criteria;
import com.mmtech.icecloud.manager.server.sys.service.SysRolePermissionService;
import com.mmtech.icecloud.manager.server.sys.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    private void setCriteria(Criteria criteria, Map<String, Object> params) {
        if(!StringUtils.isEmpty(params.getOrDefault("id","").toString()))
            criteria.andIdEqualTo(Long.parseLong(params.get("id").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanid","").toString()))
            criteria.andIdGreaterThan(Long.parseLong(params.get("greaterThanid").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanid","").toString()))
            criteria.andIdLessThan(Long.parseLong(params.get("lessThanid").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("name","").toString()))
            criteria.andNameEqualTo(params.get("name").toString().trim());
        if(!StringUtils.isEmpty(params.getOrDefault("likename","").toString()))
            criteria.andNameLike("%" + params.get("likename").toString().trim() + "%");
        if(!StringUtils.isEmpty(params.getOrDefault("desc","").toString()))
            criteria.andDescEqualTo(params.get("desc").toString().trim());
        if(!StringUtils.isEmpty(params.getOrDefault("likedesc","").toString()))
            criteria.andDescLike("%" + params.get("likedesc").toString().trim() + "%");
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
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanupdatedTime","").toString()))
            criteria.andUpdatedTimeGreaterThan(new Date(Long.parseLong(params.get("greaterThanupdatedTime").toString()) ));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanupdatedTime","").toString()))
            criteria.andUpdatedTimeLessThan(new Date(Long.parseLong(params.get("lessThanupdatedTime").toString()) ));
    }

    @Override
    public SysRole addSysRole(SysRole record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(SnowFlake.getId());
        }
        record.setCreatedTime(new Date(System.currentTimeMillis()));
        record.setUpdatedTime(new Date(System.currentTimeMillis()));
        if (this.sysRoleMapper.insert(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteSysRole(Long id) {
        return this.sysRoleMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public SysRole modifySysRole(SysRole record) {
        if (StringUtils.isEmpty(record.getId())) {
            return this.addSysRole(record);
        }
        record.setUpdatedTime(new Date());
        if (this.sysRoleMapper.updateByPrimaryKeySelective(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    @Override
    public SysRole getSysRole(Long id) {
        return this.sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> getSysRoles(Map<String, Object> params) {
        SysRoleCriteria criteria = new SysRoleCriteria();
        Criteria cri = criteria.createCriteria();
        setCriteria(cri, params);
        return this.sysRoleMapper.selectByCondition(criteria);
    }

    @Override
    public Pager<SysRole> getPagerSysRole(Map<String, Object> params) {
        SysRoleCriteria criteria = new SysRoleCriteria();
        Pager<SysRole> pager = new Pager<SysRole>();
        Criteria cri = criteria.createCriteria();
        if (params.containsKey("offset")) {
            criteria.setOffset(Integer.parseInt(params.get("offset").toString()));
        }
        if (params.containsKey("limit")) {
            criteria.setLimit(Integer.parseInt(params.get("limit").toString()));
        }
        setCriteria(cri, params);
        pager.setData(sysRoleMapper.selectByCondition(criteria));
        pager.setTotal(sysRoleMapper.countByCondition(criteria));
        pager.setTotalPage((int) Math.ceil((double) pager.getTotal() / pager.getSize()));
        return pager;
    }

    @Override
    public List<SysPermission> getSysPermissionListByRoleId(Long roleId) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("roleId", roleId);
        queryMap.put("status", StatusEnum.OFF.getCode());
        List<SysRolePermission> rolePermissionList = sysRolePermissionService.getSysRolePermissions(queryMap);
        List<SysPermission> sysPermissionList = new ArrayList<>();
        if (rolePermissionList != null && rolePermissionList.size() > 0) {
            for (SysRolePermission sysRolePermission : rolePermissionList) {
                sysPermissionList.add(sysPermissionMapper.selectByPrimaryKey(sysRolePermission.getPermissionId()));
            }
        }
        return sysPermissionList;
    }

    @Override
    public boolean updatePermissionListByRoleId(Long roleId, String addIds, String deletedIds) {
        if (!StringUtils.isEmpty(addIds)) {
            String[] permissionIds = addIds.split(",");
            for (String permissionId : permissionIds) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setId(SnowFlake.getId());
                sysRolePermission.setRoleId(roleId);
                sysRolePermission.setStatus(1);
                sysRolePermission.setPermissionId(Long.parseLong(permissionId));
                sysRolePermissionService.addSysRolePermission(sysRolePermission);
            }
        }

        if (!StringUtils.isEmpty(deletedIds)) {
            String[] needDelIdsArray = deletedIds.split(",");
            SysRolePermission sysRolePermission = new SysRolePermission();
            SysRolePermissionCriteria criteria = new SysRolePermissionCriteria();
            for (String permissionId : needDelIdsArray) {
                sysRolePermission.setStatus(0);
                criteria.clear();
                criteria.createCriteria().andPermissionIdEqualTo(Long.parseLong(permissionId)).andRoleIdEqualTo(roleId);
                sysRolePermissionMapper.updateByConditionSelective(sysRolePermission, criteria);
            }

        }
        return true;
    }
}