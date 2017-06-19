package com.cxmax.androidjunit.request;

import android.util.Log;

import com.cxmax.androidjunit.BuildConfig;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @describe :a http request which uses retrofit library test ;
 *              it will contacts the real server and get the real http response
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class GithubServiceTest {

    private static final String TAG = "GithubServiceTest";
    GithubService githubService;

    @Test
    public void setUp() throws URISyntaxException {
        ShadowLog.stream = System.out;
        githubService = GithubService.Factory.create();
    }

    @Test
    public void publicRepositories() throws IOException {
        Call<List<Repository>> call = githubService.publicRepositories("geniusmart");
        Response<List<Repository>> execute = call.execute();

        List<Repository> list = execute.body();
        Log.i(TAG,new Gson().toJson(list));
        assertTrue(list.size() > 0);
        assertNotNull(list.get(0).name);
    }
}
