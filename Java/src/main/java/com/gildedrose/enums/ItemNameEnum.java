package com.gildedrose.enums;

public enum ItemNameEnum {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured");

    public final String itemName;


    ItemNameEnum(String itemName) {
        this.itemName = itemName;
    }

    public String getValue() {
        return itemName;
    }

}
