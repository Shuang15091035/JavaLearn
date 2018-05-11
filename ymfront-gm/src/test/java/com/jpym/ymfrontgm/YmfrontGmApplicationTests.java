package com.jpym.ymfrontgm;

import com.jpym.ymfrontgm.requestParam.QueryRequestParam;
import com.jpym.ymfrontgm.requestParam.RequestParamMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class YmfrontGmApplicationTests {

    @Test
    public void contextLoads() {
        RequestParamMap queryResult = QueryRequestParam.buildBydefault();
        queryResult.set(QueryRequestParam.FUNDCODE,"code");
        queryResult.set(QueryRequestParam.FUNDNAME,"name");
        queryResult.set(QueryRequestParam.FUNDTYPE,"type");
        queryResult.set(QueryRequestParam.PAGEINDEX,"index");
        int pause = 0;
    }

}
