import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";

export default function Header({onClick, value, to}) {
    return(
        <>
            <StyledNav>
                <h1>
                    {"Welcome" + " "}
                    <SecondaryButton onClick={() => to()} value={value}/>
                </h1>
                <SecondaryButton onClick={() => onClick()} value={value}/>
            </StyledNav>
        </>
    )
}

const StyledNav = styled.nav`
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: ${colors.black};
  color: ${colors.white};
  padding: 3vw 1vh;
  border: 0.1px solid ${colors.white};
  border: 0.1px none;
  border-bottom-style: solid;
  max-height: 100px;
`