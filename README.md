# android-tictactoe

## 1단계 추가 피드백

- [x] activity_main.xml 에서 불필요한 import 제거
- [x] setAssign() 메소드 명을 좀 더 직관적으로 변경
- [x] Binder -> BindingAdapter 로 변경

## 2단계 기능 요구사항

- [ ] 메뉴에서 2인을 선태하면 2인 모드로 전환한다.
- [ ] 메뉴에서 랜덤을 선택하면 랜덤 모드로 전환한다.
- [ ] 앱을 시작하면 랜덤 모드로 시작한다

### 랜덤 모드

- [ ] 유저는 X를 표기하고 AI는 O를 표기한다.
- [ ] AI는 남은 칸 중에서 랜덤으로 표기한다.

## 프로그래밍 요구사항

- [ ] 자동으로 표기하는 로직은 domain 모듈에 위치한다.
- [ ] 단위 테스트에서 무작위성을 제거한다.

## 구현

- [x] 게임의 모드를 나타내는 sealed class `GameMode` 를 추가한다.
    - [x] `Game` 클래스의 기본 모드는 `RandomMode` 이다.
    - [ ] `RandomMode`는 전략을 주입받는다.
    - [x] `GameState` 클래스에 `gameMode` 프로퍼티를 추가한다.
    - [x] `changeMode` 메소드를 통해 게임모드를 변경할 수 있다.
        - [x] 이미 같은 모드로는 변경 불가능하다.
