import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Group from "./pages/Group";
import DrawResult from "./pages/DrawResult";
import CreateGroup from "./pages/CreateGroup";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/group">
            <Route path="create" element={<CreateGroup />} />
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
