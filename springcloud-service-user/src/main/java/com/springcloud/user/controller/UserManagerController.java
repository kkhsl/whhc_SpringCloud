package com.springcloud.user.controller;

import com.hkk.springcloud.model.User;
import com.hkk.springcloud.service.RoleRemoteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.user.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@RequestMapping("/user")
public class    UserManagerController {

    // 读取远程配置中心信息
    @Value("${spring.datasource.url}")
    private String datasource_url;

//    @Value("${info.address}")
    private String test;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IUserService userService;

    // 产品服务的接口地址 直连
    private static final String GOODS_SERVICE_ROLE1 = "http://127.0.0.1:8012/admin/role/list";

    // 产品服务的接口地址 注册中心（服务名）
    private static final String GOODS_SERVICE_ROLE2 = "http://spring-cloud-role-service/admin/role/user/key";

    // Feign远程调用客户端
    @Autowired
    private RoleRemoteService roleRemoteService;

    @RequestMapping("/get")
    public Object get(){
        return "Test";
    }

    @ApiOperation("RestTemplate + Ribbon远程调用")
    @GetMapping("/cloud/get/")
    private Object list() {
        User user = this.userService.selectPrimaryKey(Long.parseLong("1"));
        if (user != null) {
           /* ResponseEntity<Role> response = this.restTemplate.getForEntity(GOODS_SERVICE_ROLE1, Role.class);
            Role role = response.getBody();*/
            HttpHeaders headers = new HttpHeaders();

            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("id", "1");

            //HttpEntity
            HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, headers);

           /* ResponseEntity<Role> response = this.restTemplate.postForEntity("http://127.0.0.1:8012/admin/role/user/key?id=1",
                    requestEntity, Role.class);*/

            ResponseEntity<String> response = this.restTemplate.postForEntity(GOODS_SERVICE_ROLE2,
                    requestEntity, String.class);
            user.setRoles(response.getBody());
            return user;
        }
        return user;
    }


    @RequestMapping(value = "/cloud/feign/list", method = RequestMethod.GET)
    public Object cloudFeign(String id){
        // 调用远程Controller, restful的调用
        return roleRemoteService.getRoles(id);
    }

    /**
     *
     * title：
     * 超时设置
     *
     * 限流
     * @HystrixProperty(name = "coreSize", value = "2"),
     * @HystrixProperty(name = "maxQueueSize", value = "1")
     *      默认超时时间为1000毫秒
     *      比较ribbon的ReadTimeout,取小值
     * 使用Feign 远程接口调用
     * 抛出异常到前端：ignoreException:忽略异常
     *
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "fallback")
    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
    },ignoreExceptions = Throwable.class,
            threadPoolKey = "goods",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "1")
            }
    )
    @RequestMapping(value = "/cloud/hystrix/list", method = RequestMethod.GET)
    public Object cloudFeignHystrix(String id){
        // 调用远程Controller, restful的调用

        // 测试Hystrix 接口超时
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return roleRemoteService.getRoles(id);
    }


    /**
     * 降级方法:默认超时时间为1000毫秒
     *目标方法和回退方法 参数、返回值保持一致
     * 注：LK 可在降级服务方法中调用本地服务，查询本地数据库
     *  本地数据库，关联远程数据库学校id、以及直播间id，直播节目id;
     *  远程数据库记录本地数据库直播id，直播节目id
     * 作用：
     *  监听接口超时
     *  监听异常（避免线程累计、服务宕机）
     *  监听并发请求
     * @return
     */
    public Object fallback(){
        return "服务降级：接口超时";
    }

//    public Object fallback(String id){
//        return "服务降级：接口超时";
//    }+


    @RequestMapping("/springcloud/config/annotation")
    public Object spring_cloud_config_annotation(){
        return datasource_url + test;
    }
}
