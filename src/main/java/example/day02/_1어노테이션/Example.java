package example.day02._1어노테이션;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class SuperClass { // 상위 클래스
    public void method1(){} // 메소드 == 함수 == 행위 == 이벤트

} // class end

class SubClass extends SuperClass{ // 하위 클래스
    // extends란? 현재 클래스가 extends 뒤에 오는 클래스로부터 상속받는다.
    // 상속 : 현실과 동일하게 상위/부모에게 멤버변수/메소드를 물려받는 행위
    // 오버라이딩 : 부모로부터 물려받은 메소드를 커스텀/재정의 하는 행위
    @Override   // @ : 어노테이션 , 오버라이딩 기능
    public void method1(){
        System.out.println("밥먹는다");
    }
    @Deprecated // @ : 어노테이션 , 해당 함수 사용하지 않음 알림 기능
    public void method2(){
        System.out.println("굶는다");
    }

} // class end

// [2] 어노테이션 만들기
@Retention(RetentionPolicy.RUNTIME) // 런타임(실행중)까지 유지 뜻
@Target(ElementType.METHOD)         // 해당 어노테이션 메소드에 적용 뜻
@interface Annotation1{
    // 추상메소드 : {} 구현부가 없는 메소드
    String value1();

} // annotation end

// [2] 어노테이션 사용하기
class TestClass1{
    @Annotation1(value1 = "어노테이션 주입")   // 내가 만든 어노테이션을 아래 코드에 런타임 시 주입
    public void method3(){ }
        // value1(); // [x] : 런타임(실행중)할때 사용하므로 직접적인 코드로 주입받는 게 아니다.
        // 즉] 프로그램 실행시 method3() 메소드가 실행되면 value1() 메소드 같이 실행된다.
}

public class Example {
    public static void main(String[] args) {
     // [1] 표준 어노테이션
     SubClass sub1 = new SubClass();
     sub1.method1();    // 오버라이딩
     sub1.method2();    // 자동완성 시 메소드명에 취소선 보임.
     sub1.method2();
     SuperClass sup1 = new SubClass();
     sup1.method1();
     sub1.method2();
     
     

    } // main end
} // class end
