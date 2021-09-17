import React from "react";
import { useState } from "react";
import "../App.css";
import Booking from "./Booking";
import Information from "./Information";

function Homepage() {
  const [updateComponent, setUpdateComponent] = useState(false);

  let newBookingAdded = () => {
    console.log("new book called!!");
    setUpdateComponent(!updateComponent);
    console.log(updateComponent);
  };

  return (
    <div className="row pt-3 justify-content-center" style={{ height: "100%" }}>
      <div className="col-3 pr-3 pt-2">
        <Information update={updateComponent} />
      </div>
      <div style={{ width: "10px" }} />

      <div className="col-8 mr-3">
        <Booking style={{ height: "100%" }} newBookingAdded={newBookingAdded} />
      </div>
    </div>
  );
}

export default Homepage;
