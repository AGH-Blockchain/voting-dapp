const assert = require("assert");
const ganache = require("ganache-cli");
const Web3 = require("web3");
const web3 = new Web3(ganache.provider({ gasLimit: 10000000 }));

const compiledFactory = require("../ethereum/build/VotingFactory.json");
const compiledVoting = require("../ethereum/build/Voting.json");

let accounts;
let factory;
let votingAddress;
let voting;

beforeEach(async () => {
  accounts = await web3.eth.getAccounts();

  factory = await new web3.eth.Contract(compiledFactory.abi)
    .deploy({ data: compiledFactory.evm.bytecode.object })
    .send({ from: accounts[0], gas: "10000000" });

  await factory.methods.createVoting("Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"]).send({
    from: accounts[0],
    gas: "10000000",
  });

  [votingAddress] = await factory.methods.getDeployedVotings().call();
  voting = await new web3.eth.Contract(compiledVoting.abi, votingAddress);
});

describe("Votings", () => {
  it("deploys a factory and a voting", () => {
    assert.ok(factory.options.address);
    assert.ok(voting.options.address);
  });

  it("marks caller as the voting creator", async () => {
    const creator = await voting.methods.creator().call();
    assert.equal(accounts[0], creator);
  });

  it("allows people to vote and marks them as voters", async () => {
    await voting.methods.vote("Option 1").send({
      from: accounts[1],
      gas: "10000000"
    });
    const isVoter = await voting.methods.voters(accounts[1]).call();
    assert(isVoter);
  });

  it("allows creator to close voting", async () => {
    await voting.methods
      .closeVoting()
      .send({
        from: accounts[0],
        gas: "10000000"
      });
    const isClosed = await voting.methods.closed().call();

    assert(isClosed);
  });
});
