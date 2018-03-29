import React, { Component}  from 'react';
const { compose } = require("recompose");
const {
  withScriptjs,
  withGoogleMap,
  GoogleMap,
} = require("react-google-maps");
const { MarkerWithLabel } = require("react-google-maps/lib/components/addons/MarkerWithLabel");

const MapWithAMarkerWithLabel = compose(
  withGoogleMap,
)(props =>{
    return(
        <GoogleMap
    defaultZoom={15 }
    center={{ lat: parseFloat(props.position.split(',')[0]), lng:parseFloat(props.position.split(',')[1]) }}
  >

    <MarkerWithLabel
      position={{ lat: 45.6485988, lng: 25.6200351 }}
      labelAnchor={new window.google.maps.Point(0, 0)}
      labelStyle={{backgroundColor: "yellow", fontSize: "8px", padding: "4px"}}
    >
      <div>Spitalul Clinic Județean de Urgenta</div>

    </MarkerWithLabel>

        <MarkerWithLabel
      position={{ lat: 45.6489884, lng: 25.6184661 }}
      labelAnchor={new window.google.maps.Point(0, 0)}
      labelStyle={{backgroundColor: "yellow", fontSize: "8px", padding: "4px"}}
    >
      <div>Spitalul Astra</div>
    </MarkerWithLabel>

    <MarkerWithLabel
      position={{ lat: 45.6533996, lng: 25.5957472 }}
      labelAnchor={new window.google.maps.Point(0, 0)}
      labelStyle={{backgroundColor: "yellow",fontSize: "8px", padding: "4px"}}
    >
      <div>Spitalul sfantul constantin</div>
    </MarkerWithLabel>
  </GoogleMap>
    )
}
  
); 
export default MapWithAMarkerWithLabel;