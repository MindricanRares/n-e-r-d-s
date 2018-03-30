import React, { Component } from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import MapWithADirectionsRenderer from "./map-component";
import MapWithAMarkerWithLabel from "./map-component-with-labels";


class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a className="navbar-brand" href="/">
              N.E.R.D.S
            </a>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarsExampleDefault"
              aria-controls="navbarsExampleDefault"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon" />
            </button>

            <div
              className="collapse navbar-collapse"
              id="navbarsExampleDefault"
            >
              <ul className="navbar-nav mr-auto">
                <li className="nav-item">
                  <Link to="/hospitals" className="nav-link">
                    Hospitals
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to="/person" className="nav-link">
                    Person
                  </Link>
                </li>
              </ul>
            </div>
          </nav>
          <Route exact path="/" component={Home} />
          <Route path="/hospitals" component={Hospitals} />
          <Route path="/person" component={Person} />
        </div>
      </Router>
    );
  }
}

class Home extends Component {
  render() {
    return (
      <div>
        <main role="main">
          <div className="jumbotron">
            <div className="container">
              <h1>National Emergency Room Distributed Solution</h1>
              <p>
                A simple solution that can help thousand of people across the
                globe. Inspired by UBER and the way it got rid of the middle
                man. N.E.R.D.S will manage the hospitals and people around them
                in the best way possible while maintaining the smallest numbers
                of casualties
              </p>
              <p>
                <a className="btn btn-primary btn-lg"  role="button">
                  Register your hospital
                </a>
              </p>
            </div>
          </div>

          <div className="container">
            <div className="row">
              <div className="col-md-4">
                <h2>Completely free</h2>
                <p>
                  The whole project will be open source and everybody can contribuite.
                </p>
                <br />
                <br />
                <p>
                  <a className="btn btn-secondary" role="button">
                    Find us on GitHub
                  </a>
                </p>
              </div>
              <div className="col-md-4">
                <h2>Multicultural</h2>
                <p>
                  We need help to translate the website to as many languages as
                  possible{" "}
                </p>
                <br />
                <br />
                <p>
                  <a className="btn btn-secondary" role="button">
                    Contact Us
                  </a>
                </p>
              </div>
              <div className="col-md-4">
                <h2>Advocates of the future</h2>
                <p>
                  Because our solution is targeted at low income countries we
                  believe that internet access should be present everywhere in the
                  world. Thus we want to encourege everyone to help us achive
                  this.
                </p>
                <p>
                  <a className="btn btn-secondary" role="button">
                    Donate
                  </a>
                </p>
              </div>
            </div>
            <ht />
          </div>
        </main>
      </div>
    );
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
      return (
        <Link key={hospital.id} className="list-group-item" to={hospitalLink}>
          {hospital.name}
        </Link>
      );
    });
  };
  createRoutesForHospitals = () => {
    return this.state.items.map(hospital => {
      let hospitalLink = "/hospitals/" + hospital.id;
      return <Route key={hospital.id} path={hospitalLink} component={Hospital} />;
    });
  };

  render() {
    return (
      <Router>
        <ul className="hospitalPage list-group">
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
      hospitals: "",
      gps_coordonates: "45.653,25.6130",
      numberOfPersons: 1,
      userSubmited: false,
      best_hospital_location: "45.6485988,25.6200351",
      best_hospital_id: 3
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
    }, 1500);
    this.getHostpitals();
  }
  getHostpitals = () => {
    fetch(
      `http://localhost:9000/hackathonNerdsServices/person/hospital/resources`
    )
      .then(result => result.json())
      .then(hospitals => {
        this.setState({ hospitals: hospitals });
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

  compareHospitalsResults = (a, b) => {
    debugger;
    if (a.distance > b.distance) {
      return 1;
    } else if (a.distance < b.distance) {
      return 0;
    }
    return -1;
  };

  chooseBestHospital = () => {
    let coord = this.state.gps_coordonates;
    debugger;
    let result = [];
    this.state.hospitals.forEach(hospital => {
      let a =
        (coord.split(",")[0] - coord.split(",")[1]) *
        (coord.split(",")[0] - coord.split(",")[1]);
      let b =
        (hospital.hospital.coord.split(",")[0] -
          hospital.hospital.coord.split(",")[1]) *
        (hospital.hospital.coord.split(",")[0] -
          hospital.hospital.coord.split(",")[1]);
      let distanceToPerson = Math.sqrt(Math.abs(a - b));
      let x =
        (hospital.hospital.occupiedBeds + hospital.hospital.reservedBeds) *
        100 /
        hospital.hospital.totalBeds *
        distanceToPerson *
        15;
      result.push({
        distance: x,
        hospitalId: hospital.hospital.id,
        hospital_location: hospital.hospital.coord
      });
    });
    let sortedResult = result.sort(this.compareHospitalsResults);
    debugger;
    console.log(sortedResult[0].hospitalId);
    this.setState({
      best_hospital_location: sortedResult[0].hospital_location,
      best_hospital_id: sortedResult[0].hospitalId
    });
    // return sortedResult[0].hosp
  };

  sendPersonToBestHospital = () => {
    debugger;
    this.chooseBestHospital();
    // console.log(bestHospital);

    let hospitalUrl = `http://localhost:9000/hackathonNerdsServices/hospitals/hospital/${
      this.state.best_hospital_id
    }/reserve/${this.state.numberOfPersons}/?name=anonym`;

    fetch(hospitalUrl);
  };

  render() {
    return (
      <div>
        <div className="personMap">{this.drawMap()}</div>

        <div className="form-group">
          <label >Number of persons</label>
          <input
            type="text"
            onChange={this.handleChange}
            className="form-control"
            placeholder="1"
          />
          <small className="form-text text-muted">
            We need to know the number of beds required.
          </small>
          <br />
          <button onClick={this.handleClick} className="btn btn-primary">
            Send me to the best Hospital
          </button>
        </div>
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
      "http://localhost:9000/hackathonNerdsServices/hospitals/hospital/updateHsp/" +
      this.props.match.url.split("/")[2] +
      "?totalBeds=" +
      parseInt(parseInt(newHospital.hospital.totalBeds) + 1);

    fetch(hospitalUrl).then(() => {
      this.getHospital();
    });
  };

  removeBed = (newHospital, callback) => {
    let hospitalUrl =
      "http://localhost:9000/hackathonNerdsServices/hospitals/hospital/updateHsp/" +
      this.props.match.url.split("/")[2] +
      "?totalBeds=" +
      parseInt(parseInt(newHospital.hospital.totalBeds) - 1);

    fetch(hospitalUrl).then(() => {
      this.getHospital();
    });
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
          <div className="form-group">
            <p>
              {need.resource.name}: {need.quatity} 
              {need.resource.measurmentUnits}
            </p>

            <input type="text" className="form-control" />
            <br />
            <button onClick={this.handleClick} className="btn btn-primary">
              Update need
            </button>
          </div>
        );
      });
    }
    return needs;
  };
  displayHospitalsResourcdes = () => {
    return this.state.hospital.haves.map(have => {
      return (
        <div className="form-group">
          {have.resource.name}: {have.quatity}{have.resource.measurmentUnits}
          <input type="text" className="form-control" />

        </div>
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
        <div className="hospitalPage">
          {//Check if message failed
          this.state.hospital === null ? (
            <div> Loading </div>
          ) : (
            <div>
              <div className="jumbotron">
                <div className="container hosital-details">
                  <h3>Hospital name:{this.state.hospital.hospital.name}</h3>
                  <h5>
                    {" "}
                    Hospital address:{this.state.hospital.hospital.address}
                  </h5>
                </div>
              </div>
              <div className="container">
                <div className="row">
                  <div className="col-md-4">
                    <h2>
                      Number of free beds:{" "}
                      {this.state.hospital.hospital.totalBeds -
                        this.state.hospital.hospital.reservedBeds -
                        this.state.hospital.hospital.occupiedBeds}{" "}
                    </h2>
                    <p>
                      <button
                        className="btn btn-secondary"
                        onClick={() => {
                          this.addBed(this.state.hospital, this.getHospital);
                        }}
                      >
                        Add beds
                      </button>
                      <button
                        className="btn btn-secondary"
                        onClick={() => {
                          this.removeBed(this.state.hospital, this.getHospital);
                        }}
                      >
                        Remove beds
                      </button>
                    </p>
                  </div>
                  <div className="col-md-4">
                    <h2>
                      Number of reserved beds:{" "}
                      {this.state.hospital.hospital.reservedBeds}{" "}
                    </h2>
                  </div>
                  <div className="col-md-4">
                    <h2>
                      Total number of beds:{" "}
                      {this.state.hospital.hospital.occupiedBeds}
                    </h2>
                  </div>
                </div>

                <hr />
              </div>

              <div className="container hospitals-needs-resources ">
                <div className="row">
                  <div className="col-md-4 need-elem">
                    <h1>Needs</h1>
                    {this.displayHospitalsNeeds()}
                  </div>
                  <div className="col-md-4 res-elem">
                    <h1>Resources</h1>
                    {this.displayHospitalsResourcdes()}
                  </div>
                </div>

                <hr />
              </div>
            </div>
          )}
        </div>
      );
    }
  }
}

export default App;
