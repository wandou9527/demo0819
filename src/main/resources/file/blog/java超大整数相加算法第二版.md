第二版针对第一版做了一定优化，代码和算法就是个不断优化的过程。具体题目背景请看第一版文章，
地址：[https://blog.csdn.net/wandou9527/article/details/108025167](https://blog.csdn.net/wandou9527/article/details/108025167)
大佬有更好更优雅的实现方式请评论区交流讨论。

直接上代码：

```java
import org.junit.Test;

/**
 * @author liming
 * @date 2020/8/20
 */
public class BigNumAddDemo {

    @Test
    public void bigintegerAddTest() {
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        bigNumAdd("112", "2019");
        bigNumAdd(Long.MAX_VALUE + "", Long.MAX_VALUE + "");
        bigNumAdd(Long.MAX_VALUE + "", "10");
    }

    public void bigNumAdd(String strNum1, String strNum2) {
        int len1 = strNum1.length();
        int len2 = strNum2.length();
        int maxLen = Integer.max(len1, len2);
        StringBuilder targetSb = new StringBuilder();
        //进位
        int carry = 0;
        for (int i = 0; i < maxLen; i++) {
            int temp = carry;
            carry = 0;
            if (i < len1) {
                temp += Integer.parseInt(strNum1.charAt(len1 - 1 -i) + "");
            }
            if (i < len2) {
                temp += Integer.parseInt(strNum2.charAt(len2 - 1- i) + "");
            }
            if (temp >= 10) {
                temp = temp - 10;
                carry = 1;
            }
            targetSb.append(temp);
        }
        if (carry > 0) {
            targetSb.append(carry);
        }
        System.out.println(targetSb.reverse().toString());
    }
}
```
