package com.lank.edu.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LanceLan
 * @since 2021/5/13 15:49
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserQueryParam {

    private Integer currentPage;
    private Integer pageSize;
    private String phone;
    private Integer userId;
    private Date startCreateTime;
    private Date endCreateTime;
}
