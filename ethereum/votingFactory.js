import web3 from "./web3";
import VotingFactory from "./build/VotingFactory.json";


const votingFactory = new web3.eth.Contract(
  VotingFactory.abi,
  "0x10F9e47b4A4BA50f8f6265F8C5D797a3C6e3417a"
);

export default votingFactory;
