/**
 * Created by tha on 12-05-2017.
 */
import React, {Component} from 'react';
import  TodoView  from './TodoView';
import {observer} from "mobx-react";


@observer
export default class TodoList extends React.Component {
    render() {
        const store = this.props.store;

        return (
            <div>
                { store.report }
                <ul>
                    { store.todos.map(
                        (todo, idx) => <TodoView todo={ todo } key={ idx } people={this.props.people} deleteTodo={this.deleteTodo} />
                    ) }
                </ul>
                {/*{ store.pendingRequests > 0 ? <marquee>Loading...</marquee> : null }*/}
                <button onClick={ this.onNewTodo }>New Todo</button>
                <small> (double-click a todo to edit)</small>
                {/*<RenderCounter />*/}
            </div>
        );
    }

    onNewTodo = () => {
        this.props.store.addTodo(prompt('Enter a new todo:','coffee plz'));
    }
    deleteTodo = (event) => {
         console.log("todo list delete: id: "+event.target.id);
         const id = Number(event.target.id);
        this.props.store.todos = this.props.store.todos.filter((todo)=>{ return todo.id !== id });
    }
}
