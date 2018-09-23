package com.tl.dubbo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 滕柳 on 2018/8/12.
 */
public class DemoServiceImpl implements DemoService {


    @Override
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

    @Override
    public Integer getResult() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
