package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gildedrose.enums.ItemNameEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("foo", app.items[0].name);
    }

    private void testUpdateInventory(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateInventory();
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
            Arguments.of(AGED_BRIE.getValue(), 5, 10, 4, 11),
            // The quality is never above 50 for Bries
            Arguments.of(AGED_BRIE.getValue(), 2, 50, 1, 50),
            // The quality increases twice as fast after the sell by date for Brie
            Arguments.of(AGED_BRIE.getValue(), -2, 40, -3, 42)
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
            Arguments.of(SULFURAS.getValue(), 0, 80, 0, 80)
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
            // Quality increases by 1 when there are more than 10 days left
            Arguments.of(BACKSTAGE_PASSES.getValue(), 11, 10, 10, 11),
            // Quality increases by 2 when there are 10 days or less
            Arguments.of(BACKSTAGE_PASSES.getValue(), 10, 10, 9, 12),
            // Quality increases by 3 when there are 5 days or less
            Arguments.of(BACKSTAGE_PASSES.getValue(), 5, 10, 4, 13),
            // Quality drops to 0 after the concert
            Arguments.of(BACKSTAGE_PASSES.getValue(), 0, 10, -1, 0),
            // Quality is never above 50
            Arguments.of(BACKSTAGE_PASSES.getValue(), 5, 50, 4, 50),
            // Quality is never above 50 and gets to 0 after the concert
            Arguments.of(BACKSTAGE_PASSES.getValue(), 0, 50, -1, 0)
        );
    }

    // Conjured Items
    @ParameterizedTest
    @MethodSource("conjuredItemUpdateScenarios")
    @DisplayName("Conjured Items Updates")
    void conjuredItemsUpdate(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        testUpdateInventory(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> conjuredItemUpdateScenarios() {
        return Stream.of(
            // Quality decreases twice as fast
            Arguments.of(CONJURED.getValue(), 10, 10, 9, 8),
            // Quality decreases twice as fast after the sell by date
            Arguments.of(CONJURED.getValue(), 0, 10, -1, 6),
            // Quality is never negative
            Arguments.of(CONJURED.getValue(), 0, 1, -1, 0)
        );
    }
}
