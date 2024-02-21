import React, { useEffect, useState } from "react";

const MemberConstraints = ({
  currentMember,
  members,
  constraints,
  updateData,
}) => {
  const matchingConstraint = constraints.find(
    (constraint) => constraint.user === currentMember,
  );

  const [checkedMembers, setCheckedMembers] = useState(
    matchingConstraint ? [...matchingConstraint.excludedUsers] : [],
  );

  useEffect(() => {
    if (!currentMember) return;

    updateData({
      constraints: [
        ...constraints.filter(
          (constraint) => constraint.user !== currentMember,
        ),
        {
          user: currentMember,
          excludedUsers: checkedMembers,
        },
      ],
    });
  }, [checkedMembers]);

  const renderedConstraints = members
    .filter((member) => member !== currentMember && member !== "")
    .map((member) => {
      const isChecked = checkedMembers.includes(member);

      return (
        <div key={member}>
          <input
            type="checkbox"
            checked={isChecked}
            onChange={(e) => {
              if (e.target.checked) {
                setCheckedMembers((prev) => [...prev, member]);
              } else {
                setCheckedMembers((prev) =>
                  prev.filter((checkedMember) => checkedMember !== member),
                );
              }
            }}
          />
          {member}
        </div>
      );
    });

  return <div>{renderedConstraints}</div>;
};

export default MemberConstraints;
