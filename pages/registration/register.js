import React, { Component } from "react";
import { Form, Button, Input, Message } from "semantic-ui-react";
import Layout from "../../components/Layout";
import { Router } from "../../routes";

class Register extends Component {
  state = {
    email: "",
    token: "",
    blockchainAddress: "",
    errorMessage: "",
    loading: false,
  };

  onSubmit = async (event) => {
    event.preventDefault();
    this.setState({ loading: true, errorMessage: "" });

    try {
      // send PUT request to API at http://localhost:8080/verify-user
        const response = await fetch("http://localhost:8080/register-user", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email: this.state.email,
                token: this.state.token,
                blockchainAddress: this.state.blockchainAddress,
            }),
        });
        const data = await response;
        console.log(data);

      Router.pushRoute("/");
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false });
  }


  render() {
    return (
      <Layout>
        <h3>Register</h3>
        <Form onSubmit={this.onSubmit} error={!!this.state.errorMessage}>
          <Form.Field>
            <label>Email</label>
            <Input
              value={this.state.email}
              onChange={(event) =>
                this.setState({ email: event.target.value })
              }
            />
          </Form.Field>
          <Form.Field>
            <label>Token</label>
            <Input
              value={this.state.token}
              onChange={(event) =>
                this.setState({ token: event.target.value })
              }
            />
          </Form.Field>
          <Form.Field>
            <label>Blockchain Address</label>
            <Input
              value={this.state.blockchainAddress}
              onChange={(event) =>
                this.setState({ blockchainAddress: event.target.value })
              }
            />
          </Form.Field>
          <Message error header="Oops!" content={this.state.errorMessage} />
          
          <Button loading={this.state.loading} primary>
            Verify!
          </Button>
        </Form>
      </Layout>
    );
  }
}

export default Register;
