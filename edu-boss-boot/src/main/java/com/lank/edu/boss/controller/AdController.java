package com.lank.edu.boss.controller;

import com.lank.edu.ad.api.AdRemoteService;
import com.lank.edu.ad.dto.PromotionSpaceDTO;
import com.lank.edu.common.result.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LanceLan
 * @since 2021/5/12 16:20
 */
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdRemoteService adRemoteService;

    @GetMapping("/space/getAllSpaces")
    public ResponseDTO<List<PromotionSpaceDTO>> getAllSpace() throws Exception{
        List<PromotionSpaceDTO> dtoList = adRemoteService.getAllSpaces();
        return ResponseDTO.success(dtoList);
    }
}
