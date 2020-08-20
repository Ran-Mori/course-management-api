package com.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TblUser implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private BigDecimal account;

    private String password;

    private String passwordSalt;


}
