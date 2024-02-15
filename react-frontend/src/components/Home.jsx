import React from "react";
import background from "../img/home-background-2.jpg";
import image from "../img/man-giving-present-woman.jpg";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div
      className="flex h-screen w-screen flex-col items-center justify-center"
      style={{
        backgroundImage: `url(${background})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div className="grid w-full max-w-7xl auto-rows-[300px] gap-6 md:grid-cols-2">
        <div className="flex flex-col items-center justify-center md:col-span-2">
          <div className="mb-4 text-7xl font-extrabold text-white">
            Yankee Swap
          </div>
          <div className="text-lg text-gray-500">
            A fun and easy way to exchange gifts with friends
          </div>
        </div>
        <div className="flex flex-col justify-between rounded-xl bg-red-900 p-8">
          <div className="text-4xl font-semibold text-red-300">
            Already part of a group?
          </div>
          <div className="flex flex-col gap-2">
            <input
              className="w-full rounded-lg bg-gray-200 p-4 text-4xl uppercase"
              type="text"
              placeholder="GROUP IDENTIFIER"
            />
            <button className="rounded-md bg-white px-8 py-2 text-2xl font-bold text-gray-500 hover:bg-gray-700">
              Enter
            </button>
          </div>
        </div>
        <div className="row-span-2 hidden rounded-xl md:block">
          <img
            className="h-full w-full rounded-xl object-cover"
            src={image}
          ></img>
        </div>
        <div className="rounded-xl bg-red-700 flex justify-center items-center">
          <Link to="/group/create" className="font-bold text-4xl rounded-lg text-red-300 p-24">Create Group</Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
