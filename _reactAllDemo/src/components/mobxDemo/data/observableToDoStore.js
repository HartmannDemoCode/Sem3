/**
 * Created by tha on 30-03-2017.
 */
import mobx, { observable,computed} from "mobx";

//Part 2 of the exercise (Now with observable properties)
class ObservableTodoStore {
    @observable todos = [];
    @observable pendingRequests = 0;

    // constructor() {
    //     mobx.autorun(() => console.log(this.report));
    // }

    @computed get completedTodosCount() {
        return this.todos.filter(
            todo => todo.completed === true
        ).length;
    }

    @computed get report() {
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

const observableTodoStore = new ObservableTodoStore();
observableTodoStore.addTodo("read MobX tutorial");
observableTodoStore.addTodo("try MobX");
window.observableTodoStore = observableTodoStore;
export default observableTodoStore;
