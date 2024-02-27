const OwnerDetails = ({ owner, members, updateData }) => {
  const handleNameChange = (value) => {
    const oldOwner = owner;

    if (members.filter((member) => member.name).length === 0) {
      updateData({
        owner: {
          name: value,
        },
        members: [{ name: value }, ...members],
      });
      return;
    }

    updateData({
      owner: {
        name: value,
      },
      members: members.map((member) => {
        if (member.name === oldOwner.name) {
          return {
            name: value,
          };
        }
        return member;
      }),
    });
  };

  return (
    <div>
      <h2>Owner Details</h2>
      <input
        type="text"
        value={owner.name}
        onChange={(e) => handleNameChange(e.target.value)}
      />
    </div>
  );
};

export default OwnerDetails;
