# BuilderOS

**From Idea → Plan → Execution**

A lightweight, distraction-free web app for rapidly turning product ideas into structured, actionable specifications.

## 🎯 Philosophy

BuilderOS is designed for **solo makers and small teams** who need to:
1. Capture ideas quickly
2. Organize thoughts automatically
3. Get structured output without complexity
4. Work offline and independently

It's **intentionally simple** - built on vanilla JavaScript with zero dependencies, minimal UI, and fast performance.

## ✨ Features

### Projects
- **Create/Rename/Delete** projects
- **Auto-save** to browser LocalStorage
- **Quick access** from sidebar

### Chat Workspace
- Type ideas, features, and thoughts freely
- **Automatic message timestamping**
- **Message counter** for tracking conversation length
- **Real-time compilation** as you type

### Structured Output
- **7 auto-generated sections**:
  1. **Concept** - Core idea & problem/solution
  2. **Features** - Key capabilities
  3. **Screens** - UI/UX flows
  4. **Data Model** - Database schema
  5. **Monetization** - Revenue strategies
  6. **Tasks** - Development phases
  7. **Next Steps** - Immediate actions

### Smart Compiler
- Extracts concepts from natural language
- Generates realistic specs from conversation
- Updates live as you chat
- **Collapsible sections** for easy scanning

### Data Management
- **Local storage** - Everything stays private
- **Export** - Download all projects as JSON
- **Clear data** - Reset everything if needed

## 🚀 Quick Start

### Usage

1. **Open** `index.html` in any modern browser
2. **Click** the `+` button to create a project
3. **Type** your project name
4. **Start chatting** - describe features, ideas, flows
5. **Watch** the structured output auto-generate

### Keyboard Shortcuts

- `Enter` - Send message
- `Shift+Enter` - New line in message
- `Esc` - Cancel edit (when editing project name)

## 📱 Mobile-Friendly

- **Responsive layout** - Adapts to all screen sizes
- **Touch-optimized** buttons
- **Horizontal scrolling** projects on mobile
- **Stacked layout** on tablets

## 💾 Storage

All data is stored in **browser LocalStorage**:
- No account needed
- No cloud required
- **100% private** by default
- ~5-10MB storage limit per domain

### Data Structure

```json
{
  "id": "unique-project-id",
  "name": "My Project",
  "messages": [
    {
      "id": "message-id",
      "text": "Build a todo app with teams",
      "timestamp": 1234567890
    }
  ],
  "output": {
    "concept": "...",
    "features": "...",
    "screens": "...",
    "dataModel": "...",
    "monetization": "...",
    "tasks": "...",
    "nextSteps": "..."
  },
  "createdAt": 1234567890,
  "updatedAt": 1234567890
}
```

## 🎨 Design System

### Colors
- **Dark background** - Reduces eye strain
- **Accent blue** - Primary actions
- **Subtle grays** - Information hierarchy
- **Semantic reds** - Destructive actions

### Typography
- **System fonts** - Native speed
- **Large titles** - Clear hierarchy
- **Monospace** - Technical content

## 🛠️ Architecture

### Files

- **index.html** - Structure and layout
- **styles.css** - All styling (single file)
- **storage.js** - LocalStorage management
- **compiler.js** - Spec compilation logic
- **app.js** - Main application logic

### Tech Stack

- **HTML5** - Semantic markup
- **CSS3** - Modern styling
- **Vanilla JavaScript** - No frameworks
- **Zero dependencies** - Ship faster
- **~15KB total** - Lightning fast

## 🔧 Customization

### Change Theme

Edit CSS variables in `styles.css`:

```css
:root {
    --bg-primary: #0a0e27;
    --accent-primary: #3b82f6;
    /* ... more colors */
}
```

### Add Custom Sections

Edit `compiler.js`:

```javascript
extractCustomSection(text) {
    // Your logic here
}
```

### Change Storage

Edit `storage.js` to use IndexedDB, Firebase, or other backends instead of LocalStorage.

## 📊 Compiler Intelligence

The spec compiler is intentionally simple but effective:

1. **Keyword extraction** - Finds key terms in messages
2. **Template matching** - Uses patterns to suggest structure
3. **Context awareness** - Adjusts output based on mentioned features
4. **Defaults** - Provides sensible defaults when uncertain

### Example

**Input:**
```
Build a shopping app for small businesses. Users can list products, 
customers can browse and buy. Need payment integration.
```

**Auto-generated Output:**
```
Concept: Shopping platform for small businesses
Features:
- Product listing
- Customer browsing
- Payment processing

Screens:
- Home/Catalog
- Product details
- Shopping cart
- Checkout
- Profile

Data Model:
- User (merchant/buyer)
- Product
- Order

Monetization:
- Commission on sales
- Premium merchant plans
```

## 🚀 Deployment

### Static Hosting

BuilderOS works great on:
- **Netlify** - Drag & drop
- **Vercel** - Auto-deploy from GitHub
- **GitHub Pages** - Free hosting
- **Any web server** - Just serve HTML

### No Backend Needed

- Works offline
- No API calls required
- No database setup
- Just HTML + CSS + JS

## 📝 Tips for Best Results

1. **Brainstorm freely** - Type thoughts as they come
2. **Be specific** - "Allow users to filter by price" better than "filtering"
3. **Add examples** - Help the compiler understand intent
4. **Iterate** - Refine your specs as you clarify ideas
5. **Export regularly** - Save your work outside the browser

## 🐛 Troubleshooting

### Data Lost

If you cleared browser cache, data is gone. **Always export regularly**.

### Storage Full

Export projects and clear data to free up space.

### Compiler Output Wrong

Rephrase your messages with more specific terms.

## 🔮 Future Ideas (Not V1)

- AI-powered spec generation
- Team collaboration
- Cloud sync
- Design mockup generation
- Code generation
- Publish specs as websites

## 📄 License

MIT - Use freely in personal and commercial projects

## 👨‍💻 Contributing

This is intentionally minimal. For major features, fork and customize for your needs.

---

**BuilderOS**: Where great products start. 🚀
