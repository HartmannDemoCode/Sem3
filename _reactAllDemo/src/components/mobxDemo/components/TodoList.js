/**
 * Created by tha on 30-03-2017.
 */
import React from "react";
import { observer } from "mobx-react";
import TodoView from "./ToDoView";
import waitingSign from "../loading.svg";

@observer
class TodoList extends React.Component {
    render() {
        const store = this.props.store;
        return (
            <div>
                { store.report }
                <ul>
                    { store.todos.map(
                        (todo, idx) => <TodoView todo={ todo } key={ idx } />
                    ) }
                </ul>
                <div style={{height:70}}>
                    {store.pendingRequests > 0 ?  <img src={waitingSign} alt="logo" /> : null}
                </div>
                <button onClick={ this.onNewTodo }>New Todo</button>
                <small> (double-click a todo to edit)</small>
            </div>
        );
    }

    onNewTodo = () => {
        this.props.store.addTodo(prompt('Enter a new todo:','Complete Todays Exercises'));
    }
}
export default TodoList;
