package cn.latiny.community.service;

import cn.latiny.community.domain.UserDomain;

/**
 * @author Latiny
 * @version 1.0
 * @description: UserService
 * @date 2019/9/3 10:27
 */
public interface UserService {

    /**
     * 添加
     * @param domain
     */
    void insert(UserDomain domain);
}
