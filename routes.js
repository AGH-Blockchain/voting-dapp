const routes = require("next-routes")();

routes
  .add("/votings/create", "/votings/create")
  .add("/votings/:address", "/votings/details")

module.exports = routes;
