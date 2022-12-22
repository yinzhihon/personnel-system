import com.personnel.pojo.PageBean;
import com.personnel.pojo.Person;
import com.personnel.service.PersonService;
import com.personnel.utils.SpringUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PersonServiceTest {

    static {
        //初始化加载applicationContext.xml
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SpringUtil springUtil = new SpringUtil();  //自动实现了springUtil.setApplicationContext(app)
    }

    @Test
    public void selectAll(){
        PersonService personService = (PersonService) SpringUtil.getBean("personService");
        List<Person> peoples = personService.selectAll();
        System.out.println(peoples);
    }

    @Test
    public void selectPageCondition(){

        Person person = new Person();

        PersonService personService = (PersonService) SpringUtil.getBean("personService");
        PageBean<Person> personPageBean = personService.selectPageCondition(person, 1, 5);
        System.out.println(personPageBean);

    }

    @Test
    public void selectCount(){

        Person person = new Person();
        person.setJobName("实习员工");

        PersonService personService = (PersonService) SpringUtil.getBean("personService");
        Integer count = personService.selectCount(person);
        System.out.println(count);

    }


}
