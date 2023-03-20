import {createGlobalStyle} from "styled-components";
import styled from "styled-components";

export const colors = {
    primary: "#0070f3",
    secondary: "#ff0080",
    black: "#000000",
    white: "whitesmoke",
    gray: "#ccc",
    lightGray: "#eee"
};

const size = {
    small: '425px',
    medium: '768px',
    large: '1024px',
}

export const device = {
    small: `(max-width: ${size.small})`,
    medium: `(max-width: ${size.medium})`,
    mediumlarge: `(min-width: ${size.medium}) and (max-width: ${size.large})`,
    large: `(min-width: ${size.large})`,
    extralarge: `(min-width: 1200px)`,
};

export const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  @media ${device.extralarge} {
    width: 9000px;
  }
  @media ${device.large} {
    width: 700px;
  }
  @media ${device.mediumlarge} {
    width: 500px;
  }
`

export const FormControl = styled.div`
  margin-bottom: 10px;
`
export const StyledAnchor = styled.a`
  margin-top: 10px;
  text-decoration: none;
  color: dodgerblue;
`

export const StyledContainer = styled.div`
  display: grid;
  background-color: ${colors.black};
  grid-template-columns: 1fr 3fr 1fr;
  width: 100vw;
  height: 100vh;
  align-items: flex-start;
`

export const StyledMain = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
  height: 100vh;
  color: ${colors.white};
  border: ${colors.lightGray} solid 1px;
`

export const StyledNav = styled.nav`
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: flex-start;
  background-color: ${colors.black};
  color: ${colors.white};
  padding: 3vw 1vh;
  min-height: 100vh;
  height: 100vh !important;
`