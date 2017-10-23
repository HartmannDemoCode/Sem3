/**
 * Created by tha on 23-10-2017.
 */
import React from "react";
import {NavLink } from "react-router-dom";
export default class Header extends React.Component {
    render() {
        return (
            <div>
                <ul className="header">
                    <li><NavLink exact activeClassName="active" to="/">Home</NavLink></li>
                    <li><NavLink activeClassName="active" to="/products">Products</NavLink></li>
                    <li><NavLink activeClassName="active" to="/company">Company</NavLink></li>
                </ul>
            </div>
        );
    }
}