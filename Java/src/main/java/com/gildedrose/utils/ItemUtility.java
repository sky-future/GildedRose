package com.gildedrose.utils;

import com.gildedrose.Item;

public class ItemUtility {
    public static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    public static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
