import React from "react";

const MemberConstraints = ({
  currentMember,
  currentMemberConstraints,
  members,
  constraints,
  updateData,
}) => {
  const renderedConstraints = members
    .filter((member) => member !== currentMember && member !== "")
    .map((member) => {
      return (
        <div>
          <input
            type="checkbox"
            checked={currentMemberConstraints.some(
              (constraint) => constraint === member,
            )}
            onChange={(e) => {
              if (e.target.checked) {
                updateData({
                  constraints: [
                    ...constraints,
                    {
                      user: currentMember,
                      constraint: [
                        ...currentMemberConstraints.constraint,
                        member,
                      ],
                    },
                  ],
                });
              } else {
                // updateData({
                //   constraints: constraints.filter(
                //     (constraint) =>
                //       constraint.user !== currentMember ||
                //       constraint.constraint !== member,
                //   ),
                // });
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
