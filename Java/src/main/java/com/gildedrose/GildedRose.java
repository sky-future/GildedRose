package com.gildedrose;

import static com.gildedrose.enums.ItemNameEnum.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item item : items) {
            updateInventoryItem(item);
        }
    }

    private static void updateInventoryItem(Item item) {
        updateItemQuality(item);
        updateItemSellIn(item);
        if (isExpired(item)) {
            expiredSellIn(item);
        }
    }

    private static void updateItemQuality(Item item) {
        if (item.name.equals(AGED_BRIE.getValue())) {
            increaseQuality(item);
        } else if (item.name.equals((BACKSTAGE_PASSES.getValue()))) {
            increaseQuality(item);
            if (item.sellIn < 11) {
                increaseQuality(item);
            }

            if (item.sellIn < 6) {
                increaseQuality(item);
            }

        } else if (item.name.equals(SULFURAS.getValue())) {
            return;
        } else decreaseQuality(item);
    }

    private static void updateItemSellIn(Item item) {
        if (item.name.equals(SULFURAS.getValue())) {
            return;
        }
        item.sellIn--;
    }

    private static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private static void expiredSellIn(Item item) {
        if (item.name.equals(AGED_BRIE.getValue())) {
            increaseQuality(item);
        }
        else if (item.name.equals(BACKSTAGE_PASSES.getValue())) {
            item.quality = 0;
        }
        else if (item.name.equals(SULFURAS.getValue())) {
                return;
            }
        else {
                decreaseQuality(item);
            }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }


}
