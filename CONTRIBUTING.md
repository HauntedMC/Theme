# Contributing to HauntedMC Theme

## Development setup

- Java 25
- The checked-in Maven wrapper
- GitHub Packages credentials when the FeatureFramework dependency is not already cached locally

Run the complete local quality gate before opening a pull request:

```bash
./mvnw -B -ntp verify
./mvnw -B -ntp -Prelease package
shellcheck update_version.sh
```

## Project boundaries

- `hauntedmc-theme-palette` is the stable, FeatureFramework-independent Adventure colour API.
- `hauntedmc-theme-featureframework` adapts the palette into FeatureFramework's `Theme` API.
- Keep the palette free of platform and FeatureFramework implementation dependencies.
- Add a palette entry and its FeatureFramework mapping together; preserve existing item identifiers and colours unless
  making a deliberately documented breaking change.
- Public API changes need Javadoc, focused tests, README updates, and a semantic-versioning-appropriate release.

## Pull requests

- Keep commits focused and use clear commit messages.
- Add or update tests for changed behaviour.
- Run the commands above and fill out the pull-request template.
- Do not add a server-plugin bootstrap or a shaded distribution to this library repository.

## Security

Do not disclose vulnerabilities in public issues. Follow [SECURITY.md](SECURITY.md).
