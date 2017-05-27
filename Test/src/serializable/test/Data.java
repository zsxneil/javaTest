package serializable.test;


/**
 *@chenfeic 
 *
 *只是一个简单的类，用于测试序列化
 */
import java.io.Serializable;

public class Data implements Serializable {
    private static final long serialVersionUID = 7247714666080613254L;
    public int n;
    public Data(int n) {
        this.n = n;
    }
    public String toString(){
        return Integer.toString(n);
    }
}