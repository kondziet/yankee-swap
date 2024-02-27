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
    <div className="bg-gray-100 p-4 rounded-md">
      <h2 className="text-xl font-bold mb-4">Owner Details</h2>
      <div className="flex items-center">
        <label className="text-gray-600 mr-2">Owner Name:</label>
        <input
          type="text"
          value={owner.name}
          onChange={(e) => handleNameChange(e.target.value)}
          className="border rounded-md p-2"
        />
      </div>
    </div>
  );
};

export default OwnerDetails;
