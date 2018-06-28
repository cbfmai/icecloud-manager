package com.mmtech.icecloud.manager.server.sys.service.impl;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.util.SnowFlake;
import com.mmtech.icecloud.manager.server.sys.dao.SysPermissionMapper;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermission;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermissionCriteria;
import com.mmtech.icecloud.manager.server.sys.entity.SysPermissionCriteria.Criteria;
import com.mmtech.icecloud.manager.server.sys.service.SysPermissionService;
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
public class SysPermissionServiceImpl implements SysPermissionService {
    private static final Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

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
        if(!StringUtils.isEmpty(params.getOrDefault("type","").toString()))
            criteria.andTypeEqualTo(params.get("type").toString().trim());
        if(!StringUtils.isEmpty(params.getOrDefault("liketype","").toString()))
            criteria.andTypeLike("%" + params.get("liketype").toString().trim() + "%");
        if(!StringUtils.isEmpty(params.getOrDefault("path","").toString()))
            criteria.andPathEqualTo(params.get("path").toString().trim());
        if(!StringUtils.isEmpty(params.getOrDefault("likepath","").toString()))
            criteria.andPathLike("%" + params.get("likepath").toString().trim() + "%");
        if(!StringUtils.isEmpty(params.getOrDefault("permission","").toString()))
            criteria.andPermissionEqualTo(params.get("permission").toString().trim());
        if(!StringUtils.isEmpty(params.getOrDefault("likepermission","").toString()))
            criteria.andPermissionLike("%" + params.get("likepermission").toString().trim() + "%");
        if(!StringUtils.isEmpty(params.getOrDefault("parentId","").toString()))
            criteria.andParentIdEqualTo(Long.parseLong(params.get("parentId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanparentId","").toString()))
            criteria.andParentIdGreaterThan(Long.parseLong(params.get("greaterThanparentId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanparentId","").toString()))
            criteria.andParentIdLessThan(Long.parseLong(params.get("lessThanparentId").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("parentIds","").toString()))
            criteria.andParentIdsEqualTo(params.get("parentIds").toString().trim());
        if(!StringUtils.isEmpty(params.getOrDefault("likeparentIds","").toString()))
            criteria.andParentIdsLike("%" + params.get("likeparentIds").toString().trim() + "%");
        if(!StringUtils.isEmpty(params.getOrDefault("state","").toString()))
            criteria.andStateEqualTo(Integer.parseInt(params.get("state").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanstate","").toString()))
            criteria.andStateGreaterThan(Integer.parseInt(params.get("greaterThanstate").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanstate","").toString()))
            criteria.andStateLessThan(Integer.parseInt(params.get("lessThanstate").toString()));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThancreatedTime","").toString()))
            criteria.andCreatedTimeGreaterThan(new Date(Long.parseLong(params.get("greaterThancreatedTime").toString()) ));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThancreatedTime","").toString()))
            criteria.andCreatedTimeLessThan(new Date(Long.parseLong(params.get("lessThancreatedTime").toString()) ));
        if(!StringUtils.isEmpty(params.getOrDefault("greaterThanupdatedTime","").toString()))
            criteria.andUpdatedTimeGreaterThan(new Date(Long.parseLong(params.get("greaterThanupdatedTime").toString()) ));
        if(!StringUtils.isEmpty(params.getOrDefault("lessThanupdatedTime","").toString()))
            criteria.andUpdatedTimeLessThan(new Date(Long.parseLong(params.get("lessThanupdatedTime").toString()) ));
    }

    public SysPermission addSysPermission(SysPermission record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(SnowFlake.getId());
        }
        record.setCreatedTime(new Date(System.currentTimeMillis()));
        if (this.sysPermissionMapper.insert(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    public boolean deleteSysPermission(Long id) {
        return this.sysPermissionMapper.deleteByPrimaryKey(id) == 1;
    }

    public SysPermission modifySysPermission(SysPermission record) {
        if (StringUtils.isEmpty(record.getId())) {
            return this.addSysPermission(record);
        }
        if (this.sysPermissionMapper.updateByPrimaryKeySelective(record) == 1) {
            return record;
        } else {
            return null;
        }
    }

    public SysPermission getSysPermission(Long id) {
        return this.sysPermissionMapper.selectByPrimaryKey(id);
    }

    public List<SysPermission> getSysPermissions(Map<String, Object> params) {
        SysPermissionCriteria criteria = new SysPermissionCriteria();
        Criteria cri = criteria.createCriteria();
        setCriteria(cri, params);
        return this.sysPermissionMapper.selectByCondition(criteria);
    }

    public Pager<SysPermission> getPagerSysPermission(Map<String, Object> params) {
        SysPermissionCriteria criteria = new SysPermissionCriteria();
        Pager<SysPermission> pager = new Pager<SysPermission>();
        Criteria cri = criteria.createCriteria();
        if (params.containsKey("offset")) {
            criteria.setOffset(Integer.parseInt(params.get("offset").toString()));
        }
        if (params.containsKey("limit")) {
            criteria.setLimit(Integer.parseInt(params.get("limit").toString()));
        }
        setCriteria(cri, params);
        pager.setData(sysPermissionMapper.selectByCondition(criteria));
        pager.setTotal(sysPermissionMapper.countByCondition(criteria));
        pager.setTotalPage((int) Math.ceil((double) pager.getTotal() / pager.getSize()));
        return pager;
    }

    @Override
    public Pager<SysPermission> getSysPermissionByParentId(Long parentId) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("parentId", parentId);

        return null;
    }
}