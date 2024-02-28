import { useEffect, useState } from "react";
import publicClientRequest from "../api/ClientRequest";
import { useParams } from "react-router-dom";
import background from "../img/home-background.jpg";

const DrawResult = () => {
  const { groupId, userName } = useParams();
  const [result, setResult] = useState("");
  useEffect(() => {
    publicClientRequest
      .get(`/group/${groupId}/${userName}`)
      .then((response) => {
        setResult(response.data.name);
      });
  }, []);

  const toggleYankeeSwapParticipation = async () => {
    await publicClientRequest.post(`/group/${groupId}/${userName}`);
  };

  return (
    <div
      className="flex h-screen items-center justify-center"
      style={{
        backgroundImage: `url(${background})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div className="flex max-w-2xl flex-col items-center justify-center rounded-md bg-white p-12 shadow-md">
        <div className="mb-8 text-3xl font-bold">
          {result ? `You drew: ${result} 🎉` : "Loading..."}
        </div>
        <div className="mb-8 flex items-center">
          <input
            type="checkbox"
            className="form-checkbox h-6 w-6 text-blue-500"
            onChange={toggleYankeeSwapParticipation}
          />
          <label className="ml-4 text-lg text-gray-700">
            Participate in Yankee Swap
          </label>
        </div>
      </div>
    </div>
  );
};

export default DrawResult;
