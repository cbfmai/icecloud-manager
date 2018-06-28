package com.mmtech.icecloud.manager.server.sys.service.impl;

import com.mmtech.icecloud.manager.common.entity.Pager;
import com.mmtech.icecloud.manager.common.entity.enums.StatusEnum;
import com.mmtech.icecloud.manager.common.util.ListUtils;
import com.mmtech.icecloud.manager.common.util.SnowFlake;
import com.mmtech.icecloud.manager.common.HttpStatus;
import com.mmtech.icecloud.manager.common.ShiroMd5Util;
import com.mmtech.icecloud.manager.exception.BusinessException;
import com.mmtech.icecloud.manager.exception.SystemException;
import com.mmtech.icecloud.manager.server.sys.dao.SysRoleMapper;
import com.mmtech.icecloud.manager.server.sys.dao.SysUserMapper;
import com.mmtech.icecloud.manager.server.sys.entity.SysRole;
import com.mmtech.icecloud.manager.server.sys.entity.SysUser;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserCriteria;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserCriteria.Criteria;
import com.mmtech.icecloud.manager.server.sys.entity.SysUserRole;
import com.mmtech.icecloud.manager.server.sys.service.SysUserRoleService;
import com.mmtech.icecloud.manager.server.sys.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    private void setCriteria(Criteria criteria, Map<String, Object> params) {
        if (!StringUtils.isEmpty(params.getOrDefault("id", "").toString()))
            criteria.andIdEqualTo(Long.parseLong(params.get("id").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThanid", "").toString()))
            criteria.andIdGreaterThan(Long.parseLong(params.get("greaterThanid").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThanid", "").toString()))
            criteria.andIdLessThan(Long.parseLong(params.get("lessThanid").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("username", "").toString()))
            criteria.andUsernameEqualTo(params.get("username").toString().trim());
        if (!StringUtils.isEmpty(params.getOrDefault("likeusername", "").toString()))
            criteria.andUsernameLike("%" + params.get("likeusername").toString().trim() + "%");
        if (!StringUtils.isEmpty(params.getOrDefault("password", "").toString()))
            criteria.andPasswordEqualTo(params.get("password").toString().trim());
        if (!StringUtils.isEmpty(params.getOrDefault("likepassword", "").toString()))
            criteria.andPasswordLike("%" + params.get("likepassword").toString().trim() + "%");
        if (!StringUtils.isEmpty(params.getOrDefault("realname", "").toString()))
            criteria.andRealnameEqualTo(params.get("realname").toString().trim());
        if (!StringUtils.isEmpty(params.getOrDefault("likerealname", "").toString()))
            criteria.andRealnameLike("%" + params.get("likerealname").toString().trim() + "%");
        if (!StringUtils.isEmpty(params.getOrDefault("gender", "").toString()))
            criteria.andGenderEqualTo(Integer.parseInt(params.get("gender").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("greaterThangender", "").toString()))
            criteria.andGenderGreaterThan(Integer.parseInt(params.get("greaterThangender").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("lessThangender", "").toString()))
            criteria.andGenderLessThan(Integer.parseInt(params.get("lessThangender").toString()));
        if (!StringUtils.isEmpty(params.getOrDefault("mobile", "").toString()))
            criteria.andMobileEqualTo(params.get("mobile").toString().trim());
        if (!StringUtils.isEmpty(params.getOrDefault("likemobile", "").toString()))
            criteria.andMobileLike("%" + params.get("likemobile").toString().trim() + "%");
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
    public SysUser addSysUser(SysUser record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(SnowFlake.getId());
        }
        record.setPassword(DigestUtils.md5Hex(record.getPassword()));
        record.setCreatedTime(new Date(System.currentTimeMillis()));
        record.setUpdatedTime(new Date(System.currentTimeMillis()));
        record.setStatus(1);
        if (sysUserMapper.insert(record) == 1) {
            List<Long> roleIds = record.getRoleIds();
            int count = 0;
            if (roleIds != null && roleIds.size() > 0) {
                for (Long roleId : roleIds) {
                    int success = sysUserRoleService.saveUserRole(record.getId(), roleId);
                    count += success;
                }
            }
            // 没有保存成功
            if (count != roleIds.size()) {
                throw SystemException.of(HttpStatus.DB_UPDATE_FAIL);
            }
            return record;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteSysUser(Long id) {
        return this.sysUserMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public SysUser modifySysUser(SysUser record, boolean modifyRole) {
        if (StringUtils.isEmpty(record.getId())) {
            return this.addSysUser(record);
        }
        record.setUpdatedTime(new Date());
        if (this.sysUserMapper.updateByPrimaryKeySelective(record) == 1) {

            if (modifyRole) {
                List<Long> roleIds = record.getRoleIds();
                Long userId = record.getId();
                if (ListUtils.isNotEmptyList(roleIds)) {
                    for (Long roleId : roleIds) {
                        sysUserRoleService.saveUserRole(userId, roleId);
                    }
                }

                List<SysUserRole> existingRoleList = sysUserRoleService.getSysUserRoleByUserId(record.getId());
                if (ListUtils.isNotEmptyList(existingRoleList)) {
                    for (SysUserRole sysUserRole : existingRoleList) {
                        sysUserRole.setStatus(StatusEnum.OFF.getCode());
                        sysUserRoleService.modifySysUserRole(sysUserRole);
                    }
                }
            }
            return record;
        } else {
            throw SystemException.of(HttpStatus.DB_UPDATE_FAIL);
        }
    }


    @Override
    public SysUser resetPassword(Long userId, String password) {
        if (userId == null || password == null) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        SysUser sysUser = getSysUser(userId);
        sysUser.setPassword(ShiroMd5Util.md5(password));

        if (1 == sysUserMapper.updateByPrimaryKeySelective(sysUser)) {
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser getSysUser(Long id) {
        SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(id);
        List<SysRole> userRoleList = this.getRoleListById(sysUser.getId());
        sysUser.setRoleList(userRoleList);
        if (!userRoleList.isEmpty()) {
            List<Long> roleIds = userRoleList.stream().map(x -> x.getId()).collect(Collectors.toList());
            sysUser.setRoleIds(roleIds);
        }
        return sysUser;
    }

    @Override
    public List<SysUser> getSysUsers(Map<String, Object> params) {
        SysUserCriteria criteria = new SysUserCriteria();
        Criteria cri = criteria.createCriteria();
        setCriteria(cri, params);
        return this.sysUserMapper.selectByCondition(criteria);
    }

    @Override
    public Pager<SysUser> getPagerSysUser(Map<String, Object> params) {
        SysUserCriteria criteria = new SysUserCriteria();
        Pager<SysUser> pager = new Pager<SysUser>();
        Criteria cri = criteria.createCriteria();
        if (params.containsKey("offset")) {
            criteria.setOffset(Integer.parseInt(params.get("offset").toString()));
        }
        if (params.containsKey("limit")) {
            criteria.setLimit(Integer.parseInt(params.get("limit").toString()));
        }
        setCriteria(cri, params);
        pager.setData(sysUserMapper.selectByCondition(criteria));
        pager.setTotal(sysUserMapper.countByCondition(criteria));
        pager.setTotalPage((int) Math.ceil((double) pager.getTotal() / pager.getSize()));
        return pager;
    }

    @Override
    public List<SysRole> getRoleListById(Long id) {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", id);
        queryMap.put("status", StatusEnum.ON.getCode());
        List<SysUserRole> userRoleList = sysUserRoleService.getSysUserRoles(queryMap);

        List<SysRole> sysRoleList = new ArrayList<>();
        if (userRoleList != null && userRoleList.size() > 0) {

            for (SysUserRole sysUserRole : userRoleList) {
                sysRoleList.add(sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId()));
            }
        }
        return sysRoleList;
    }

    @Override
    public SysUser findByUsername(String username) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("username", username);
        List<SysUser> sysUserList = getSysUsers(queryMap);

        if (sysUserList != null && sysUserList.size() > 0) {
            return sysUserList.get(0);
        }
        return null;
    }

    @Override
    public SysUser selectSysUserByUsername(String username) {
        return sysUserMapper.selectSysUserByUsername(username);
    }
}