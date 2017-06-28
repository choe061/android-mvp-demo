package com.example.choi.tapp.adapter.contact;

import com.example.choi.tapp.util.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 6. 28..
 * ListView나 RecyclerView의 Adapter는 화면에 필요한 Item을 구성하고 완성된 View를 관리한다
 * 그렇기 때문에 Adapter는 View와 Model의 모습을 모두 갖고 있다
 *
 * Adapter를 View와 Model을 Interface를 통해 분할하고
 * Presenter에서는 DataModel에 접근
 * View에서는 DataView에 접근
 *
 * View에서는 직접 Data에 접근하는 것이 아닌 AdapterDataView를 통해 ListView나 RecyclerView를 갱신하고
 * 데이터의 관리는 Presenter 가 AdapterDataModel 을 통해서 처리한다
 *
 * Model<M>, 제네릭을 사용하여 모든 Adapter에서 공통으로 사용한다.
 */

public interface BaseAdapterContact {

    interface View {

        void setOnClickListener(OnItemClickListener onItemClickListener);

        void notifyAdapter();

    }

    interface Model<M> {

        void addItems(ArrayList<M> models);

        M getItem(int position);

        void clear();

    }
}
