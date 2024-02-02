package Test_.java;

/**
 * @author yangda
 * @create 2022-11-02-22:28
 */

public class G {
    protected String g = "1";


    public String getG() {

        return this.g;
    }
}

class BB extends G {
    protected String g = "2";//

    public String getG() {//
        return g;//
    }

    public static void main(String[] args) {
        BB x = new BB();
        System.out.println(x.getG());
    }
}