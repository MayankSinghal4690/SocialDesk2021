import OfficeMap from "./office";
import React from "react";
import "../App.css";

function Map({ tables, handleDesk }) {
  return (
    <div className="map">
      <OfficeMap
        data={tables}
        onSelect={(desk) => {
          handleDesk(desk);
        }}
        editMode={true}
        showNavigator={true}
        horizontalSize={5}
        verticalSize={3}
      />
    </div>
  );
}

export default Map;
