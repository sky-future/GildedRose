package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    private void testUpdateInventory(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(expectedSellIn, gildedRose.items[0].sellIn);
        assertEquals(expectedQuality, gildedRose.items[0].quality);
    }

    // Regular Item
    @ParameterizedTest
    @MethodSource("regularItemUpdateScenarios")
    @DisplayName("Regular Item Update Scenarios")
    void regularItemUpdates(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        testUpdateInventory(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    public static Stream<Arguments> regularItemUpdateScenarios() {
        return Stream.of(
            // Each day both values are lowered for every item
            Arguments.of("Regular Item", 10, 20, 9, 19),
            // Sell by date has passed, Quality degrades twice as fast
            Arguments.of("Regular Item", 0, 20, -1, 18),
            // The quality of an item is never nagative
            Arguments.of("Regular Item", 0, 1, -1, 0)
        );
    }

    //Aged Brie
    @ParameterizedTest
    @MethodSource("agedBrieUpdateScenarios")
    @DisplayName("Aged Brie Updates")
    void agedBrieUpdates(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        testUpdateInventory(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> agedBrieUpdateScenarios() {
        return Stream.of(
            // Aged Brie increases in quality the older it gets
            Arguments.of("Aged Brie", 5, 10, 4, 11),
            // The quality is never above 50
            Arguments.of("Aged Brie", 2, 50, 1, 50),
            Arguments.of("Aged Brie", -2, 40, -3, 42)
        );
    }

    // Sulfuras
    @ParameterizedTest
    @MethodSource("sulfurasUpdateScanarios")
    @DisplayName("Sulfuras Updates")
    void sulfurasUpdates(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        testUpdateInventory(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> sulfurasUpdateScanarios() {
        return Stream.of(
            // Sulfuras never has to be sold and never decreases in quality
            Arguments.of("Sulfuras, Hand of Ragnaros", 0, 80, 0, 80)
        );

    }

    // Backstage Passes
    @ParameterizedTest
    @MethodSource("backstagePassUpdateScenarios")
    @DisplayName("Backstage Passes Updates")
    void backstagePassesUpdates(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        testUpdateInventory(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> backstagePassUpdateScenarios() {
        return Stream.of(
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 11, 10, 10, 11), // SellIn > 10
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 10, 10, 9, 12), // 10 >= SellIn > 5
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 5, 10, 4, 13), // 5 >= SellIn > 0
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 0, 10, -1, 0), // SellIn = 0
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 5, 50, 4, 50), // SellIn < 10, Quality = 50
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 0, 50, -1, 0) // SellIn = 0, Quality = 50
        );
    }

    // Conjured Items
    @ParameterizedTest
    @MethodSource("conjuredItemUpdateScenarios")
    @DisplayName("Conjured Items Updates")
    void smellyItemsUpdates(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        testUpdateInventory(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    // Falling for the moment because code not implemented yet !
    private static Stream<Arguments> conjuredItemUpdateScenarios() {
        return Stream.of(
            Arguments.of("Conjured", 10, 10, 9, 8) // Quality decreases twice as fast
        );
    }
}
