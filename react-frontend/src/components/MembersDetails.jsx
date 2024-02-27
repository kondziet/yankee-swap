const MembersDetails = ({ members, constraints, updateData }) => {
  const handleMemberChange = (index, value) => {
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
    <div className="rounded-md bg-gray-100 p-4 shadow-md">
      <h2 className="mb-4 text-xl font-bold">Insert Remaining Members</h2>
      {members.map((member, index) => (
        <div key={index} className="mb-2 flex items-center">
          <input
            value={member.name}
            onChange={(e) => handleMemberChange(index, e.target.value)}
            placeholder={`Member ${index + 1}`}
            className="mr-2 rounded-md border p-2 focus:border-blue-300 focus:outline-none focus:ring"
          />
          <button
            onClick={() => handleMemberDeletion(index)}
            className="rounded-md bg-red-500 px-2 py-1 text-white"
          >
            Delete
          </button>
        </div>
      ))}
      <button
        onClick={handleMemberCreation}
        className="rounded-md bg-green-500 px-2 py-1 text-white"
      >
        Add Member
      </button>
    </div>
  );
};

export default MembersDetails;
