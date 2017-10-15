/**
 * Created by tha on 30-03-2017.
 */
import React from "react";
import { observer } from "mobx-react";

@observer
class TodoView extends React.Component {
    render() {
        const todo = this.props.todo;
        return (
            <li onDoubleClick={this.onRename}>
                <input type='checkbox'
                    checked={todo.completed}
                    onChange={this.onToggleCompleted}
                />
                {todo.task} <span style={{ color: "darkGreen" }}>
          {todo.assignee
              ? <small>(Assignee: {todo.assignee.name})</small>
              : null
          }</span>
            </li>
        );
    }

    onToggleCompleted = () => {
        const todo = this.props.todo;
        todo.completed = !todo.completed;
    }

    onRename = () => {
        const todo = this.props.todo;
        todo.task = prompt('Task name', todo.task) || todo.task;
    }
}
export default TodoView;
