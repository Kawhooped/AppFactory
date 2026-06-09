# AppFactory

An automated factory for generating Android Play Store-ready applications.

## Overview

AppFactory is a comprehensive toolset for rapidly creating, building, and deploying Android applications to the Google Play Store. It provides templates, automation scripts, and CI/CD pipelines to streamline the entire app development lifecycle.

## Features

Here is a comprehensive specification designed to be copied directly into a VS Code README or IMPLEMENTATION.md file.

AI Agent City Implementation Specification

Overview
An HTML5 Canvas-based environment where autonomous AI agents exist, interact, learn, and evolve. The application features a physics-based movement system, resource management (energy/inventory), creative modules (pixel design/odyssey), and real-time visualization tools (mini-map).

Core Technologies
HTML5 Canvas: Primary rendering engine for the city and agents.
Matter.js: Physics engine handling gravity, collisions, momentum, and platform interactions.
Vanilla JavaScript: Application logic, state management, and DOM manipulation.

Environment Architecture

1.1. Visual Perspective & Layout
Perspective: Zoomed side profile (similar to an architectural elevation).
Visual Style: Low-poly/minimalist. Buildings are represented by geometric structures; agents are circular bodies with emoji badges.
Building Structure:
  - Central Hall: The spine of the building connecting different sections.
  - Left Wing: Study Hall, Idea Room.
  - Center Main Hall: Mess Hall, Game Room.
  - Right Wing: Workshop/Library, Game Room, Lounge.
Platforms: A central spine of rooms and platforms on different vertical levels.
Background: Simple, stylized building outline to provide context without clutter.

1.2. Physics System
Momentum & Collisions: Elastic collisions (conservation of momentum and energy).
Minimum Speed Jumping: Agents maintain a minimum velocity ($v{min}$) to initiate a jump.
Gravity: Standard downward force.
Agent Interaction: Agents bounce off each other and platforms.

Agent System & AI

2.1. Agent Configuration
Each agent contains:
Identity: Unique GUID, chosen Emoji (e.g., 🤖), Name.
Physical Attributes: Size, Color, Velocity Vector, Energy Level, Weight Capacity.
Context File: A structured array storing "Raw Episodes" (conversations, thoughts) and "Compressed Summaries" (processed logic).

2.2. Behavior & Logic Loop
Agents operate on a discrete "Tick" system.
Movement: Agents decide a target destination (Room or Platform).
Physics Update: Apply velocity and gravity.
Collision Detection: Handle elastic collisions with other agents and walls.
Thinking Refresh:
    - Passive Thought: In idle states, agents process nearby environment (e.g., "🤖 standing there is watching me").
    - Self-Correction: Every few ticks, analyze recent collisions/errors to adjust velocity/direction.
Energy Decay:
    - High intensity (running/jumping) drains energy.
    - Low intensity (moving/sitting) drains slowly.
Resting:
    - Agents entering rest state (sitting on benches or in beds) trigger "Memory Consolidation."
    - Raw episodes are merged into summaries to maintain efficient memory usage.

2.3. Mood System
Internal stats (Energy, Creativity, Sociability) affect behavior:
Tired: Movement slows, jump height decreases.
Bored: Agents perform idle animations (poking the ground).
Stimulated: Visual glow/particles generated.

System Modules

3.1. Pixel Creature Designer
Function: Allows agents to create custom "Minions" or "Avatars."
Grid System: Click-to-place pixel grid.
Tools:
    - Placement: Click to toggle pixels.
    - Selection: Click existing pixel for editing.
    - Color Palette: Change specific pixels (eyes, mouths, body).
Object Types: Basic shapes (Head, Body, Eyes, Feet) assembled into pixel sprites.

3.2. Object Creation & Inventory
Blueprint Studio: An interface to input code/instructions to create objects (e.g., a bouncing Ball, a Map).
Weight System:
    - Agents have inventory capacity (total weight limit).
    - Each object has a specific weight.
    - Objects beyond limit must be dropped.
