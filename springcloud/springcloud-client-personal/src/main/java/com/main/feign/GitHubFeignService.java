package com.main.feign;

import com.main.config.GitHubFeignServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  调用远程的GitHub的Http接口(调用的不是本地的服务!)
 */
@FeignClient(name = "github-client", url = "https://api.github.com", configuration = GitHubFeignServiceConfig.class)
public interface GitHubFeignService {

    /**
     * 通过名称查询Github的仓库(开启了GZIP压缩,使用ResponseEntity<byte[]>作为接收)
     * @param queryStr 仓库名称
     */
    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String queryStr);

}
