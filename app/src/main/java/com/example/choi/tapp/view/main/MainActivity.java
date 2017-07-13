package com.example.choi.tapp.view.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.choi.tapp.R;
import com.example.choi.tapp.adapter.GithubUserRecyclerAdapter;
import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.view.BaseActivity;
import com.example.choi.tapp.view.main.presenter.MainContact;
import com.example.choi.tapp.view.main.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 기본적인 MVP Pattern에 대해 학습
 * 안드로이드의 MVC 패턴은 Controller역할을 하는 Activity(Fragment)가 View에 대한 직접적인 조작을 하게 되어
 * Activity(Fragment)에 Controller와 View의 역할이 섞여 MVC의 구조에 어긋나는 문제가 생긴다.
 *
 * 내용 : 1. Retrofit + RecyclerView = GitHub "/users" API를 연동
 *       2. 통신하여 받은 데이터를 RecyclerView로 보여주고 Item ClickListener를 달아준다
 *       3. Item 클릭 시 User의 상세정보에 해당하는 "/user/{id}" API를 연동하여 보여준다
 *
 * Presenter를 구분하는 방법은 여러가지가 있지만 Google Architecture를 따른
 * *Google Architecture
 *      1. Contact : View와 Presenter에 대한 Interface를 작성
 *      2. Presenter : Contact.Presenter Interface를 상속받아 구현
 *      3. View : Contact.View Interface를 상속받아 구현
 *      - 장점 : Interface 정의로 코드 파악이 쉬움, 가독성 증가
 *      - 단점 : Interface가 너무 많아짐
 *
 * Model : Data와 관련된 처리를 담당한다
 *      - Data에 전반적인 부분을 Model에서 담당한다. (네트워크와 로컬데이터 등을 포함)
 * View : 사용자의 실질적인 이벤트가 발생하고, 이를 처리 담당자인 Presenter로 전달
 *      - 완전한 View의 형태를 가지도록 설계합니다. 계산을 하거나, 데이터를 가져오는 등의 행위는 Presenter에서 처리
 * Presenter : View에서 전달받은 이벤트를 처리하고, 이를 다시 View에 전달
 *      - View와는 무관한 Data등을 가지고, 이를 가공하고, View에 다시 전달하는 역할
 * 상황에 따라서 Model을 사용할 수도 있고, 사용하지 않을 수도 있다
 *
 * View와 Presenter의 실행 순서 (Flow)
 *      1. View : View에서 터치 이벤트 발생
 *      2. View -> Presenter : Presenter로 이벤트 전달
 *      3. Presenter : View에서 요청한 이벤트 처리 (View와 무관한 처리만 한다)
 *      4. Presenter -> View : 처리한 결과를 View로 전달
 *      5. View : 처리된 결과를 바탕으로 UI를 갱신
 *
 * View + Presenter + Model의 실행 순서 (Flow)
 *      1. View : View에서 터치 이벤트 발생
 *      2. View -> Presenter : Presenter로 이벤트 전달
 *      3. Presenter : 이벤트의 형태에 따라 캐시 데이터를 가져오거나, Model에 요청
 *      4. Presenter -> Model : Presenter에서 데이터를 요청받음
 *      5. Model : 데이터를 로컬 또는 서버에서 가져온다
 *      6. Model -> Presenter : Model로부터 데이터를 통보받는다
 *      7. Presenter : 전달받은 데이터를 가공
 *      8-1. Presenter -> View : 가공한 데이터를 View에 전달, 8-2. Presenter -> Adapter.Model : 데이터 전달
 *      9-1. View : Presenter로 전달받은 데이터로 View에서 UI를 갱신, 9-2. Presenter -> Adapter.View : 갱신
 */

public class MainActivity extends BaseActivity implements MainContact.View {

    private static final String TAG = MainActivity.class.getName();

    private MainPresenter mainPresenter;
    private RequestManager requestManager;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        requestManager = Glide.with(this);
        GithubUserRecyclerAdapter githubUserRecyclerAdapter = new GithubUserRecyclerAdapter(this, requestManager);
        recyclerView.setAdapter(githubUserRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this, httpService);
        mainPresenter.setAdapterModel(githubUserRecyclerAdapter);
        mainPresenter.setAdapterView(githubUserRecyclerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.requestGetGithubUsers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(User user) {
        final UserDialog userDialog = new UserDialog(this, requestManager);
        userDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                userDialog.setDialogText(user);
            }
        });
        userDialog.show();
    }
}
