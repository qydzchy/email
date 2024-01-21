package com.ruoyi.email.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
public class TranslateUtil {

    private static volatile com.aliyun.teaopenapi.Client client;

    private TranslateUtil() {
    }

    // 获取单例实例的方法
    public static com.aliyun.teaopenapi.Client getClient(String accessKeyId, String accessKeySecret) {
        // 第一次检验，如果实例已经存在，直接返回
        if (client == null) {
            // 同步块，只有第一次才会进入
            synchronized (TranslateUtil.class) {
                // 第二次检验，防止多线程环境下的重复创建
                if (client == null) {
                    try {
                        client = TranslateUtil.createClient(accessKeyId, accessKeySecret);
                    } catch (Exception e) {
                        log.error("获取翻译客户端对象失败");
                    }
                }
            }
        }
        return client;
    }

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private static com.aliyun.teaopenapi.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/alimt
        config.endpoint = "mt.aliyuncs.com";
        return new com.aliyun.teaopenapi.Client(config);
    }

    /**
     * API 相关
     * @param
     * @return OpenApi.Params
     */
    public static com.aliyun.teaopenapi.models.Params createApiInfo() throws Exception {
        com.aliyun.teaopenapi.models.Params params = new com.aliyun.teaopenapi.models.Params()
                // 接口名称
                .setAction("TranslateGeneral")
                // 接口版本
                .setVersion("2018-10-12")
                // 接口协议
                .setProtocol("HTTPS")
                // 接口 HTTP 方法
                .setMethod("POST")
                .setAuthType("AK")
                .setStyle("RPC")
                // 接口 PATH
                .setPathname("/")
                // 接口请求体内容格式
                .setReqBodyType("formData")
                // 接口响应体内容格式
                .setBodyType("json");
        return params;
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.teaopenapi.Client client = TranslateUtil.createClient("LTAI5tBRVRKejS2QnuVHsRMa", "hPwRn1Z2Yc7aTvpM7aVANbD7HWBfP7");
        com.aliyun.teaopenapi.models.Params params = TranslateUtil.createApiInfo();
        // body params
        java.util.Map<String, Object> body = new java.util.HashMap<>();
        body.put("FormatType", "text");
        body.put("SourceLanguage", "en");
        body.put("TargetLanguage", "zh");
        body.put("SourceText", "Our mission is to lead the global community in creating identity standards that are secure, interoperable and privacy-preserving.");
        body.put("Scene", "general");
        // runtime options
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.teaopenapi.models.OpenApiRequest request = new com.aliyun.teaopenapi.models.OpenApiRequest()
                .setBody(body);
        // 复制代码运行请自行打印 API 的返回值
        // 返回值为 Map 类型，可从 Map 中获得三类数据：响应体 body、响应头 headers、HTTP 返回的状态码 statusCode。
        Map<String, ?> map = client.callApi(params, request, runtime);
        String jsonString = JSON.toJSONString(map);

        JSONObject jsonO = JSONObject.parseObject(jsonString);
        if (jsonO.getIntValue("statusCode") == 200) {
            JSONObject bodyJsonO = jsonO.getJSONObject("body");
            if (bodyJsonO.getString("Code").equals("200")) {
                JSONObject dataJsonO = bodyJsonO.getJSONObject("Data");
                System.out.println(dataJsonO.getString("Translated"));
            }
        }
    }
}
