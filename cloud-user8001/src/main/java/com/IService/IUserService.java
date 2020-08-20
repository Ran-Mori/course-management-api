package com.IService;

import com.entity.TblUser;


public interface IUserService {
    int insertUser(TblUser user);
    TblUser selectUserByAccount(String account);
}
