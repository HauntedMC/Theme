package nl.hauntedmc.theme.featureframework;

import nl.hauntedmc.featureframework.theme.Theme;
import nl.hauntedmc.theme.HauntedMcColor;

/** FeatureFramework adapter for the canonical HauntedMC palette. */
public final class HauntedMcTheme {
    /** The stable name used to register the HauntedMC FeatureFramework theme. */
    public static final String IDENTIFIER = HauntedMcColor.THEME_IDENTIFIER;
    private static final Theme THEME = build();

    private HauntedMcTheme() {
    }

    /**
     * Gets the immutable canonical HauntedMC theme.
     *
     * @return the shared FeatureFramework theme instance
     */
    public static Theme theme() {
        return THEME;
    }

    private static Theme build() {
        Theme.Builder builder = Theme.builder(IDENTIFIER);
        for (HauntedMcColor color : HauntedMcColor.values()) {
            builder.solid(color.itemId(), color.textColor());
        }
        return builder.build();
    }
}