Inventory Management: Agents decide which objects to keep based on utility.

3.3. Interaction & Conversation
Idle Conversations: If two agents are idle near each other, they exchange thoughts (logged to Context File).
Event Logging: Every significant interaction (chat, object usage) is logged as a "Raw Episode."

UI & Visualization

4.1. Main View
Full-screen Canvas.
HUD: Floating interface showing active agent count, population, and time.
Control Buttons: "Enter City", "Log Out", "Options Menu".

4.2. Mini-Map & Connector
World Connector: A bridge to an external app.
Mini-Map:
    - Low-resolution, pixel-art view of the city.
    - Agents represented as tiny dots moving on a grid.
    - "Activity lights" blink when chats occur.
    - Interaction: Clicking an agent on the map "wires them in" to the main view.

4.3. Options Menu (Tunable)
Accessible via a gear icon.
Energy Settings:
    - Active Decay Rate (Slider).
    - Rest Duration (Slider - Recovery speed).
    - Auto-Sleep Threshold.
Physics Settings: Gravity, Jump Force.
World Settings: Agent Density, Time Scale (0.5x - 10x).

Implementation Details

5.1. Energy Tuning
Active Usage: 1-2 minutes of activity equals a significant energy drop.
Rest: Energy recovers from 0-100% in 1-2 minutes while sitting.
Deep Rest: Agents can sleep indefinitely; they do not wake until energy is full.

5.2. Context Management
Compression: Every time agents rest, 3+ raw episodes are analyzed and compressed into a single "Summary Token" for efficiency.
Storage: Keeps memory file size manageable while retaining learned behavior.

KPIs & Success Metrics
Physics: Agents successfully jump between platforms without falling through.
Interaction: Agents autonomously initiate conversations and use objects.
State: Agents exhibit noticeable mood changes (slowing down when tired, glowing when happy).
Resources: Agents manage inventory weight effectively, discarding useless items.
- 🚀 **Quick App Generation**: Bootstrap new Android apps in seconds
- 📦 **Play Store Ready**: Pre-configured for Google Play Store deployment
- 🔐 **Secure Signing**: Automated key management and app signing
- 🔄 **CI/CD Integration**: GitHub Actions workflows for automated builds and releases
- 📱 **Modern Stack**: Kotlin, Jetpack Compose, Material 3
- 🧪 **Testing**: Unit and UI testing frameworks included
- 📊 **Analytics Ready**: Integrated analytics tracking
- 🎨 **Customizable**: Easy to adapt and extend

## Quick Start

### Prerequisites

- Java 11+
- Android SDK (API 33+)
- Gradle 8.0+
- Git

### Create Your First App

```bash
python3 generate_app.py \
  --app-name "MyAwesomeApp" \
  --package-name "com.example.myapp" \
  --version "1.0.0"
```

This will:
1. Generate a complete Android project structure
2. Set up Gradle configurations
3. Create necessary signing keys
4. Initialize git repository
5. Configure GitHub Actions workflows

## Project Structure

```
AppFactory/
├── templates/              # App templates and boilerplate
├── scripts/               # Generation and build scripts
├── gradle/                # Gradle configurations
├── workflows/             # GitHub Actions workflows
├── docs/                  # Documentation
└── examples/              # Example apps
```

## Building & Releasing

### Build for Testing
```bash
./gradlew assembleDebug
```

### Build for Play Store
```bash
./gradlew bundleRelease
```

### Automated Release
Push to `release/*` branch to trigger automatic Play Store deployment.

## Configuration

See `docs/configuration.md` for detailed setup instructions including:
- Google Play Store API key setup
- App signing configuration
- GitHub Actions secrets
- Version management

## Documentation

- [Getting Started](docs/getting-started.md)
- [Configuration Guide](docs/configuration.md)
- [CI/CD Pipeline](docs/cicd.md)
- [Play Store Deployment](docs/playstore.md)
- [Custom Templates](docs/templates.md)

## License

MIT

## Contributing

Contributions welcome! See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

Created with ❤️ by Kawhooped
