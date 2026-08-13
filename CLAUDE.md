# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A personal collection of LeetCode / DSA practice solutions in Java, organized by algorithmic pattern rather than by project structure. There is no build system, no test suite, and no dependency manager (no `pom.xml`, `build.gradle`, or `package.json`) — files are standalone `.java` solutions, many written to be pasted directly into the LeetCode editor.

## Repo layout

Directories group solutions by technique, not by feature:

- `BinarySearch/` — binary search problems (rotated arrays, "minimize the maximum" / capacity-style problems, peak finding, etc.)
- `SlidingWindow/` — fixed and variable window problems. `SlidingWindowTemplate.java` is not a solution — it's a reference cheat sheet (fixed vs. variable window decision process, what to expand/shrink, when to update the answer) worth checking before adding a new sliding-window solution.
- `LinkedList/` — linked list problems.
- `LeetCode/` — files exported by the vscode-leetcode extension, recognizable by the `@lc app=leetcode id=... lang=java` header comment and `// @lc code=start` / `// @lc code=end` markers. These use `class Solution` and rely on LeetCode's own judge environment (imports like `HashMap` are sometimes omitted since the LeetCode runner supplies them).
- `Leet75/` — currently empty, reserved for future "Blind 75"-style additions.

## Conventions to follow when adding a solution

- **No package declarations.** Files are flat, single-class, and standalone.
- **Class name matches the file name** (e.g. `FindPeakElement.java` → `public class FindPeakElement`), except for files under `LeetCode/`, which follow the vscode-leetcode-extension convention of `class Solution` with `@lc` markers.
- **Helper data structures like `ListNode` are not defined locally** — problems that need them (see `LinkedList/removeElements.java`) assume the LeetCode-provided definition and won't compile standalone. Don't add a `ListNode` class unless asked; that would break the pattern the rest of the repo follows.
- **Solutions are heavily commented as study notes**, often including: a restated problem/example, an intuition/why-this-works section, a dry run with concrete input tracing through the algorithm step by step, and complexity analysis. When adding a new solution, match this teaching style rather than writing a terse, comment-free implementation — the point of the repo is the explanation, not just the working code.
- There is no test runner: correctness is verified by pasting into LeetCode, not by local tests. Do not introduce a build/test framework unless explicitly asked.

## Working with files here

Since there's no compiler wired up, you can't `mvn compile` or `./gradlew build` to sanity-check a file — read the code carefully and reason through the dry run instead. If you want to actually compile/run a single standalone file locally, plain `javac`/`java` works for files that don't reference an undefined type like `ListNode`.
