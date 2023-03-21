import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";
import React from "react";
import Link from "next/link";
import {AccountButton} from "@/ui/components/buttons/AccountButton";

export const Tweet = ({post}) => {
    return (
        <StyledTweet>
            <Link href={`/users/${post.content}`}>
                <AccountButton
                    value={post.content}
                />
            </Link>
            <div>
                <h4>{post.author}</h4>
            </div>
            <div>
                <h4>{post.date}</h4>
            </div>
        </StyledTweet>
    )
}

const StyledTweet = styled.div`
  margin: 10px;
  color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid ${colors.gray};
  padding: 10px;
  width: 50vw;
  
  div {
    margin: 5px;
  }
`;