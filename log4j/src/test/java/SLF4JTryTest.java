import com.huafeng.practice.slf.SLF4JTry;
import org.junit.Test;

/**
 * Created by i067561 on 05/05/2017.
 */
public class SLF4JTryTest {

    @Test
    public void testLog4J () {
        delagateVargs();
    }

    private void delagateVargs (String... args){
        SLF4JTry.main(args);
    }
}
