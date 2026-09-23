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
    TEXT("Text", 0xE2E8F0),
    /** Friends, private messages, and social activity. */
    SOCIAL("Social", 0xF9A8D4),
    /** Local backend and gameplay notices. */
    SERVER("Server", 0xFB923C),
    /** Balances, payments, lottery, and rewards. */
    ECONOMY("Economy", 0xD9C18A),
    /** Community events and invitations. */
    EVENT("Event", 0x5EEAD4),
    /** Staff chat, moderation, and staff alerts. */
    STAFF("Staff", 0xB8A4F8),
    /** Supporting information between default text and muted metadata. */
    DETAIL("Detail", 0xBAC8D9),
    /** Start of the blue gradient reserved for the HauntedMC wordmark. */
    WORDMARK_HAUNTED_START("WordmarkHauntedStart", 0x2A78F6),
    /** End of the blue gradient reserved for the HauntedMC wordmark. */
    WORDMARK_HAUNTED_END("WordmarkHauntedEnd", 0x6ACDF7),
    /** Start of the orange gradient reserved for the MC wordmark segment. */
    WORDMARK_MC_START("WordmarkMcStart", 0xFC7B2F),
    /** End of the orange gradient reserved for the MC wordmark segment. */
    WORDMARK_MC_END("WordmarkMcEnd", 0xFB9A34),
    /** The "since 2013" brand signature. */
    BRAND_SINCE("BrandSince", 0xD0D6F0),
    /** Subdued labels and supporting copy in tablists and scoreboards. */
    UI_TEXT("UiText", 0xAAB2C9),
    /** Warm emphasis for tablist counts, icons, and compact values. */
    UI_HIGHLIGHT("UiHighlight", 0xFFD79C),
    /** Low-contrast structural separator on a dark UI surface. */
    UI_DIVIDER("UiDivider", 0x1C2F45),
    /** Focused scoreboard metadata such as time and server address. */
    UI_AMBER("UiAmber", 0xFFD166);

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
