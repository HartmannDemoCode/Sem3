import React from 'react';
import ReactDOM from 'react-dom';
import './css/index.css';
import App from './components/App';
import StateChanger from './components/StateChanger';
import Converter from './components/converters/Converter';
import { BrowserRouter } from 'react-router-dom';
import RoutingDemo from './components/RoutingDemo';
import CarApp from './components/CarApp';
import Data3Ways from './components/Data3Ways';
import TicTacToe from './games/tictactoe/TicTacToeGame';
import Container from './components/stateChangerAdv/Container';
import ObservableTodoStore, {peopleStore} from './components/mobxDemo/data/ObservableTodoStore';
import TodoList from './TodoList';
//const observableTodoStore = window.store = ObservableTodoStore;
// ReactDOM.render(<CarApp/>, document.getElementById('root'));
// ReactDOM.render(<TicTacToe/>, document.getElementById('root'));
// ReactDOM.render(<TodoList store={observableTodoStore} people={peopleStore}/>, document.getElementById('root'));
ReactDOM.render(<Data3Ways/>, document.getElementById('root'));

{/* Use this for router demo
 (<BrowserRouter>
 <RoutingDemo />
 </BrowserRouter>)
 */}