const GroupDetails = ({ group, updateData }) => {
  return (
    <div>
      <h2>Group Details</h2>
      <input
        type="text"
        value={group.name}
        onChange={(e) =>
          updateData({
            group: { name: e.target.value, description: group.description },
          })
        }
      />
      <input
        type="text"
        value={group.description}
        onChange={(e) =>
          updateData({
            group: { name: group.name, description: e.target.value },
          })
        }
      />
    </div>
  );
};

export default GroupDetails;
