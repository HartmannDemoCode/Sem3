/**
 * Created by tha on 01-04-2017.
 */
import { observable, computed} from 'mobx';

class BookStore{
    @observable books = [];
}
const bookStore = window.bookStore =  new BookStore();
bookStore.books.push({id: 1, name: "Den grønne kogebog", author: "Liselotte Preisler", year: 1999});
bookStore.books.push({id: 2, name: "4 dage i humlebæk", author: "Charlotte Hartmann", year: 2015});
export default bookStore;