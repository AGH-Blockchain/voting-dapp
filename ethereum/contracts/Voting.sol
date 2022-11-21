// SPDX-License-Identifier: MIT

pragma solidity ^0.8.9;

contract VotingFactory {
    address[] public deployedVotings;

    mapping(address => bool) public students;
    mapping(address => bool) public employees;

    function createVoting(string memory topic, string[] memory options) public {
        require(keccak256(abi.encodePacked(topic)) != keccak256(abi.encodePacked("")), "Topic must be provided"); // check if topic is not empty
        require(options.length > 1, "At least two options must be provided"); // check if there are at least two options
        address newVoting = address(new Voting(msg.sender, topic, options));
        deployedVotings.push(newVoting);
    }

    function addStudent(address _address) public {
        students[_address] = true;
    }

    function addEmployee(address _address) public {
        employees[_address] = true;
    }

    function removeStudent(address _address) public {
        delete students[_address];
    }

    function removeEmployee(address _address) public {
        delete employees[_address];
    }

    function isEmployee(address _employee) public view returns (bool) {
        return employees[_employee];
    }

    function isStudent(address _student) public view returns (bool) {
        return students[_student];
    }

    function getDeployedVotings() public view returns (address[] memory) {
        return deployedVotings;
    }
}

contract Voting {
    
    address public creator;
    string public topic;
    string[] public options;
    mapping(string => uint) public optionsVotes;
    mapping(address => bool) public voters;
    uint public votersCount;
    bool public closed;

    modifier restricted() {
        require(msg.sender == creator, "Only creator can call this function");
        _;
    }

    constructor (address _creator, string memory _topic, string[] memory _options) {
        creator = _creator;
        topic = _topic;
        options = _options;
    }

    function vote(string memory option) public {
        require(!voters[msg.sender], "You have already voted");
        require(!closed, "Voting is closed");
        bool optionExists = false;
        for(uint i = 0; i < options.length; i++) {
            if(keccak256(abi.encodePacked(options[i])) == keccak256(abi.encodePacked(option))) {
                optionExists = true;
                break;
            }
        }
        require(optionExists, "Option does not exist");
        voters[msg.sender] = true;
        optionsVotes[option] += 1;
        votersCount += 1;
    }

    function closeVoting() public restricted {
        require(!closed, "Voting is already closed");

        closed = true;
    }
    
    function getSummary() public view returns (
      address, string memory, uint, bool
      ) {
        return (
          creator,
          topic,
          votersCount,
          closed
        );
    }

    function getOptions() public view returns (string[] memory) {
        return options;
    }

    function getOptionVotes(string memory option) public view returns (uint) {
        return optionsVotes[option];
    }
    
    function getVotersCount() public view returns (uint) {
        return votersCount;
    }

    function getOptionsCount() public view returns (uint) {
        return options.length;
    }
}