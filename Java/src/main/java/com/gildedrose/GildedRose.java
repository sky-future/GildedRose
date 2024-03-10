package com.gildedrose;

import static com.gildedrose.enums.ItemNameEnum.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE.getValue())
                    && !item.name.equals(BACKSTAGE_PASSES.getValue())) {
                if (item.quality > 0) {
                    if (!item.name.equals(SULFURAS.getValue())) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(BACKSTAGE_PASSES.getValue())) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS.getValue())) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE.getValue())) {
                    if (!item.name.equals(BACKSTAGE_PASSES.getValue())) {
                        if (item.quality > 0) {
                            if (!item.name.equals(SULFURAS.getValue())) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
