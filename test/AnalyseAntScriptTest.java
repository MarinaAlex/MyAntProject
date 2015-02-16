
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import java.util.ArrayList;


public class AnalyseAntScriptTest  {
    private ArrayList<String> instance;



    @Before
    public void setUp() {
        String a1 = "run";
        String a2 = "compile";
        String a3 = "jar";
        instance.add(a1);
        instance.add(a2);
        instance.add(a3);

}
    @Test
    public void listTargetIsTrue(){
        ArrayList<String> list = new ArrayList<String>();
        String a1 = "run";
        String a2 = "compile";
        String a3 = "jar";
        list.add(a1);
        list.add(a2);
        list.add(a3);
        assertTrue("It is true", list.equals(instance));
    }

    @Test
    public void listTargetNoTrue(){
        ArrayList<String> list = new ArrayList<String>();
        String a1 = "all";
        String a2 = "compile";
        String a3 = "jar";
        list.add(a1);
        list.add(a2);
        list.add(a3);
        assertTrue("It is true",!list.equals(instance));
    }


}