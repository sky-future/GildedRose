package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtility;

public class AgedBrieItemUpdateStrategy implements UpdateQualityStrategyInterface{
    @Override
    public void updateQuality(Item item) {
        ItemUtility.increaseQuality(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            ItemUtility.increaseQuality(item);
        }
    }
}
