import React from "react";
import axios from "axios";
import { useState } from "react";

export default function Floorblock(props) {
  const headers = {
    "Content-Type": "application/json",
  };
  console.log("Inside Bloock Floor!!");
  console.log(props.floorstatus);
  const [operationalFloor, setOperationalFloor] = useState(props.floorstatus.status);

  const handleClick = () => {
    var temp = operationalFloor;
    if (temp === "available") {
      temp = "blocked";
    } else {
      temp = "available";
    }

    axios
      .put(
        "https://spring-dot-grads-coding-group-15.uc.r.appspot.com/admin",
        {
          officeName: props.floorstatus.officeName,
          floorNo: props.floorstatus.floorNo,
          status: temp,
        },
        { headers: headers }
      )
      .then((res) => {
        console.log(res);
        setOperationalFloor(temp);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <div style={{ height: "100vh" }}>
      {operationalFloor === "available" ? (
        <div className="FloorStatus" style={{ height: "50%", paddingTop: "12%" }}>
          <h4>
            This floor is currently <span className="bold">{operationalFloor}</span>
          </h4>
          <button className="submitFloorButton" style={{ backgroundColor: "#A80000" }} onClick={handleClick}>
            {" "}
            Block it{" "}
          </button>
        </div>
      ) : (
        <div className="FloorStatus" style={{ height: "50%", paddingTop: "12%" }}>
          <h4 className="">
            This floor is currently <span className="bold">{operationalFloor}</span>
          </h4>
          <button className="submitFloorButton" style={{ backgroundColor: "green" }} onClick={handleClick}>
            {" "}
            UnBlock it{" "}
          </button>
        </div>
      )}
    </div>
  );
}
