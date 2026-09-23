# HauntedMC colour contract

![HauntedMC palette on a dark chat background](palette-preview.svg)

The theme has two independent signals: **where a message comes from** and **what happened**. Source colours identify a message at a glance; state colours mark its outcome or urgency. A readable message also names its source. Colour is a cue, not the only way to recognize it.

## Roles

| Role | Items | Use |
|---|---|---|
| Identity | `Brand` | HauntedMC title marks and general account panels. Avoid a Brand prefix on every feature message. |
| Source | `Social`, `Server`, `Economy`, `Event`, `Staff` | A short named prefix, icon, or key subject. Keep the rest of the sentence readable. |
| Network and action | `Accent` | Network routing notices, links, buttons, selected controls, and general interactive highlights. Name network messages because controls share this hue. |
| State | `Success`, `Warning`, `Error` | Outcome, priority marker, or a short state phrase. They do not replace the source colour. |
| Reading hierarchy | `Text`, `Detail`, `Muted` | Main sentence, useful secondary value, and quiet metadata or separators, respectively. |

Choose the most specific source represented in the message. A friend request is Social even when delivered through the network inbox. A payment is Economy even when addressed to one player. An event invite is Event even when delivered by the proxy. “Personal” describes the audience, so it does not override the source. For account or preference messages without a more specific source, use a Brand title or icon.

`Server` identifies local backend or gameplay notices, such as a grave expiry or local restart. `Accent` identifies network-wide routing, queue, or delivery notices. `Staff` identifies staff communication and moderation; a player-facing ban or kick may also use `Error` for the state. `Event` is for community participation, while a general network announcement uses `Accent` or `Brand` according to its sender.

## Message anatomy

Use one short coloured prefix with a word or recognizable icon, followed by `Text`. Put timestamps, separators, and low-priority context in `Muted`. Use `Detail` when a secondary value needs to remain easy to read. Highlight an actionable control with `Accent`. Use bold or layout sparingly to strengthen a heading; avoid using many bright colours within one sentence.

These MiniMessage examples show colour placement. They are examples for future consumer migrations; this Theme release does not rewrite existing feature messages.

```text
<HauntedMC:Social>✦ Vrienden <HauntedMC:Muted>· <HauntedMC:Error>Verzoek mislukt. <HauntedMC:Text>Probeer het later opnieuw.
<HauntedMC:Accent>◆ Netwerk <HauntedMC:Muted>· <HauntedMC:Warning>Omgeleid <HauntedMC:Text>naar <HauntedMC:Detail>{server}<HauntedMC:Text>.
<HauntedMC:Server>◆ Server <HauntedMC:Muted>· <HauntedMC:Text>Je graf verloopt over <HauntedMC:Detail>{remaining}<HauntedMC:Text>.
<HauntedMC:Economy>◆ Economie <HauntedMC:Muted>· <HauntedMC:Text>Je ontving <HauntedMC:Economy>{amount} <HauntedMC:Text>van {player}. <HauntedMC:Success>✓
<HauntedMC:Event>✦ Evenement <HauntedMC:Muted>· <HauntedMC:Text>Je bent uitgenodigd voor <HauntedMC:Detail>{title}<HauntedMC:Text>. <HauntedMC:Accent>[Bekijk]
<HauntedMC:Staff>◆ Staff <HauntedMC:Muted>· <HauntedMC:Text>{player} kreeg een waarschuwing. <HauntedMC:Warning>!
```

For a list or panel, use `Brand` for the HauntedMC mark, the source colour for its title, `Text` for row labels, `Detail` for important values, and `Muted` for pagination or timestamps. Use `Success` and `Error` for actual states, such as enabled/disabled, rather than as colours for every row in a list.

## Adopting the contract

Feature defaults in ProxyFeatures and ServerFeatures are often embedded in Java `MessageMap` entries, while DataRegistry builds Adventure components directly. Migrate each visible message based on its source and purpose. Do not replace every existing `Accent`, legacy colour code, or severity tag mechanically. Existing custom/localized messages stay under the consumer's control.

Both published artifacts expose the same values: `HauntedMcColor.SOCIAL.textColor()` in direct Adventure code and `<HauntedMC:Social>` through the FeatureFramework theme. The original seven values and identifiers are unchanged.
