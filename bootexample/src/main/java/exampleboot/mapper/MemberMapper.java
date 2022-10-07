package exampleboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper {
    @Select("select * from member where email=#{email}" )
    public Member getLoginInfo(@Param("email")String email)throws Exception;
    @Select("select * from member limit 100" )
    public List<Member> getMemberList() throws Exception;
    @Select("select * from Member where email=#{email} and pwd=#{pwd}")
    public Member getLoginMember(@Param("email")String email, @Param("pwd")String pwd)throws Exception;
}
