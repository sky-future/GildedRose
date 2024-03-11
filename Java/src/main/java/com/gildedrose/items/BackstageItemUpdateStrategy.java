package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtility;

public class BackstageItemUpdateStrategy implements UpdateQualityStrategyInterface{
    @Override
    public void updateQuality(Item item) {
        ItemUtility.increaseQuality(item);
        if (item.sellIn < 11) {
            ItemUtility.increaseQuality(item);
        }
        if (item.sellIn < 6) {
            ItemUtility.increaseQuality(item);
        }
        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
