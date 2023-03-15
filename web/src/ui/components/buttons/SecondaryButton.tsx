import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";

export default function SecondaryButton({onClick, value}){
    return(
        <StyledButton onClick={()=> onClick() }>{value}</StyledButton>
    )
}

const StyledButton = styled.span`
  background-color: ${colors.black};
  padding: 10px;
  color: ${colors.lightGray};
  border-radius: 25%;
  &:hover {
    cursor: pointer;
    background-color: #2F3336;
  }
`