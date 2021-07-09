package com.besscroft.lfs.service.impl;

import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.repository.RoleRepository;
import com.besscroft.lfs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<AuthRole> listAll() {
        return roleRepository.findAll();
    }

}
