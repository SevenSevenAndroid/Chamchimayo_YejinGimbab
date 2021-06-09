# ![마크업 이미지3](https://user-images.githubusercontent.com/80473521/118394517-1e93bd80-b680-11eb-9fb1-a14cb453e3ba.jpg) Seventh Seminar Assignment ![마크업 이미지3](https://user-images.githubusercontent.com/80473521/118394517-1e93bd80-b680-11eb-9fb1-a14cb453e3ba.jpg)


## ![마크업 이미지1](https://user-images.githubusercontent.com/80473521/118394520-1fc4ea80-b680-11eb-9641-df4063f3f257.jpg) 자동 로그인 구현하기

1) SignInActivity로 처음 들어왔을 때 SharedPreference에서 ID/PW가 있다면? 로그인 과정을 건너뛴다.
2) 로그인할 때 성공하면 SharedPreference에 집어 넣는다.
3) 서비스에서 로그아웃하면 SharedPreference를 clear한다.

result => 위와 같은 과정으로 자동 로그인처럼 구현할 수 있다.

### 1. Lv1-1 Activity에서 어떻게 처리했는지 정리
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
- SignInActivity가 onCreate일 때 이전에 작성한 ID/PW가 있으면 바로 로그인 시키고 없으면 원래 과정으로 진행
- 전의 세미나에서 만든 것 그대로 
'button' 버튼을 누르면 id, password 데이터를 전달,
'signup' 텍스트를 누르면 SignUpActivity로 이동

```kotlin
private fun requestLogin(requestLoginData: RequestLoginData) {
        val call: Call<ResponseLoginData> = ServiceCreator.soptService
            .postLogin(requestLoginData)
        call.enqueueUtil(
            onSuccess = { response ->
                val data = response.data
                showToast(data?.user_nickname.toString())
                with(SoptUserAuthStorage.getInstance(this)) {
                    saveUserData(requestLoginData.let { SoptUserInfo(it.id, it.password) })
                }
                startHomeActivity()
            }
        )
    }
```
- 확장함수를 이용하여 requestLogin 간단하게 구현

< 코틀린 확장 함수>
- ToastUtil.kt
```kotlin
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        .show()
}
```
- RetrofitEnqueueUtil.kt
```kotlin
fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
            } else {
                onError?.invoke(response.code())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("NetworkTest", "error:$t")
        }
    })
}
```
### 2. Lv1-2 SharedPreference 어떻게 코드를 정리했는지 코드 첨부
- SharedPreference를 계속 만들면 비효율적이므로 object을 이용해 한 번만 만들기
```kotlin
class SoptUserAuthStorage private constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        "${context.packageName}.$STORAGE_KEY",
        Context.MODE_PRIVATE
    )

    private val editor = sharedPreferences.edit()

    companion object {
        private const val STORAGE_KEY = "user_auth"
        private const val USER_ID_KEY = "user_id"
        private const val USER_PW_KEY = "user_pw"

        @Volatile
        private var instance: SoptUserAuthStorage? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: SoptUserAuthStorage(context).apply {
                instance = this
            }
        }
    }
    fun getUserData(): SoptUserInfo = SoptUserInfo(
        id = sharedPreferences.getString(USER_ID_KEY, "") ?: "",
        password = sharedPreferences.getString(USER_PW_KEY, "") ?: ""
    )
    fun saveUserData(userData: SoptUserInfo) {
        editor.putString(USER_ID_KEY, userData.id)
            .putString(USER_PW_KEY, userData.password)
            .apply()
    }
    fun hasUserData(): Boolean {
        with(getUserData()) {
            return id.isNotEmpty() && password.isNotEmpty()
        }
    }
    fun clearAuthStorage() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}
```
### 3. 과제를 통해 배운 내용 or 개발자로 성장한 내용
- 이번 과제를 하면서 처음 SharedPreference를 만들어보았습니다. 처음이라 굉장히 어렵다고 생각이 들었고 열심히 세미나 복습을 하면서 과제를 진행해나갔습니다. 
어려운 부분은 파트장님의 코드를 보면서 이렇게 만들 수 있구나 생각하고 배울 점은 코드에 반영해가며 과제를 해나간 것 같습니다.
꽤 오랜 시간동안 세미나를 듣고 과제를 해나가면서 그 전보다 한층 더 성장할 수 있었다고 생각이 듭니다. sopt를 들어와 활동한 것은 제가 개발자로 성장할 수 있는 좋은 기회였다고 생각합니다.
감사합니다!
