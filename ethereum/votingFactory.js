import web3 from "./web3";
import VotingFactory from "./build/VotingFactory.json";


const votingFactory = new web3.eth.Contract(
  VotingFactory.abi,
  "0x52AeB6dD11bb4F4Fc4c853b8E3fc089537514416"
);

export default votingFactory;
