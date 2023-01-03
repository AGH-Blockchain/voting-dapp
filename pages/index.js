import React, { Component } from "react";
import { Card, Button } from "semantic-ui-react";
import factory from "../ethereum/votingFactory";
import Layout from "../components/Layout";
import { Link } from "../routes";
import Voting from "../ethereum/voting";

class VotingIndex extends Component {

  static async getInitialProps() {
    const votings = await factory.methods.getDeployedVotings().call();
    return {votings};
  }

  constructor(props) {
    super(props);
    this.state = {
      topics: [] 
    };
  }

  componentDidMount() {
    this.fetchTopics();
  }
  
  async fetchTopics() {
    const votings = await factory.methods.getDeployedVotings().call();
    const newTopics = [];
    for (let i = 0; i < votings.length; i++) {
      const voting = Voting(votings[i]);
      let topic = await voting.methods.getSummary().call();
      newTopics.push(topic[1]);
    }
    //console.log(newTopics)    // test how the newTopics array looks like
    this.setState({ topics: newTopics });
  }


  renderVotings() {
    const items = this.props.votings.map((address,index) => {
      console.log(this.state.topics[index]);
      return {
        header: this.state.topics[index],  //  print the topic of the voting
        description: (
          <Link route={`/votings/${address}`}>
            <a>View Voting</a>
          </Link>
        ),
        fluid: true,
      };
    });
    
    return <Card.Group items={items} />;
  }
  render() {
    return (
      <Layout>
        <div>
          <h3>Votings</h3>
          <Link route="/votings/create">
            <a>
              <Button
                floated="right"
                content="Create Voting"
                icon="add"
                primary
              />
            </a>
          </Link>
          {this.renderVotings()}
        </div>
      </Layout>
    );
  }
}

export default VotingIndex;
