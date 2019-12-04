import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午10:47 2018/1/17
 * @Modifyed By:
 */
public class plant<r> {
    private List<r> list = new ArrayList<r>(400);

    public r get() {
        return list.get(1);
    }
}
