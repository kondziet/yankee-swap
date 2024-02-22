import React, { useEffect, useState } from "react";

const MemberConstraints = ({
  currentMember,
  members,
  constraints,
  updateConstraints,
}) => {
  const matchingConstraint = constraints.find(
    (constraint) => constraint.user === currentMember,
  );

  const [checkedMembers, setCheckedMembers] = useState(
    matchingConstraint ? [...matchingConstraint.excludedUsers] : [],
  );

  useEffect(() => {
    if (!currentMember) return;
    updateConstraints(currentMember, checkedMembers);
  }, [checkedMembers]);

  const renderedConstraints = members
    .filter((member) => member !== currentMember && member.name)
    .map((member) => {
      const isChecked = checkedMembers.includes(member);

      return (
        <div key={member.name}>
          <input
            type="checkbox"
            checked={isChecked}
            onChange={(e) => {
              if (e.target.checked) {
                setCheckedMembers((prev) => [...prev, member]);
              } else {
                setCheckedMembers((prev) =>
                  prev.filter(
                    (checkedMember) => checkedMember !== member,
                  ),
                );
              }
            }}
          />
          {member.name}
        </div>
      );
    });

  return <div>{renderedConstraints}</div>;
};

export default MemberConstraints;
