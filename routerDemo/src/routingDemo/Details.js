/**
 * Created by tha on 23-10-2017.
 */
import React from "react";
import { Link } from "react-router-dom";

export default class Details extends React.Component {
    render() {
        let id = this.props.match.params.id;
        let book = this.props.bookStore.books.filter((book) => {
            return book.id === Number(id);
        })[0];
        return (
            <div style={{ padding: 4 }}>
                <h4 style={{ color: "steelblue" }}>Detailed info for the title: {book.title}</h4>
                <p>Info: {book.info}</p>
                <br />

            </div>
        );
    }
}