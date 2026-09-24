#!/usr/bin/env bash
set -euo pipefail

usage() {
  echo 'Usage: ./update_version.sh [--dry-run] <palette|adapter> <major|minor|patch>' >&2
}

if [[ "${1:-}" == '--dry-run' ]]; then
  dry_run=true
  shift
else
  dry_run=false
fi
[[ $# -eq 2 ]] || { usage; exit 64; }
case "$1" in
  palette) module=hauntedmc-theme-palette; tag_prefix=palette ;;
  adapter) module=hauntedmc-theme-featureframework; tag_prefix=adapter ;;
  *) usage; exit 64 ;;
esac
case "$2" in major|minor|patch) bump="$2" ;; *) usage; exit 64 ;; esac
cd "$(git rev-parse --show-toplevel)"
pom="$module/pom.xml"
current="$(./mvnw -q -ntp -f "$pom" -DforceStdout help:evaluate -Dexpression=project.version | awk '/^[0-9]+\.[0-9]+\.[0-9]+$/ { print; exit }')"
[[ "$current" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]] || { echo "Invalid current version: $current" >&2; exit 1; }
IFS=. read -r major minor patch <<< "$current"
case "$bump" in
  major) major=$((major + 1)); minor=0; patch=0 ;;
  minor) minor=$((minor + 1)); patch=0 ;;
  patch) patch=$((patch + 1)) ;;
esac
next="$major.$minor.$patch"
tag="$tag_prefix-v$next"
echo "$module: $current -> $next ($tag)"
[[ "$dry_run" == false ]] || exit 0
[[ -z "$(git status --porcelain)" ]] || { echo 'Working tree must be clean.' >&2; exit 1; }
git rev-parse -q --verify "refs/tags/$tag" >/dev/null 2>&1 && { echo "$tag already exists." >&2; exit 1; }
CURRENT="$current" NEXT="$next" POM="$pom" python3 - <<'PY'
import os
from pathlib import Path
p = Path(os.environ['POM'])
s = p.read_text()
old = f"<revision>{os.environ['CURRENT']}</revision>"
new = f"<revision>{os.environ['NEXT']}</revision>"
if s.count(old) != 1:
    raise SystemExit(f'Expected one {old} in {p}')
p.write_text(s.replace(old, new))
PY
git diff --check
echo 'Version prepared. Commit the changed POM in a pull request; CI will publish before creating the tag.'
