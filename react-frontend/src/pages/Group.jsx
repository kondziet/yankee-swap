import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import publicClientRequest from "../api/ClientRequest";
import { useEffect, useState } from "react";
import background from "../img/home-background.jpg";

const Group = () => {
  const navigate = useNavigate();
  const { groupId } = useParams();
  const [membersDraws, setMembersDraws] = useState([]);

  const fetchMembersDraws = async () => {
    try {
      const response = await publicClientRequest.get(`/group/${groupId}`);
      setMembersDraws(response.data);
    } catch (error) {
      if (error.response && error.response.status === 404) {
        alert("Group not found");
      }
    }
  };

  useEffect(() => {
    fetchMembersDraws();
  }, []);

  return (
    <div
      className="flex h-screen flex-col items-center justify-center bg-gray-100"
      style={{
        backgroundImage: `url(${background})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <h2 className="mb-4 text-3xl font-bold">Choose Your Name</h2>
      <div className="mx-auto grid max-w-3xl grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3">
        {membersDraws.map((memberDraw, index) => (
          <div
            key={index}
            className="rounded-md bg-white p-6 shadow-md transition duration-300 hover:shadow-lg"
          >
            <Link
              to={`/group/${groupId}/${memberDraw.name}`}
              className="text-2xl font-bold hover:text-blue-500"
            >
              {memberDraw.name}
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Group;
