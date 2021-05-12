package com.lank.edu.ad.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LanceLan
 * @since 2021/5/12 15:33
 */
@Data
public class PromotionAdDTO {

    private Integer id;

    /**
     * 广告名
     */
    private String name;

    /**
     * 广告位id
     */
    private Integer spaceId;

    /**
     * 精确搜索关键词
     */
    private String keyword;

    /**
     * 静态广告的内容
     */
    private String htmlContent;

    /**
     * 文字一
     */
    private String text;

    /**
     * 链接一
     */
    private String link;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;

    /**
     * 优先级
     */
    private Integer priority;

    private String img;
}
