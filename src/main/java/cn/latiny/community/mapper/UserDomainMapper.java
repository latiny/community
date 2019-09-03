package cn.latiny.community.mapper;

import cn.latiny.community.domain.UserDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDomainMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserDomain record);

    int insertSelective(UserDomain record);

    UserDomain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDomain record);

    int updateByPrimaryKey(UserDomain record);
}