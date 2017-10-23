/**
 * Created by tha on 23-10-2017.
 */
import React from "react";
import bookStore from './BookStore';
export default class AddBook extends React.Component {
    constructor(props) {
        super(props);
        this.state = { "book": { title: "", info: "" }, isDirty: false }
    }
    onSave = () => {
        bookStore.addBook(this.state.book);
    }
    onChange = (e) => {
        const name = e.target.name;
        const val = e.target.value;
        if(name === 'title'){
            this.state.book.title = val;
        }
        else if(name === 'info'){}
    }
    render() {
        return (
            <div>
                Title: <input name="title" onChange={this.onChange} />
                Info: <input name="info" />
                <button onClick={this.onSave}>Save</button>
                {/*
                 <Prompt
                 when={this.state.isDirty}
                 message="Yoy have unsaved data that will be lost!"
                 />*/}
            </div>
        )
    }
}