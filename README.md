# ![마크업 이미지3](https://user-images.githubusercontent.com/80473521/118394517-1e93bd80-b680-11eb-9fb1-a14cb453e3ba.jpg) Seventh Seminar Assignment ![마크업 이미지3](https://user-images.githubusercontent.com/80473521/118394517-1e93bd80-b680-11eb-9fb1-a14cb453e3ba.jpg)


## ![마크업 이미지1](https://user-images.githubusercontent.com/80473521/118394520-1fc4ea80-b680-11eb-9641-df4063f3f257.jpg) 로그인, 회원가입 통신 구현하기

### * 자동 로그인 구현하기
1) SignInActivity로 처음 들어왔을 때 SharedPreference에서 ID/PW가 있다면? 로그인 과정을 건너뛴다.
2) 로그인할 때 성공하면 SharedPreference에 집어 넣는다.
3) 서비스에서 로그아웃하면 SharedPreference를 clear한다.

result => 위와 같은 과정으로 자동 로그인처럼 구현할 수 있다.

#### 1. Lv1-1 Activity에서 어떻게 처리했는지 정리
< SignInActivity 코드 정리 >
```kotlin
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchUserAuthStorage()
        loginButtonEvent()
        signUpResult()
    }
    private fun searchUserAuthStorage() {
        with(SoptUserAuthStorage.getInstance(this)) {
            if (hasUserData()) {
                requestLogin(getUserData().let { RequestLoginData(it.id, it.password) })
            }
        }
    }

    private fun loginButtonEvent() {
        binding.button.setOnClickListener{
            val requestLoginData = RequestLoginData(
                    id = binding.idedit.text.toString(),
                    password = binding.passedit.text.toString()
            )
            requestLogin(requestLoginData)
        }
    }
    private fun signUpResult() {
        binding.signup.setOnClickListener {
            signUpActivityLauncher.launch(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }
```
이와 같은 3개의 TextView를 생성하였다. 레포지터리 이름과 레포지터리 설명이 너무 긴경우 ...이 나오도록 
```kotlin
android:ellipsize="end"
```
코드도 추가해주었다. 
* RecyclerView를 이용해 HomeActivity에 해당 Fragment를 보여주기 위해 fragment_following_repo.xml에
```kotlin
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/repo_list"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/text"
        tools:listitem="@layout/item_repo"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"/>
```
위와 같은 코드를 작성하고 화면에 보여주기 위해 HomeActivity에 
```kotlin
val repoFragment = FollowingRepoFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container,repoFragment)
        transaction.addToBackStack(null)
        transaction.commit()
```
위와 같은 코드를 추가해주었다. 이 코드를 통해 Fragment가 HomeActivity의 화면에 뜨게 된다.
* HomeActivity화면에 MORE 버튼을 추가하여 2차 세미나 시간에 만든 Fragment를 가진 Activity를 띄워주기 위해 먼저 
```kotlin
<Button
        android:id="@+id/btn_more"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="300dp"
        app:layout_constraintBottom_toBottomOf="@id/text"
        app:layout_constraintEnd_toEndOf="parent"
        android:text="MORE" />


    <LinearLayout
        android:id="@+id/fragment_container"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="600dp"
        app:layout_constraintTop_toBottomOf="@+id/btn_more"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintStart_toStartOf="parent">
    </LinearLayout>
```
activity_home.xml파일에 MORE button과 Fragment를 가진 Activity가 띄워질 위치 선정을 위해 LinearLayout을 생성했다.
(앞서 직접 만든 레포지터리 이름, 설명 등의 Fragment를 가진 Activity도 해당 위치에 띄워질 수 있도록 위의 LinearLayout을 이용했다.)
* 그리고 MORE button을 눌렀을 경우 2차 세미나에서 만든 Fragment를 가진 Activity가 띄워질 수 있도록 
```kotlin
binding.btnMore.setOnClickListener(){
            val infoFragment = FollowingListFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container,infoFragment)
            transaction.addToBackStack(null)
            transaction.commit()
```
위와 같은 코드를 HomeActivity에 추가하였다.
