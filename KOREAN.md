# 스프렉서 (Sprexor) - 0.2.7 Beta
- Sprexor
  - Sprexor s = new Sprexor(); : 스프렉서 초기화
  - boolean isExist(String) : 이 커맨드가 존재하는지 확인합니다
  - String ~~getList()~~ : 명명어 리스트 반환하는데 @Deprecated 됨
  - void setComment(String) : 주석을 사용할 문구를 정합니다.
  - void register(String str, CommandProvider cp, String hd) : str( 커맨드 이름), cp(아래 참조), hd(help 명령어에서 사용될 도움말)
  - void exec(String com) throws CommandNotFoundException: com 이라는 문자열을 파싱 후, 자동으로 실행해줍니다.
  - void exec(String id, String[] args)
  - void useSyntax(boolean b) : 기본 문법을 검사할건지의 여부
  - void ~~initScope()~~ @Deprecated (constructor 로 병합됨)
  - void importSprex(CommandProvider t | CommandProvider[] t) : t라는 클래스로부터 스프렉서 를 임폴트합니다
  - void send(String, IOCenter.TYPE) : 인스턴트 메세지를 전송합니다.
  - void activate() : 이것이 실행되어야만 exec메소드를 포함한 몇가지 메서드들를 실행할 수 있으며, 만일 실행되었을 경우 상세 설정을 할 수 없습니다. (충돌 방지)
  - void call(문자열 키) : 키 값을 통해 정해진 명령을 실행합니다. 사용 가능 키: entry_on, entry_off

 - IOCenter : 코드의 꼬임을 방지하기 위해 만든 클래스
   - enum IOCenter.TYPE {STDOUT, CMT, ERR, NO_VALUE, custom1, custom2, custom3}
   - IOCenter ioc = new IOCenter(Sprexor sp); sp로부터 출력 결과를 가져옴
   - Object[] getMessage() : 최근 메세지를 반환합니다.. 목차 0 : 출력, 목차 1 : TYPE
   - Vector<Object[]> getOuput() : 출력된 모든 메세지를 반환합니다.
   - exitEntry() : 엔트리 모드를 종료합니다. 
 
  - CommandProvider
    - public Object code(String[] args, boolean[] isWrapped, GlobalData gd) : arg(인자), isWrapped[i](arg[i]가 ' 또는 "로 묶였는지 여부), gd (GlobalData로부터 저장하거나 삭제할 수 있습니다. )
    - public default Object emptyArgs() : exec에서 들어온 매개변수가 없으면 실행됩니다. (재정의 가능)
    - public default error(Exception) : 오버라이드된 code 메소드에서 오류날 경우 이 메소드가 실행됩니다.(register로부터). (재정의 가능)
    - public default getCommandName() : 임폴트 전용 메소드
    - public default String help() : 임폴트 전용 메소드
    - EntryMode : 이 메서드의 반환 값이 null이 아닐 경우 그 엔트리 모드가 끝날 때까지 이 메서드가 실행됩니다. (확장 용이성)

  - Tools
    - String Processer(String opt) : 서브프로세스 사용 (window : exe, linux : sh)
    - byte AnalOption(String, boolean[])
    - boolean OptionPrs(String, String, byte) throws Exception
    - String[] binder (문자열 배열, 시작값) : 문자열 배열에서 사직값부터 마지막 값까지 묶어 반환합니다.
    
  - GlobalData : 삭제하거나 바꾸고자 할 경우 그 키의 이름의 마지막 글자가 _이면 읽기 전용이라고 정했습니다. 따라서 삭제나 변경은 불가능합니다.
    - GlobalData gd = new GlobalData()
    - void putData(String key, Object value)
    - Object getData(String key)
    - void removeData(String key)
    - boolean existData(Obejct data)
    - boolean existKey(String key)
    - boolean modify(String key, Object data)
    - boolean reset()
    - boolean forceReset()
    
  - sprexor.cosmos.BasicPackages implement sprexor.CommandProvider : 이걸 어떻게 사용할 지 알려주고, 미래에 많은 기능이 추가될 것입니다.
    - "example" 샘플입니다.

## basic feature
  - @(name) :  저장된 값을 불러옵니다.
  - var (name) (value) : 이름=값 으로 정의합니다.
  - echo
  - help : 기능 향상됨.
  - delete (name) : 변수 삭제함
<br>