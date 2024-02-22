const GroupDetails = ({ name, description, updateData }) => {
  return (
    <div>
      <h2>Group Details</h2>
      <input
        type="text"
        value={name}
        onChange={(e) =>
          updateData({
            name: e.target.value,
          })
        }
      />
      <input
        type="text"
        value={description}
        onChange={(e) =>
          updateData({
            description: e.target.value,
          })
        }
      />
    </div>
  );
};

export default GroupDetails;
