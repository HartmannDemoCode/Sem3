/**
 * Created by tha on 23-10-2017.
 */
//DataStore for this Demo
//Place in a file bookStore.js
class BookStore {

    constructor() {
        this._books = [
            { id: 1,title: "How to Learn JavaScript - Vol 1", info: "Study hard"},
            { id: 2,title: "How to Learn ES6", info: "Complete all exercises :-)"},
            { id: 3,title: "How to Learn React",info: "Complete all your CA's"},
            { id: 4,title: "Learn JavaScript, React and MobX",info: "Don't drink beers, until Friday (after four)"
            }
        ]
        this._nextID= 5;
    }
    get books(){
        return this._books;
    }
    addBook(book){
        book.id = this._nextID;
        this._books.push(book);
        this._nextID++;
    }
}

let bookStore = new BookStore();
export default bookStore;