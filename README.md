# Voting Dapp by AGH Blockchain


## Table of contents
1. [How to run](#1-how-to-run)
2. [Unit tests](#2-unit-tests)


## 1. How to run

### Requirements
- [Ganache](https://trufflesuite.com/ganache/) - local blockchain
- [Node.js](https://nodejs.org/en/)
- [Metamask wallet](https://metamask.io/) - *optionally other provider

### Steps to run locally

#### 1. Run Ganache
- Recommended to create a workspace
- Set server port to `8545`

#### 2. Import local account to Metamask
Open a web browser and activate the Metamask plugin, then import an account from Ganache using it's private key.
Remember to select the `Localhost 8545` network.

#### 3. Install packages
To install all dependencies run:
```sh
npm install
```

#### 4. Compile Solidity smart contracts
The contracts are located inside `ethereum\contracts`. To compile them, execute command:
```sh
node ./ethereum/compile.js
```
This will create (or update) the `build` folder with the compiled versions of contracts.

#### 5. Deploy contract to local network
Notice that we only need to deploy the `VotingFactory` contract, it will later enable us to interact with `Voting` contracts.
To deploy, run:
```sh
node ./ethereum/deploy.js
```
**! Important !** The console will output the address where the contract has been deployed. Copy this address and paste it into `ethereum\votingFactory.js`

#### 6. Run the app
Now everything is set up, so let's run:
```sh
npm run dev
```
This wil start the app at [localhost:3000](http://localhost:3000/)


## 2. Unit tests
There is a dedicated directory `test` for unit testing Solidity smart contracts. To run the tests, simply execute:
```sh
npm run test
```
