package com.gildedrose;

import com.gildedrose.items.UpdateQualityStrategyFactory;
import com.gildedrose.items.UpdateQualityStrategyInterface;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item item : items) {
            UpdateQualityStrategyInterface updateQualityStrategy = UpdateQualityStrategyFactory.getStrategy(item);
            updateQualityStrategy.updateQuality(item);
        }
    }

}
