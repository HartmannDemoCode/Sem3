/**
 * Created by tha on 14-10-2017.
 * */
import React from 'react';
import './TicTacToe.css';
export default class TicTacToeGame extends React.Component{
    constructor(){
        super();
        this.state = {isX:true, game:[]}
    }
    move = (event)=>{
        if(this.state.winner){return;}
        const id = event.target.id;
        const player = (this.state.isX)?'X':'0';
        this.state.game[id]=player;
        document.getElementById(id).innerText = player;
        const winner = this.calcWinner();
        this.setState({isX:!this.state.isX, winner: winner});
    }
    calcWinner(){
        const game = this.state.game;
        if(game[1] === game[2] && game[2] === game[3]){ return game[3];}
        if(game[4] === game[5] && game[5] === game[6]){ return game[6];}
        if(game[7] === game[8] && game[8] === game[9]){ return game[9];}
        if(game[1] === game[4] && game[4] === game[7]){ return game[7];}
        if(game[2] === game[5] && game[5] === game[8]){ return game[8];}
        if(game[3] === game[6] && game[6] === game[9]){ return game[9];}
        if(game[1] === game[5] && game[5] === game[9]){ return game[9];}
        if(game[3] === game[5] && game[7] === game[7]){ return game[7];}
        return null;
    }
    render(){

        return (<div>
            {(this.state.winner)?`The winner is ${this.state.winner}`:''}
            <div><Square id="1" onClick={this.move}/><Square id="2" onClick={this.move}/><Square id="3" onClick={this.move}/></div>
            <div><Square id="4" onClick={this.move}/><Square id="5" onClick={this.move}/><Square id="6" onClick={this.move}/></div>
            <div><Square id="7" onClick={this.move}/><Square id="8" onClick={this.move}/><Square id="9" onClick={this.move}/></div>
            </div>
        );
    }
}
const Square = (props)=><div className="square" id={props.id} onClick={props.onClick}></div>;