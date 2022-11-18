import React from "react";
import { Menu } from "semantic-ui-react";
import { Link } from "../routes";

const Header = () => {
  return (
    <Menu style={{ marginTop: "10px" }}>
      <Link route="/">
        <a className="item">Voting Dapp</a>
      </Link>
      <Menu.Menu position="right">
        <Link route="/">
          <a className="item">Votings</a>
        </Link>
      </Menu.Menu>
    </Menu>
  );
};

export default Header;
