[벨로그 정리글 바로가기](https://velog.io/@jini_1514/CodeLab-Jetpack-Compose-%EA%B8%B0%EC%B4%88)

# [Jetpack Compose 기초](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#0)

코드랩을 보고 따라 한 것을 기록합니다.
그저 기록이 아닌 새로이 배운 것 위주로 기록하려 합니다.

약 65분 소요라고 써있고, 
저는 핸즈온 방식으로 실습을 하고, 후에 정리글을 쓰는 방식으로 공부하고 있습니다!

아래와 같은 것들을 배울 수 있는 코스라고 합니다.

- Compose의 정의
- Compose로 UI를 빌드하는 방법
- 구성 가능한 함수의 상태를 관리하는 방법
- 성능 기준에 맞는 목록을 만드는 방법
- 애니메이션을 추가하는 방법
- 앱 스타일과 테마를 지정하는 방법



## [UI 조정](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#3)

기본 배경이 아닌 `Text`의 뒷배경과 같이, 특정 영역만 배경을 없애기 위해서는 `Surface`로 감싼 후에 `Text`를 안에 넣어준다. 

```
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}
```

- `Surface`는 요소를 감싸는 컨테이너와 같은 역할을 한다. 
	컨테이너를 만들고 그 컨테이너의 색상을 부여하는 느낌!

- `Modifier`
	`Surface`나 `Text`의 매개변수로 `modifier`를 넣어줄 수 있다.(선택적)
    한국어로는 수정자라고 번역되는 것 같다. 
    padding, 기타 등등의 설정을 해줄 수 있다. [Compose 수정자 목록](https://developer.android.com/jetpack/compose/modifiers-list?hl=ko)
## [컴포저블 재사용](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#4)

코드 중복과 가독성, 기타 등의 이유로 인해 코드를 함수로 나누어 블록으로 작성한다.


## [열과 행 만들기](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#5)

컴포즈의 기본 표준 레이아웃은 다음과 같다. 

 ![](https://velog.velcdn.com/images/jini_1514/post/c0fc9fb8-f818-4ee0-befd-56aa1714e972/image.png)
 
 Column을 사용해서 세로로 배치 해보는 코드를 작성해봤다. 
 
 ```
import androidx.compose.foundation.layout.Column
// ...

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        Column(modifier = modifier.padding(24.dp)) {
            Text(text = "Hello ")
            Text(text = name)
        }
    }
}
```

이런식으로 for 문을 사용해서 넣어줄 수도 있다.

```
@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(modifier) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}
```


버튼도 달아봤다.

> 참고: Compose는 Material Design 버튼 사양(Button, ElevatedButton, FilledTonalButton, OutlinedButton, TextButton)에 따라 다양한 유형의 Button을 제공합니다. 이 경우에는 Text를 ElevatedButton 콘텐츠로 래핑하는 ElevatedButton을 사용합니다.

머티리얼 기본 버튼들이 꽤 많다. 



## [Compose에서의 상태](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#6)

## [상태 호이스팅](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#7)

정중앙에 배치하는 법

```
  Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) 
```

## [성능 지연 목록 만들기](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#8)

>참고: LazyColumn은 RecyclerView와 같은 하위 요소를 재활용하지 않습니다. 컴포저블을 방출하는 것은 Android Views를 인스턴스화하는 것보다 상대적으로 비용이 적게 들므로 LazyColumn은 스크롤 할 때 새 컴포저블을 방출하고 계속 성능을 유지합니다.

번역이 저게 맞는지 모르겠으나, `LazyColumn`을 사용해보았고, 이는 `RecyclerView`를 대체할 수 있다. 

```
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
// ...

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}
```

## **[상태 유지](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#9)**

`rememberSaveable`을 알게 되었다. 

기존의 `remember` 같은 경우는 화면을 회전하게 되면 상탯값을 저장하지 못하고 초기화해버렸다.컴포저블이 컴포지션에 유지되는 동안에만 작동하기 때문이다.

`rememberSaveable`은 구성 변경(예: 회전)과 프로세스 중단에도 각 상태를 저장한다. 
구성 변경 예시)
- 회전
- 다크모드 전환
- 프로세스 종료 (앱종료x)
- 스크롤해서 위의 아이템이 보이지 않게 되었을 때

`var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }`



## [목록에 애니메이션 적용](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#10)

`animateDpAsState` 컴포저블을 사용해봄

## [앱의 스타일 지정 및 테마 설정](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#11)

- 기본적인 폰트 지정 방법
```
                Text(text = name, style = MaterialTheme.typography.headlineMedium)

```

- 수정하여 사용하기

```
Text(
    text = name,
    style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.ExtraBold
    )
)
```

## [설정 완료](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#12)

`stringResource(R.string.show_less)`로 value/string.xml에 있는 값을 불러다 쓸 수 있다. 
