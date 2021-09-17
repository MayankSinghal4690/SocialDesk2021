import axios from "axios";
export const axiosRequest = axios.create({
  baseURL: "https://spring-dot-grads-coding-group-15.uc.r.appspot.com",
});

function objectHandle(chair) {
  return {
    id: chair.id,
    chairDirection: chair.rotation,
    x: chair.coordinate_X,
    y: chair.coordinate_Y,
    equipments: [
      { type: "desk", specification: "Simple desk" },
      { type: "laptop", specification: "Laptop Dell Inspiron 15 5000" },
      { type: chair.status, specification: "Ergonomic Office Chair" },
      {
        type: "Booking Status",
        specification: chair.teammembername != "null" ? `Booked by ${chair.teammembername}` : "Vacant",
      },
    ],
  };
}

export const getFloorPlan = (floor, office, startDate) => {
  const email = localStorage.getItem("userEmail");
  const data = [
    {
      id: "Chair001",
      coordinate_X: 1,
      coordinate_Y: 1,
      rotation: "0",
      status: "chair-na",
      teammembername: "Sumit",
    },
    {
      id: "Chair002",
      coordinate_X: 3,
      coordinate_Y: 3,
      rotation: "0",
      status: "chair",
      teammembername: "null",
    },
    {
      id: "Chair003",
      coordinate_X: 1,
      coordinate_Y: 3,
      rotation: "0",
      status: "chair",
      teammembername: "null",
    },
    {
      id: "Chair004",
      coordinate_X: 3,
      coordinate_Y: 1,
      rotation: "0",
      status: "chair",
      teammembername: "null",
    },
  ];
  // return data.map((e) => objectHandle(e));

  const headers = {
    "Content-Type": "application/json",
  };
  axios
    .post(
      `https://spring-dot-grads-coding-group-15.uc.r.appspot.com/desks/${email}`,
      {
        startDate: "2021-08-14",
        endDate: "2021-08-16",
        officeName: "Yerwada",
        floorNo: 1,
      },
      { headers: headers }
    )
    .then((res) => {
      const arr = res.data.map((e) => objectHandle(e));
      console.log(arr);
      // setTimeout(1000, () => {
      return arr;
      // });
      // return res.data;
    })
    .catch((errors) => {
      // react on errors.
      console.log(errors);
      return [];
    });
};

export const blockFloor = (floor, office, date) => {
  console.log(floor, office, date);
  return false;
};

export const bookSeat = (data) => {
  // const email = localStorage.getItem("userEmail");
  // axios
  //   .put(
  //     `https://spring-dot-grads-coding-group-15.uc.r.appspot.com/desks/${email}/book
  //     `,
  //     {
  //       startDate: startDate,
  //       endDate: endDate,
  //       officeName: office,
  //       floorNo: floor,
  //       chairId:
  //     },
  //     { headers: headers }
  //   )
  //   .then((res) => {
  //     return res.map((e) => objectHandle(e));
  //   })
  //   .catch((errors) => {
  //     // react on errors.
  //     console.log(errors);
  //     return [];
  //   });
};
