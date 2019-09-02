package cn.latiny.community.provider;

import cn.latiny.community.model.AccessTokenDTO;
import cn.latiny.community.model.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Latiny
 * @version 1.0
 * @description: Github Provider
 * @date 2019/9/2 17:51
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        String url = "https://github.com/login/oauth/access_token";
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            if (string.split("&").length > 0) {
                string = string.split("&")[0].split("=")[1];
            }
            return string;
        } catch (IOException e) {
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return JSON.parseObject(response.body().string(), GithubUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
