import styled from "styled-components";

export default function AnchorButton({to, value}){
    return(
        <StyledAnchor
            href={to}
        >
            {value}
        </StyledAnchor>
    )
}

const StyledAnchor = styled.a`   
    text-decoration: none;
    &:hover {
        cursor: pointer;        
        text-decoration: underline;
    }
`