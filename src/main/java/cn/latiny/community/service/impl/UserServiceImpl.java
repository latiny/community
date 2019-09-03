package cn.latiny.community.service.impl;

import cn.latiny.community.domain.UserDomain;
import cn.latiny.community.mapper.UserDomainMapper;
import cn.latiny.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Latiny
 * @version 1.0
 * @description: UserServiceImpl
 * @date 2019/9/3 10:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDomainMapper userDomainMapper;

    @Override
    public void insert(UserDomain domain) {
        userDomainMapper.insertSelective(domain);
    }
}
