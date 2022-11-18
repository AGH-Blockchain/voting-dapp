const Web3 = require("web3");
const compiledFactory = require("./build/VotingFactory.json");


const web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'));

const deploy = async () => {
  const accounts = await web3.eth.getAccounts();

  console.log("Attempting to deploy from account", accounts[0]);

  const result = await new web3.eth.Contract(compiledFactory.abi)
    .deploy({ data: compiledFactory.evm.bytecode.object })
    .send({ gas: "6721975", from: accounts[0] });

  console.log("Contract deployed to", result.options.address);
};
deploy();
