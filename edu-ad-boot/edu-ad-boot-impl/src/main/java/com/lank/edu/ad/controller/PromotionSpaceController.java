package com.lank.edu.ad.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lank.edu.ad.api.AdRemoteService;
import com.lank.edu.ad.dto.PromotionAdDTO;
import com.lank.edu.ad.dto.PromotionSpaceDTO;
import com.lank.edu.ad.entity.PromotionAd;
import com.lank.edu.ad.entity.PromotionSpace;
import com.lank.edu.ad.service.IPromotionAdService;
import com.lank.edu.ad.service.IPromotionSpaceService;
import com.lank.edu.common.util.ConverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LanceLan
 * @since 2021-05-12
 */
@RestController
@RequestMapping("/ad")
public class PromotionSpaceController implements AdRemoteService {

    @Autowired
    private IPromotionSpaceService iPromotionSpaceService;

    @Autowired
    private IPromotionAdService iPromotionAdService;


    @GetMapping("/space/getAllSpaces")
    @Override
    public List<PromotionSpaceDTO> getAllSpaces() {
        List<PromotionSpace> promotionSpaceList = iPromotionSpaceService.list();
        return ConverUtil.convertList(promotionSpaceList, PromotionSpaceDTO.class);
    }

    @Override
    public List<PromotionSpaceDTO> getAdBySpaceKey(String[] spaceKey) {
        List<PromotionSpace> promotionSpaceList = iPromotionSpaceService.list(
                new QueryWrapper<PromotionSpace>().in("spaceKey", Arrays.asList(spaceKey)));
        List<PromotionSpaceDTO> promotionSpaceDTOList = ConverUtil.convertList(promotionSpaceList, PromotionSpaceDTO.class);
        promotionSpaceDTOList
                .forEach(item -> {
                    item.setPromotionAdDTOList(ConverUtil.convertList(iPromotionAdService.list(
                            new QueryWrapper<PromotionAd>()
                                    .eq("spaceId", item.getId())
                                    .eq("status", 1)
//                                    .lt("startTime", LocalDateTime.now())
//                                    .gt("endTime", LocalDateTime.now())
                    ), PromotionAdDTO.class));
                });
        return promotionSpaceDTOList;
    }
}
