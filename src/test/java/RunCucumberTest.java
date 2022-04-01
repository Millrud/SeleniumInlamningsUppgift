import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:/features/createUser.feature"},
        glue = {"createUserFeature"},
        tags = "@createAccount")
public class RunCucumberTest {
}


