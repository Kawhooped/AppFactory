/**
 * Local Storage Manager
 * Handles all project persistence and management
 */
class StorageManager {
    constructor() {
        this.storageKey = 'builderos_projects';
    }

    /**
     * Get all projects
     */
    getAllProjects() {
        const data = localStorage.getItem(this.storageKey);
        return data ? JSON.parse(data) : [];
    }

    /**
     * Get a specific project by ID
     */
    getProject(projectId) {
        const projects = this.getAllProjects();
        return projects.find(p => p.id === projectId);
    }

    /**
     * Create a new project
     */
    createProject(name) {
        const projects = this.getAllProjects();
        const newProject = {
            id: this.generateId(),
            name: name || `Project ${projects.length + 1}`,
            messages: [],
            output: {},
            createdAt: Date.now(),
            updatedAt: Date.now()
        };
        projects.push(newProject);
        this.saveProjects(projects);
        return newProject;
    }

    /**
     * Update a project
     */
    updateProject(projectId, updates) {
        const projects = this.getAllProjects();
        const projectIndex = projects.findIndex(p => p.id === projectId);
        
        if (projectIndex !== -1) {
            projects[projectIndex] = {
                ...projects[projectIndex],
                ...updates,
                updatedAt: Date.now()
            };
            this.saveProjects(projects);
            return projects[projectIndex];
        }
        return null;
    }

    /**
     * Delete a project
     */
    deleteProject(projectId) {
        const projects = this.getAllProjects();
        const filtered = projects.filter(p => p.id !== projectId);
        this.saveProjects(filtered);
    }

    /**
     * Add a message to a project
     */
    addMessage(projectId, message) {
        const project = this.getProject(projectId);
        if (project) {
            project.messages.push({
                id: this.generateId(),
                text: message,
                timestamp: Date.now()
            });
            this.updateProject(projectId, { messages: project.messages });
        }
    }

    /**
     * Update project output (structured sections)
     */
    updateOutput(projectId, output) {
        this.updateProject(projectId, { output });
    }

    /**
     * Get project output
     */
    getOutput(projectId) {
        const project = this.getProject(projectId);
        return project ? project.output : {};
    }

    /**
     * Rename a project
     */
    renameProject(projectId, newName) {
        return this.updateProject(projectId, { name: newName });
    }

    /**
     * Export all projects as JSON
     */
    exportProjects() {
        const projects = this.getAllProjects();
        return JSON.stringify(projects, null, 2);
    }

    /**
     * Clear all data
     */
    clearAllData() {
        localStorage.removeItem(this.storageKey);
    }

    /**
     * Generate unique ID
     */
    generateId() {
        return `${Date.now()}_${Math.random().toString(36).substr(2, 9)}`;
    }

    /**
     * Save projects to localStorage
     */
    saveProjects(projects) {
        localStorage.setItem(this.storageKey, JSON.stringify(projects));
    }
}

// Export for use in other modules
const storage = new StorageManager();
