# HauntedMC Theme

[![CI](https://github.com/HauntedMC/Theme/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/HauntedMC/Theme/actions/workflows/ci.yml)
[![Release](https://img.shields.io/github/v/release/HauntedMC/Theme)](https://github.com/HauntedMC/Theme/releases)
[![License](https://img.shields.io/github/license/HauntedMC/Theme)](LICENSE)
[![Java 25](https://img.shields.io/badge/Java-25-007396)](https://adoptium.net/)

Versioned HauntedMC colours for Adventure components and FeatureFramework localization.

This repository publishes libraries, not server plugins. Use the palette directly when building Adventure components,
or use the FeatureFramework adapter to make the same named colours available to localized MiniMessage content.

## Artifacts

- `nl.hauntedmc.theme:hauntedmc-theme-palette` exposes `HauntedMcColor` for direct Adventure use.
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
  <version>1.0.0</version>
</dependency>
```

FeatureFramework hosts can depend on the adapter instead; it brings in the palette transitively:

```xml
<dependency>
  <groupId>nl.hauntedmc.theme</groupId>
  <artifactId>hauntedmc-theme-featureframework</artifactId>
  <version>1.0.0</version>
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
<HauntedMC:Brand>◆ Friends  <HauntedMC:Text>No friends are online
```

For code that builds Adventure components directly:

```java
Component.text("Success", HauntedMcColor.SUCCESS.textColor());
```

| Item | Colour |
|---|---|
| `Brand` | `#A855F7` |
| `Accent` | `#38BDF8` |
| `Success` | `#4ADE80` |
| `Warning` | `#FACC15` |
| `Error` | `#FB7185` |
| `Muted` | `#94A3B8` |
| `Text` | `#E2E8F0` |

Identifiers are resolved case-insensitively by FeatureFramework. Both artifacts are libraries, not server plugins;
applications should include them in their distributable jar. The palette has a `provided` Adventure API dependency,
which Paper and Velocity already supply.

## Build and release

Run `./mvnw verify` for the complete quality gate. Use `./update_version.sh major|minor|patch`
from a clean worktree to prepare a local release commit and tag. Publishing uses the `github`
Maven server configured by the release workflow.

For a coordinated stack release, publish FeatureFramework first, then Theme, DataProvider, DataRegistry,
and finally ServerFeatures and ProxyFeatures. This follows the dependency graph and keeps every downstream build
resolvable from GitHub Packages.

## Contributing and support

- [Contributing](CONTRIBUTING.md)
- [Security policy](SECURITY.md)
- [Support](SUPPORT.md)
- [Code of conduct](CODE_OF_CONDUCT.md)

## License

This project is licensed under the [GNU Affero General Public License v3.0](LICENSE).
