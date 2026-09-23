package nl.hauntedmc.theme.featureframework;

import nl.hauntedmc.featureframework.theme.ThemeColor;
import nl.hauntedmc.theme.HauntedMcBranding;
import nl.hauntedmc.theme.HauntedMcColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class HauntedMcThemeTest {
    @Test
    void buildsAllThemeItemsFromThePalette() {
        assertEquals(HauntedMcColor.values().length + 2, HauntedMcTheme.theme().items().size());
        assertSame(HauntedMcTheme.theme(), HauntedMcTheme.theme());
        assertEquals(HauntedMcColor.BRAND.textColor(),
                ((ThemeColor.Solid) HauntedMcTheme.theme().item("brand").orElseThrow().color()).color());
        for (HauntedMcColor color : HauntedMcColor.values()) {
            assertEquals(color.textColor(),
                    ((ThemeColor.Solid) HauntedMcTheme.theme().item(color.itemId()).orElseThrow().color()).color());
        }
        assertEquals(HauntedMcBranding.hauntedGradient(),
                ((ThemeColor.Gradient) HauntedMcTheme.theme()
                        .item(HauntedMcBranding.HAUNTED_GRADIENT_ITEM).orElseThrow().color()).colors());
        assertEquals(HauntedMcBranding.mcGradient(),
                ((ThemeColor.Gradient) HauntedMcTheme.theme()
                        .item(HauntedMcBranding.MC_GRADIENT_ITEM).orElseThrow().color()).colors());
    }
}
