package com.gildedrose.items;

import com.gildedrose.Item;

public class SulfureItemUpdateStrategy implements UpdateQualityStrategyInterface{
    @Override
    public void updateQuality(Item item) {
        // Sulfuras never has to be sold or decreases in Quality
    }
}
