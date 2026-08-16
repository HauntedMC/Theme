#!/usr/bin/env bash
set -euo pipefail

readonly VERSION_PROPERTY="revision"
readonly VERSIONS_PLUGIN="org.codehaus.mojo:versions-maven-plugin:2.18.0"
readonly MODULES=(hauntedmc-theme-palette hauntedmc-theme-featureframework)

die() {
  echo "Error: $*" >&2
  exit 1
}

usage() {
  echo "Usage: ./update_version.sh <major|minor|patch>" >&2
}

resolve_version() {
  local module="${1:-}"
  local -a module_args=()
  local version
  if [[ -n "$module" ]]; then
    module_args=(-pl "$module")
  fi
  version="$(
    ./mvnw -q -ntp "${module_args[@]}" -DforceStdout help:evaluate -Dexpression=project.version \
      | awk '/^[0-9]+\.[0-9]+\.[0-9]+$/ { print; exit }'
  )"
  [[ -n "$version" ]] || die "Unable to resolve Maven version${module:+ for ${module}}."
  printf '%s\n' "$version"
}

[[ $# -eq 1 ]] || { usage; exit 1; }
[[ "$1" == "major" || "$1" == "minor" || "$1" == "patch" ]] || { usage; exit 1; }
git rev-parse --is-inside-work-tree >/dev/null 2>&1 || die "Run this script inside the repository."

repository_root="$(git rev-parse --show-toplevel)"
cd "$repository_root"
[[ -f pom.xml && -x mvnw ]] || die "pom.xml or executable mvnw is missing."
[[ -z "$(git status --porcelain)" ]] || die "Working tree must be clean."

current="$(resolve_version)"
IFS=. read -r major minor patch <<<"$current"
case "$1" in
  major) major=$((major + 1)); minor=0; patch=0 ;;
  minor) minor=$((minor + 1)); patch=0 ;;
  patch) patch=$((patch + 1)) ;;
esac
next="${major}.${minor}.${patch}"
tag="v${next}"
git rev-parse -q --verify "refs/tags/${tag}" >/dev/null 2>&1 \
  && die "Tag ${tag} already exists."

./mvnw -B -ntp "${VERSIONS_PLUGIN}:set-property" \
  -Dproperty="${VERSION_PROPERTY}" -DnewVersion="$next" -DgenerateBackupPoms=false
for module in "${MODULES[@]}"; do
  [[ "$(resolve_version "$module")" == "$next" ]] \
    || die "Module ${module} did not resolve to ${next}."
done

./mvnw -B -ntp -Prelease verify
git diff --check
git add pom.xml
git commit -m "Bump version to ${tag} for release"
git tag --annotate "$tag" --message "Release ${tag}"

echo "Prepared ${tag}. Push the commit and tag when ready."
