import { React } from "react";
import { useState } from "react";
import "../App.css";
import dateFormat from "dateformat";
import BookingItemCard from "./BookingItemCard";

import BookingTeamItemCard from "./BookingTeamItemCard";
import { axiosRequest } from "../api/services";
import { useEffect } from "react";
import axios from "axios";

const Information = ({ update }) => {
  const userEid = localStorage.getItem("userEmail");
  const [isEmptyList, setIsEmptyList] = useState(true);
  const [isEmptyTeamList, setIsEmptyTeamList] = useState(false);
  const [bookings, setBookings] = useState([]);
  const [teamBookings, setTeamBookings] = useState([]);

  // const headers = {
  //   "Content-Type": "application/json",
  // };

  const requestUpcomingBookings = axiosRequest.get(`/upcomingbooking/${userEid}`);
  const requestTeamBookings = axiosRequest.get(`/team/${userEid}`);

  const [showComponent, setShowComponent] = useState(false);
  const [index, setIndex] = useState(0);
  const [showTeamComponent, setShowTeamComponent] = useState(false);
  const [teamIndex, setTeamIndex] = useState(0);
  const [bookingDeleted, setBookingDeleted] = useState(false);

  let BookingItemClick = (idx) => {
    setIndex(idx);
    setShowComponent(true);
  };
  let BookingTeamItemClick = (idx) => {
    setTeamIndex(idx);
    setShowTeamComponent(true);
  };

  let bookingGotDeleted = () => {
    setBookingDeleted(!bookingDeleted);
  };

  useEffect(() => {
    console.log("information is called");

    axios
      .all([requestUpcomingBookings, requestTeamBookings])
      .then(
        axios.spread((...responses) => {
          console.log("in the axios call");
          const responseUpcomingBookings = responses[0];
          const responseTeamBookings = responses[1];

          console.log("responseUpcomingBookings", responseUpcomingBookings.data);
          console.log("responseTeamBookings", responseTeamBookings);
          if (responseUpcomingBookings.data.booking === null || responseUpcomingBookings.data.booking.length === 0) {
            setIsEmptyList(true);
          } else {
            setBookings(responseUpcomingBookings.data.booking);
            setIsEmptyList(false);
          }

          console.log("hiiiiii");

          if (responseTeamBookings.data.length !== 0) {
            setTeamBookings(responseTeamBookings.data);
            console.log("teams here");
            console.log(teamBookings);
          } else {
            console.log("empty");
            setIsEmptyTeamList(true);
          }
        })
      )
      .catch((errors) => {
        // react on errors.
      });
  }, [update, bookingDeleted]);

  return (
    <div style={{ height: "100%" }}>
      <BookingItemCard showComponent={showComponent} setShowComponent={setShowComponent} booking={bookings[index]} bookingGotDeleted={{ bookingGotDeleted }} />
      <BookingTeamItemCard showTeamComponent={showTeamComponent} setShowTeamComponent={setShowTeamComponent} booking={teamBookings[teamIndex]} />

      <h1 className="title bold" style={{ color: "#001BAB" }}>
        dbSpace
      </h1>

      <h6 style={{ height: "20px" }}>
        <span className="bold">{userEid}</span>
      </h6>

      <div className="text-secondary mt-5 mb-3" style={{ fontWeight: "bold" }}>
        YOUR UPCOMING BOOKINGS
      </div>

      <div className="bookingList" style={{ height: "40%" }}>
        {isEmptyList === true ? (
          <p className="bookingItem ">
            <span style={{ paddingLeft: "15px" }}>No Upcoming Bookings </span>
          </p>
        ) : (
          bookings.map((item, idx) => (
            <button onClick={() => BookingItemClick(idx)} key={idx} className="bookingItem">
              <span style={{ paddingLeft: "15px", fontWeight: "bold" }}>{item.officeName},</span> {dateFormat(item.startdate, " dS mmmm  yyyy")}
            </button>
          ))
        )}
      </div>

      <div style={{ marginTop: "50px", color: "#001BAB", fontWeight: "bold" }}>dbSpace SMART TEAMS</div>

      <div className="mt-2">
        {isEmptyTeamList ? (
          <p className="bookingItem ">
            <span style={{ paddingLeft: "15px" }}>No Upcoming Bookings </span>
          </p>
        ) : (
          teamBookings.map((item, idx) => (
            <button onClick={() => BookingTeamItemClick(idx)} key={idx} className="bookingItem">
              <span style={{ paddingLeft: "15px", fontWeight: "bold" }}>{item.name},</span> {dateFormat(item.startDate, " dS mmmm  yyyy")}
            </button>
          ))
        )}
      </div>
    </div>
  );
};

export default Information;
