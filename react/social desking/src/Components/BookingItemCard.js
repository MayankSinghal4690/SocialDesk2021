import React, { useRef, useEffect, useCallback } from "react";
import { useSpring, animated } from "react-spring";
import styled from "styled-components";
import { MdClose } from "react-icons/md";
import dateFormat from "dateformat";
import "../App.css";
import ArrowDownwardSharpIcon from "@material-ui/icons/ArrowDownwardSharp";
import CloseSharpIcon from "@material-ui/icons/CloseSharp";
import { axiosRequest } from "../api/services";

const Background = styled.div`
  width: 100%;
  height: 100vh;
  margin-left: -50px;
  margin-right: -50px;
  margin-top: -40px;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  display: flex;
  justify-content: center;
  z-index: 1;
  align-items: center;
`;

const ModalWrapper = styled.div`
  width: 450px;
  height: 500px;
  box-shadow: 0 5px 16px rgba(0, 0, 0, 0.2);
  background: #fff;
  color: #000;
  position: relative;
  z-index: 10;
  border-radius: 25px;
`;

const CloseModalButton = styled(MdClose)`
  cursor: pointer;
  position: absolute;
  top: 20px;
  right: 20px;
  width: 32px;
  height: 32px;
  padding: 0;
`;

function BookingItemCard({ showComponent, setShowComponent, booking, bookingGotDeleted }) {
  const modalRef = useRef();
  const userEid = localStorage.getItem("userEmail");
  const animation = useSpring({
    config: {
      duration: 250,
    },
    opacity: showComponent ? 1 : 0,
    transform: showComponent ? `translateY(0%)` : `translateY(-100%)`,
  });

  const closeModal = (e) => {
    if (modalRef.current === e.target) {
      setShowComponent(false);
    }
  };

  const keyPress = useCallback(
    (e) => {
      if (e.key === "Escape" && showComponent) {
        setShowComponent(false);
        console.log("I pressed");
      }
    },
    [setShowComponent, showComponent]
  );

  function onDelete() {
    console.log(booking);
    axiosRequest
      .delete(`/bookings/${userEid}/${booking.id}`)
      .then((res) => {
        if (res.data === "success") {
          alert("Booking Deleted Successfully!");
          //bookingGotDeleted();
        } else {
          alert("Unable to delete your booking. Please try again.");
        }
        setShowComponent(false);
      })
      .catch((err) => {
        alert("Server Down");
      });
  }

  function notify() {
    axiosRequest
      .get(`/getBookingReminder/${booking.id}`)
      .then((res) => {
        alert("mail sent to " + res.data);
        setShowComponent(false);
      })
      .catch((err) => {
        console.log(err);
        alert(err);
      });
  }

  useEffect(() => {
    document.addEventListener("keydown", keyPress);
    return () => document.removeEventListener("keydown", keyPress);
  }, [keyPress]);

  return (
    <>
      {showComponent ? (
        <Background onClick={closeModal} ref={modalRef}>
          <animated.div style={animation}>
            <ModalWrapper showModal={showComponent}>
              <h2
                style={{
                  color: "#001BAB",
                  fontWeight: "bold",
                  width: "100%",
                  textAlign: "center",
                  paddingTop: "15px",
                }}
                className="title "
              >
                dbSpace
              </h2>

              <h4
                style={{
                  color: "#006400",
                  fontWeight: "bold",
                  width: "100%",
                  textAlign: "center",
                  paddingTop: "15px",
                }}
                className="title "
              >
                Your Upcoming Booking
              </h4>

              <div className="row mt-4">
                <h4 className="col" style={{ color: "#001BAB", textAlign: "end" }}>
                  Desk No.
                </h4>
                <div style={{ width: "2px" }}></div>
                <h4 className="col"> {booking.chairId} </h4>
              </div>

              <div className="row mt-2">
                <h4 className="col" style={{ color: "#001BAB", textAlign: "end" }}>
                  Floor
                </h4>
                <div style={{ width: "2px" }}></div>
                <h4 className="col"> {booking.floorId}</h4>
              </div>

              <div className="row mt-2">
                <h4 className="col" style={{ color: "#001BAB", textAlign: "end" }}>
                  Location
                </h4>
                <div style={{ width: "2px" }}></div>
                <h4 className="col"> {booking.officeName} </h4>
              </div>

              <div className="row mt-2">
                <h4 className="col" style={{ color: "#001BAB", textAlign: "end" }}>
                  Start Date
                </h4>
                <div style={{ width: "2px" }}></div>
                <h4 className="col"> {dateFormat(booking.startdate, "dd-mm-yyyy")}</h4>
              </div>

              <div className="row mt-2">
                <h4 className="col" style={{ color: "#001BAB", textAlign: "end" }}>
                  End Date
                </h4>
                <div style={{ width: "2px" }}></div>
                <h4 className="col">{dateFormat(booking.enddate, "dd-mm-yyyy")} </h4>
              </div>
              <h6 style={{ width: "100%", textAlign: "center" }} className="mt-4">
                Please contact your Floor Manager for Support
              </h6>

              <div className="row mt-5">
                <div className="col ">
                  <button onClick={notify} className="cardButton pr-3" style={{ backgroundColor: "#001BAB", float: "right" }}>
                    <span style={{ paddingRight: "10px" }}>Notify</span>
                    <ArrowDownwardSharpIcon style={{ fill: "white" }} />
                  </button>
                </div>

                <div className="col">
                  <button
                    className="cardButton pl-4"
                    style={{ backgroundColor: "#A80000", width: "80%" }}
                    onClick={() => {
                      onDelete();
                    }}
                  >
                    <span style={{ paddingRight: "3px" }}>Cancel Booking</span>
                    <CloseSharpIcon style={{ fill: "white" }} />
                  </button>
                </div>
              </div>

              <CloseModalButton aria-label="Close modal" onClick={() => setShowComponent((prev) => !prev)} />
            </ModalWrapper>
          </animated.div>
        </Background>
      ) : null}
    </>
  );
}

export default BookingItemCard;
