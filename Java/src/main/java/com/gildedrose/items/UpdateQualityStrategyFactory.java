package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.enums.ItemNameEnum;

public class UpdateQualityStrategyFactory {

    public static UpdateQualityStrategyInterface getStrategy(Item item){
        ItemNameEnum itemName = ItemNameEnum.fromString(item.name);
        switch (itemName){
            case AGED_BRIE:
                return new AgedBrieItemUpdateStrategy();
            case BACKSTAGE_PASSES:
                return new BackstageItemUpdateStrategy();
            case SULFURAS:
                return new SulfureItemUpdateStrategy();
            default:
                return new RegularItemUpdateStrategy();
        }
    }
}
