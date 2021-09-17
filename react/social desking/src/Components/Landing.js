import React, { useState } from "react";
import { Row, Col, Form, Button } from "react-bootstrap";
import "../App.css";
import ArrowForwardIcon from "@material-ui/icons/ArrowForward";
import { useHistory } from "react-router-dom";
import axios from "axios";

function Landing() {
  const history = useHistory();
  var user = "admin";
  var path;
  const [email, setEmail] = useState("");

  const headers = {
    "Content-Type": "application/json",
  };

  var handleSubmit = () => {
    axios
      .post(
        "https://spring-dot-grads-coding-group-15.uc.r.appspot.com/login",
        { email: email },
        { headers: headers }
      )
      .then((res) => {
        if (res.data.status === "Error") {
          alert("Invalid User!");
        } else {
          console.log(res);
          localStorage.setItem("userType", res.data.role);
          window.localStorage.setItem("userEmail", email);
          setEmail("");
          localStorage.getItem("userType") === "Non-admin"
            ? (path = `/homepage`)
            : (path = `/homepage/admin`);
          history.push(path);
        }
      })
      .catch((err) => {
        console.log(err);
        alert("Server Down");
      });
  };

  return (
    <div className="landing" style={{ height: "100%" }}>
      <Row style={{ padding: "5%" }}>
        <Col>
          <h2
            style={{ color: "#001bab", fontWeight: "bold" }}
            className="title"
          >
            dbSpace
          </h2>
        </Col>
        <Col>
          <h4 style={{ textAlign: "right" }}>Bookings | Contact</h4>
        </Col>
      </Row>
      <Row style={{ paddingLeft: "5%", marginTop: "2%" }}>
        <h3
          style={{
            textDecoration: "underline",
            textDecorationColor: "#001bab",
          }}
        >
          Welcome back to office!
        </h3>
      </Row>
      <Row style={{ paddingLeft: "5%", marginTop: "1%" }}>
        <h5
          style={{
            color: "rgba(0, 0, 0, 0.5)",
            lineHeight: "1.5em",
            width: "80%",
          }}
        >
          Please sign in to{" "}
          <span style={{ color: "#001bab", fontWeight: "bold" }}>dbSpace</span>{" "}
          with your personal email ID to book your seat in advance and have more
          productive meetings with your teammates safely.
        </h5>
      </Row>
      <Row style={{ paddingLeft: "5%", marginTop: "2%" }}>
        <Col lg={3}>
          <Form.Control
            type="text"
            placeholder="enter your email address"
            value={email}
            style={{
              color: "rgba(0, 0, 0, 0.5)",
              backgroundColor: "#0018A80D",
              textAlign: "center",
              borderRadius: "5px",
            }}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Col>
      </Row>
      <Row style={{ paddingLeft: "5%", marginTop: "2%" }}>
        <Col>
          <Button
            style={{
              width: "15%",
              color: "#FFF",
              backgroundColor: "#0018A8",
              textAlign: "center",
              borderRadius: "50px",
              height: "40px",
            }}
            onClick={handleSubmit}
          >
            <span style={{ paddingRight: "15px" }}>Proceed</span>
            <ArrowForwardIcon style={{ fill: "white" }} />
          </Button>
        </Col>
      </Row>
    </div>
  );
}

export default Landing;
