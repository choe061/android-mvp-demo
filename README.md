Android MVP Demo
================
* 안드로이드의 MVC 패턴은 Controller역할을 하는 Activity(Fragment)가 View에 대한 직접적인 조작을 하게 되어 Activity(Fragment)에 Controller와 View의 역할이 섞여 MVC의 구조에 어긋나는 문제가 생긴다.
* Library : Retrofit2, RxJava2, RxAndroid2, retrofit-rxjava-adapter, retrolambda, recyclerView, glide, Butterkinfe, lombok
* [Google android-architecture](https://github.com/googlesamples/android-architecture) 참고
* Github api 연동

## 1. 구조
* <code>Model</code>, <code>View</code>, <code>Presenter</code> (이 외 adapter, network, util)으로 구성
* View와 Presenter 사이의 연결에 대해 정의한 Contact Interface를 생성
* Fragment가 android view인 경우 Activity는 Fragment와 Presenter를 생성하고, Fragment에서 View를 상속받아 구현

#### 1.1 Model
Data와 관련된 전반적인 처리를 Model에서 담당한다. domain클래스를 정의하거나 Presenter에서 데이터 요청이 오면 데이터를 로컬 또는 서버로부터 가져와 데이터를 넘겨준다. android-architecture에서는 Remote data source 부분을 Presenter가 아닌 Data Layer에 두고있다.

#### 1.2 View
사용자의 실질적인 이벤트가 발생하고, 이를 Presenter에 처리를 위임한다. 데이터를 가공하거나 데이터를 가져오는 행위는 View가 아닌 Presenter에서 처리한다.

#### 1.3 Presenter
View에서 전달받은 이벤트를 처리하고, 결과를 다시 View에 전달한다. 또는 Model에 요청하여 받은 Data를 View에 전달한다.

#### 1.4 adapter, network, util
RecyclerView에서 사용하는 adapter는 화면(Activity)에 필요한 Data를 받아 구성하고, View를 관리한다. 그렇기 때문에 adapter는 View와 Model의 특징을 모두 가진다. View와 Model에 대해 정의한 Contact Interface를 만들어 adapter에서 상속받아 구현한다. 이후 Presenter에서 adapter Model을 통해 데이터를 관리하고, View에서 adapter View를 통해 RecyclerView를 갱신한다.

## 2. 처리 순서
1. View : 이벤트 발생
2. View -> Presenter : 이벤트 전달 or 처리 위임
3. Presenter : 이벤트의 형태에 따라 이벤트를 바로 처리하거나 Model에 데이터를 요청
4. Presenter -> Model : Presenter로부터 데이터를 요청받음
5. Model : 서버 API와 통신하여 데이터를 가져옴
6. Model -> Presenter : Model로부터 데이터를 받음
7. Presenter : 받은 데이터를 가공 (RecyclerView는 8. Presenter -> Adapter.Model : 데이터를 전달, 9. Presenter -> Adapter.View : 뷰 갱신)
8. Presenter -> View : 가공한 데이터를 View에 전달
9. View : Presenter로부터 전달받은 데이터로 View에서 UI를 갱신
