import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import MapWithADirectionsRenderer from "./map-component";
import MapWithAMarkerWithLabel from "./map-component-with-labels";

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

class Home extends Component {
  render() {
    return <div />;
  }
}

class Hospitals extends Component {
  constructor() {
    super();
    this.state = { items: [] };
  }
  componentDidMount() {
    this.getHostpitals();
  }

  getHostpitals = () => {
    fetch(`http://localhost:3000/hospitals`)
      .then(result => result.json())
      .then(items => {
        this.setState({ items });
        console.log(items);
      });
  };

  chooseHospitalBtn = hospital => {
    console.log(hospital.id);
  };

  displayListOfHospitals = () => {
    return this.state.items.map(hospital => {
      let hospitalLink = "/hospitals/" + hospital.id;
      return <Link to={hospitalLink}>{hospital.name}</Link>;
    });
  };
  createRoutesForHospitals = () => {
    return this.state.items.map(hospital => {
      let hospitalLink = "/hospitals/" + hospital.id;
      return <Route path={hospitalLink} component={Hospital} />;
    });
  };

  render() {
    return (
      <Router>
        <ul>
          {this.displayListOfHospitals()}
          {this.createRoutesForHospitals()}
        </ul>
      </Router>
    );
  }
}

class Person extends Component {
  constructor(props) {
    super(props);
    this.state = { gps_coordonates: "45.653,25.6130", numberOfPersons: 1,userSubmited:false,best_hospital_location:"45.6485988,25.6200351" };
    this.userFound = false;
  }

  geolocationCallBack = position => {
    console.log("showing position...");
    console.log(
      "Position: " +
        String(position.coords.latitude).slice(0, 6) +
        "," +
        String(position.coords.longitude).slice(0, 7)
    );
    // alert(String(position.coords.latitude).slice(0, 6) + "," + String(position.coords.longitude).slice(0, 7))
    this.setState({
      gps_coordonates:
        String(position.coords.latitude).slice(0, 6) +
        "," +
        String(position.coords.longitude).slice(0, 7)
    });
    this.userFound = true;
    this.forceUpdate();
    return (
      String(position.coords.latitude).slice(0, 6) +
      "," +
      String(position.coords.longitude).slice(0, 7)
    );
  };
  componentDidMount() {
    setInterval(() => {
      console.log("getting location...");
      if (navigator.geolocation) {
        // navigator.geolocation.getCurrentPosition(this.geolocationCallBack);

        navigator.geolocation.getCurrentPosition(this.geolocationCallBack);
      } else {
        alert("Geolocation is not supported by this browser.");
      }
    }, 5000);
    this.getHostpitals();
  }
  getHostpitals = () => {
    fetch(`http://localhost:3000/hospitals`)
      .then(result => result.json())
      .then(items => {
        this.setState({ items });
        console.log(items);
      });
  };

  drawMap = () => {
    if (this.state.userSubmited === false) {
      return (
        <MapWithAMarkerWithLabel
          isMarkerShown
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places"
          loadingElement={<div style={{ height: `100%` }} />}
          containerElement={<div style={{ height: `400px` }} />}
          mapElement={<div style={{ height: `100%` }} />}
          position={this.state.gps_coordonates}
        />
      );
    } else {
      return (
        <MapWithADirectionsRenderer
          isMarkerShown
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places"
          loadingElement={<div style={{ height: `100%` }} />}
          containerElement={<div style={{ height: `400px` }} />}
          mapElement={<div style={{ height: `100%` }} />}
          person_position={this.state.gps_coordonates}
          hospital_position={this.state.best_hospital_location}
        />
      );
    }
  };
  handleChange = e => {
    this.setState({ numberOfPersons: e.target.value });
  };

  handleClick = () => {
    console.log(this.state.numberOfPersons);
    this.setState({
      userSubmited:true
    })
    // this.sendPersonToBestHospital();
  };

  sendPersonToBestHospital = () => {};

  render() {
    return (
      <div>
        {this.drawMap()}
        <input type="text" onChange={this.handleChange} />
        <input
          type="button"
          value="Alert the text input"
          onClick={this.handleClick}
        />
      </div>
    );
  }
}

class Hospital extends Component {
  constructor(props) {
    super();
    this.state = { hospital: {} };
  }

  componentDidMount() {
    this.getHospital();
  }

  getHospital = () => {
    let hospitalUrl =
      "http://localhost:3000/hospitals/" + this.props.match.url.split("/")[2];
    fetch(hospitalUrl)
      .then(result => result.json())
      .then(hospital => {
        this.setState({ hospital });
      });
  };

  addBed = (newHospital, callback) => {
    debugger;
    var request = require("request");
    let urlLink =
      "http://localhost:3000/hospitals/" + this.props.match.url.split("/")[2];
    newHospital.nr_beds = newHospital.nr_beds + 1;
    var options = {
      method: "PUT",
      url: urlLink,
      headers: {
        "Postman-Token": "bba376ad-508e-4344-a8f5-75aede3f4fc9",
        "Cache-Control": "no-cache",
        "Content-Type": "application/json"
      },
      body: {
        name: newHospital.name,
        address: newHospital.address,
        gps_coordonates: newHospital.gps_coordonates,
        nr_beds: newHospital.nr_beds,
        nr_of_pending_beds: newHospital.nr_of_pending_beds
      },
      json: true
    };

    request(options, function(error, response, body) {
      if (error) throw new Error(error);
      return callback();
    });
  };
  removeBed = (newHospital, callback) => {
    debugger;
    var request = require("request");
    let urlLink =
      "http://localhost:3000/hospitals/" + this.props.match.url.split("/")[2];
    newHospital.nr_beds = newHospital.nr_beds - 1;
    var options = {
      method: "PUT",
      url: urlLink,
      headers: {
        "Postman-Token": "bba376ad-508e-4344-a8f5-75aede3f4fc9",
        "Cache-Control": "no-cache",
        "Content-Type": "application/json"
      },
      body: {
        name: newHospital.name,
        address: newHospital.address,
        gps_coordonates: newHospital.gps_coordonates,
        nr_beds: newHospital.nr_beds,
        nr_of_pending_beds: newHospital.nr_of_pending_beds
      },
      json: true
    };

    request(options, function(error, response, body) {
      if (error) throw new Error(error);
      return callback();
    });
  };

  render() {
    return (
      <div>
        This will be just a hospital with name {this.state.hospital.name}
        <p>You have only: {this.state.hospital.nr_beds} left</p>
        <button
          onClick={() => {
            this.addBed(this.state.hospital, this.getHospital);
          }}
        >
          Add beds
        </button>
        <button
          onClick={() => {
            this.removeBed(this.state.hospital, this.getHospital);
          }}
        >
          Remove beds
        </button>
      </div>
    );
  }
}

export default App;
