import React from "react";
import "../App.css";
import Map from "./Map";
import ArrowForwardIcon from "@material-ui/icons/ArrowForward";
import { useState } from "react";
import { axiosRequest } from "../api/services";
import axios from "axios";
import { useEffect } from "react";
import { blockFloor, bookSeat } from "../api/services";
import Floorblock from "./BlockFloor";

function AdminBooking() {
  const headers = {
    "Content-Type": "application/json",
  };
  const userEid = localStorage.getItem("userEmail");
  const userType = "Admin";
  //const [officeOptions, setOfficeOptions] = useState();
  // userType = "Admin" // Delete this afterwards...
  var operationalFloor = "blocked";
  //const [officeOptions, setOfficeOptions] = useState();
  const [officeFloor, setOfficeFloor] = useState([]);
  var [visibility, setVisibility] = useState(0);
  const [adminfloorstatus, setFloorStatus] = useState("");
  var OfficeList = [];
  var floorstatus;

  const [adminInfo, setAdminInfo] = useState({
    officeName: "",
    floorNo: 0,
    status: "",
  });
  const [officeRegions, setOfficeRegions] = useState([
    // "yer",
    // "pune",
    // "banglore",
  ]);
  const [days, setDays] = useState(0);

  const [searchDetails, setSearchDetails] = useState({
    officeRegion: "",
    floor: 0,
    user: userType,
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
          setOfficeFloor(responseOfficeList.data);
          setOfficeRegions(responseOfficeList.data);
          //setOfficeRegions(responseOfficeList.data.map((opt) => ({ label: `${opt.name}, ${opt.locid.name}`, value: `${opt.name}` })));
          //console.log(officeOptions);
        })
      )
      .catch((errors) => {
        // react on errors.
      });
  }, []);

  const handleAdminFloor = () => {
    visibility = 1;
    // console.log(visibility);
    setVisibility(1);
    // <blockfloor />
    // axios.put('https://spring-dot-grads-coding-group-15.uc.r.appspot.com/admin' ,{
    //   "officeName" : "Yerwada",
    //   "floorNo" : "2",
    //   "status" : "available"
    // },  { headers: headers }).then(res => {console.log(res)}).catch((err) => {console.log(err)});
    var status = 0;
    console.log(officeFloor);
    var responseOffices = [];
    officeFloor.map((obj) => {
      if (obj.officeName == searchDetails.officeRegion && searchDetails.floor <= obj.numFloor) {
        floorstatus = obj.floorStatus[searchDetails.floor - 1];
        status = 1;
      }

      // console.log(obj.officeName);
    });
    //   axios.get("https://spring-dot-grads-coding-group-15.uc.r.appspot.com/office").then(res => {
    //   const floorNumber = searchDetails.floor;
    //   console.log(res);
    //   res.data.map((obj) => {
    //     if(obj.officeName == searchDetails.officeRegion && obj.floorId == searchDetails.floor){
    //     setFloorStatus(obj.floorStatus);
    //     console.log(floorstatus);
    //       status = 1;
    //     }else{
    //       // alert("Office or Floor Not Found, Choose another Office!!");

    //     }
    //   })
    if (status == 0) {
      alert("Office or Floor Not Found, Choose another Office or Floor");
    }
    // })
    console.log(floorstatus);
    // axios.get('https://spring-dot-grads-coding-group-15.uc.r.appspot.com/office').then(res => {console.log(res)})
    // const x = adminInfo;
    // setFloorStatus(floorstatus);
    setAdminInfo({ ...adminInfo, ["officeName"]: searchDetails.officeRegion, ["floorNo"]: searchDetails.floor, ["status"]: floorstatus });
    // setAdminInfo({...adminInfo,["floorNo"]: searchDetails.floor});
    // setAdminInfo({...adminInfo,["status"]: adminfloorstatus});
    // adminInfo.officeName = searchDetails.officeRegion;
    // adminInfo.floorNo = searchDetails.floor;
    // adminInfo.status = adminfloorstatus;
  };
  const handleInput = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    // console.log(name, value);
    setSearchDetails({ ...searchDetails, [name]: value });
  };

  return (
    <div className="" style={{ margin: "0", padding: "0", height: "100%" }}>
      <div style={{ height: "100px" }} className="">
        <form className="row mt-5">
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
                {officeRegions.length !== 0 && officeRegions.find((o) => o.officeName === searchDetails.officeRegion).floorStatus.map((e, index) => <option key={index}>{index + 1}</option>)}
              </select>
            </div>
          </div>

          <div className="col" style={{ width: "100%" }}>
            <div className="submitButton" style={{ width: "80%", marginTop: "12%", paddingTop: "3%" }} type="submit" onClick={handleAdminFloor}>
              <span style={{ textAlign: "center", paddingRight: "10px" }}>Check</span>
              <ArrowForwardIcon style={{ fill: "white" }} />
            </div>
          </div>
        </form>
      </div>
      <div>{visibility === 1 ? <Floorblock floorstatus={adminInfo} /> : <div></div>}</div>
    </div>
  );
}

export default AdminBooking;
