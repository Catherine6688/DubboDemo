package com.tl.dubbo;

import java.util.List;

/**
 * Created by 滕柳 on 2018/8/12.
 */
public interface DemoService {

    List<String> getPermissions(Long id);

    public Integer getResult();


}
