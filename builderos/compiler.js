/**
 * Spec Compiler
 * Converts chat messages into structured product specs
 */
class SpecCompiler {
    constructor() {
        this.sections = [
            'Concept',
            'Features',
            'Screens',
            'Data Model',
            'Monetization',
            'Tasks',
            'Next Steps'
        ];
    }

    /**
     * Compile messages into structured output
     */
    compile(messages) {
        if (messages.length === 0) {
            return this.getEmptySpec();
        }

        const text = messages.map(m => m.text).join(' ');
        
        return {
            concept: this.extractConcept(text),
            features: this.extractFeatures(text),
            screens: this.extractScreens(text),
            dataModel: this.extractDataModel(text),
            monetization: this.extractMonetization(text),
            tasks: this.extractTasks(text),
            nextSteps: this.extractNextSteps(text)
        };
    }

    /**
     * Extract concept from messages
     */
    extractConcept(text) {
        // Find first message as the core concept
        const lines = text.split('.');
        const concept = lines[0].trim();
        
        if (concept.length < 10) return 'No concept defined yet. Add more details to your project.';
        
        return `${concept}\n\nProblem:\n${this.findKeywords(text, ['problem', 'issue', 'challenge', 'pain'])}\n\nSolution:\n${this.findKeywords(text, ['solve', 'solution', 'help', 'enable'])}`;
    }

    /**
     * Extract features from messages
     */
    extractFeatures(text) {
        const keywords = ['feature', 'feature:', 'can', 'ability to', 'allow', 'enable', 'support'];
        const features = this.extractList(text, keywords);
        
        if (features.length === 0) {
            return 'Core features not yet defined. Describe what users can do with your app.';
        }
        
        return features.map((f, i) => `${i + 1}. ${this.capitalize(f)}`).join('\n');
    }

    /**
     * Extract screens from messages
     */
    extractScreens(text) {
        const keywords = ['screen', 'page', 'view', 'dashboard', 'panel'];
        const screens = this.extractList(text, keywords);
        
        const defaultScreens = [
            'Home/Dashboard - Main entry point',
            'Create/Setup - Onboarding or initial setup',
            'List/Browse - Display main content',
            'Detail/Expand - View full item details',
            'Settings - User preferences and options',
            'Profile - User account information'
        ];
        
        const combined = [...new Set([...screens, ...defaultScreens])];
        return combined.slice(0, 8).map((s, i) => `${i + 1}. ${this.capitalize(s)}`).join('\n');
    }

    /**
     * Extract data model from messages
     */
    extractDataModel(text) {
        const keywords = ['user', 'product', 'order', 'data', 'model', 'store', 'save'];
        
        let model = `
Primary Entities:
`;
        
        // User model (usually always needed)
        if (this.hasKeywords(text, ['user', 'account', 'profile', 'login'])) {
            model += `\n1. User\n   - id, name, email, password, preferences\n`;
        }
        
        // Core entity
        if (this.hasKeywords(text, ['product', 'item', 'listing'])) {
            model += `\n2. Product\n   - id, name, description, price, category\n`;
        } else if (this.hasKeywords(text, ['post', 'article', 'content'])) {
            model += `\n2. Post/Content\n   - id, title, content, author, timestamp\n`;
        } else if (this.hasKeywords(text, ['task', 'todo', 'note'])) {
            model += `\n2. Task\n   - id, title, description, completed, priority\n`;
        }
        
        // Activity/Analytics
        if (this.hasKeywords(text, ['track', 'analytics', 'history', 'activity'])) {
            model += `\n3. Activity\n   - id, userId, action, timestamp\n`;
        }
        
        return model + `\nStorage: Browser LocalStorage + optional backend sync`;
    }

