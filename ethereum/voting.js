import web3 from "./web3";
import Voting from "./build/Voting.json";

const voting = (address) => {
  return new web3.eth.Contract(Voting.abi, address);
};
export default voting;
