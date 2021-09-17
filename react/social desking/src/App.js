import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Landing from "./Components/Landing";
import Homepage from "./Components/Homepage";
import Admin from "./Components/Admin";


function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/" exact component={Landing}></Route>
          <Route path="/homepage" exact component={Homepage}></Route>
          <Route path="/homepage/admin" exact component={Admin}></Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
