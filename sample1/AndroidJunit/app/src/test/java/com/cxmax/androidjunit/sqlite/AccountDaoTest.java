package com.cxmax.androidjunit.sqlite;

import com.cxmax.androidjunit.BuildConfig;
import com.cxmax.androidjunit.util.AccountUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AccountDaoTest {

    @Test
    public void save() {
        Account account = AccountUtil.createAccount("1");
        long result = AccountDao.save(account);

        Assert.assertTrue(result > 0);
    }

    @Test
    public void update(){
        Account account = AccountUtil.createAccount("2");
        AccountDao.save(account);

        account.name = "geniusmart_update";
        int result = AccountDao.update(account);
        Assert.assertEquals(result , 1);

        Account newAccount = AccountDao.get("2");
        assertEquals(newAccount.name, "geniusmart_update");
    }

    @Test
    public void query(){
        AccountDao.save(AccountUtil.createAccount("3"));
        AccountDao.save(AccountUtil.createAccount("4"));
        AccountDao.save(AccountUtil.createAccount("5"));
        AccountDao.save(AccountUtil.createAccount("5"));

        List<Account> accountList = AccountDao.query();
        assertEquals(accountList.size(), 3);
    }
}
