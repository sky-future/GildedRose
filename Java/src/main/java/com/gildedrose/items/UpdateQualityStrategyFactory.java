package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.enums.ItemNameEnum;

public class UpdateQualityStrategyFactory {

    private UpdateQualityStrategyFactory() {
        // Prevent instantiation
        throw new IllegalStateException("Utility class");
    }

    public static UpdateQualityStrategyInterface getStrategy(Item item){
        ItemNameEnum itemName = ItemNameEnum.fromString(item.name);
        switch (itemName){
            case AGED_BRIE:
                return new AgedBrieItemUpdateStrategy();
            case BACKSTAGE_PASSES:
                return new BackstageItemUpdateStrategy();
            case SULFURAS:
                return new SulfureItemUpdateStrategy();
            case CONJURED:
                return new ConjuredItemUpdateStrategy();
            default:
                return new RegularItemUpdateStrategy();
        }
    }
}
