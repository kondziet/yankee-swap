import React from "react";
import MemberConstraints from "./MemberConstraints";

const MembersConstraints = ({ members, constraints, updateData }) => {
  const [currentMember, setCurrentMember] = React.useState("");

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
    <div>
      {members.map((member) => {
        return <div key={member} onClick={() => handleMemberChange(member)}>{member}</div>;
      })}
      {currentMember && (
        <MemberConstraints
          key={currentMember}
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
