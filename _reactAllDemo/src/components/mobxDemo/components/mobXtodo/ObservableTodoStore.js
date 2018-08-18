/**
 * Created by tha on 12-05-2017.
 */
import mobx, {observable, computed, action, useStrict} from 'mobx';
useStrict(true);
const URL = "http://localhost:4000/todos";
class ObservableTodoStore {
    @observable todos = []; //by adding the @observable annotation the array becomes a mobX array
    @observable pendingRequests = 0;

    @action
    loadTodos(){
        fetch(URL)
            .then(res => res.json() )
            .then( action((todosArray) =>{ //surrounding the callback with action() is equivelent to annotating a method with @action.
                //this.todos = todosArray; //This dont work cuss this.todos is a reference (not to a js array, but) to a mobx array (it would no longer be a mobX array if we did this)
                this.todos.replace(todosArray); //mobx has this replace method to replace the content of the mobx array with a new array.

            }))
    }

    @computed get completedTodosCount() {
        return this.todos.filter(
            todo => todo.completed === true
        ).length;
    }

    @computed get report(){
        if (this.todos.length === 0)
            return "<none>";
        return `Next todo: "${this.todos[this.todos.length-1].task}". ` +
            `Progress: ${this.completedTodosCount}/${this.todos.length}`;
    }
    print(){

        this.todos.forEach((todo)=>console.log(`${todo.task} with id: ${todo.id} is ${(todo.completed)?'completed': 'not completed'} and assigned to ${(todo.assignee)? todo.assignee.name: 'N/A'}`));
    }

   @action
    addTodo(task){
        this.todos.push({
            id: this.todos.length,
            task: task,
            completed: false,
            assignee: null
        });
    }
}

var peopleStore = mobx.observable([
    { name: "Michel" },
    { name: "Freddy" },
    { name: "Paula" }
]);

const observableTodoStore = new ObservableTodoStore();
export default observableTodoStore;
export {peopleStore};