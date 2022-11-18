import React, { Component } from "react";
import { Form, Button, Input, Message } from "semantic-ui-react";
import Layout from "../../components/Layout";
import factory from "../../ethereum/votingFactory";
import web3 from "../../ethereum/web3";
import { Router } from "../../routes";

class VotingCreate extends Component {
  state = {
    topic: "",
    options: ["", ""],
    optionsCount: 0,
    errorMessage: "",
    loading: false,
  };

  onSubmit = async (event) => {
    event.preventDefault();
    this.setState({ loading: true, errorMessage: "" });

    try {
      const accounts = await web3.eth.getAccounts();
      await factory.methods
        .createVoting(this.state.topic, this.state.options)
        .send({
          from: accounts[0],
        });

      Router.pushRoute("/");
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false });
  };

  render() {
    return (
      <Layout>
        <h3>Create Voting</h3>
        <Form onSubmit={this.onSubmit} error={!!this.state.errorMessage}>
          <Form.Field>
            <label>Topic</label>
            <Input
              value={this.state.topic}
              onChange={(event) =>
                this.setState({ topic: event.target.value })
              }
            />
            <label>Option 1</label>
            <Input
              value={this.state.options[0]}
              onChange={(event) =>
                this.setState({ options: [event.target.value, this.state.options[1]] })
              }
            />
            <label>Option 2</label>
            <Input
              value={this.state.options[1]}
              onChange={(event) =>
                this.setState({ options: [this.state.options[0], event.target.value] })
              }
            />

          </Form.Field>
          <Message error header="Oops!" content={this.state.errorMessage} />
          
          <Button loading={this.state.loading} primary>
            Create!
          </Button>
        </Form>
      </Layout>
    );
  }
}

export default VotingCreate;
