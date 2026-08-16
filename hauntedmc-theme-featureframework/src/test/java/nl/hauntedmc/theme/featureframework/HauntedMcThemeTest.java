package nl.hauntedmc.theme.featureframework;

import nl.hauntedmc.featureframework.theme.ThemeColor;
import nl.hauntedmc.theme.HauntedMcColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class HauntedMcThemeTest {
    @Test
    void buildsAllThemeItemsFromThePalette() {
        assertEquals(HauntedMcColor.values().length, HauntedMcTheme.theme().items().size());
        assertSame(HauntedMcTheme.theme(), HauntedMcTheme.theme());
        assertEquals(HauntedMcColor.BRAND.textColor(),
                ((ThemeColor.Solid) HauntedMcTheme.theme().item("brand").orElseThrow().color()).color());
    }
}
