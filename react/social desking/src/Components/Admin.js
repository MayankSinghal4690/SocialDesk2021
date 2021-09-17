import React from "react";
import "../App.css";
import Booking from "./Booking";

import ArrowForwardIcon from "@material-ui/icons/ArrowForward";
import { useState } from "react";
import AdminBooking from "./AdminBooking";

function Admin() {
  return (
    <div className="row pt-5 justify-content-center">
      <div className="col-3 pr-3 ">
        <h2 style={{ color: "#001bab", fontWeight: "bold" }} className="title">
          dbSpace
        </h2>
        <h3 style={{ marginTop: "1em" }}>Welcome Admin!!</h3>
      </div>
      <div style={{ width: "10px" }} />

      <div className="col-8 mr-3">
        <AdminBooking />
      </div>
    </div>
  );
}

export default Admin;
/*

*/
