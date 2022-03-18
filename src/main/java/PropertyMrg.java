import java.io.IOException;
import java.util.Properties;
//读取配置文件
public class PropertyMrg {

    static Properties props=new Properties();
    static {
        try {
            props.load(PropertyMrg.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object get(String key){
        if(props==null) return null;
        else
            return props.get(key);
    }
}
