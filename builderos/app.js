/**
 * BuilderOS - Main Application
 * Lightweight product planning web app
 */
class BuilderOS {
    constructor() {
        this.currentProjectId = null;
        this.compiler = compiler;
        this.storage = storage;
        this.init();
    }

    /**
     * Initialize the application
     */
    init() {
        this.attachEventListeners();
        this.renderProjectsList();
        this.restoreState();
    }

    /**
     * Attach all event listeners
     */
    attachEventListeners() {
        // Project management
        document.getElementById('newProjectBtn').addEventListener('click', () => this.createNewProject());
        document.getElementById('emptyNewBtn').addEventListener('click', () => this.createNewProject());
        
        // Chat
        document.getElementById('sendBtn').addEventListener('click', () => this.sendMessage());
        document.getElementById('chatInput').addEventListener('keypress', (e) => {
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                this.sendMessage();
            }
        });
        
        // Project actions
        document.getElementById('editTitleBtn').addEventListener('click', () => this.editProjectTitle());
        document.getElementById('deleteProjectBtn').addEventListener('click', () => this.deleteProject());
        document.getElementById('regenerateBtn').addEventListener('click', () => this.regenerateOutput());
        
        // Data management
        document.getElementById('exportBtn').addEventListener('click', () => this.exportData());
        document.getElementById('clearBtn').addEventListener('click', () => this.clearAllData());
        
