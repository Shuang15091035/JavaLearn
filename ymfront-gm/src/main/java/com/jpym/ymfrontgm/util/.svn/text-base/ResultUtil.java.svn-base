package com.jpym.ymfrontgm.util;


import com.jpym.ymfrontgm.exception.YmfrontGmException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

public class ResultUtil {

    public static Result success(String code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Map resultSetPaging(List resultSet, int pageSize, int pageIndex) throws Exception {
        Map pageMap = new HashedMap();
        List pgList = new ArrayList();
        if (pageSize <= 0 || pageIndex <= 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[pageSize，pageIndex]为正整数");
        int totalPage1 = 0, resultSetSize = resultSet.size();
        if (resultSetSize / pageSize == 0 || resultSetSize % pageSize != 0) {
            totalPage1 = resultSetSize / pageSize + 1;
        } else {
            totalPage1 = resultSetSize / pageSize;
        }
        if (pageIndex <= totalPage1 && resultSetSize > 0) {
            if ((pageIndex - 1) * pageSize < resultSetSize && (pageIndex * pageSize) > resultSetSize) {
                pgList = resultSet.subList((pageIndex - 1) * pageSize, resultSetSize);
            }
            if (pageIndex * pageSize <= resultSetSize) {
                pgList = resultSet.subList((pageIndex - 1) * pageSize, (pageIndex * pageSize));
            }
        }
        pageMap.put("totalpage", totalPage1);
        pageMap.put("resultset", pgList);
        return pageMap;
    }

    public static List packetAccordingToName(String groupName, List originList) throws Exception {
        List totalGroupList = new ArrayList();
        TreeMap<String, List<Map<String, Object>>> totalGroupNameMap = new TreeMap<>();
        JSONArray resultList = JSONArray.fromObject(originList);
        int originListSize = resultList.size();
        String fundCode = null;
        for (int i = 0; i < originListSize; i++) {
            fundCode = resultList.getJSONObject(i).getString(groupName);
            if (totalGroupNameMap.containsKey(fundCode)) {
                ArrayList fundArr = (ArrayList) totalGroupNameMap.get(fundCode);
                fundArr.add(resultList.getJSONObject(i));
            } else {
                List<Map<String, Object>> groupList = new ArrayList<>();
                groupList.add(resultList.getJSONObject(i));
                totalGroupNameMap.put(fundCode, groupList);
            }
        }
        totalGroupList = convertKeyMapByName(totalGroupNameMap, "result", "resultpoint");
        return totalGroupList;
    }

    public static List convertKeyMapByName(TreeMap<String, List<Map<String, Object>>> originMap, String listKeyName, String listValueName) throws Exception {
        List answerPointList = new ArrayList();
        for (String questionCode : originMap.keySet()) {
            Map answerPointMap = new HashMap();
            for (Object question : originMap.get(questionCode)) {
                JSONObject answer = JSONObject.fromObject(question);
                answerPointMap.put(answer.getString(listKeyName), answer.getString(listValueName));
            }
            answerPointList.add(answerPointMap);
        }
        return answerPointList;
    }
}
