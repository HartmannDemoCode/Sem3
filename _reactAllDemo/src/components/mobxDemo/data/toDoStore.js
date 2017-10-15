/**
 * Created by tha on 30-03-2017.
 */
//Part 1 of the exercise (using React without observable).
class ToDoStore {
    todos = [];
    get completedTodosCount() {
        return this.todos.filter(
            todo => todo.completed === true
        ).length;
    }

    report() {
        if (this.todos.length === 0)
            return "<none>";
        return `Next todo: "${this.todos[0].task}". ` +
            `Progress: ${this.completedTodosCount}/${this.todos.length}`;
    }

    addTodo(task) {
        this.todos.push({
            task: task,
            completed: false,
            assignee: null
        });
    }
}
const todoStore = new ToDoStore();
window.todoStore = todoStore; //Only for initial debugging
export default todoStore;


