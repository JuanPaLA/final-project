import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";

export default function PrimaryButton({onClick, value, disabled}){
    return(
        <StyledButton
            type={"submit"}
            onClick={()=> onClick()}
            disabled={disabled}
        >
            {value}
        </StyledButton>
    )
}

const StyledButton = styled.button`
  background-color: ${colors.primary};
  opacity: ${props => props.disabled == true ? 0.5 : 1};
  border-radius: 15%;
  padding: 10px;
  color: ${colors.white};
  font-size: medium;
  &:hover {
    cursor: ${props => props.disabled == true ? "inherit" : "pointer"};
  }
`