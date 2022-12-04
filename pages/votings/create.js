import React, { Component } from "react";
import { Form, Button, Input, Message } from "semantic-ui-react";
import Layout from "../../components/Layout";
import factory from "../../ethereum/votingFactory";
import web3 from "../../ethereum/web3";
import { Router } from "../../routes";

class VotingCreate extends Component {
  state = {
    topic: "",
    /*
      Audience:
        0 - Students
        1 - Employees
        2 - All
     */
    audience: 0,
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
        .createVoting(this.state.topic, this.state.options, this.state.audience)
        .send({
          from: accounts[0],
        });

      Router.pushRoute("/");
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false });
  };

  createOptionField = (formDiv) => {
    const optionCount = formDiv.querySelectorAll('input').length;
    if(optionCount > 10){
      window.alert('You cannot create more than 10 options per voting.');
      return;
    }

    // create new option field
    const optionLabel = document.createElement('label');
    optionLabel.textContent = `Option ${optionCount}`;
    const inputDiv = document.createElement('div');
    inputDiv.classList.add('ui', 'input');
    const inputField = document.createElement('input');
    inputField.type = 'text';

    // add slot for the option in state
    const optionsCopy = this.state.options.slice();
    optionsCopy.push("")
    this.setState({options: optionsCopy});
    // update options array on change
    inputField.addEventListener('change', (event) => {
      // create copy of options
      const optionsCopy = this.state.options.slice();
      optionsCopy[optionCount-1] = event.target.value;
      this.setState({options: optionsCopy});
    })

    inputDiv.appendChild(inputField);
    // add label and option to the form
    formDiv.appendChild(optionLabel);
    formDiv.appendChild(inputDiv);
  }

  removeOptionField = (formDiv) => {
    const labels = formDiv.querySelectorAll('label');
    const options = formDiv.querySelectorAll('.ui .input');
    const  inputOptionsInputs = Array.from(options);

    // remove topic input field from list
    inputOptionsInputs.shift();
    if(inputOptionsInputs.length  <= 2){
      window.alert("At least two options must be provided");
      return;
    }

    // remove option from state
    this.setState({ options: this.state.options.slice(0, -1)});
    // remove last option label and input field
    labels[labels.length-1].remove();
    options[options.length-1].remove();
  }

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
            <label>Audience</label>
            <select onChange={(event) => {
              this.setState({audience: event.target.value});
            }}>
              <option value="0">Students</option>
              <option value="1">Employees</option>
              <option value="2">All</option>
          </select>
            {/*// define options*/}
            <label>Option 1</label>
            <Input
              value={this.state.options[0]}
              onChange={(event) => {
                const optionsCopy = this.state.options.slice();
                optionsCopy[0] = event.target.value;
                this.setState({options: optionsCopy});
                }
              }
            />
            <label>Option 2</label>
            <Input
              value={this.state.options[1]}
              onChange={(event) => {
                let optionsCopy = this.state.options.slice();
                optionsCopy[1] = event.target.value;
                this.setState({options: optionsCopy});
              }
            }
            />
          </Form.Field>
          <Message error header="Oops!" content={this.state.errorMessage} />
          
          <Button loading={this.state.loading} primary>
            Create!
          </Button>
        </Form>
        <Button onClick={() => {
          let fieldsDiv = document.querySelector('.field');
          this.createOptionField(fieldsDiv);
        }}
          variant="outlined"
          icon="add"
          content="Add option"
          secondary
        />
        <Button onClick={() => {
          let fieldsDiv = document.querySelector('.field');
          this.removeOptionField(fieldsDiv);
        }}
          variant="outlined"
          icon="minus"
          content="Remove option"
          secondary
        />

      </Layout>
    );
  }
}

export default VotingCreate;
