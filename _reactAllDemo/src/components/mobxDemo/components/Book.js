/**
 * Created by tha on 01-04-2017.
 */
import React, { Component } from 'react';
import { observer } from "mobx-react";

export default (props)=>{
    console.log(props);
    return (
    <tr>
        <td>{props.book.id}</td>
        <td>{props.book.name}</td>
        <td>{props.book.year}</td>
        <td>{props.book.author}</td>
    </tr>)
}
