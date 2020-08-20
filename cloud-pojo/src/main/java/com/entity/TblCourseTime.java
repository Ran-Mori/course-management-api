package com.entity;

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
public class TblCourseTime implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer courseId;

    private Integer beginWeek;

    private Integer endWeek;

    private Integer beginTime;

    private Integer endTime;
}
