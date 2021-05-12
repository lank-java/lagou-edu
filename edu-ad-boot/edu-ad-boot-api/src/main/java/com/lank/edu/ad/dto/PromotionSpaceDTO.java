package com.lank.edu.ad.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LanceLan
 * @since 2021/5/12 11:18
 */
@Data
public class PromotionSpaceDTO {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 广告位key
     */
    private String spaceKey;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDel;

    private List<PromotionAdDTO> promotionAdDTOList;

}
