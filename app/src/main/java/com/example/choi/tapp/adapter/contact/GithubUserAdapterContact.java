package com.example.choi.tapp.adapter.contact;

import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.widget.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 6. 8..
 * ListView나 RecyclerView의 Adapter는 화면에 필요한 Item을 구성하고 완성된 View를 관리한다
 * 그렇기 때문에 Adapter는 View와 Model의 모습을 모두 갖고 있다
 *
 * Adapter를 View와 Model을 Interface를 통해 분할하고
 * Presenter에서는 DataModel에 접근
 * View에서는 DataView에 접근
 *
 * View에서는 직접 Data에 접근하는 것이 아닌 AdapterDataView를 통해 ListView나 RecyclerView를 갱신하고
 * 데이터의 관리는 Presenter 가 AdapterDataModel 을 통해서 처리한다
 */

public interface GithubUserAdapterContact {

    interface View {
        void setOnClickListener(OnItemClickListener onItemClickListener);

        void notifyAdapter();
    }

    interface Model {
        void addItems(ArrayList<User> users);

        void clear();

        User getUser(int position);
    }
}
