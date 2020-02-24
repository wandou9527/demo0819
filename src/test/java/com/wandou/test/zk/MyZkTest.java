package com.wandou.test.zk;

import com.sun.javafx.sg.prism.EffectFilter;
import com.wandou.demo.MyZkUtil;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;

public class MyZkTest {

    @Test
    public void m1() throws Exception {
        String parentPath = "/aaa/b2";
        String path = "/aaa/b2/c5";
        CuratorFramework zkCli = MyZkUtil.getZkCli();
        Stat stat = zkCli.checkExists().forPath(path);
        System.out.println("stat = " + stat);
        if (stat == null) {
            zkCli.create().creatingParentsIfNeeded().forPath(path, "2020".getBytes());
        }
        byte[] data = zkCli.getData().forPath(path);
        System.out.println(new String(data));
        List<String> childrens = zkCli.getChildren().forPath(parentPath);
        System.out.println("childrens = " + childrens);

    }

    public void m4() {
        System.out.println("nihaoo hahah lllfd");
        
    }


}
