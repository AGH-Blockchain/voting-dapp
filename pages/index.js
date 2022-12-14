import React, { Component } from "react";
import { Card, Button } from "semantic-ui-react";
import factory from "../ethereum/votingFactory";
import Layout from "../components/Layout";
import { Link } from "../routes";

class VotingIndex extends Component {
  static async getInitialProps() {
    const votings = await factory.methods.getDeployedVotings().call();

    return { votings };
  }
  renderVotings() {
    const items = this.props.votings.map((address) => {
      return {
        header: address,
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
