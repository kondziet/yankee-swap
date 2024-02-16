import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Group from "./components/Group";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/group">
            <Route path="create" element={<div>Create Group</div>} />
            <Route path=":groupId" element={<Group />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
