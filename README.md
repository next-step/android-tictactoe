# Step4(2023-08-26) # 
    * 틱택토(Hilt 마이그레이션)
        * [x] 이전 단계의 기능 요구사항을 모두 만족해야 한다.
        * [x] Hilt를 통해 의존성을 주입한다.
        * [x] domain 모듈에서 외부로 노출될 필요가 없는 구현체들은 internal로 선언되어야 한다.
    * 1차 피드백
        * [x] Default & Impl 네이밍 겹치던 내용 수정
        * [x] app 모듈에서 hilt 사용
        * [x] put 메서드에서 tictactoeMap 파라미터로 받던 내용 수정
        * [x] ViewModel 에서 UseCase의 게임 모드에 따라서 틱택토 모드 변경
        * [x] TictactoeModule @Provides -> @Binds 수정

# Step3(2023-08-18) #
    * 틱택토(중급)
        * [x] 메뉴에서 중급를 선택하면 중급 모드로 전환한다.
        * [x] 앱을 시작하면 중급 모드로 시작한다.
        * [x] AI는 승리 직전의 상태(2목)이면 남은 한 자리에 둔다.
        * [x] AI는 상대가 승리 직전의 상태(2목)이면 남은 한 자리에 두어서 방해한다.
        * [x] 이외의 상황에서는 랜덤으로 둔다.
    * 1차 피드백
        * [x] 전략에서는 돌을 놓는 put만 가지도록 수정
        * [x] TictactocMap 에서 getGameResultFromSetMapPoint를 통해서 gameResult 호출하던 내용 수정
        * [x] TictactocMap 에서 다음에 랜덤으로 둘 곳 판별하는 로직 가지도록 수정
        * [x] RandomStrategy 에서 사용하는 함수들 통합 
        * [x] 랜덤 전략 테스트 코드 추가
    * 2차 피드백
        * [x] DefaultTictactoe 메서드 반환 타입 명시
        * [x] getGameResultFromSetMapPoint() 함수 역활 분리
        * [x] 대각선, 행, 열 다음 둘 곳 찾는 함수 통합

# Step2(2023-08-14) #
    * 틱택토(랜덤)
        * [x] 메뉴에서 2인을 선택하면 2인 모드로 전환한다.
        * [x] 메뉴에서 랜덤을 선택하면 랜덤 모드로 전환한다.
        * [x] 앱을 시작하면 랜덤 모드로 시작한다.
        * [x] 유저는 X를 표기하고 AI는 O를 표기한다.
        * [x] AI는 남은 칸 중에서 랜덤으로 표기한다.
    * 1차 피드백
        * [x] showToastMessage 함수 @StringRes 에노테이션 추가
        * [x] isFinish 함수명 수정(기능에 맞는 함수명으로)
        * [x] activity_tictactoc 내부 variable -> import로 수정(잘못된 값을 참조할 수 있으므로)
        * [x] Random, Player 기능을 하는 클래스 Tictactoc 상속 이용
        * [x] tictactocBoard 이중 배열 타입으로 지정되어 있던 내용 Board 클래스로 수정
    * 2차 피드백
        * [x] Board 클래스로 변경 & Empty 상수화
        * [x] GameResultManager 제거 & map으로 기능 이전
        * [x] Tictactoc 전략 인터페이스화 & DefaultTictactoc에 주입
        * [x] TictactocMap 초기화 부분 수정

# Step1(2023-08-13) #
    * 틱택토(2인)
        * [x] 앱을 시작하면 바로 틱택토 게임이 시작된다.
        * [x] X가 선수, O가 후수를 둔다.
        * [x] 유저가 직접 X, O를 표기하며 진행한다.
        * [x] 다시 시작하기 버튼을 누르면, 게임이 다시 시작된다.
        * [x] viewModel 테스트 코드 추가
        * [x] domain tictactoc 테스트 코드 추가
    * 1차 피드백
        * [x] TictactocToastMessage enum class message res 가지도록 수정
        * [x] TictactocToastMessage Event 래핑 추가
        * [x] View 에서 doamin Point 대신 TictactocCell import 하도록 수정
        * [x] 테스트 코드 assertEquals -> assertThat 수정
        * [x] 기존 map 객체 일급 컬렉션으로 수정
        * [x] viewModel 에서 값 검증하던 내용 수정
        * [x] viewModel board LiveData 값 수정( Array<Array<LiveData<Turn>>> -> LiveData<Array<Array<Turn>>> ) 