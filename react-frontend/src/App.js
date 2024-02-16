import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Group from "./pages/Group";
import DrawResult from "./pages/DrawResult";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/group">
            <Route path="create" element={<div>Create Group</div>} />
            <Route path=":groupId">
              <Route index element={<Group />} />
              <Route path=":userName" element={<DrawResult />} />
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
