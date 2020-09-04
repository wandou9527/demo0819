java里有数字long来表示大的整数，如果两个数字的范围超过了long，要做加法算法怎么做呢？
这个问题在面试中经常碰到，如果之前没有经历的，可能一时有点想不起来怎么做。
下面我们来分析一下，两个数字超过了long的范围，那显然不能用java中的基本数字类型来计算了。
我们可以想小时候刚学习加法的竖式运算，个位对个位，十位对十位，百位对百位、、、以此类推。当个位满十向十位进1，十位满十向百位进1、、、以此类推，就这样完成了运算。
表示出来如下：

```java
    1 4 5
  + 2 6 9
-------------
    4 1 4  
```
受此启发我们可以做这道题了，数字如果超过了long的范围那显然在java里是以String类型呈现的，下面我们上代码。

```java
package com.wandou.demo.algorithm.post;

import org.junit.Test;

/**
 * @author liming
 * @date 2020-08-19
 * @description 算法
 */
public class BigNumAddDemo {

    @Test
    public void bigintegerAddTest() {
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        bigNumAdd("111", "2010");
        bigNumAdd(Long.MAX_VALUE + "", Long.MAX_VALUE + "");
        bigNumAdd(Long.MAX_VALUE + "", "10");
    }

    public void bigNumAdd(String strNum1, String strNum2) {
        char[] chars1 = strNum1.toCharArray();
        char[] chars2 = strNum2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        boolean oneBiger = len1 > len2;
        int len = Integer.max(len1, len2);
        StringBuilder targetSb = new StringBuilder();
        //进位
        int carry = 0;
        for (int i = 0; i < len; i++) {
            int temp;
            int idx1 = len1 - 1 - i;
            int idx2 = len2 - 1 - i;
            if (oneBiger) {
                if (i < len2) {
                    temp = Integer.parseInt(chars1[idx1] + "") + Integer.parseInt(chars2[idx2] + "") + carry;
                } else {
                    temp = Integer.parseInt(chars1[idx1] + "") + carry;
                }
            } else {
                if (i < len1) {
                    temp = Integer.parseInt(chars1[idx1] + "") + Integer.parseInt(chars2[idx2] + "") + carry;
                } else {
                    temp = Integer.parseInt(chars2[idx2] + "") + carry;
                }
            }
            carry = 0;
            if (temp > 9) {
                carry = 1;
                targetSb.append(temp - 10);
            } else {
                targetSb.append(temp);
            }
        }
        if (carry > 0) {
            targetSb.append(carry);
        }
        System.out.println(targetSb.reverse().toString());
    }

}

```

测试结果：
![测试结果](https://img-blog.csdnimg.cn/20200821143932953.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmRvdTk1Mjc=,size_16,color_FFFFFF,t_70#pic_center)

==========================================================
### 算法优化：
优化第二版：[https://blog.csdn.net/wandou9527/article/details/108122925](https://blog.csdn.net/wandou9527/article/details/108122925)



