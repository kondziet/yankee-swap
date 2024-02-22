const MembersDetails = ({ members, constraints, updateData }) => {
  const handleMemeberChange = (index, value) => {
    const newMembers = [...members];
    newMembers[index].name = value;
    updateData({ members: newMembers });
  };

  const handleMemberCreation = () => {
    updateData({ members: [...members, { name: "" }] });
  };

  const handleMemberDeletion = (index) => {
    const newMembers = members.filter((_, i) => i !== index);
    const newConstraints = constraints
      .filter((constraint) => constraint.user !== members[index].name)
      .map((constraint) => {
        let newExcludedUsers = constraint.excludedUsers.filter(
          (excludedUser) => excludedUser !== members[index].name,
        );
        if (newExcludedUsers.length >= newMembers.length - 2) {
          newExcludedUsers = newExcludedUsers.slice(0, -1);
        }
        return {
          user: constraint.user,
          excludedUsers: newExcludedUsers,
        };
      });

    updateData({ members: newMembers, constraints: newConstraints });
  };

  return (
    <div>
      <h2>Insert remaining members</h2>
      {members.map((member, index) => {
        return (
          <div key={index}>
            <input
              value={member.name}
              onChange={(e) => handleMemeberChange(index, e.target.value)}
              placeholder={`Member ${index + 1}`}
            />
            <button onClick={() => handleMemberDeletion(index)}>Delete</button>
          </div>
        );
      })}
      <button onClick={handleMemberCreation}>Add member</button>
    </div>
  );
};

export default MembersDetails;
