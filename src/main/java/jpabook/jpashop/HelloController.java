package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {  //model에 데이터를 실어서 view에 넘길 수 있음
        model.addAttribute("data", "hello!!!");     //data라는 key에 값을 hello!!!로 설정해서 넘김
        return "hello";     //view Name임
    }
}
