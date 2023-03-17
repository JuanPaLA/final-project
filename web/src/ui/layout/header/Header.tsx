import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";
import Link from "next/link";

export const Header = () => {
    return (
        <StyledNav>
            <StyledAnchor href={'/home'}>
                <p>Home</p>
            </StyledAnchor>
            <div>
                <StyledAnchor href={"/"}>
                    <p>Signup</p>
                </StyledAnchor>
                <StyledAnchor href={"/login"}>
                    <p>login</p>
                </StyledAnchor>
            </div>
        </StyledNav>
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
  border-bottom-style: solid;
  max-height: 100px;
  width: 100vw;
  
  div {
    display: flex;
    justify-content: space-between;
    
    a {
        margin-left: 10px;
    }
  }
`

const StyledAnchor = styled(Link)`
    margin-top: 10px;
    text-decoration: none;
    color: dodgerblue;
`