const MembersDetails = ({ members, updateData }) => {
  const handleMemeberChange = (index, value) => {
    const newMembers = [...members];
    newMembers[index].name = value;
    updateData({ members: newMembers });
  };

  const handleMemberCreation = () => {
    updateData({ members: [...members, { name: "" }] });
  };

  const handleMemberDeletion = (index) => {
    // remove constraints of removed member
    updateData({ members: members.filter((_, i) => i !== index) });
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
