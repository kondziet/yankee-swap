import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import publicClientRequest from "../api/ClientRequest";
import { useEffect, useState } from "react";

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

  const renderedMembersDraws = membersDraws.map((memberDraw, index) => {
    return (
      <div key={index} className="flex items-center justify-between">
        <Link to={`/group/${groupId}/${memberDraw.name}`}>
          {memberDraw.name}
        </Link>
      </div>
    );
  });

  return (
    <div>
      <div className="flex flex-col gap-2">{renderedMembersDraws}</div>
    </div>
  );
};

export default Group;
