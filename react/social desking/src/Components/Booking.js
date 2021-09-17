import React from "react";
import "../App.css";
import Map from "./Map";
import ArrowForwardIcon from "@material-ui/icons/ArrowForward";
import { useState } from "react";
import { axiosRequest } from "../api/services";
import axios from "axios";
import { useEffect } from "react";
import { blockFloor, bookSeat } from "../api/services";
import dateFormat from "dateformat";

function Booking({ newBookingAdded }) {
  const headers = {
    "Content-Type": "application/json",
  };
  const userEid = localStorage.getItem("userEmail");
  const userType = localStorage.getItem("userType");
  //const [officeOptions, setOfficeOptions] = useState();
  const [officeRegions, setOfficeRegions] = useState([]);
  const [days, setDays] = useState(0);
  const todaysDate = dateFormat(new Date(), "yyyy-mm-dd").toString();

  const [searchDetails, setSearchDetails] = useState({
    startDate: "",
    endDate: "",
    officeRegion: "",
    floor: 0,
    user: userType,
    desk: 0,
  });
  const [tables, updateTables] = useState([]);

  const handleDesk = (desk) => {
    console.log(desk);
    setSearchDetails({ ...searchDetails, desk: desk.id });
  };

  const requestOfficeList = axiosRequest.get(`/office`);

  useEffect(() => {
    axios
      .all([requestOfficeList])
      .then(
        axios.spread((...responses) => {
          const responseOfficeList = responses[0];

          console.log(responseOfficeList.data);
          searchDetails.officeRegion = responseOfficeList.data[0].officeName;
          setOfficeRegions(responseOfficeList.data);
          //setOfficeRegions(responseOfficeList.data.map((opt) => ({ label: `${opt.name}, ${opt.locid.name}`, value: `${opt.name}` })));
          //console.log(officeOptions);
        })
      )
      .catch((errors) => {
        // react on errors.
      });
  }, []);

  const objectHandle = (chair) => {
    return {
      id: parseInt(chair.id),
      chairDirection: chair.rotation,
      x: chair.coordinate_X,
      y: chair.coordinate_Y,
      equipments: [
        { type: "desk", specification: "Simple desk" },
        { type: "laptop", specification: "Laptop Dell Inspiron 15 5000" },
        { type: chair.status, specification: "Ergonomic Office Chair" },
        {
          type: "Booking Status",
          specification: chair.status === "chair" ? "Vacant" : chair.teammembername !== "null" ? `Booked by ${chair.teammembername}` : "Unavailable",
        },
      ],
    };
  };

  const getFloorPlan = (floor, office, startDate, endDate) => {
    // const data = [
    //   {
    //     id: 1,
    //     coordinate_X: 1,
    //     coordinate_Y: 1,
    //     rotation: "0",
    //     status: "chair-na",
    //     teammembername: "Sumit",
    //   },
    //   {
    //     id: 2,
    //     coordinate_X: 3,
    //     coordinate_Y: 3,
    //     rotation: "0",
    //     status: "chair",
    //     teammembername: "null",
    //   },
    //   {
    //     id: 3,
    //     coordinate_X: 1,
    //     coordinate_Y: 3,
    //     rotation: "0",
    //     status: "chair",
    //     teammembername: "null",
    //   },
    //   {
    //     id: 4,
    //     coordinate_X: 3,
    //     coordinate_Y: 1,
    //     rotation: "0",
    //     status: "chair",
    //     teammembername: "null",
    //   },
    // ];

    const arr = [
      {
        id: "chair12",
        chairDirection: "east",
        x: 1,
        y: 1,
        equipments: [
          { type: "desk", specification: "Simple desk" },
          { type: "laptop", specification: "Laptop Dell Inspiron 15 5000" },
          {
            type: "chair-na",
            specification: "817L Kare Ergonomic Office Chair",
          },
          { type: "Booking Status", specification: "Booked" },
        ],
      },
      {
        id: "chair34",
        chairDirection: "east",
        x: 1,
        y: 3,
        equipments: [
          { type: "desk", specification: "Simple desk" },
          { type: "chair", specification: "817L Kare Ergonomic Office Chair" },
          { type: "Booking Status", specification: "Vacant" },
        ],
      },
    ];

    // updateTables(data.map((e) => objectHandle(e)));
    console.log(floor, office, startDate, endDate);
    const email = localStorage.getItem("userEmail");
    const headers = {
      "Content-Type": "application/json",
    };
    axios
      .post(
        `https://spring-dot-grads-coding-group-15.uc.r.appspot.com/desks/${email}`,
        {
          startDate: startDate,
          endDate: endDate,
          officeName: office,
          floorNo: floor,
        },
        { headers: headers }
      )
      .then((res) => {
        const arr = res.data.map((e) => objectHandle(e));
        updateTables(arr);
      })
      .catch((errors) => {
        // react on errors.
        console.log(errors);
        return [];
      });
  };

  /*const bookSeat = () => {
    axios
      .put(
        `https://spring-dot-grads-coding-group-15.uc.r.appspot.com/desks/${userEid}/book
        `,
        {
          startDate: searchDetails.startDate,
          endDate: searchDetails.endDate,
          officeName: searchDetails.officeRegion,
          floorNo: searchDetails.floor,
          chairId: searchDetails.desk,
        },
        { headers: headers }
      )
      .then((res) => {
        // todo
        console.log(res);
      })
      .catch((errors) => {
        console.log(errors);
      });
  };
*/
  const handleFloor = (e) => {
    console.log("called");
    if (searchDetails.user === "admin") {
      alert("Floor " + searchDetails.floor + " is blocked!!");
      blockFloor(searchDetails.floor, searchDetails.officeRegion, searchDetails.date);
    } else {
      var time_difference = new Date(searchDetails.endDate).getTime() - new Date(searchDetails.startDate).getTime();
      var days_difference = time_difference / (1000 * 60 * 60 * 24);
      setDays(days_difference + 1);
      console.log(days_difference, days, time_difference);
      updateTables(getFloorPlan(searchDetails.floor, searchDetails.officeRegion, searchDetails.startDate, searchDetails.endDate));

      // getFloorPlan(
      //   searchDetails.floor,
      //   searchDetails.officeRegion,
      //   searchDetails.date
      // );
    }
  };

  const handleInput = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    console.log(name, value);
    setSearchDetails({ ...searchDetails, [name]: value });
  };

  const handleBooking = async (e) => {
    e.preventDefault();
    axios
      .put(
        `https://spring-dot-grads-coding-group-15.uc.r.appspot.com/desks/${userEid}/book
        `,
        {
          startDate: searchDetails.startDate,
          endDate: searchDetails.endDate,
          officeName: searchDetails.officeRegion,
          floorNo: searchDetails.floor,
          chairId: searchDetails.desk,
        },
        { headers: headers }
      )
      .then((res) => {
        // todo
        console.log(res);
        let arr = [];
        tables.forEach((t) => {
          if (t.id === searchDetails.desk) {
            t.equipments.forEach((e) => (e.type === "chair" ? (e.type = "chair-na") : null));
          }
          arr.push(t);
        });
        newBookingAdded();
        updateTables(arr);
      })
      .catch((errors) => {
        console.log(errors);
      });
  };

  return (
    <div className="" style={{ margin: "0", padding: "0", height: "100%" }}>
      <div style={{ height: "100px" }} className="">
        <form className="row mt-4">
          <div className="col">
            <label htmlFor="date" className="d-block  text-secondary mb-2 bold">
              START DATE
            </label>
            <input min={todaysDate} className="bold drop" style={{ width: "100%" }} value={searchDetails.startDate} onChange={handleInput} type="date" name="startDate" id="startDate" />
          </div>

          <div className="col">
            <label htmlFor="date" className="d-block  text-secondary mb-2 bold">
              END DATE
            </label>
            <input min={todaysDate} className="bold drop" style={{ width: "100%" }} value={searchDetails.endDate} onChange={handleInput} type="date" name="endDate" id="endDate" />
          </div>

          <div className="col">
            <label htmlFor="officeRegion" className="d-block text-secondary mb-2 bold ">
              OFFICE
            </label>
            <div className="" style={{ width: "100%" }}>
              <select className="drop" name="officeRegion" value={searchDetails.officeRegion} onChange={handleInput}>
                {officeRegions.map((e) => (
                  <option key={e.officeName}>{e.officeName}</option>
                ))}
              </select>
            </div>
          </div>

          <div className="col">
            <label htmlFor="floor" className="d-block text-secondary mb-2 bold">
              FLOOR
            </label>
            <div className="" style={{ width: "100%" }}>
              <select className="drop" name="floor" value={searchDetails.floor} onChange={handleInput}>
                <option key="-1"></option>
                {officeRegions.length !== 0 && officeRegions.find((o) => o.officeName === searchDetails.officeRegion).floorStatus.map((e, index) => e === "available" && <option key={index}>{index + 1}</option>)}
              </select>
            </div>
          </div>

          <div className="col" style={{ width: "100%" }}>
            <div className="submitButton" style={{ width: "80%", marginTop: "20%", paddingTop: "7%" }} type="submit" onClick={handleFloor}>
              <span style={{ paddingRight: "10px" }}>Search</span>
              <ArrowForwardIcon style={{ fill: "white" }} />
            </div>
          </div>
        </form>
      </div>

      {userType !== "Admin" && (
        <div>
          <Map tables={tables} handleDesk={handleDesk} />

          <div className="mt-2 row">
            <div className="text-secondary bold col-3 text-center mt-2 pt-1">SELECTED DESK</div>
            <div className="col">
              <div style={{ paddingLeft: "-15px" }} className="box-color bold text-center">
                {searchDetails.desk}
              </div>
            </div>

            <div className="text-secondary bold col mt-2 pt-1"> NO. OF DAYS</div>
            <input className="bold col box-color" type="text" value={days} name="days" id="days" />

            <div className="col"></div>
            <div className="col">
              <button
                className="text-white bold"
                style={{
                  width: "100px",
                  height: "40px",
                  borderRadius: "20px",
                  backgroundColor: "#006400",
                }}
                onClick={handleBooking}
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );

  // return (
  //   <div className="" style={{ margin: "0", padding: "0", height: "100%" }}>
  //     <div style={{ height: "100px" }} className="">
  //       <form className="row mt-5">
  //         <div className="col">
  //           <label htmlFor="date" className="d-block text-secondary mb-2 bold">
  //             DATE
  //           </label>
  //           <input
  //             className="bold"
  //             style={{ width: "100%" }}
  //             value={searchDetails.date}
  //             onChange={handleInput}
  //             type="date"
  //             name="date"
  //             id="date"
  //           />
  //         </div>

  //         <div className="col">
  //           <label
  //             htmlFor="officeRegion"
  //             className="d-block text-secondary mb-2 bold "
  //           >
  //             OFFICE
  //           </label>
  //           <select
  //             id="officeRegion"
  //             value={searchDetails.officeRegion}
  //             onChange={handleInput}
  //           >
  //             {officeRegions.map((e) => {
  //               return <options>{e}</options>;
  //             })}
  //           </select>
  //         </div>

  //         <div className="col">
  //           <label htmlFor="floor" className="d-block text-secondary mb-2 bold">
  //             FLOOR
  //           </label>
  //           <input
  //             className="bold"
  //             type="text"
  //             value={searchDetails.floor}
  //             name="floor"
  //             onChange={handleInput}
  //             id="floor"
  //           />
  //         </div>

  //         <div className="col">
  //           <div className="submitButton" type="submit" onClick={handleFloor}>
  //             <span style={{ paddingRight: "10px" }}>Search</span>
  //             <ArrowForwardIcon style={{ fill: "white" }} />
  //           </div>
  //         </div>
  //       </form>
  //     </div>

  //     {userType !== "Admin" && (
  //       <div>
  //         <Map tables={tables} handleDesk={handleDesk} />

  //         <div className="mt-2 row">
  //           <div className="text-secondary bold col-3 text-center mt-2 pt-1">
  //             SELECTED DESK
  //           </div>
  //           <div className="col">
  //             <div
  //               style={{ paddingLeft: "-15px" }}
  //               className="box-color bold text-center"
  //             >
  //               {searchDetails.desk}
  //             </div>
  //           </div>

  //           <div className="text-secondary bold col mt-2 pt-1">
  //             {" "}
  //             NO. OF DAYS
  //           </div>
  //           <input
  //             className="bold col box-color"
  //             type="number"
  //             value={searchDetails.days}
  //             name="days"
  //             onChange={handleInput}
  //             id="days"
  //           />

  //           <div className="col"></div>
  //           <div className="col">
  //             <button
  //               className="text-white bold"
  //               style={{
  //                 width: "100px",
  //                 height: "40px",
  //                 borderRadius: "20px",
  //                 backgroundColor: "#006400",
  //               }}
  //               onClick={handleBooking}
  //             >
  //               Submit
  //             </button>
  //           </div>
  //         </div>
  //       </div>
  //     )}
  //   </div>
  // );
}

export default Booking;
