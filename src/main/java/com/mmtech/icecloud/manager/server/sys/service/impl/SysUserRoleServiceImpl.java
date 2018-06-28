package com.mmtech.icecloud.manager.server.sys.service.impl;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.entity.enums.StatusEnum;
import com.mmtech.icecloud.manager.common.util.SnowFlake;
import com.mmtech.icecloud.manager.server.sys.dao.SysUserRoleMapper;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserRoleCriteria;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserRoleCriteria.Criteria;
import com.mmtech.icecloud.manager.server.sys.service.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    private void setCriteria(Criteria criteria, Map<String, Object> params) {
        if (!StringUtils.isEmpty(params.getOrDefault("id", "").toString()))
            criteria.andIdEqualTo(Long.parseLong(params.get("id").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThanid", "").toString()))
            criteria.andIdGreaterThan(Long.parseLong(params.get("greaterThanid").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThanid", "").toString()))
            criteria.andIdLessThan(Long.parseLong(params.get("lessThanid").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("userId", "").toString()))
            criteria.andUserIdEqualTo(Long.parseLong(params.get("userId").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThanuserId", "").toString()))
            criteria.andUserIdGreaterThan(Long.parseLong(params.get("greaterThanuserId").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThanuserId", "").toString()))
            criteria.andUserIdLessThan(Long.parseLong(params.get("lessThanuserId").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("roleId", "").toString()))
            criteria.andRoleIdEqualTo(Long.parseLong(params.get("roleId").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThanroleId", "").toString()))
            criteria.andRoleIdGreaterThan(Long.parseLong(params.get("greaterThanroleId").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThanroleId", "").toString()))
            criteria.andRoleIdLessThan(Long.parseLong(params.get("lessThanroleId").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("status", "").toString()))
            criteria.andStatusEqualTo(Integer.parseInt(params.get("status").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThanstatus", "").toString()))
            criteria.andStatusGreaterThan(Integer.parseInt(params.get("greaterThanstatus").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThanstatus", "").toString()))
            criteria.andStatusLessThan(Integer.parseInt(params.get("lessThanstatus").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThancreatedTime", "").toString()))
            criteria.andCreatedTimeGreaterThan(new Date(Long.parseLong(params.get("greaterThancreatedTime").toString())));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThancreatedTime", "").toString()))
            criteria.andCreatedTimeLessThan(new Date(Long.parseLong(params.get("lessThancreatedTime").toString())));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThanupdatedTime", "").toString()))
            criteria.andUpdatedTimeGreaterThan(new Date(Long.parseLong(params.get("greaterThanupdatedTime").toString())));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThanupdatedTime", "").toString()))
            criteria.andUpdatedTimeLessThan(new Date(Long.parseLong(params.get("lessThanupdatedTime").toString())));
    }

    @Override
    public SysUserRole addSysUserRole(SysUserRole record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(SnowFlake.getId());
        }
        if (this.sysUserRoleMapper.insert(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    @Override
    public int saveUserRole(Long userId, Long roleId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(SnowFlake.getId());
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setStatus(StatusEnum.ON.getCode());
        sysUserRole.setCreatedTime(new Date());
        sysUserRole.setUpdatedTime(new Date());
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public boolean deleteSysUserRole(Long id) {
        return this.sysUserRoleMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public SysUserRole modifySysUserRole(SysUserRole record) {
        if (StringUtils.isEmpty(record.getId())) {
            return this.addSysUserRole(record);
        }
        if (this.sysUserRoleMapper.updateByPrimaryKeySelective(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    @Override
    public SysUserRole getSysUserRole(Long id) {
        return this.sysUserRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUserRole> getSysUserRoleByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", userId);
        return this.getSysUserRoles(queryMap);
    }

    @Override
    public List<SysUserRole> getSysUserRoles(Map<String, Object> params) {
        SysUserRoleCriteria criteria = new SysUserRoleCriteria();
        Criteria cri = criteria.createCriteria();
        setCriteria(cri, params);
        return this.sysUserRoleMapper.selectByCondition(criteria);
    }

    @Override
    public Pager<SysUserRole> getPagerSysUserRole(Map<String, Object> params) {
        SysUserRoleCriteria criteria = new SysUserRoleCriteria();
        Pager<SysUserRole> pager = new Pager<SysUserRole>();
        Criteria cri = criteria.createCriteria();
        if (params.containsKey("offset")) {
            criteria.setOffset(Integer.parseInt(params.get("offset").toString()));
        }
        if (params.containsKey("limit")) {
            criteria.setLimit(Integer.parseInt(params.get("limit").toString()));
        }
        setCriteria(cri, params);
        pager.setData(sysUserRoleMapper.selectByCondition(criteria));
        pager.setTotal(sysUserRoleMapper.countByCondition(criteria));
        pager.setTotalPage((int) Math.ceil((double) pager.getTotal() / pager.getSize()));
        return pager;
    }
}