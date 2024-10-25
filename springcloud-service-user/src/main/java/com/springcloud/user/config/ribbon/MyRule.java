package com.springcloud.user.config.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.Random;

// 结合Feign
@Api("自定义IRule：负载均衡策略")
public class MyRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    /**
     * 选择服务
     * 自定义负载均衡策略
     * @param o
     * @return
     */
    @Override
    public Server choose(Object o) {
        ILoadBalancer lb = getLoadBalancer();
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while (server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }
                int index = new Random().nextInt();
                // int index = this.chooseRandomInt(serverCount);
                server = (Server) upList.get(index);
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }
}
