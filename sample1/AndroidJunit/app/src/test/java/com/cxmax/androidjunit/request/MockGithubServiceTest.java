package com.cxmax.androidjunit.request;

import com.cxmax.androidjunit.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.net.URISyntaxException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 21)
public class MockGithubServiceTest {

    private static final String JSON_ROOT_PATH = "/json/";
    private String jsonFullPath;
    GithubService mockGithubService;

    @Before
    public void setUp() throws URISyntaxException {
        ShadowLog.stream = System.out;
        jsonFullPath = getClass().getResource(JSON_ROOT_PATH).toURI().getPath();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor(jsonFullPath))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mockGithubService = retrofit.create(GithubService.class);
    }

    @Test
    public void mockPublicRepositories() throws Exception {
        Response<List<Repository>> repositoryResponse = mockGithubService.publicRepositories("geniusmart").execute();
        assertEquals(repositoryResponse.body().get(5).name, "LoveUT");
    }

    @Test
    public void mockFollowingUser() throws Exception {
        Response<List<User>> followingResponse = mockGithubService.followingUser("geniusmart").execute();
        assertEquals(followingResponse.body().get(0).login,"JakeWharton");
    }

    @Test
    public void mockUser() throws Exception {
        Response<User> userResponse = mockGithubService.user("geniusmart").execute();
        assertEquals(userResponse.body().login,"geniusmart");
    }

}
