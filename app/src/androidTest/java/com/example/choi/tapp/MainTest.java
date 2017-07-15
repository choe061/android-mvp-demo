package com.example.choi.tapp;

import android.support.test.runner.AndroidJUnit4;

import com.example.choi.tapp.network.HttpService;
import com.example.choi.tapp.view.MainActivity;
import com.example.choi.tapp.presenter.contact.MainContact;
import com.example.choi.tapp.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

/**
 * Created by choi on 2017. 7. 14..
 */

@RunWith(AndroidJUnit4.class)
public class MainTest {

    private MainContact.View view;
    private MainPresenter presenter;
    private HttpService httpService;

    @Before
    public void setUp() throws Exception {
        this.view = Mockito.mock(MainActivity.class);
        this.httpService = Mockito.mock(HttpService.class);
        this.presenter = new MainPresenter();
        presenter.attachView(view, httpService);
    }

    @Test
    public void testRequestGetUser() throws Exception {
        presenter.requestGetGithubUsers();
    }
}
