package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtility;

public class RegularItemUpdateStrategy implements UpdateQualityStrategyInterface{
    @Override
    public void updateQuality(Item item) {
        ItemUtility.decreaseQuality(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            ItemUtility.decreaseQuality(item);
        }
    }
}
