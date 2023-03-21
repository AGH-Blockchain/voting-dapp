import React, { Component } from "react";
import { Card, Grid, Button, Form, Message } from "semantic-ui-react";
import Layout from "../../components/Layout";
import Voting from "../../ethereum/voting";
import web3 from "../../ethereum/web3";
import VoteForm from "../../components/VoteForm";

class VotingDetails extends Component {
  state = {
    errorMessage: "",
    loading: false,
  };

  static async getInitialProps(props) {
    const voting = Voting(props.query.address);
    const summary = await voting.methods.getSummary().call();
    var errorMessage = "";
    try {
        const options = await voting.methods.getOptions().call();
        let votes = [];
        for (let i = 0; i < options.length; i++) {
            votes.push(await voting.methods.getOptionVotes(options[i]).call());
        }
    } catch (err) {
        const regex = /"reason": "([^"]*)"/;
        const str = err.message.match(regex)[0].split(":")[1];
        const start = str.indexOf('"') + 1;
        const end = str.lastIndexOf('"');
        const result = str.substring(start, end);
        errorMessage = result;
    };

    return (
        errorMessage.length == 0 ? {
        address: props.query.address,
        creator: summary[0],
        topic: summary[1],
        votersCount: summary[2],
        closed: summary[3],
        audience: summary[4],
        options: options,
        votes: votes
    } : {
        errorMessage: errorMessage
    });
  }

  renderOptions() {
    let items = [];
    for (let i = 0; i < this.props.options.length; i++) {
      items.push({
        header: this.props.options[i],
        meta: "Votes: " + this.props.votes[i],
        fluid: true,
      });
    }
    return <Card.Group items={items} />;
  }

  renderCards() {
    const {
      creator,
      audience,
      topic,
      votersCount,
      closed
    } = this.props;

    const items = [
      {
        header: creator,
        meta: "Address of Creator",
        description:
          "The person who created this voting and can close it",
        style: { overflowWrap: "break-word" },
      },
      {
        header: audience,
        meta: "Audience",
        description:
          "People who can cast a vote in this voting",
        style: { overflowWrap: "break-word" },
      },
      {
        header: votersCount,
        meta: "Number of voters",
        description:
          "Number of people who have already voted in this voting",
      },
      {
        header: closed ? "Closed" : "Open",
        meta: "State of the voting",
        description:
          "You can vote on only open votings",
        style: { overflowWrap: "break-word" },
      }
    ];

    return <Card.Group items={items} />;
  }

  closeVoting = async (event) => {
    event.preventDefault();

    const voting = Voting(this.props.address);
    this.setState({ loading: true, errorMessage: "" });

    try {
      const accounts = await web3.eth.getAccounts();
      await voting.methods.closeVoting().send({
        from: accounts[0]
      });
      Router.replaceRoute(`/votings/${this.props.address}`);
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false, value: "" });
  };

  render() {
    return (
      this.state.errorMessage.length != 0 ? (
        <Layout>
        <h3>{this.props.topic}</h3>
        <Grid>
          <Grid.Column width={10}>
            <Grid.Row>
              <h4>Options</h4>
              { this.renderOptions() }
            </Grid.Row>
            <Grid.Row marginTop={10}>
              { !this.props.closed ? 
                  <VoteForm address={this.props.address} options={this.props.options}/> 
                  : null }
            </Grid.Row>
            <Grid.Row>
              { !this.props.closed ?
                  <Form onSubmit={this.closeVoting} error={!!this.state.errorMessage} >
                    <Button
                      content="Close Voting"
                      icon="cancel"
                      color="red"
                      floated="left"
                      labelPosition="right"
                      loading={this.state.loading}
                    />
                  </Form>
                : null }
            </Grid.Row>
          </Grid.Column>
          <Grid.Column width={6}>
            <h3>Voting details</h3>
            {this.renderCards()}
          </Grid.Column>
        </Grid>
      </Layout>
      ) : (
        <Layout>
            <p>ERROR: {this.props.errorMessage}</p>
        </Layout>
      )
    );
  }
}

export default VotingDetails;
