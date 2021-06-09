#### 1. 구현방법
- 2차 세미나에서 배운 내용을 바탕으로 FollowingRepo.kt, FollowingRepoAdapter.kt, FollowingRepoFragment.kt, RepoInfoActivity.kt 등의 파일들을 
만들어 코드를 작성하였다. 
- 먼저 부분 화면 Fragment를 만들어 ist로 구현해 화면으로 보여주기 위해 item_repo.xml을 만들어 
```kotlin
<TextView
        android:id="@+id/text1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="25sp"
        android:textStyle="bold"
        android:ellipsize="end"
        android:layout_marginHorizontal="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="레포지터리 이름" />
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

![Screenshot_20210425-135256_Se1_hw](https://user-images.githubusercontent.com/80473521/115981424-99604000-a5ce-11eb-8ff4-1dabe69c326f.jpg)
![Screenshot_20210425-135312_Se1_hw](https://user-images.githubusercontent.com/80473521/115981442-c57bc100-a5ce-11eb-94a5-64c47e17c7bd.jpg)
