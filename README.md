# HauntedMC Theme

[![CI](https://github.com/HauntedMC/Theme/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/HauntedMC/Theme/actions/workflows/ci.yml)
[![License](https://img.shields.io/github/license/HauntedMC/Theme)](LICENSE)
[![Java 25](https://img.shields.io/badge/Java-25-007396)](https://adoptium.net/)

Versioned HauntedMC Theme for plugin localization.

## Artifacts

- `nl.hauntedmc.theme:hauntedmc-theme-palette` exposes `HauntedMcColor` and `HauntedMcBranding` for direct Adventure use.
- `nl.hauntedmc.theme:hauntedmc-theme-featureframework` exposes `HauntedMcTheme.theme()`.

The artifacts are published to GitHub Packages. Add the repository to the consuming build (and configure GitHub
Packages credentials when required):

```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/HauntedMC/Theme</url>
</repository>
```

Use the palette without FeatureFramework:

```xml
<dependency>
  <groupId>nl.hauntedmc.theme</groupId>
  <artifactId>hauntedmc-theme-palette</artifactId>
  <version>1.2.1</version>
</dependency>
```

FeatureFramework hosts can depend on the adapter instead; it brings in the palette transitively:

```xml
<dependency>
  <groupId>nl.hauntedmc.theme</groupId>
  <artifactId>hauntedmc-theme-featureframework</artifactId>
  <version>1.2.1</version>
</dependency>
```

Register the theme while building a FeatureFramework host:

```java
PaperFeatureHost.builder(plugin, Api.class, features)
        .theme(HauntedMcTheme.theme())
        .build();
```

Localization can then use persistent colours or scoped effects:

```text
<HauntedMC:Social>✦ Friends <HauntedMC:Muted>· <HauntedMC:Text>No friends are online
```

For code that builds Adventure components directly:

```java
Component.text("Success", HauntedMcColor.SUCCESS.textColor());
```

The exact HauntedMC wordmark and signature are available for both localization paths:

```java
String localizedWordmark = HauntedMcBranding.THEME_WORDMARK;
String directWordmark = HauntedMcBranding.MINIMESSAGE_WORDMARK;
String localizedSince = HauntedMcBranding.THEME_SINCE;
String directSince = HauntedMcBranding.MINIMESSAGE_SINCE;
```

Use the `THEME_` fragments where FeatureFramework expands theme tags and the `MINIMESSAGE_` fragments with a standard MiniMessage parser. The wordmark has two separately scoped gradients, so it keeps the supplied blue and orange lettering without changing `Brand` or `Accent`.

| Item | Colour |
|---|---|
| `Brand` | `#A855F7` |
| `Accent` | `#38BDF8` |
| `Success` | `#4ADE80` |
| `Warning` | `#FACC15` |
| `Error` | `#FB7185` |
| `Muted` | `#94A3B8` |
| `Text` | `#E2E8F0` |
| `Social` | `#F9A8D4` |
| `Server` | `#FB923C` |
| `Economy` | `#D9C18A` |
| `Event` | `#5EEAD4` |
| `Staff` | `#B8A4F8` |
| `Detail` | `#BAC8D9` |

Brand and UI colors are intentionally narrower in purpose than the general message palette:

| Item | Colour | Use |
|---|---|---|
| `WordmarkHauntedStart` | `#2A78F6` | Blue wordmark gradient start |
| `WordmarkHauntedEnd` | `#6ACDF7` | Blue wordmark gradient end |
| `WordmarkMcStart` | `#FC7B2F` | Orange wordmark gradient start |
| `WordmarkMcEnd` | `#FB9A34` | Orange wordmark gradient end |
| `BrandSince` | `#D0D6F0` | Stylized “since 2013” signature |
| `UiText` | `#AAB2C9` | Tablist and scoreboard labels |
| `UiHighlight` | `#FFD79C` | Tablist counts and compact highlights |
| `UiDivider` | `#1C2F45` | Dark UI separators |
| `UiAmber` | `#FFD166` | Scoreboard time and server address |

The FeatureFramework theme also registers the scoped `WordmarkHaunted` and `WordmarkMc` gradient items. The existing 13 solid colors retain their values and identifiers.

See the [colour contract](docs/color-contract.md) for source and state rules, message examples, and dark-background previews.

Identifiers are resolved case-insensitively by FeatureFramework. Both artifacts are libraries, not server plugins;
applications should include them in their distributable jar. The palette has a `provided` Adventure API dependency,
which Paper and Velocity already supply.

## Build and release

Run `./mvnw verify` for the complete quality gate. Palette and FeatureFramework adapter have independent versions and tags. From a clean worktree, run `./update_version.sh palette patch` or `./update_version.sh adapter patch`, review the changed POM in a PR, and merge after CI passes. The release workflow publishes the selected module, resolves it from a fresh Maven repository, and only then creates `palette-vX.Y.Z` or `adapter-vX.Y.Z`. If both change together, palette publishes first. The adapter may select a palette version only after that palette release exists.

The [organization release guide](https://github.com/HauntedMC/HauntedPlatform/blob/main/docs/releasing.md) describes downstream updates and the GitHub App reconciler.

## Contributing and support

- [Contributing](CONTRIBUTING.md)
- [Security policy](SECURITY.md)
- [Support](SUPPORT.md)
- [Code of conduct](CODE_OF_CONDUCT.md)

## License

This project is licensed under the [GNU Affero General Public License v3.0](LICENSE).
