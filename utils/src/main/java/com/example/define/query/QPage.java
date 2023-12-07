package com.example.define.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QPage {

    private Integer page;
    private Integer size;

    static Integer DEF = 10;

    //
    static String KEY_PAGE_FROM_MAP = "page";
    static String KEY_SIZE_FROM_MAP = "size";

    static String KEY_LIMIT_START = "star";
    static String KEY_LIMIT_OFFSET = "offset";

    /**
    * 为了方便 IPAGE
    * @params
    * @return
    */
    public static Integer easyCurrent(Map<String, Object> map) {
        try {
            return Integer.parseInt(map.get(KEY_PAGE_FROM_MAP).toString());
        } catch (Exception ignored) { return 1; }
    }
    public static Integer easySize(Map<String, Object> map) {
        try {
            return Integer.parseInt(map.get(KEY_SIZE_FROM_MAP).toString());
        } catch (Exception ignored) { return DEF; }
    }

    /**
    * 取出 MAP 內的 值
    * @params MAP
    * @return ME
    */
    public static QPage ofMap(Map<String, Object> map) {
        int p = 1;
        int s = DEF;

        if (map != null) {
            Object _p = map.get(KEY_PAGE_FROM_MAP);
            if (_p instanceof Long || _p instanceof Integer || _p instanceof String) {
                p = Integer.parseInt(_p.toString());
            }
            Object _s = map.get(KEY_SIZE_FROM_MAP);
            if (_s instanceof Long || _s instanceof Integer || _s instanceof String) {
                s = Integer.parseInt(_s.toString());
            }
        }

        return new QPage(p, s);
    }

    /**
     * 轉為 MAP
     * @return HASHMAP
     * @params NUII
     */
    public HashMap<String, Integer> result() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(KEY_PAGE_FROM_MAP, this.page);
        map.put(KEY_SIZE_FROM_MAP, this.size);
        map.put(KEY_LIMIT_START, numStar());
        map.put(KEY_LIMIT_OFFSET, this.size);
        return map;
    }

    public Integer numStar() { return (this.page - 1) * this.size; }
}