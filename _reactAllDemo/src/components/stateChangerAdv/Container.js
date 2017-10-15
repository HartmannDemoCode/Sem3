import React, { Component } from 'react';
import Child1 from './Child1';
import Child2 from './Child2';
import logo from './logo.svg';
import Parent from './Parent';
import './App.css';

class Container extends Component {

  render() {
    return (
      <div className="App">
        <Parent>< Child2 update={this.update}/></Parent>
      </div>
    );
  }
}
export default Container;
