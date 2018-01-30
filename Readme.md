# ADS04 Android

## 수업 내용

- MVP 디자인 패턴 학습

## Code Review

### MainActvity
```Java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 해당 화면에서 정의해놓은 View,Presenter interface를 선언한다.

        MainContract.iView mainView = new MainView(this); // MainView클래스에서 context자원을 받아서 뷰를 생성한다.
        MainContract.iPresenter mainPresenter = new MainPresenter();

        mainPresenter.attachView(mainView);
        mainView.setPresenter(mainPresenter);

        // View는 화면에 보여주는 역할이므로, setContentView를 통해 setting한다.
        // contet
        setContentView(mainView.getView());
    }
}
```
### MainContract

```Java
public interface MainContract { // 이곳에서 View, Presenter interface를 정의함.

    interface iView{  // 화면에 보여지는 View 담당 및 Presenter와 상호작용
        /**
         * View 를 반환하는 메소드
         * @return View
         */
        View getView();

        /**
         * Presenter 연결 메소드
         * @param presenter
         */
        void setPresenter(iPresenter presenter);

        /**
         * Text 변경 메소드
         * @param text
         */
        void setText(String text);
    }

    interface iPresenter{ // View와 Model 중간에서 로직을 처리하는 중계자 역할
                          // 그렇기 때문에, View와 연결하는 것, 정의해놓은 model 아이템들을 불러오는 것 등이 이슈이다.

        /**
         * View 연결 메소드
         * @param view
         */
        void attachView(iView view);

        // 이하 생략....
}
```

### MainView

```Java
public class MainView implements MainContract.iView{ // 뷰를 관리하는 곳이므로 해당 View Interface를 implement한다.

   MainContract.iPresenter presenter; // presenter를 사용하는 이유는 View와 모델간, View와 View간에 상호작용을 위함이다.

    private Context context;
    private View view;
    private TextView textView;
    // 중략....

    public MainView(Context context){ // 이 메소드가 호출되면 해당 context자원을 받아 inflate를 해준다.
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        initView();
      // 생략....
    }

    
    private void initView() {
        textView = view.findViewById(R.id.textView);
        // 생략...
    }
    // 생략...

    @Override
    public View getView() { // 이 메소드가 호출되면 inflate 해준 뷰를 리턴해준다.
        return view;
    }

   // 중략...

    @Override
    public void setText(String text) {
        textView.setText(text); // 프레젠터에서 받아온 text
    }
}
```

### MainPresenter

```Java
public class MainPresenter implements MainContract.iPresenter, OnItemClickListener { // View와 Model들간에 필요한 로직들을 중간에서 처리해주는 역할
                                                                                    // click리스너라는 인터페이스를 정의해서, 클릭시 이벤트 처리. event여서 presenter에서 처리

    private MainContract.iView view;
    // 생략...

    @Override
    public void attachView(MainContract.iView view) { // 이 함수는 MainActivity에서 호출이 되었고 그곳에서 View를 세팅한 것을 인자로 넣었다.
                                                      // 이미 View가 세팅이 되서 넘어와서, 이곳에서 그 View를 가지고 로직을 처리할 수 있다.
        this.view = view;
    }

   // 생략...
}
```


## 보충설명

### MVP
> __MVP 패턴은 Model-View-Presenter으로 구성__

![mvp](http://thdev.tech/images/architecture/2016/2016-05-03-MediaProjection-MVP-Pattern/MVP.png)

- __View__ - 어플케이션의 인터페이스(Activity, Fragment). Model에서 처리된 데이터를 Presenter를 통해 받아서 사용자에게 보여줌. presenter를 이용해 데이터를 주고 받음. (presenter의존적)

- __Presenter__ - View와 model을 연결, 기능/흐름을 제공. View에서 action을 전달받아 model 로직 호출, Model로직으로부터 나온 결과를 전달받아 View에 보내 Ui변경 이벤트를 발생 시키는 등의 역할을 하는 일종의 중계자 역할.

- __model__ - 비즈니스 로직, 어플리케이션 데이터.(독립적)

![실행흐름](http://thdev.tech/images/architecture/2016/2016-10-12-Android-MVP-Intro/mvp-model.png)

- ※ 상황에 따라 View와 Presenter만 상호작용하기도 함.

### MVP 등 디자인 패턴을 사용안 했을 때 어려운점?

- 실행 흐름 파악이 어려움
- 부분적인 테스트가 어려움
- 변경이 점점 어려워짐

### 구현 시 참고 사항

- View는 Presenter에 의존적이기에 Presenter 객체를 멤버변수로 가져야 함,정해진 이벤트 발생시점에 presenter 로직이 실행(위임)

- Presenter는 View와 Model에 종속적이기 때문에 둘다 멤버변수를 가져야 함. 요구 사항에 맞게 View, Model 정의된 Method들을 호출해 사용

- Model은 독립적이기에, 일반적으로 만들면 됨. 

### 출처

- 출처: http://blog.happydong.kr/204 [최고보다,최선을...]
- 출처: https://brunch.co.kr/@mobiinside/913
- 출처: http://thdev.tech/androiddev/2016/10/12/Android-MVP-Intro.html
- 코드참조: https://github.com/Hooooong/DAY44_MVP

## TODO

- 학습한 내용을 토대로, 기존에 만들었던 예제들 MVP형태로 바꿔보기
- MVC, MVVM 등 다른 디자인 패턴과 비교분석해보기

## Retrospect

- View, Presenter, Model간에 관계는 이해가 되나, 이를 적용하려니 기존에 로직을 짜던 습관때문에 어려움을 느꼈다.
- 큰 프로젝트를 할때는 굉장히 도움이 많이 될 것 같다.
- 디자인 패턴에 대해 공부가 더 필요하다고 느꼈다.(예제 분석하는데 이해를 하기 힘들어서, 다른 사람들의 코드를 많이 참조함.)

## Output
- 생략
