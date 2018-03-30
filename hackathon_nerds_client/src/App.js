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
    fetch(`http://localhost:9000/hackathonNerdsServices/hospitals/hospitalList`)
      .then(result => result.json())
      .then(items => {
        this.setState({ items });
        // console.log(items);
      });
  };

  chooseHospitalBtn = hospital => {
    // console.log(hospital.id);
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
    this.state = {
      hospitals:"",
      gps_coordonates: "45.653,25.6130",
      numberOfPersons: 1,
      userSubmited: false,
      best_hospital_location: "45.6485988,25.6200351"
    };
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
    fetch(
      `http://localhost:9000/hackathonNerdsServices/person/hospital/resources`
    )
      .then(result => result.json())
      .then(hospitals => {
        this.setState({ hospitals:hospitals });
        // console.log(hospitals);
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
    // console.log(this.state.numberOfPersons);
    this.setState({
      userSubmited: true
    });
    this.sendPersonToBestHospital();
  };

  compareHospitalsResults=(a,b)=>{
    debugger;
    if(a.distance>b.distance){
      return 1;
    }else if(a.distance<b.distance){
      return 0;
    }
    return -1;
  }

  chooseBestHospital = () =>{
    let coord=this.state.gps_coordonates;
    debugger;
    let result =[];
    this.state.hospitals.forEach(hospital => {
      let a=(coord.split(',')[0]-coord.split(',')[1])*(coord.split(',')[0]-coord.split(',')[1]);
      let b=((hospital.hospital.coord.split(',')[0]-hospital.hospital.coord.split(',')[1])*(hospital.hospital.coord.split(',')[0]-hospital.hospital.coord.split(',')[1]));
      let distanceToPerson=Math.sqrt(Math.abs(a-b));
      let x=(((hospital.hospital.occupiedBeds+hospital.hospital.reservedBeds)*100)/hospital.hospital.totalBeds)*distanceToPerson*15
      result.push({distance:x,hospitalId:hospital.hospital.id});
    });
    let sortedResult=result.sort(this.compareHospitalsResults)
    console.log(sortedResult[0].hospitalId);
  }

  sendPersonToBestHospital = () => {

    let bestHospital=this.chooseBestHospital();
    // console.log(bestHospital);


    let hospitalUrl =
    `http://localhost:9000/hackathonNerdsServices/hospitals/hospital/2/reserve/${this.state.numberOfPersons}/?name=anonym`;

    fetch(hospitalUrl);
  };

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
    this.state = { hospital: {}, need: [] };
  }

  componentDidMount() {
    this.getHospital();
  }

  getHospital = () => {
    let hospitalUrl =
      "http://localhost:9000/hackathonNerdsServices/hospitals/hospital/" +
      this.props.match.url.split("/")[2] +
      "/resources";

    fetch(hospitalUrl)
      .then(result => result.json())
      .then(hospital => {
        this.setState({ hospital: hospital });
      });
  };

  addBed = (newHospital, callback) => {
    let hospitalUrl =
    "http://localhost:9000/hackathonNerdsServices/hospitals/hospital/updateHsp/"+ this.props.match.url.split("/")[2]+"?totalBeds="+parseInt(parseInt(newHospital.hospital.totalBeds)+1) ;

    fetch(hospitalUrl).then(
      ()=>{
        this.getHospital();
      }
    );

  };

  removeBed = (newHospital, callback) => {
    let hospitalUrl =
    "http://localhost:9000/hackathonNerdsServices/hospitals/hospital/updateHsp/"+ this.props.match.url.split("/")[2]+"?totalBeds="+parseInt(parseInt(newHospital.hospital.totalBeds)-1) ;

    fetch(hospitalUrl).then(
      ()=>{
        this.getHospital();
      }
    );

  };

  // handleChange = e => {
  //   this.setState({ numberOfPersons: e.target.value });
  // };

  // handleClick = () => {
  //   console.log(this.state.numberOfPersons);
  //   this.setState({
  //     : true
  //   });
  //   // this.sendPersonToBestHospital();
  // };

  displayHospitalsNeeds = () => {
    let needs = "";
    if (
      Object.keys(this.state.hospital).length === 0 &&
      this.state.hospital.constructor === Object
    ) {
      needs = <li>Still loading</li>;
    } else {
      needs = this.state.hospital.needs.map(need => {
        return (
          <li key={need.id}>
            {need.resource.name}:{need.quatity} {need.resource.measurmentUnits}
            <br/>
            <input type="text" onChange={this.handleChange} />
            <input
              type="button"
              value="Change"
              onClick={this.handleClick}
            />
          </li>
        );
      });
    }
    return needs;
  };
  displayHospitalsResourcdes = () => {
      return this.state.hospital.haves.map( have=> {
        return (
          <li key={have.id}>
            {have.resource.name}:{have.quatity} {have.resource.measurmentUnits}
          </li>
        );
      });
  };

  render() {
    if (
      Object.keys(this.state.hospital).length === 0 &&
      this.state.hospital.constructor === Object
    ) {
      return (
        <div>
          <p>Loading</p>
        </div>
      );
    } else {
      return (
        <div>
          {//Check if message failed
          this.state.hospital === null ? (
            <div> Loading </div>
          ) : (
            <div>
              {" "}
              This will be just a hospital with name{" "}
              {this.state.hospital.hospital.name}{" "}
            </div>
          )}
          <p>You have only: {this.state.hospital.hospital.totalBeds} left</p>
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
          <h1>Needs</h1>
          <ul>{this.displayHospitalsNeeds()}</ul>
          <h1>Resources</h1>
          <ul>{this.displayHospitalsResourcdes()}</ul>
        </div>
      );
    }
  }
}

export default App;
