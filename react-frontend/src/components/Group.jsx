import { useLocation, useParams } from "react-router-dom";
import publicClientRequest from "../api/ClientRequest";
import { useEffect, useState } from "react";

const Group = () => {
  const { groupId } = useParams();
  const { state } = useLocation();
  const [membersDraws, setMembersDraws] = useState([]);

  const fetchMembersDraws = async () => {
    try {
      if (state) {
        setMembersDraws(state.membersDraws);
      } else {
        const response = await publicClientRequest.get(`/group/${groupId}`);
        setMembersDraws(response.data);
      }
    } catch (error) {
      if (error.response && error.response.status === 404) {
        alert("Group not found");
      }
    }
  };

  useEffect(() => {
    fetchMembersDraws();
  }, []);

  const handleMemberDrawClick = async (userName) => {
    await publicClientRequest
      .get(`/group/${groupId}/${userName}`)
      .then((response) => {
        alert(response.data);
      });
  };

  const renderedMembersDraws = membersDraws.map((memberDraw, index) => {
    return (
      <div key={index} className="flex items-center justify-between">
        <div onClick={() => handleMemberDrawClick(memberDraw.name)}>
          {memberDraw.name}
        </div>
      </div>
    );
  });

  return (
    <div>
      <div className="text-4xl font-semibold text-red-300">
        Group Identifier: {groupId}
      </div>
      <div className="flex flex-col gap-2">{renderedMembersDraws}</div>
    </div>
  );
};

export default Group;
