import PrimaryButton from "@/ui/components/buttons/PrimaryButton";
import {colors, Container} from "@/ui/layout/styles/Globals";
import React from "react";
import styled from "styled-components";

export const NewTweet = ({presenter}) => {
    return (
        <>
            <StyledInput
                type={"text"}
                value={presenter.model.content}
                onChange={(e) => presenter.setContent(e.target.value)}
                placeholder={"What's on your mind?"}
            >
            </StyledInput>
            <PrimaryButton
                onClick={() => presenter.doPost()}
                value="Tweet!"
                disabled={presenter.isPostDisabled()}
            />
        </>
    )
}

const StyledInput = styled.input`
  width: 100%;
  height: 120px;
  border: 0.1px solid ${colors.gray};
  border-radius: 5px;
  border-top: none;
  padding: 10px;
  font-size: 1.2rem;
  margin-bottom: 10px;
  background-color: ${colors.black};
  color: ${colors.white};
`