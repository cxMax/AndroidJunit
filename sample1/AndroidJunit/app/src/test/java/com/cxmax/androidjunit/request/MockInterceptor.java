package com.cxmax.androidjunit.request;

import com.cxmax.androidjunit.util.FileUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @describe : okhttp client interceptor is used to return a mock data via a real http request
 *              the custom mock data you can write it down in this class
 *              thanks for :
 *              http://stackoverflow.com/questions/17544751/square-retrofit-server-mock-for-testing
 *              https://github.com/square/okhttp/wiki/Interceptors
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */

public class MockInterceptor implements Interceptor{

    private final String responeJsonPath;

    public MockInterceptor(String responeJsonPath) {
        this.responeJsonPath = responeJsonPath;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //mock json
        String responseString = createResponseBody(chain);

        Response response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }

    private String createResponseBody(Chain chain) {

        String responseString = null;

        HttpUrl uri = chain.request().url();
        String path = uri.url().getPath();

        if (path.matches("^(/users/)+[^/]*+(/repos)$")) {//匹配/users/{username}/repos
            responseString = getResponseString("users_repos.json");
        } else if (path.matches("^(/users/)+[^/]+(/following)$")) {//匹配/users/{username}/following
            responseString = getResponseString("users_following.json");
        } else if (path.matches("^(/users/)+[^/]*+$")) {//匹配/users/{username}
            responseString = getResponseString("users.json");
        }
        return responseString;
    }

    private String getResponseString(String fileName) {
        return FileUtil.readFile(responeJsonPath + fileName, "UTF-8").toString();
    }
}
