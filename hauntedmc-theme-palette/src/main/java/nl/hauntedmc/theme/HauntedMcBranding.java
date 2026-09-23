package nl.hauntedmc.theme;

import net.kyori.adventure.text.format.TextColor;

import java.util.List;

/** Canonical HauntedMC wordmark and signature for MiniMessage and FeatureFramework. */
public final class HauntedMcBranding {
    /** The first segment of the wordmark, with its exact small-cap lettering. */
    public static final String HAUNTED_TEXT = "ʜᴀᴜɴᴛᴇᴅ";
    /** The second segment of the wordmark, with its exact small-cap lettering. */
    public static final String MC_TEXT = "ᴍᴄ";
    /** The exact stylized brand signature. */
    public static final String SINCE_TEXT = "𝕤𝕚𝕟𝕔𝕖 𝟚𝟘𝟙𝟛";

    /** FeatureFramework item for the blue wordmark segment. */
    public static final String HAUNTED_GRADIENT_ITEM = "WordmarkHaunted";
    /** FeatureFramework item for the orange wordmark segment. */
    public static final String MC_GRADIENT_ITEM = "WordmarkMc";

    /** Wordmark fragment for strings processed by the FeatureFramework theme expander. */
    public static final String THEME_WORDMARK = "<bold>"
            + themeTag(HAUNTED_GRADIENT_ITEM) + HAUNTED_TEXT + "</HauntedMC>"
            + themeTag(MC_GRADIENT_ITEM) + MC_TEXT + "</HauntedMC></bold>";
    /** Signature fragment for strings processed by the FeatureFramework theme expander. */
    public static final String THEME_SINCE = HauntedMcColor.BRAND_SINCE.themeTag()
            + SINCE_TEXT + "</HauntedMC>";

    /** Standard MiniMessage wordmark fragment for use without FeatureFramework. */
    public static final String MINIMESSAGE_WORDMARK = "<bold>"
            + gradientTag(hauntedGradient()) + HAUNTED_TEXT + "</gradient>"
            + gradientTag(mcGradient()) + MC_TEXT + "</gradient></bold>";
    /** Standard MiniMessage signature fragment for use without FeatureFramework. */
    public static final String MINIMESSAGE_SINCE = HauntedMcColor.BRAND_SINCE.miniMessageTag()
            + SINCE_TEXT + "</color>";

    private HauntedMcBranding() {
    }

    /**
     * Returns the immutable blue gradient stops in reading order.
     *
     * @return the Haunted segment gradient stops
     */
    public static List<TextColor> hauntedGradient() {
        return List.of(HauntedMcColor.WORDMARK_HAUNTED_START.textColor(),
                HauntedMcColor.WORDMARK_HAUNTED_END.textColor());
    }

    /**
     * Returns the immutable orange gradient stops in reading order.
     *
     * @return the MC segment gradient stops
     */
    public static List<TextColor> mcGradient() {
        return List.of(HauntedMcColor.WORDMARK_MC_START.textColor(),
                HauntedMcColor.WORDMARK_MC_END.textColor());
    }

    private static String themeTag(String itemId) {
        return '<' + HauntedMcColor.THEME_IDENTIFIER + ':' + itemId + '>';
    }

    private static String gradientTag(List<TextColor> stops) {
        return "<gradient:" + stops.get(0).asHexString() + ':' + stops.get(1).asHexString() + '>';
    }
}
