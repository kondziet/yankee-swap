const OwnerDetails = ({ owner, updateData }) => {
  return (
    <div>
      <h2>Owner Details</h2>
      <input
        type="text"
        value={owner.name}
        onChange={(e) => updateData({ owner: { name: e.target.value } })}
      />
    </div>
  );
};

export default OwnerDetails;
