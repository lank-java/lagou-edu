package com.lank.edu.user.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lank.edu.user.dto.UserDTO;
import com.lank.edu.user.vo.UserQueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author LanceLan
 * @since 2021/5/13 15:46
 */
@FeignClient(name = "edu-user-boot",path = "/user")
public interface UserRemoteService {

    @PostMapping(value = "/getUserPages")
    Page<UserDTO> getUserPages(@RequestBody UserQueryParam userQueryParam);
}