        // Section collapse/expand
        document.querySelectorAll('.section-header').forEach(header => {
            header.addEventListener('click', (e) => {
                const section = e.currentTarget.closest('.output-section');
                section.classList.toggle('collapsed');
            });
        });
    }

    /**
     * Create a new project
     */
    createNewProject() {
        const name = prompt('Project name:', '');
        if (name !== null) {
            const project = this.storage.createProject(name.trim() || 'Untitled Project');
            this.renderProjectsList();
            this.selectProject(project.id);
        }
    }

    /**
     * Select a project
     */
    selectProject(projectId) {
        this.currentProjectId = projectId;
        this.renderProjectView();
        this.updateProjectsList();
        localStorage.setItem('builderos_currentProject', projectId);
    }

    /**
     * Render projects list in sidebar
     */
    renderProjectsList() {
        const projectsList = document.getElementById('projectsList');
        const projects = this.storage.getAllProjects();
        
        projectsList.innerHTML = projects.map(project => `
            <div class="project-item ${project.id === this.currentProjectId ? 'active' : ''}">
                <span class="project-item-name" title="${project.name}">${project.name}</span>
                <div class="project-item-actions">
                    <button class="btn-icon" onclick="app.deleteProject('${project.id}')" title="Delete" style="display: none;">🗑️</button>
                </div>
            </div>
        `).join('');
        
        // Add click handlers
        document.querySelectorAll('.project-item').forEach(item => {
            item.addEventListener('click', (e) => {
                if (!e.target.classList.contains('btn-icon')) {
                    const projectName = item.querySelector('.project-item-name').textContent;
                    const projectId = Array.from(this.storage.getAllProjects()).find(p => p.name === projectName)?.id;
                    if (projectId) this.selectProject(projectId);
                }
            });
        });
    }

    /**
     * Update projects list (for active state)
     */
    updateProjectsList() {
        document.querySelectorAll('.project-item').forEach(item => {
            item.classList.remove('active');
        });
        
        if (this.currentProjectId) {
            const activeItem = Array.from(document.querySelectorAll('.project-item')).find(item => {
                const project = this.storage.getProject(this.currentProjectId);
                return item.textContent.includes(project?.name);
            });
            if (activeItem) activeItem.classList.add('active');
        }
    }

    /**
     * Render project view
     */
    renderProjectView() {
        const project = this.storage.getProject(this.currentProjectId);
        
        if (!project) {
            this.showEmptyState();
            return;
        }
        
        document.getElementById('emptyState').style.display = 'none';
        document.getElementById('projectView').style.display = 'flex';
        
        // Update title
        document.getElementById('projectTitle').textContent = project.name;
        document.getElementById('projectTitleInput').value = project.name;
        
        // Render messages
        this.renderMessages(project.messages);
        
        // Update message count
        document.getElementById('messagesCount').textContent = project.messages.length;
        
        // Render output
        this.renderOutput(project);
        
        // Clear chat input
        document.getElementById('chatInput').value = '';
        document.getElementById('chatInput').focus();
    }

    /**
     * Render messages in chat
     */
    renderMessages(messages) {
        const container = document.getElementById('messagesContainer');
        container.innerHTML = messages.map(msg => `
            <div class="message" title="${new Date(msg.timestamp).toLocaleString()}">
                ${this.escapeHtml(msg.text)}
            </div>
        `).join('');
        
        // Scroll to bottom
        setTimeout(() => {
            container.scrollTop = container.scrollHeight;
        }, 0);
    }

    /**
     * Send a message
     */
    sendMessage() {
        const input = document.getElementById('chatInput');
        const text = input.value.trim();
        
        if (!text) return;
        if (!this.currentProjectId) return;
        
        // Add message to storage
        this.storage.addMessage(this.currentProjectId, text);
        
        // Get updated project
        const project = this.storage.getProject(this.currentProjectId);
        
        // Re-compile output
        const output = this.compiler.compile(project.messages);
        this.storage.updateOutput(this.currentProjectId, output);
        
        // Re-render
        this.renderMessages(project.messages);
        document.getElementById('messagesCount').textContent = project.messages.length;
        this.renderOutput(project);
        input.value = '';
        input.focus();
    }

    /**
     * Render structured output
     */
    renderOutput(project) {
        const output = project.output || this.compiler.compile(project.messages);
        
        const sections = [
            { id: 'conceptSection', key: 'concept', title: 'Concept' },
            { id: 'featuresSection', key: 'features', title: 'Features' },
            { id: 'screensSection', key: 'screens', title: 'Screens' },
            { id: 'dataModelSection', key: 'dataModel', title: 'Data Model' },
            { id: 'monetizationSection', key: 'monetization', title: 'Monetization' },
            { id: 'tasksSection', key: 'tasks', title: 'Tasks' },
            { id: 'nextStepsSection', key: 'nextSteps', title: 'Next Steps' }
        ];
        
        sections.forEach(section => {
            const element = document.getElementById(section.id);
            if (element) {
                const content = output[section.key] || 'Empty';
                element.textContent = content;
            }
        });
    }

    /**
     * Edit project title
     */
    editProjectTitle() {
        const titleEl = document.getElementById('projectTitle');
        const inputEl = document.getElementById('projectTitleInput');
        const btnEl = document.getElementById('editTitleBtn');
        
        if (inputEl.style.display === 'none') {
            // Enter edit mode
            titleEl.style.display = 'none';
            inputEl.style.display = 'block';
            inputEl.focus();
            inputEl.select();
            btnEl.textContent = '✓';
        } else {
            // Save
            const newName = inputEl.value.trim() || 'Untitled Project';
            this.storage.renameProject(this.currentProjectId, newName);
            
            titleEl.textContent = newName;
            titleEl.style.display = 'block';
            inputEl.style.display = 'none';
            btnEl.textContent = '✏️';
            
            this.renderProjectsList();
        }
    }

    /**
     * Delete project
     */
    deleteProject(projectId = this.currentProjectId) {
        if (!projectId) return;
        
        const project = this.storage.getProject(projectId);
        if (confirm(`Delete project "${project.name}"? This cannot be undone.`)) {
            this.storage.deleteProject(projectId);
            this.currentProjectId = null;
            this.renderProjectsList();
            this.showEmptyState();
        }
    }

    /**
     * Regenerate output
     */
    regenerateOutput() {
        if (!this.currentProjectId) return;
        
        const project = this.storage.getProject(this.currentProjectId);
        const output = this.compiler.compile(project.messages);
        this.storage.updateOutput(this.currentProjectId, output);
        this.renderOutput(project);
    }

    /**
     * Export all data
     */
    exportData() {
        const data = this.storage.exportProjects();
        const blob = new Blob([data], { type: 'application/json' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `builderos-export-${Date.now()}.json`;
        a.click();
        URL.revokeObjectURL(url);
    }

    /**
     * Clear all data
     */
    clearAllData() {
        if (confirm('Clear ALL data? This cannot be undone.')) {
            this.storage.clearAllData();
            this.currentProjectId = null;
            this.renderProjectsList();
            this.showEmptyState();
        }
    }

    /**
     * Show empty state
     */
    showEmptyState() {
        document.getElementById('emptyState').style.display = 'flex';
        document.getElementById('projectView').style.display = 'none';
    }

    /**
     * Restore last selected project
     */
    restoreState() {
        const lastProject = localStorage.getItem('builderos_currentProject');
        const projects = this.storage.getAllProjects();
        
        if (projects.length === 0) {
            this.showEmptyState();
        } else if (lastProject && this.storage.getProject(lastProject)) {
            this.selectProject(lastProject);
        } else if (projects.length > 0) {
            this.selectProject(projects[0].id);
        }
    }

    /**
     * Escape HTML to prevent XSS
     */
    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }
}

// Initialize app when DOM is ready
let app;
document.addEventListener('DOMContentLoaded', () => {
    app = new BuilderOS();
});
