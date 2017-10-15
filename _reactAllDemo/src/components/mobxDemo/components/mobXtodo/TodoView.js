/**
 * Created by tha on 12-05-2017.
 */
import React, { Component } from 'react';
import {observer} from 'mobx-react';

@observer
export default class TodoView extends React.Component {

    render() {
        const people = this.props.people;
        const todo = this.props.todo;
        const name = (todo.assignee)? todo.assignee.name : 'N/A';
        return (
            <li onDoubleClick={ this.onRename }>
                <input
                    type='checkbox'
                    checked={ todo.completed }
                    onChange={ this.onToggleCompleted }
                />
                { todo.task } responsible: { name }. assign task to:
                <select onChange={ this.changeAssignee} >
                    <option disabled selected>Choose an assignee</option>
                    {people.map((assignee, idx)=><option key={idx}>{assignee.name}</option>)}
                </select>
                <button id={todo.id} onClick={this.props.deleteTodo}>delete task</button>
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
    changeAssignee = (event) => {
        console.log("selected: "+event.target.options[event.target.selectedIndex].value);
        const todo = this.props.todo;
        todo.assignee = {name: event.target.options[event.target.selectedIndex].value}
    }
    // deleteTask(event){
    //     //console.log("delete");
    //    this.props.deleteTask;
    // }
}


