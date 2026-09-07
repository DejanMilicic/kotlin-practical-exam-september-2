---
theme: default
title: Film Festival Catalog
titleTemplate: "%s"
class: text-left
mdc: true
---

<div class="cover-shell">
  <div class="cover-kicker">Practical Exam</div>
  <h1>Film Festival Catalog</h1>
  <p>Model films and directors, parse the festival CSV, and implement five collection queries in Kotlin.</p>
  <div class="cover-meta">
    <div class="meta-chip">Duration: 2 hours</div>
    <div class="meta-chip">Total: 60 points</div>
    <div class="meta-chip">IDE: IntelliJ IDEA</div>
    <div class="meta-chip">Language level: Java 21</div>
  </div>
</div>

---
layout: default
---

# Before You Start

<div class="accent-line"></div>

<div class="panel-grid">
  <div class="panel-card">
    <h3>What you need</h3>
    <p>IntelliJ IDEA and Git should already be available on your machine.</p>
  </div>
  <div class="panel-card">
    <h3>What you do not need</h3>
    <p>You do not need to install Gradle manually for this assignment.</p>
  </div>
  <div class="panel-card">
    <h3>Project language level</h3>
    <p>The assignment targets Java 21.</p>
  </div>
  <div class="panel-card">
    <h3>If Java 21 is missing</h3>
    <p>Use <span class="inline-pill">run-gradle.cmd</span> from the project root. It will locate or set up Corretto 21 for your OS.</p>
  </div>
</div>

<div class="repo-card command-card">
  <strong>Run this only if Java 21 is missing:</strong>
  <div class="command-stack">
    <span class="repo-url">macOS / Linux: sh ./run-gradle.cmd compileKotlin</span>
    <span class="repo-url">Windows: .\run-gradle.cmd compileKotlin</span>
  </div>
</div>

<div class="warning-box">
  If the project opens and Gradle works immediately, start solving the tasks. Use the bootstrap script only if Java 21 is not available.
</div>

---
layout: default
---

# Clone the Repository

<div class="accent-line"></div>

1. Open IntelliJ IDEA.
2. Choose **Get from VCS**.
3. Paste the exam repository URL.
4. Choose a local folder and clone the project.
5. Open the cloned project and wait for indexing to finish.

<div class="repo-card">
  <strong>Repository URL:</strong><br>
  <span class="repo-url">https://github.com/DejanMilicic/kotlin-practical-exam-september-2.git</span>
</div>

<p class="subtle">If you prefer the terminal, you can also clone first and then open the folder in IntelliJ IDEA.</p>

---
layout: default
---

# Work on the Assignment

<div class="accent-line"></div>

- Follow the full specification in README.md.
- Create the models (10 points), parse films.csv (10 points), and implement FilmCatalog (40 points).
- Run each task's prepared tests using IntelliJ IDEA's gutter controls or the README commands.
- Re-run the relevant check after each completed task instead of waiting until the end.
- When you want a full verification pass, run the full test suite from IntelliJ or Gradle.

<div class="checklist-card">
  <strong>Main idea:</strong> solve one task, run its prepared configuration, fix issues immediately, then continue.
</div>

---
layout: default
---

# The Five Queries

<div class="accent-line"></div>

- Top N highest-rated films — 4 points.
- All directors without duplicates — 8 points.
- Films matching a language and genre — 8 points.
- Films in the most common language, sorted by title descending — 8 points.
- The director with the most films — 8 points.

<div class="checklist-card">
  <strong>Catalog setup:</strong> creating FilmCatalog and completing its factory earns the remaining 4 points. Prefer Kotlin collection operations; follow the ordering and tie rules in README.md.
</div>

---
layout: default
---

# If Java 21 Is Missing

<div class="accent-line"></div>

- Open a terminal in the project root.
- Run the command for your operating system:
- After the environment is ready, continue using IntelliJ IDEA and the prepared tests.

<div class="repo-card command-card">
  <div class="command-stack">
    <span class="repo-url">macOS / Linux: sh ./run-gradle.cmd compileKotlin</span>
    <span class="repo-url">Windows: .\run-gradle.cmd compileKotlin</span>
  </div>
</div>

<div class="warning-box">
  Use this step only when Java 21 is not already available. The script is there to remove setup friction, not as an extra mandatory task.
</div>

---
layout: default
---

# Submit Your Work

<div class="accent-line"></div>

1. Open a terminal in the project root after you finish.
2. Run the command for your operating system:
3. Enter your first name, last name, and index number when prompted.
4. Use the usual index format such as <span class="inline-pill">123/2026</span>.
5. Submit the generated ZIP file.

<div class="repo-card command-card">
  <div class="command-stack">
    <span class="repo-url">macOS / Linux: sh ./zip-project.cmd</span>
    <span class="repo-url">Windows: .\zip-project.cmd</span>
  </div>
</div>

<div class="checklist-card">
  The ZIP file name will use <span class="inline-pill">123-2026</span> instead of <span class="inline-pill">123/2026</span> because file names cannot contain a slash.
</div>

---
layout: default
---

# Final Checklist

<div class="accent-line"></div>

<ul class="tight-list">
  <li>The exam lasts exactly 2 hours.</li>
  <li>The repository is cloned and opened in IntelliJ IDEA.</li>
  <li>All required tasks are implemented.</li>
  <li>The prepared tests were used to verify the assignment.</li>
  <li>If Java 21 was missing, <span class="inline-pill">run-gradle.cmd</span> was used.</li>
  <li><span class="inline-pill">zip-project.cmd</span> was run at the end.</li>
  <li>The generated ZIP is the file you submit.</li>
</ul>

<div class="warning-box">
  When you are ready to submit, call me so I can come and pick up your assignment.
</div>
