package com.springcloud.user.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Zuul熔断降级
 */
@Component
public class ZuulFallback implements FallbackProvider {

    /**
     *  "*:对所有服务降级"；可指定服务降级
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 降级返回
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                // 状态码
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.BAD_REQUEST.getReasonPhrase();// 原因短语
            }

            @Override
            public void close() {
                // 关闭Response流，做资源清理
            }

            // 响应体
            @Override
            public InputStream getBody() throws IOException {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("系统维护中，请稍后重试.".getBytes());
                return byteArrayInputStream;
            }

            //响应头
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "text/html;charset=UTF-8");
                return headers;
            }
        };
    }
}
