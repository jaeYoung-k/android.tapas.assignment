android tapas assignment
=================

타파스에서 가장 기본적으로 사용되는 시리즈 리스트뷰와 시리즈 디테일뷰를 만드는 작업을 진행했습니다. 

Introduction
------------
Android Architecture Components 기반으로 작성하였습니다. 
시리즈 리스트, 상세화면은 View Binding을 사용하여 xml과 view를 상호작용 하도록 만들었습니다.
네트워크 통신은 반응형으로 만들기 위해 RxJava3, RxAndroid를 사용하였습니다.
서버에서 응답받은 데이터를 View에 사용하기 위해 ViewModel을 사용하였습니다.
객체 간의 의존성을 줄이고 결합도를 낮추기 위해 Hilt를 사용하였습니다.

Libraries Used
--------------
* [Architecture][10] - Android 아키텍처 구성요소는 강력하고 테스트와 유지관리가 쉬운 앱을 디자인하도록 돕는 라이브러리 모음입니다.
  * [View Binding][11] - 뷰 결합 기능을 사용하면 뷰와 상호작용하는 코드를 쉽게 작성할 수 있습니다.
  * [LiveData][12] - 수명 주기 인식을 통해 LiveData는 활동 수명 주기 상태에 있는 앱 구성요소 관찰자만 업데이트합니다.
  * [ViewModel][13] - ViewModel 클래스는 수명 주기를 고려하여 UI 관련 데이터를 저장하고 관리하도록 설계되었습니다.

* Third party and miscellaneous libraries
  * [Glide][21] - 이미지 로딩을 쉽고 효과적으로 사용가능한 라이브러리입니다.
  * [Hilt][22] - 의존성 주입을 위한 Dagger 기반 라이브러리입니다.
  * [RxJava3][23] - observable sequences를 사용하여 비동기, 이벤트 기반 프로그램을 구성할 수 있습니다.
  * [Retrofit][24] - 네트워크 통신을 type-safe하게 만든 HTTP client 라이브러리입니다.
  * [Kotlin Serializable][25] - Json 직열화/역직열화를 지원합니다.

[10]: https://developer.android.com/topic/libraries/architecture/
[11]: https://developer.android.com/topic/libraries/view-binding/
[12]: https://developer.android.com/topic/libraries/architecture/livedata/
[13]: https://developer.android.com/topic/libraries/architecture/viewmodel/
[21]: https://github.com/bumptech/glide
[22]: https://dagger.dev/hilt/
[23]: https://github.com/ReactiveX/RxJava
[24]: https://square.github.io/retrofit/
[25]: https://kotlinlang.org/docs/serialization.html
