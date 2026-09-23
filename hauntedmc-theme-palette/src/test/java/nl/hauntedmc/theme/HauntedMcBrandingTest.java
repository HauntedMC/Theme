package nl.hauntedmc.theme;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HauntedMcBrandingTest {
    @Test
    void exposesExactBrandingForBothLocalizationPaths() {
        assertEquals(List.of(HauntedMcColor.WORDMARK_HAUNTED_START.textColor(),
                HauntedMcColor.WORDMARK_HAUNTED_END.textColor()), HauntedMcBranding.hauntedGradient());
        assertEquals(List.of(HauntedMcColor.WORDMARK_MC_START.textColor(),
                HauntedMcColor.WORDMARK_MC_END.textColor()), HauntedMcBranding.mcGradient());
        assertThrows(UnsupportedOperationException.class,
                () -> HauntedMcBranding.hauntedGradient().clear());

        assertEquals("<bold><gradient:#2A78F6:#6ACDF7>ʜᴀᴜɴᴛᴇᴅ</gradient>"
                + "<gradient:#FC7B2F:#FB9A34>ᴍᴄ</gradient></bold>",
                HauntedMcBranding.MINIMESSAGE_WORDMARK);
        assertEquals("<color:#D0D6F0>𝕤𝕚𝕟𝕔𝕖 𝟚𝟘𝟙𝟛</color>", HauntedMcBranding.MINIMESSAGE_SINCE);
        assertEquals("<bold><HauntedMC:WordmarkHaunted>ʜᴀᴜɴᴛᴇᴅ</HauntedMC>"
                + "<HauntedMC:WordmarkMc>ᴍᴄ</HauntedMC></bold>", HauntedMcBranding.THEME_WORDMARK);
        assertEquals("<HauntedMC:BrandSince>𝕤𝕚𝕟𝕔𝕖 𝟚𝟘𝟙𝟛</HauntedMC>",
                HauntedMcBranding.THEME_SINCE);
    }
}
