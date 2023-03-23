import {AccountButton} from "@/ui/components/buttons/AccountButton";
import React from "react";
import styled from "styled-components";

export const UserList = ({users, title}) => {
    return (
        <StyledList>
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
        </StyledList>
    )
}

const StyledList = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: flex-start;
  margin: 2vh 0 2vh 4vh;
  
`

const List = styled.ul`
  margin: 20px auto;
  width: 100%;
`;