    /**
     * Extract monetization from messages
     */
    extractMonetization(text) {
        const keywords = ['monetize', 'pay', 'price', 'subscription', 'free', 'premium'];
        
        if (this.hasKeywords(text, keywords)) {
            const models = [];
            
            if (this.hasKeywords(text, ['free', 'freemium'])) models.push('Freemium with premium features');
            if (this.hasKeywords(text, ['subscription', 'monthly'])) models.push('Subscription (monthly/yearly)');
            if (this.hasKeywords(text, ['ad', 'advertising'])) models.push('Ad-supported');
            if (this.hasKeywords(text, ['one-time', 'purchase'])) models.push('One-time purchase');
            
            if (models.length > 0) {
                return `Recommended models:\n${models.map((m, i) => `${i + 1}. ${m}`).join('\n')}`;
            }
        }
        
        return `Monetization Strategy:\n1. Start free with clear upgrade path\n2. Add premium features for power users\n3. Track user engagement before monetizing`;
    }

    /**
     * Extract tasks from messages
     */
    extractTasks(text) {
        const phases = [];
        
        phases.push(`Phase 1: Setup (Week 1)\n1. Create project structure\n2. Set up database/storage\n3. Design UI mockups`);
        
        phases.push(`Phase 2: Core Features (Week 2-3)\n1. Build main screens\n2. Implement data management\n3. Add user interactions`);
        
        if (this.hasKeywords(text, ['user', 'auth', 'login'])) {
            phases.push(`Phase 3: Authentication (Week 3)\n1. User signup/login\n2. Session management\n3. Password reset`);
        }
        
        phases.push(`Phase 4: Polish & Launch (Week 4)\n1. Testing and debugging\n2. Performance optimization\n3. Deployment`);
        
        return phases.join('\n\n');
    }

    /**
     * Extract next steps from messages
     */
    extractNextSteps(text) {
        return `Immediate Next Actions:\n1. Define 3-5 core user flows\n2. Sketch key screens on paper/wireframe tool\n3. List all required data fields\n4. Start building MVP (minimum viable product)\n5. Get user feedback early and often\n\nSuccess Metrics:\n- User retention rate\n- Feature usage\n- Time-to-first-value\n- User satisfaction/NPS`;
    }

    /**
     * Helper: Extract keywords from text
     */
    findKeywords(text, keywords) {
        const found = keywords
            .map(kw => {
                const regex = new RegExp(`${kw}[^.!?]{10,100}`, 'gi');
                const matches = text.match(regex);
                return matches ? matches[0] : null;
            })
            .filter(Boolean);
        
        return found.length > 0 ? found[0] : 'Not yet specified';
    }

    /**
     * Helper: Extract list of items from text
     */
    extractList(text, keywords) {
        const items = [];
        const sentences = text.split(/[.!?]/);
        
        sentences.forEach(sentence => {
            keywords.forEach(kw => {
                if (sentence.toLowerCase().includes(kw)) {
                    const cleaned = sentence.replace(new RegExp(kw, 'i'), '').trim();
                    if (cleaned.length > 3 && cleaned.length < 100) {
                        items.push(cleaned);
                    }
                }
            });
        });
        
        return [...new Set(items)].filter(Boolean);
    }

    /**
     * Helper: Check if text contains keywords
     */
    hasKeywords(text, keywords) {
        const lower = text.toLowerCase();
        return keywords.some(kw => lower.includes(kw.toLowerCase()));
    }

    /**
     * Helper: Capitalize string
     */
    capitalize(str) {
        return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
    }

    /**
     * Get empty spec template
     */
    getEmptySpec() {
        return {
            concept: 'No messages yet. Start by describing your product idea.',
            features: 'Add messages to generate features.',
            screens: 'Add messages to generate screens.',
            dataModel: 'Add messages to generate data model.',
            monetization: 'Add messages to generate monetization strategy.',
            tasks: 'Add messages to generate task breakdown.',
            nextSteps: 'Add messages to generate next steps.'
        };
    }
}

// Export for use in app
const compiler = new SpecCompiler();
