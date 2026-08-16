package nl.hauntedmc.theme;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HauntedMcColorTest {
    @Test
    void exposesCanonicalPaletteInStableOrder() {
        assertEquals(List.of("#A855F7", "#38BDF8", "#4ADE80", "#FACC15", "#FB7185", "#94A3B8", "#E2E8F0"),
                java.util.Arrays.stream(HauntedMcColor.values()).map(HauntedMcColor::hex).toList());
        assertEquals("<HauntedMC:Brand>", HauntedMcColor.BRAND.themeTag());
        assertEquals("<color:#4ADE80>", HauntedMcColor.SUCCESS.miniMessageTag());
    }
}
