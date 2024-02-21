import React from "react";
import MemberConstraints from "./MemberConstraints";

const MembersConstraints = ({ members, constraints, updateData }) => {
  const [currentMember, setCurrentMember] = React.useState("");

  const handleMemberChange = (member) => {
    setCurrentMember(member);
  };

  return (
    <div>
      {members.map((member, index) => {
        return <div onClick={() => handleMemberChange(member)}>{member}</div>;
      })}
      <MemberConstraints
        currentMember={currentMember}
        members={members}
        constraints={constraints}
        updateData={updateData}
      />
    </div>
  );
};

export default MembersConstraints;
