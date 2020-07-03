package com.jckj.materialmanagement.enumd;

/**
 * 字典类型
 */
public enum DictType {

    BRAND("brand", "品牌" ),
    UNIT("unit", "单位" ),
    CATEGORY("category", "商品分类" ),
    LOCATION("location", "库位");


    public final String type;
    public final String text;



    DictType(String type, String text) {
        this.type = type;
        this.text = text;

    }

    /**
     * 根据value返回枚举类型
     * @param type
     * @return
     */
    public static DictType getByType(String type) {
        for (DictType temp : values()) {
            if (temp.type.equals(type)) {
                return temp;
            }
        }
        return null;
    }

}
