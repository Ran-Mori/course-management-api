package com.service.impl;

import com.entity.TblUser;
import com.mapper.TblUserMapper;
import com.service.ITblUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-08-20
 */
@Service
public class TblUserServiceImpl extends ServiceImpl<TblUserMapper, TblUser> implements ITblUserService {

}
