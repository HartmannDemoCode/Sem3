/**
 * Created by tha on 01-04-2017.
 */
import React, { Component } from 'react';
import Book from "./Book";
import { observer } from "mobx-react";

@observer
export default class BookList extends Component{
    render(){
        const books = this.props.books.map((book,idx)=><Book key={idx} book={book}/>);
        return (
            <table>
                <thead><tr>
                <th>id</th>
                <th>name</th>
                <th>year</th>
                <th>author</th>

                </tr></thead>
                <tbody>
                {books}
                </tbody>
            </table>
            );
    }
}