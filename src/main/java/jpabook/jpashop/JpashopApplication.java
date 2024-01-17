package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule.Feature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();
		System.out.println("data : " + data);

		SpringApplication.run(JpashopApplication.class, args);
	}

	/**
	 * 엔티티를 사용할 수 있는 방법. 이지만 사용하지 말자! 성능이 너무 떨어짐.
	 */

	//초기화된 프록시 객체만 노출, 초기화되지 않은 경우 노출시키지 않음.
//	@Bean
//	Hibernate5JakartaModule hibernate5Module() {
//		return new Hibernate5JakartaModule();
//	}

	//강제로 노출시킬수도 있음. Json 생성 시점에 LAZY 로딩을 진행시킴.
	@Bean
	Hibernate5JakartaModule hibernate5JakartaModule() {
		Hibernate5JakartaModule hibernate5JakartaModule = new Hibernate5JakartaModule();
		//해당 옵션을 중지하고 컨트롤러 내의 메서드에서 강제 초기화 할 수도 있음.
//		hibernate5JakartaModule.configure(Feature.FORCE_LAZY_LOADING, true);
		return hibernate5JakartaModule;
	}
}
