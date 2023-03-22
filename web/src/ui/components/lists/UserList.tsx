import {AccountButton} from "@/ui/components/buttons/AccountButton";
import React from "react";
import styled from "styled-components";

export const UserList = ({users, title}) => {
    return (
        <>
        <h4>{title}</h4>
        <List>
            {
                users.map((user, i) => {
                    return (
                        <div key={i}>
                            <AccountButton
                                value={user.name ? user.name : user}
                            />
                        </div>
                    )
                })
            }
        </List>
        </>
    )
}

const List = styled.ul`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  margin: 20px auto;
  width: 100%;
`;