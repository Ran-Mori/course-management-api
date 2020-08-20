package com.Service;

import com.IService.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.TblUser;
import com.mapper.TblUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Service
public class UserService implements IUserService {
    @Autowired
    TblUserMapper userMapper;

    @Override
    public int insertUser(TblUser user) {
        int i = userMapper.insert(user);
        return i;
    }

    @Override
    public TblUser selectUserByAccount(String account) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("account",account);
        TblUser user = userMapper.selectOne(wrapper);
        return user;
    }


}
