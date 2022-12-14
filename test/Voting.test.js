const assert = require("assert");
const ganache = require("ganache-cli");
const Web3 = require("web3");
const web3 = new Web3(ganache.provider({ gasLimit: 10000000 }));

const compiledFactory = require("../ethereum/build/VotingFactory.json");
const compiledVoting = require("../ethereum/build/Voting.json");

let accounts;
let factory;
let votingAddress;

async function createTestVoting(factory, topic, options, uintAudience){
    await factory.methods.createVoting(topic, options, uintAudience).send({
        from: accounts[0],
        gas: "10000000",
    });

    [votingAddress] = await factory.methods.getDeployedVotings().call();
    // return voting
    return new web3.eth.Contract(compiledVoting.abi, votingAddress);
}

beforeEach(async () => {
    accounts = await web3.eth.getAccounts();
    
    factory = await new web3.eth.Contract(compiledFactory.abi)
        .deploy({ data: compiledFactory.evm.bytecode.object })
        .send({ from: accounts[0], gas: "10000000" });

    await factory.methods.addStudent(accounts[0]).send({from: accounts[0]});
    await factory.methods.addStudent(accounts[1]).send({from: accounts[0]});
    await factory.methods.addEmployee(accounts[2]).send({from: accounts[0]});
});

describe("Voting Factory", () => {
    it("add student", async () => {
        await factory.methods.addStudent(accounts[2]).send({from: accounts[0]});
        const isStudent = await factory.methods.students(accounts[2]).call();
        assert.equal(isStudent, true);
    })

    it("add employee", async () => {
        await factory.methods.addEmployee(accounts[2]).send({from: accounts[0]});
        const isEmployee = await factory.methods.employees(accounts[2]).call();
        assert.equal(isEmployee, true);
    })

    it("remove student", async () => {
        var student = accounts[2];
        await factory.methods.addStudent(student).send({from: accounts[0]});
        await factory.methods.removeStudent(student).send({from: accounts[0]});
        const isStudent = await factory.methods.isStudent(student).call();
        assert.equal(isStudent, false);
    })

    it("remove employee", async () => {
        var employee = accounts[2];
        await factory.methods.addEmployee(employee).send({from: accounts[0]});
        await factory.methods.removeEmployee(employee).send({from: accounts[0]});
        const isStudent = await factory.methods.employees(employee).call();
        assert.equal(isStudent, false);
    })

    it("restriction", async () => {
        try {
            await await factory.methods.addStudent(accounts[2]).send({from: accounts[1]});
            assert.fail("The transaction should have thrown an error");
        } catch(err) {
            assert.equal(err.message, "VM Exception while processing transaction: revert Sender not authorized.");
        }
    })
})

describe("Votings", () => {
    it("deploys a factory and a voting",async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 1);
        assert.ok(factory.options.address);
        assert.ok(voting.options.address);
    });

    it("marks caller as the voting creator", async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 1);
        const creator = await voting.methods.creator().call();
        assert.equal(accounts[0], creator);
    });

    it("allows people to vote and marks them as voters", async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 1);
        await voting.methods.vote("Option 1").send({
            from: accounts[2],
            gas: "10000000"
        });
        const isVoter = await voting.methods.voters(accounts[2]).call();
        assert(isVoter);
    });

    it("allows creator to close voting", async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 1);
        await voting.methods
            .closeVoting()
            .send({
                from: accounts[0],
                gas: "10000000"
            });
        const isClosed = await voting.methods.closed().call();

        assert(isClosed);
    });

    it("onlyByAudience restriction", async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 0);

        try {
            await await voting.methods.vote("Option 1").send({from: accounts[2]});
            assert.fail("The transaction should have thrown an error");
        } catch(err) {
            assert.equal(err.message, "VM Exception while processing transaction: revert Only students can call this function");
        }
    })

    it("sets voting audience as students", async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 0);

        const audience = await voting.methods.audience().call();
        const audienceToString = await voting.methods.getAudienceToString().call();

        assert.equal(audience, 0);
        assert.equal(audienceToString, 'students');
    });

    it("sets voting audience as employees", async () => {
        const voting = await createTestVoting(factory, "Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 1);

        const audience = await voting.methods.audience().call();

        console.log(audience);

        const audienceToString = await voting.methods.getAudienceToString().call();

        assert.equal(audience, 1);
        assert.equal(audienceToString, 'employees');
    });

    it("sets voting audience as all", async () => {
        const voting = await createTestVoting(factory,"Some topic", ["Option 1", "Option 2", "Option 3", "Option 4"], 2);

        const audience = await voting.methods.audience().call();
        const audienceToString = await voting.methods.getAudienceToString().call();

        assert.equal(audience, 2);
        assert.equal(audienceToString, 'all');
    });

    it("allows to create 10 options", async () => {
        const voting = await createTestVoting(
            factory, "Some topic", ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5', 'Option 6', 'Option 7', 'Option 8', 'Option 9', 'Option 10'], 2);

        const options = await voting.methods.getOptions().call({
            from: accounts[1]
        });
        assert.equal(options.length, 10);
    });

    it("does not allow to have less than 2 options", async () => {
        try {
            await factory.methods.createVoting(
                "Some topic",
                ['Option 1'],
                2
            ).send({
                from: accounts[0],
                gas: "10000000",
            })
        } catch (e) {
            const errorMessage = 'VM Exception while processing transaction: revert At least two options must be provided';
            assert.equal(e.message,errorMessage)
        }
    });

    it("does not allow to have more than 10 options", async () => {
        try {
            await factory.methods.createVoting(
                "Some topic",
                ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5', 'Option 6', 'Option 7', 'Option 8', 'Option 9', 'Option 10', 'Option 11'],
                2
            ).send({
                from: accounts[0],
                gas: "10000000",
            })
        } catch (e) {
            const errorMessage = 'VM Exception while processing transaction: revert No more than 10 options can be provided';
            assert.equal(e.message,errorMessage)
        }
    });
});
