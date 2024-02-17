import { useState } from "react";

const CreateGroup = () => {
  const [members, setMembers] = useState(["", "", "", "", ""]);

  const handleMemeberChange = (index, value) => {
    const newMembers = [...members];
    newMembers[index] = value;
    setMembers(newMembers);
  };

  const handleMemberCreation = () => {
    setMembers([...members, ""]);
  };

  const handleMemberDeletion = (index) => {
    setMembers((oldMembers) => {
      return oldMembers.filter((_, i) => i !== index);
    });
  };

  return (
    <div>
      <h1>Insert name of the group</h1>
      <input type="text" className="border-2 border-black" />
      <h1>Insert description of the group</h1>
      <textarea type="text" className="border-2 border-black" />
      <h1>Insert your name</h1>
      <input type="text" className="border-2 border-black" />
      <h2>Insert remaining members</h2>
      {members.map((member, index) => {
        return (
          <div key={index}>
            <input
              value={members[index]}
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

export default CreateGroup;
