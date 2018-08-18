/**
 * Created by tha on 01-04-2017.
 */
import React, { Component } from 'react';
import './App.css';
import BookList from "./components/BookList";
// import store from "./data/observableToDoStore";
// import observableStore from "./data/observableToDoStore";
import bookStore from "./data/bookStore";
// import TodoList from "./components/TodoList";
@observer
class MyApp extends Component {
    render() {
        return (
            <div className="App">
                <BookList books={bookStore.books}></BookList>
            </div>
        );
    }
}

export default MyApp;
