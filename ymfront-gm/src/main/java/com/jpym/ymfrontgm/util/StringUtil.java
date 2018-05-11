package com.jpym.ymfrontgm.util;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.ListOrderedMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringUtil {
    public static Map<String, Object> jsonToMap(Object jsonStr) {
        ListOrderedMap map = new ListOrderedMap();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(jsonToMap(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    public static boolean empty(final String str) {

        return (str == null) || (str.length() == 0);
    }

    public static boolean notEmpty(final String str) {

        return !empty(str);
    }

    public static String replaceStringByMap(String originalStr, List anwserPointMap) throws Exception {
        String pointString = "";
        String anwserString = originalStr;
        String[] answerList = anwserString.split("\\|");
        if (answerList.length != anwserPointMap.size())
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, " [questionsize] is " + anwserPointMap.size());
        for (int i = 0; i < answerList.length; i++) {
            String answer = answerList[i];
            JSONObject anwserPoint = JSONObject.fromObject(anwserPointMap.get(i));
            if (answer.contains(",")) {
                String[] mulAnswerList = answer.split(",");
                for (String singleAnswer : mulAnswerList) {
                    if (anwserPoint.containsKey(singleAnswer)) {
                        pointString += anwserPoint.getString(singleAnswer) + ",";
                    } else {
                        throw new YmfrontGmException(MsgConstant.ERROR_CODE, " [questioncode] " + i++ + "【answer】" + singleAnswer + " is Wrong");
                    }
                }
                pointString = pointString.substring(0, pointString.length() - 1);
                pointString += "|";
            } else {
                if (anwserPoint.containsKey(answer)) {
                    pointString += anwserPoint.getString(answer) + "|";
                } else {
                    throw new YmfrontGmException(MsgConstant.ERROR_CODE, " [questioncode] " + i++ + "【answer】" + answer + " is Wrong");
                }
            }

        }
        pointString = pointString.substring(0, pointString.length() - 1);
        return pointString;
    }

}
