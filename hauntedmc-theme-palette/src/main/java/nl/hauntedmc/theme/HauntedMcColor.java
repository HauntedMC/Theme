package nl.hauntedmc.theme;

import net.kyori.adventure.text.format.TextColor;

/** The canonical HauntedMC colour palette and FeatureFramework item identifiers. */
public enum HauntedMcColor {
    /** Primary HauntedMC brand colour. */
    BRAND("Brand", 0xA855F7),
    /** Secondary accent colour for interactive or highlighted content. */
    ACCENT("Accent", 0x38BDF8),
    /** Positive-state colour. */
    SUCCESS("Success", 0x4ADE80),
    /** Warning-state colour. */
    WARNING("Warning", 0xFACC15),
    /** Error-state colour. */
    ERROR("Error", 0xFB7185),
    /** De-emphasized supporting-text colour. */
    MUTED("Muted", 0x94A3B8),
    /** Default readable foreground-text colour. */
    TEXT("Text", 0xE2E8F0);

    /** The identifier used by the FeatureFramework theme and its MiniMessage tags. */
    public static final String THEME_IDENTIFIER = "HauntedMC";

    private final String itemId;
    private final TextColor textColor;

    HauntedMcColor(String itemId, int rgb) {
        this.itemId = itemId;
        this.textColor = TextColor.color(rgb);
    }

    /**
     * Gets the stable FeatureFramework item identifier for this colour.
     *
     * @return the item identifier
     */
    public String itemId() {
        return itemId;
    }

    /**
     * Gets this colour as an Adventure text colour.
     *
     * @return the Adventure text colour
     */
    public TextColor textColor() {
        return textColor;
    }

    /**
     * Gets the canonical uppercase hexadecimal representation of this colour.
     *
     * @return the RGB colour in {@code #RRGGBB} form
     */
    public String hex() {
        return textColor.asHexString();
    }

    /**
     * Gets a standard MiniMessage colour tag for this colour.
     *
     * @return a {@code <color:#RRGGBB>} tag
     */
    public String miniMessageTag() {
        return "<color:" + hex() + '>';
    }

    /**
     * Gets the FeatureFramework MiniMessage theme tag for this colour.
     *
     * @return a tag in the form {@code <HauntedMC:ItemId>}
     */
    public String themeTag() {
        return '<' + THEME_IDENTIFIER + ':' + itemId + '>';
    }
}
