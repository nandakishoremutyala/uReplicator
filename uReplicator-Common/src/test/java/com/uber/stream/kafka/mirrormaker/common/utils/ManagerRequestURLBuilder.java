/*
 * Copyright (C) 2015-2019 Uber Technologies, Inc. (streaming-data@uber.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uber.stream.kafka.mirrormaker.common.utils;

import org.apache.commons.lang.StringUtils;
import org.restlet.Request;
import org.restlet.data.Method;

public class ManagerRequestURLBuilder {

  private final String _baseUrl;

  private ManagerRequestURLBuilder(String baseUrl) {
    _baseUrl = baseUrl;
  }

  public static ManagerRequestURLBuilder baseUrl(String baseUrl) {
    return new ManagerRequestURLBuilder(baseUrl);
  }

  public Request getHealthCheck() {
    String requestUrl = StringUtils.join(new String[]{
        _baseUrl, "/health"
    });

    Request request = new Request(Method.GET, requestUrl);
    return request;
  }

  public Request getTopicExternalViewRequestUrl(String topic) {
    String requestUrl = StringUtils.join(new String[]{
        _baseUrl, "/topics/", topic
    });

    Request request = new Request(Method.GET, requestUrl);
    return request;
  }

  public Request getTopicDeleteRequestUrl(String topic, String src, String dst) {
    String requestUrl = StringUtils.join(new String[]{
        _baseUrl, "/topics/", topic, "?src=", src, "&dst=", dst
    });

    Request request = new Request(Method.DELETE, requestUrl);
    return request;
  }

  public Request getTopicCreationRequestUrl(String topic, String src, String dst) {
    String requestUrl = StringUtils.join(new String[]{
        _baseUrl, "/topics/", topic, "?src=", src, "&dst=", dst
    });

    Request request = new Request(Method.POST, requestUrl);

    return request;
  }

  /*public Request getTopicExpansionRequestUrl(String topic, int numPartitions) {
    Request request = new Request(Method.PUT, _baseUrl + "/topics/");
    TopicPartition topicPartitionInfo = new TopicPartition(topic, numPartitions);
    request.setEntity(topicPartitionInfo.toJSON().toJSONString(), MediaType.APPLICATION_JSON);
    return request;
  }*/
}
