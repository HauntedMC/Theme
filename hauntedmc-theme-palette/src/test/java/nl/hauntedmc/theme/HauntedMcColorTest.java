package nl.hauntedmc.theme;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HauntedMcColorTest {
    @Test
    void exposesCanonicalPaletteInStableOrder() {
        assertEquals(List.of(
                "#A855F7", "#38BDF8", "#4ADE80", "#FACC15", "#FB7185", "#94A3B8", "#E2E8F0",
                "#F9A8D4", "#FB923C", "#D9C18A", "#5EEAD4", "#B8A4F8", "#BAC8D9",
                "#2A78F6", "#6ACDF7", "#FC7B2F", "#FB9A34", "#D0D6F0",
                "#AAB2C9", "#FFD79C", "#1C2F45", "#FFD166"),
                Arrays.stream(HauntedMcColor.values()).map(HauntedMcColor::hex).toList());
        assertEquals(List.of(
                "Brand", "Accent", "Success", "Warning", "Error", "Muted", "Text",
                "Social", "Server", "Economy", "Event", "Staff", "Detail",
                "WordmarkHauntedStart", "WordmarkHauntedEnd", "WordmarkMcStart", "WordmarkMcEnd",
                "BrandSince", "UiText", "UiHighlight", "UiDivider", "UiAmber"),
                Arrays.stream(HauntedMcColor.values()).map(HauntedMcColor::itemId).toList());
        assertEquals(HauntedMcColor.values().length,
                Set.copyOf(Arrays.stream(HauntedMcColor.values())
                        .map(color -> color.itemId().toLowerCase(Locale.ROOT)).toList()).size());
        assertEquals("<HauntedMC:Brand>", HauntedMcColor.BRAND.themeTag());
        assertEquals("<color:#4ADE80>", HauntedMcColor.SUCCESS.miniMessageTag());
        for (HauntedMcColor color : HauntedMcColor.values()) {
            assertEquals("<HauntedMC:" + color.itemId() + '>', color.themeTag());
            assertEquals("<color:" + color.hex() + '>', color.miniMessageTag());
        }
    }
}
