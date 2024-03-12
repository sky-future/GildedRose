package com.gildedrose.utils;

import com.gildedrose.Item;

public class ItemUtility {

    static final int MAX_QUALITY = 50;
    static final int MIN_QUALITY = 0;
    public static void decreaseQuality(Item item) {
        if (item == null) {
            return;
        }

        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }

    public static void increaseQuality(Item item) {
        if (item == null) {
            return;
        }

        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }
}
