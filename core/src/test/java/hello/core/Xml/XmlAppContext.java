package hello.core.Xml;

import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        MemberRepository memberService = ac.getBean("memberService", MemberRepository.class);
        assertThat(memberService).isInstanceOf(MemberRepository.class);

    }
}
