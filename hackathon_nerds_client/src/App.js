import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <Router>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/hospitals">Hospitals</Link>
          </li>
          <li>
            <Link to="/person">Person</Link>
          </li>
          <Route exact path="/" component={Home} />
          <Route path="/hospitals" component={Hospitals} />
          <Route path="/person" component={Person} />
        </ul>
      </Router>
    );
  }
}


class Home extends Component{
  render() {
    return (
      <div>
        
      </div>
    );
  }
}

class Hospitals extends Component{
  constructor() {
  	super();
 		 this.state={items:[]};
  }
  componentDidMount(){
    this.getHostpitals();
  }

  getHostpitals=()=>{
    fetch(`http://localhost:3000/hospitals`)
    .then(result=>result.json())
   .then(items=>{
     this.setState({items});
     console.log(items);
   })
  }
  
  chooseHospitalBtn=(hospital)=>{
    console.log(hospital.id);
  }

  displayListOfHospitals = () =>{
    return this.state.items.map(hospital =>{
      return(<button onClick={()=>this.chooseHospitalBtn(hospital)}>{hospital.name}</button>)
    })
  }

  render() {
    return (
      <div>
        {this.displayListOfHospitals()}
      </div>
    );
  }
}

class Person extends Component{
  render() {
    return (
      <div>
        This will be person
      </div>
    );
  }
}

class Hospital extends Component{
  render() {
    return (
      <div>
        This will be just a hospital
      </div>
    );
  }
}

export default App;
