import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/group">
            <Route path="create" element={<div>Create Group</div>} />
            <Route path=":groupId" element={<div>Group</div>} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
