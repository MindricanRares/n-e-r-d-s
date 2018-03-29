
import React, { Component}  from 'react';
const { compose, withProps, lifecycle } = require("recompose");
const {
  withScriptjs,
  withGoogleMap,
  GoogleMap,
  DirectionsRenderer,
} = require("react-google-maps");

const MapWithADirectionsRenderer = compose(
  withProps({
    googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyC4R6AN7SmujjPUIGKdyao2Kqitzr1kiRg&v=3.exp&libraries=geometry,drawing,places",
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div style={{ height: `400px` }} />,
    mapElement: <div style={{ height: `100%` }} />,
  }),
  withGoogleMap,
  lifecycle({
    componentDidMount() {
        
      const DirectionsService = new window.google.maps.DirectionsService();
      debugger;
// eslint-disable-next-line
console.log(this.props.personPosition);
      DirectionsService.route({
          // eslint-disable-next-line
        origin: new window.google.maps.LatLng(this.props.person_position.split(',')[0],this.props.person_position.split(',')[1]),
        destination: new window.google.maps.LatLng(this.props.hospital_position.split(',')[0],this.props.hospital_position.split(',')[1]),
        travelMode: window.google.maps.TravelMode.WALKING,
      }, (result, status) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
          this.setState({
            directions: result,
          });
        } else {
          console.error(`error fetching directions ${result}`);
        }
      });
    }
  })
)(props =>
  <GoogleMap
    defaultZoom={7}
    defaultCenter={new window.google.maps.LatLng(props.person_position.split(',')[0],props.person_position.split(',')[1])}
  >
    {props.directions && <DirectionsRenderer directions={props.directions} personPosition={props.person_position} hospitalPosition={props.hospital_position}/>}
  </GoogleMap>
);


export default  MapWithADirectionsRenderer;
