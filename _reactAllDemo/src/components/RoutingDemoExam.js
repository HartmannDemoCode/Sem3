/**
 * Created by tha on 12-10-2017.
 * Based on: "https://medium.com/@pshrmn/a-simple-react-router-v4-tutorial-7f23ff27adf"
 */
import React, { Component } from 'react';
import { Switch, Route, Link } from 'react-router-dom'
//import { Link } from 'react-router-dom';
import '../css/routing.css';
import '../css/exam.css';

// const products = [
//     {number: '101', name: 'Wetsuit', price: 3.00}
//     ,{number: '102', name: 'Gloves', price: 5.10}
//     ,{number: '103', name: 'Paddlefloat', price: 2.70}
//     ,{number: '104', name: 'Pump', price: 3.40}
//     ,{number: '105', name: 'Hood', price: 3.70}
// ];
const electionCandidates = [
    {id: 1, kommune:'Lyngby', name: 'Dorthe La Cour', party:'Konservative', description:'Formand for Børne- og Ungdomsudvalget, og medlem af Økonomiudvalget og Kultur- og Fritidsudvalget', image:'cour_dorthe_la.jpg',url:'http://www.ltk.dk/dorthe-la-cour'},
    {id: 2, kommune:'Lyngby', name: 'Mette Schmidt Olsen', party:'Konservative', description:'Formand for Teknik- og Miljøudvalget og næstformand for Kultur- og Fritidsudvalget', image:'schmidt_olsen_mette.jpg',url:'http://www.ltk.dk/mette-schmidt-olsen'},
    {id: 3, kommune:'Lyngby', name: 'Sofia Osmani', party:'Konservative', description:'Borgmester', image:'osmani_sofia.jpg',url:'http://www.ltk.dk/sofia-osmani'},
    {id: 4, kommune:'Lyngby', name: 'Bodil Kornbek', party:'Socialdemokratiet', description:'Formand for Social- og Sundhedsudvalget, næstformand for Teknik- og Miljøudvalget, og medlem af Børne- og Ungdomsudvalget', image:'bodil_kornbek.jpg',url:'http://www.ltk.dk/bodil-kornbek'},
    {id: 5, kommune:'Lyngby', name: 'Ib Carlsen', party:'Socialdemokratiet', description:'Medlem af Kultur- og Fritidsudvalget og Beskæftigelses- og Integrationsudvalget', image:'carlsen_ib.jpg',url:'http://www.ltk.dk/ib-carlsen'},
    {id: 6, kommune:'Lyngby', name: 'Mette Hoff', party:'Socialdemokratiet', description:'Medlem af Økonomiudvalget og Næstformand for Børne- og Ungdomsudvalget', image:'hoff_mette.jpg',url:'http://www.ltk.dk/mette-hoff'},
    {id: 7, kommune:'Lyngby', name: 'Simon Pihl Sørensen', party:'Socialdemokratiet', description:'1. viceborgmester. Formand for Byplanudvalget og næstformand for Økonomiudvalget', image:'pihl_simon.jpg',url:'http://www.ltk.dk/simon-pihl-soerensen'},
    {id: 8, kommune:'Lyngby', name: 'Finn Riber Rasmussen', party:'Socialdemokratiet', description:'Medlem af Kultur- og Fritidsudvalget og Social- og Sundhedsudvalget', image:'riber_finn.jpg',url:'http://www.ltk.dk/finn-riber-rasmussen'},
    {id: 9, kommune:'Lyngby', name: 'Karsten Lomholt', party:'Konservative', description:'Næstformand for Social- og Sundhedsudvalget, og medlem af Beskæftigelses- og Integrationsudvalget, Byplanudvalget og Børne- og Ungdomsudvalget', image:'lomholt_karsten.jpg',url:'http://www.ltk.dk/karsten-lomholt'},
    {id: 10, kommune:'Lyngby', name: 'Jan Kaspersen', party:'Konservative', description:'Medlem af Økonomiudvalget og Byplanudvalget', image:'kaspersen_jan.jpg',url:'http://www.ltk.dk/jan-kaspersen'},
    {id: 11, kommune:'Fredensborg', name: 'Thomas Lykke Pedersen', party:'Socialdemokratiet', description:'Borgmester og Formand for Økonomiudvalget', image:'thomas_lykke.jpg',url:''},
    {id: 12, kommune:'Fredensborg', name: 'Ulla Hardy Hansen', party:'Konservative', description:'1. Viceborgmester og Formand for Kulturudvalget, medlem af Økonomiudvalget og Plan-, Miljø- og Klimaudvalget.', image:'ulla_hardy_hansen.jpg',url:''},
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
                <li><Link to='/'>Front page</Link></li>
                <li><Link to='/municipality-board-members'>City councils</Link></li>
                <li><Link to='/about'>About</Link></li>
            </ul>
        </nav>
    </header>
);

const Main = () => (
    <main>
        <Switch>
            <Route exact path='/' component={Home}/>
            <Route path='/municipality-board-members' component={Members}/>
            <Route path='/about' component={About}/>
        </Switch>
    </main>
);
const Home = () => (
    <div>
        <h1>Welcome to an overview of the political landscape in Denmarks 98 municipalities!</h1>
    </div>
)
const Members = () => (
    <Switch>
        <Route exact path='/municipality-board-members' component={CandidateList}/>
        <Route path='/municipality-board-members/:id' component={CandidateDetails}/>
    </Switch>
);
const CandidateList = () => (
    <div>
        <h2>These are the candidates </h2>
        <ul>
            {
                electionCandidates.map(c => (
                    <li key={c.id}>
                        <Link to={`/municipality-board-members/${c.id}`}><span className="tabulator">{c.name}</span> {c.party}</Link>
                    </li>
                ))
            }
        </ul>
    </div>
);
const CandidateDetails = (props) => {
    const candidatesFiltered = electionCandidates.filter((c)=>c.id === Number(props.match.params.id));
    console.log('props.match.params.id: '+props.match.params.id + ' and length of array: '+electionCandidates.length);
    const candidate = (candidatesFiltered.length > 0)?candidatesFiltered[0]:null;
    if (!candidate) {
        return <div>Sorry, but the candidate no. {props.match.params.number} was not found</div>
    }
    return (
        <div>
            <h2>Candidate details:</h2>
            <h3>{candidate.name} (#{candidate.id})</h3>
            <img src={require('./img/'+candidate.image)}/>
            <h5>Description: {candidate.description}</h5>
        </div>
    );
}
const About = () => (
    <div>
        <h2>The purpose of this web site</h2>
        <p>To inform anybody who would have the interest about the people and the parties that are gorverning the 98 danish municipalities</p>
    </div>
);