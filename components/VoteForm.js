import React, { Component } from "react";
import { Form, Input, Message, Button, Dropdown } from "semantic-ui-react";
import Voting from "../ethereum/voting";
import web3 from "../ethereum/web3";
import { Router } from "../routes";

class VoteForm extends Component {
  state = {
    value: "",
    errorMessage: "",
    loading: false,
  };

  onSubmit = async (event) => {
    event.preventDefault();

    const voting = Voting(this.props.address);

    this.setState({ loading: true, errorMessage: "" });

    try {
      const accounts = await web3.eth.getAccounts();
      await voting.methods.vote(this.state.value).send({
        from: accounts[0]
      });
      Router.replaceRoute(`/votings/${this.props.address}`);
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false, value: "" });
  };

  render() {
    const voteOptions = this.props.options.map((option) => ({
      key: option,
      text: option,
      value: option,
    }))

    return (
      <Form onSubmit={this.onSubmit} error={!!this.state.errorMessage}>
        <Form.Field>
          <h3>Vote</h3>
          <label>Option to vote for</label>
          <Dropdown
            placeholder='Option'
            fluid
            search
            selection
            options={voteOptions}
            onChange={(event) => this.setState({ value: event.target.textContent })}
          />
        </Form.Field>
        <Message error header="Oops!" content={this.state.errorMessage} />
        <Button primary loading={this.state.loading}>
          Vote!
        </Button>
      </Form>
    );
  }
}

export default VoteForm;
