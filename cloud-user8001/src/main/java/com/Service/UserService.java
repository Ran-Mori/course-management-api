package com.Service;

import com.IService.IUserService;
import com.entity.TblUser;
import com.mapper.TblUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    TblUserMapper userMapper;

    @Override
    public void insertUser(TblUser user) {
        userMapper.insert(user);
    }
}
