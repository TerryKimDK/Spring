package exampleboot.controller;

import exampleboot.mapper.Member;
import exampleboot.mapper.MemberMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController // 'json 데이터 형식으로 뿌려준다' 라는 의미 / spring에서는 requestbody 같은의미
//@Controller // return 하면 template에 파일이 있어야됨
public class MemberController {
    @RequestMapping("/hello")
    public String hello() {
        return "RestContorller";
    }

    @RequestMapping("/helloJson")
    public String helloJson() {
        String msg = "Spring Boot는 Rest방식으로 JSON / XML 데이터를 전송합니다.";
        return msg;
    }

    @Autowired
    MemberMapper mapper;

    @GetMapping("/jsonData/{email}")
    public ResponseEntity<Member> jsonData(@PathVariable String email){
        try {
            Member member = mapper.getLoginInfo(email);
            return new ResponseEntity<>(member, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.OK);
        }
    }

    @GetMapping("/jsonData/{email}/{pwd}")
    public ResponseEntity<Member> jsonData(@PathVariable String email,
                                           @PathVariable String pwd) {
        try {
            Member member = mapper.getLoginMember(email, pwd);
            return new ResponseEntity<>(member, HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.OK);
        }
    }

    @GetMapping("list")
    public ModelAndView list(){
        ModelAndView mav = null;
        try{
            mav = new ModelAndView("list");
            mav.addObject("member", mapper.getMemberList());

        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
}
