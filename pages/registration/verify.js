import React, { Component } from "react";
import { Form, Button, Input, Message } from "semantic-ui-react";
import Layout from "../../components/Layout";
import { Router } from "../../routes";

class Verify extends Component {
  state = {
    email: "",
    errorMessage: "",
    loading: false,
  };

  onSubmit = async (event) => {
    event.preventDefault();
    this.setState({ loading: true, errorMessage: "" });

    try {
      // send PUT request to API at http://localhost:8080/register-user
        const response = await fetch("http://localhost:8080/verify-user", {
            method: "PUT",
            headers: {
                "Content-Type": "text/plain",
            },
            body: this.state.email
        });
        const data = await response;
        console.log(data);

      Router.pushRoute("/registration/register");
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false });
  }


  render() {
    return (
      <Layout>
        <h3>Verify email address</h3>
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
          <Message error header="Oops!" content={this.state.errorMessage} />
          
          <Button loading={this.state.loading} primary>
            Verify!
          </Button>
        </Form>
      </Layout>
    );
  }
}

export default Verify;
