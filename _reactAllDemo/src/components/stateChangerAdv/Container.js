import React, { Component } from 'react';
import Child1 from './Child1';
import Child2 from './Child2';
import Parent from './Parent';


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
