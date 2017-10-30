import React from 'react';
import ReactDOM from 'react-dom';
import './css/index.css';
//import App from './components/App';
import StateChanger from './components/StateChanger';
import Converter from './components/converters/Converter';
import { BrowserRouter } from 'react-router-dom';
import RoutingDemo from './components/RoutingDemo';
import CarApp from './components/CarApp';
import Data3Ways from './components/Data3Ways';
import TicTacToe from './games/tictactoe/TicTacToeGame';
import Container from './components/stateChangerAdv/Container';
//import ObservableTodoStore, {peopleStore} from './components/mobxDemo/data/ObservableToDoStore';
//import TodoList from './TodoList';
import W2D1App from './exercises/week2-D1_COS_React_Routing/Week2-D1_COS_React_Routing';
// import BookStore from './exercises/week2-D1_COS_React_Routing/BookStore';
// import StateDemo from './components/liftingUp/StateDemo';
// let bookStore = new BookStore();
//const observableTodoStore = window.store = ObservableTodoStore;
// ReactDOM.render(<CarApp/>, document.getElementById('root'));
// ReactDOM.render(<TicTacToe/>, document.getElementById('root'));
// ReactDOM.render(<TodoList store={observableTodoStore} people={peopleStore}/>, document.getElementById('root'));
// ReactDOM.render(<W2D1App bookStore={bookStore}/>, document.getElementById('root'));
ReactDOM.render(<BrowserRouter>
    <RoutingDemo />
</BrowserRouter>, document.getElementById('root'));
{/* Use this for router demo
 ()
 */}