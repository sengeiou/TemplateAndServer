/*
 * Copyright 2018 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.server.component;

import androidx.annotation.NonNull;

import com.xuexiang.server.utils.ApiUtils;
import com.yanzhenjie.andserver.annotation.Resolver;
import com.yanzhenjie.andserver.error.BasicException;
import com.yanzhenjie.andserver.framework.ExceptionResolver;
import com.yanzhenjie.andserver.framework.body.JsonBody;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;
import com.yanzhenjie.andserver.util.StatusCode;

/**
 * 应用服务错误信息处理
 *
 * @author xuexiang
 * @since 2020/8/30 11:04 PM
 */
@Resolver
public class AppExceptionResolver implements ExceptionResolver {

    @Override
    public void onResolve(@NonNull HttpRequest request, @NonNull HttpResponse response, @NonNull Throwable e) {
        e.printStackTrace();
        if (e instanceof BasicException) {
            BasicException exception = (BasicException) e;
            response.setStatus(exception.getStatusCode());
        } else {
            response.setStatus(StatusCode.SC_INTERNAL_SERVER_ERROR);
        }
        String body = ApiUtils.failedJson(response.getStatus(), e.getMessage());
        response.setBody(new JsonBody(body));
    }
}