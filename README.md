# ![마크업 이미지3](https://user-images.githubusercontent.com/80473521/118394517-1e93bd80-b680-11eb-9fb1-a14cb453e3ba.jpg) Fourth Seminar Assignment ![마크업 이미지3](https://user-images.githubusercontent.com/80473521/118394517-1e93bd80-b680-11eb-9fb1-a14cb453e3ba.jpg)


## ![마크업 이미지1](https://user-images.githubusercontent.com/80473521/118394520-1fc4ea80-b680-11eb-9641-df4063f3f257.jpg) 로그인, 회원가입 통신 구현하기

### * PostMan 테스트 사진
- 로그인
<img width="639" alt="sopt1" src="https://user-images.githubusercontent.com/80473521/118395630-530a7800-b686-11eb-95f8-1f14599ab8fc.PNG">
- 회원가입
<img width="650" alt="sopt2" src="https://user-images.githubusercontent.com/80473521/118395632-53a30e80-b686-11eb-95da-1c1a0113e378.PNG">

### * 로그인 완료 구현
![hw1](https://user-images.githubusercontent.com/80473521/118396990-feb6c680-b68c-11eb-887c-3aba41401e76.jpg)
![hw2](https://user-images.githubusercontent.com/80473521/118396991-ff4f5d00-b68c-11eb-9270-ec80d35afb61.jpg)


### * 회원가입 완료 구현
![hw3](https://user-images.githubusercontent.com/80473521/118396988-fd859980-b68c-11eb-8954-3613886ccdd4.jpg)
![hw4](https://user-images.githubusercontent.com/80473521/118396989-feb6c680-b68c-11eb-9f08-1dbbef8ed71c.jpg)

### * Retrofit Interface
```kotlin
interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>
    @POST("/login/signup")
    fun postSignUp(
            @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>
}
```
### * Retrofit Interface 구현체
```kotlin
object ServiceCreator {
    private const val BASE_URL = "http://cherishserver.com"

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    val soptService: SoptService = retrofit.create(SoptService::class.java)
}
```
### * callback
```kotlin
val requestLoginData = RequestLoginData(
                    id = binding.idedit.text.toString(),
                    password = binding.passedit.text.toString()
            )
            val call: Call<ResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)
            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                ){
                    if(response.isSuccessful){
                        val data = response.body()?.data
                        Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        startHomeActivity()
                    }else {
                        Toast.makeText(
                                this@SignInActivity,
                                "아이디/비밀번호를 확인해주세요!",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<ResponseLoginData>, t:Throwable){
                    Log.d("NetworkTest", "error:$t")
                }
```
``` kotlin
val requestSignUpData = RequestSignUpData(
                    id = binding.idEdit.text.toString(),
                    password = binding.pwdEdit.text.toString(),
                    nickname = binding.nameEdit.text.toString(),
                    sex = "0",
                    phone = "010-7940-2825",
                    birth = "1999-05-29"
            )
            val call: Call<ResponseSignUpData> = ServiceCreator.soptService.postSignUp(requestSignUpData)
            call.enqueue(object : Callback<ResponseSignUpData> {
                override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                ){
                    if(response.isSuccessful){
                        Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        startLogin()
                    }else {
                        Toast.makeText(
                                this@SignUpActivity,
                                "다시 입력해주세요!",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<ResponseSignUpData>, t:Throwable){
                    Log.d("NetworkTest", "error:$t")
                }
```
### * 배운 점 및 성장한 점
- 세미나를 듣고 과제를 하면서 직접 서버 통신을 해볼 수 있어 한 층 더 성장할 수 있었던 것 같습니다. 어떻게 로그인, 회원가입 통신을 구현할 수 있을지 하나하나 자세하게 배우며 직접 코딩해보니 쉽지는 않았지만 재미있고 흥미를 느꼈습니다. 더 잘 이해하고 사용하기 위해서 더 열심히 공부하겠습니다!
