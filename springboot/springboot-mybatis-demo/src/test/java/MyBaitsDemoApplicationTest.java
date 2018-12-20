import com.main.MyBatisTkApplication;
import com.main.entity.SysPerson;
import com.main.manager.SysPersonManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisTkApplication.class)
@Slf4j
public class MyBaitsDemoApplicationTest {

    @Autowired
    SysPersonManager personManager;

    @Test
    public void testQueryPerson(){
        personManager.selectAll();
    }

}
