/**
 * Created by tha on 12-10-2017.
 * Based on: "https://medium.com/@pshrmn/a-simple-react-router-v4-tutorial-7f23ff27adf"
 */
import React, { Component } from 'react';
import { Switch, Route, Link } from 'react-router-dom'
//import { Link } from 'react-router-dom';
import '../css/routing.css';

const products = [
    {number: '101', name: 'Wetsuit', price: 3.00}
    ,{number: '102', name: 'Gloves', price: 5.10}
    ,{number: '103', name: 'Paddlefloat', price: 2.70}
    ,{number: '104', name: 'Pump', price: 3.40}
    ,{number: '105', name: 'Hood', price: 3.70}
];
const RoutingDemo = () => (

    <div>
        <Header />
        <Main />
    </div>
);
export default RoutingDemo;

const Header = () => (
    <header>
        <nav>
            <ul>
                <li><Link to='/'>Home</Link></li>
                <li><Link to='/products'>products</Link></li>
                <li><Link to='/about'>About us</Link></li>
            </ul>
        </nav>
    </header>
);

const Main = () => (
    <main>
        <Switch>
            <Route exact path='/' component={Home}/>
            <Route path='/products' component={Product}/>
            <Route path='/about' component={About}/>
        </Switch>
    </main>
);
const Home = () => (
    <div>
        <h1>Welcome to the Tornadoes Website!</h1>
    </div>
)
const Product = () => (
    <Switch>
        <Route exact path='/products' component={ProductList}/>
        <Route path='/products/:number' component={ProductDetails}/>
    </Switch>
);
const ProductList = () => (
    <div>
        <h2>Our products: </h2>
        <ul>
            {
                products.map(p => (
                    <li key={p.number}>
                        <Link to={`/products/${p.number}`}>{p.name}</Link>
                    </li>
                ))
            }
        </ul>
    </div>
);
const ProductDetails = (props) => {
    const productsFiltered = products.filter((p)=>p.number === props.match.params.number, 10);
    const product = (productsFiltered.length > 0)?productsFiltered[0]:null;
    if (!product) {
        return <div>Sorry, but the product {props.match.params.number} was not found</div>
    }
    return (
        <div>
            <h2>Product details:</h2>
            <h3>{product.name} (#{product.number})</h3>
            <h3>Price: {product.price}</h3>
        </div>
    );
}
const About = () => (
    <div>
        <h2>Who are we</h2>
        <p>We are a small company that .....</p>
    </div>
);