package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.util.Column;
import com.jpym.ymfrontgm.util.StringUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class FieldMapperService {


    /**
     * 对数组中的数据进行字段简化
     *
     * @param tradeDatas  原始交易数组
     * @param transMapper 转化后的数据
     */
    public <T> T getNewResults(T tradeDatas, Class transMapper) {
        T tradeDatasTransfer = null;
        Map tradeDatasMap = new HashMap();
        if (tradeDatas instanceof List) {
            List tradeDatasListTransfer = new ArrayList();
            //原始数据为数组
            for (int i = 0; i < ((List) tradeDatas).size(); i++) {
                Map<String, Object> listMap = (Map<String, Object>) ((List) tradeDatas).get(i);
                //每一个数据对象进行字段映射
                tradeDatasMap = changeFieldNameAndType(listMap, transMapper);
                tradeDatasListTransfer.add(tradeDatasMap);
            }
            tradeDatasTransfer = (T) tradeDatasListTransfer;
        }
        if (tradeDatas instanceof Map) {
            tradeDatasMap = changeFieldNameAndType((Map<String, Object>) tradeDatas, transMapper);
            tradeDatasTransfer = (T) tradeDatasMap;
        }
        return tradeDatasTransfer;
    }

    /**
     * 对原始数据按照transMapper定义的值为准进行字段简化
     *
     * @param order       原始数据
     * @param transMapper 需求的数据
     */
    private Map<String, Object> changeFieldNameAndType(Map order, Class transMapper) {
        Field[] fields = transMapper.getDeclaredFields();
        Map<String, Object> orderMap = new HashMap<>();
        Set dataKey = order.keySet();
        for (Object k : dataKey) {
            for (Field field : fields) {
                boolean isExists = field.isAnnotationPresent(Column.class);
                if (!isExists) {
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                if (k.toString().equals(columnName)) {
                    String value = order.get(k.toString()).toString();
                    orderMap.put(field.getName(), value);
                }
                if (StringUtil.notEmpty(columnName) && !dataKey.contains(columnName)) { //以定义Column值为key(原始数据中没有的字段)
                    orderMap.put(columnName, "");
                }
            }
        }
        return orderMap;
    }
}
