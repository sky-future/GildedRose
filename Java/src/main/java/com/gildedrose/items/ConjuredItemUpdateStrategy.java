package com.gildedrose.items;

import com.gildedrose.Item;

public class ConjuredItemUpdateStrategy implements UpdateQualityStrategyInterface{
    @Override
    public void updateQuality(Item item) {
        // decrease quality twice as fast as normal items according to the SellIn value
        int degradationRate = item.sellIn > 0 ? 2 : 4;
        int newQuality = item.quality - degradationRate;
        // check if the new quality is negative
        item.quality = Math.max(0, newQuality);
        item.sellIn--;
    }
}
