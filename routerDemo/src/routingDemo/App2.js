/**
 * Created by tha on 23-10-2017.
 */
//Create a new file App2.js, and copy all from this file into is

import React from "react";
import { BrowserRouter, Route, Link, NavLink, Switch, Prompt } from "react-router-dom";
import bookStore from './BookStore';
import Details from './Details';
import Header from './Header';
import Product from './Products';

//Todo: This component must be completed



const Home = () => (
    <div>
        <h2>Home View</h2>
        <p>Info about this site</p>
    </div>
)

const Company = () => {
    return (
        <div>
            <h2>About Us</h2>
            <p>Our about page</p>
        </div>
    )
}

export default class App2 extends React.Component {
    constructor() {
        super();
    }
    render() {
        return (
            <BrowserRouter>
                <div>
                    <Header />
                    <Switch>
                        <Route path="/company" component={Company} />
                        <Route path="/products" render={(props) =>{return  (<Product {...props} bookStore={bookStore} />)}} />
                        <Route component={Home}></Route>
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}