import React, { useEffect, useState } from "react";
import MemberConstraints from "./MemberConstraints";

const MembersConstraints = ({ members, constraints, updateData }) => {
  const [currentMember, setCurrentMember] = useState();

  useEffect(() => {
    updateData({
      members: members.filter((member) => member.name.trim() !== ""),
    });
  }, []);

  const handleMemberChange = (member) => {
    setCurrentMember(member);
  };

  const handleConstraintsChange = (member, excludedUsers) => {
    updateData({
      constraints: [
        ...constraints.filter((constraint) => constraint.user !== member),
        {
          user: member,
          excludedUsers,
        },
      ],
    });
  };

  return (
    <div className="rounded-md bg-gray-100 p-4 shadow-md">
      {members.map((member) => {
        return (
          <div
            className="mb-2 cursor-pointer rounded-md bg-white p-2 hover:bg-gray-200"
            key={member.name}
            onClick={() => handleMemberChange(member)}
          >
            {member.name}
          </div>
        );
      })}
      {currentMember && (
        <MemberConstraints
          key={currentMember.name}
          currentMember={currentMember}
          members={members}
          constraints={constraints}
          updateConstraints={handleConstraintsChange}
        />
      )}
    </div>
  );
};

export default MembersConstraints;
